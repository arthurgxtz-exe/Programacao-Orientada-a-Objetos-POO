#!/bin/sh
# Instalador para Geany 2.x no Linux. Execute: sh configurar_geany.sh
# O instalador usa sh/awk; o gerador de getters/setters usa Python 3.
set -eu

fail() { printf 'Erro: %s\n' "$*" >&2; exit 1; }
if [ "${1:-}" = '--help' ]; then
    printf 'Uso: sh configurar_geany.sh\nFeche o Geany e execute sem sudo. Autosave: 30 segundos.\n'
    exit 0
fi
[ "$#" -eq 0 ] || fail 'Use: sh configurar_geany.sh'
[ "$(id -u)" -ne 0 ] || fail 'Execute com seu usuário, sem sudo.'
for tool in geany python3 awk pgrep mktemp javac java; do
    command -v "$tool" >/dev/null 2>&1 || fail "Programa necessário não encontrado: $tool"
done
if pgrep -u "$(id -u)" -x geany >/dev/null 2>&1; then
    fail 'Salve seus arquivos e feche o Geany antes de instalar.'
fi
config_root=${XDG_CONFIG_HOME:-"$HOME/.config"}/geany
data_root=${XDG_DATA_HOME:-"$HOME/.local/share"}
# As listas GLib usam ; e a barra invertida como separadores/escapes.
case "$config_root$data_root" in
    *';'*|*'\'*|*'"'*|*'
'*) fail 'Caminho de configuração com caracteres não suportados.' ;;
esac
plugin=
for candidate in /usr/lib/geany/saveactions.so /usr/lib/*/geany/saveactions.so /usr/lib64/geany/saveactions.so /usr/local/lib/geany/saveactions.so; do
    if [ -f "$candidate" ]; then plugin=$candidate; break; fi
done
[ -n "$plugin" ] || fail 'Plugin Save Actions não encontrado. No Ubuntu/Debian, instale o pacote geany.'

stage=$(mktemp -d)
trap 'rm -rf "$stage"' EXIT HUP INT TERM

# Lê ou altera uma chave, mantendo as outras opções e comentários.
get_key() {
    section_name=$2 setting_key=$3 awk '
        /^\[/ { section=$0; sub(/^\[/,"",section); sub(/\].*$/,"",section) }
        section==ENVIRON["section_name"] {
            pos=index($0,"=")
            if(pos) { key=substr($0,1,pos-1); gsub(/^[ \t]+|[ \t]+$/,"",key)
                if(key==ENVIRON["setting_key"]) { print substr($0,pos+1); exit }
            }
        }
    ' "$1"
}
set_key() {
    section_name=$2 setting_key=$3 setting_value=$4 awk '
        function add() { print ENVIRON["setting_key"] "=" ENVIRON["setting_value"]; done=1 }
        /^\[/ {
            if(active && !done) add()
            section=$0; sub(/^\[/,"",section); sub(/\].*$/,"",section)
            active=(section==ENVIRON["section_name"])
            if(active) found=1
        }
        {
            pos=index($0,"="); key=substr($0,1,pos-1); gsub(/^[ \t]+|[ \t]+$/,"",key)
            if(active && pos && key==ENVIRON["setting_key"]) { if(!done) add(); next }
            print
        }
        END {
            if(!found) { print ""; print "[" ENVIRON["section_name"] "]" }
            if(!done) add()
        }
    ' "$1" > "$stage/updated"
    mv "$stage/updated" "$1"
}
for name in geany.conf session.conf keybindings.conf; do
    if [ -f "$config_root/$name" ]; then cp "$config_root/$name" "$stage/$name"; else : > "$stage/$name"; fi
done
if [ -f "$config_root/plugins/saveactions/saveactions.conf" ]; then
    cp "$config_root/plugins/saveactions/saveactions.conf" "$stage/saveactions.conf"
else
    : > "$stage/saveactions.conf"
fi
commands=$(get_key "$stage/geany.conf" geany custom_commands)
labels=$(get_key "$stage/geany.conf" geany custom_commands_labels)
# Preserva comandos anteriores e reutiliza o slot do gerador quando já existe.
slot=$(command_list=$commands awk 'BEGIN {
    n=split(ENVIRON["command_list"],a,";"); count=0
    for(i=1;i<=n;i++) {
        if(a[i]!="") count++
        if(index(a[i],"java_getters_setters.py")) { print i; exit }
    }
    print count+1
}')
[ "$slot" -le 9 ] || fail 'Os nove comandos personalizados estão ocupados; nenhum foi alterado.'
# O comando usa aspas duplas para aceitar espaços no caminho.
# Escapa $ e crase para que sejam literais na linha de comando do Geany.
script_path=$config_root/scripts/java_getters_setters.py
quoted_path=$(printf '%s' "$script_path" | sed 's/[$`]/\\&/g')
command_path=$(command -v python3)
new_command="\"$command_path\" \"$quoted_path\""
commands=$(command_list=$commands slot_number=$slot new_command=$new_command awk 'BEGIN {
    n=split(ENVIRON["command_list"],a,";"); slot=ENVIRON["slot_number"]+0
    if(n<slot) n=slot
    for(i=1;i<=n;i++) {
        if(i==slot) printf "%s;", ENVIRON["new_command"]
        else if(a[i]!="") printf "%s;",a[i]
    }
}')
labels=$(label_list=$labels slot_number=$slot awk 'BEGIN {
    n=split(ENVIRON["label_list"],a,";"); slot=ENVIRON["slot_number"]+0
    if(n<slot) n=slot
    for(i=1;i<=n;i++) {
        if(i==n && i>slot && a[i]=="") break
        printf "%s;",(i==slot ? "Gerar getters e setters" : a[i])
    }
}')
set_key "$stage/geany.conf" geany custom_commands "$commands"
set_key "$stage/geany.conf" geany custom_commands_labels "$labels"
set_key "$stage/geany.conf" geany color_scheme java-escuro.conf
set_key "$stage/geany.conf" geany auto_complete_symbols true
set_key "$stage/geany.conf" geany complete_snippets true
binding=edit_sendtocmd$slot
shortcut=$(get_key "$stage/keybindings.conf" Bindings "$binding")
[ -n "$shortcut" ] || shortcut="<Primary>$slot"
set_key "$stage/keybindings.conf" Bindings "$binding" "$shortcut"
plugins=$(get_key "$stage/session.conf" plugins active_plugins)
case ";$plugins" in
    *";$plugin;"*) ;;
    *) plugins=${plugins%;}";$plugin;" ;;
esac
set_key "$stage/session.conf" plugins load_plugins true
set_key "$stage/session.conf" plugins active_plugins "$plugins"
set_key "$stage/saveactions.conf" saveactions enable_autosave true
set_key "$stage/saveactions.conf" autosave interval 30
set_key "$stage/saveactions.conf" autosave save_all true
set_key "$stage/saveactions.conf" autosave print_messages false

cat > "$stage/main.java" <<'JAVA_TEMPLATE'
public class {untitled} {

    // Atributos

    // Construtores

    // Getters

    // Setters

    // Métodos

    // Método principal
    public static void main(String[] args) {

    }
}
JAVA_TEMPLATE
cat > "$stage/java-escuro.conf" <<'THEME'
[theme_info]
name=Java Escuro
description=Tema escuro simples
version=1.0

[named_styles]
default=#d4d4d4;#1e1e1e;false;false
selection=#ffffff;#264f78;false;true
current_line=#d4d4d4;#282828;true
margin_line_number=#858585;#1e1e1e
margin_folding=#858585;#252526
fold_symbol_highlight=#ffffff
caret=#ffffff;#ffffff
indent_guide=#404040
white_space=#404040;#1e1e1e
brace_good=#ffffff;#375737;true;false
brace_bad=#ffffff;#803030;true;false
call_tips=#d4d4d4;#252526
comment=#6a9955
comment_doc=comment
comment_line=comment
comment_line_doc=comment
number=#b5cea8
string=#ce9178
character=string
word=#569cd6;;true;false
word2=#4ec9b0
type=#4ec9b0
operator=#d4d4d4
preprocessor=#c586c0
identifier=#d4d4d4
error=#ffffff;#803030
THEME
cat > "$stage/geany-java-escuro.desktop" <<'DESKTOP'
[Desktop Entry]
Type=Application
Name=Geany Java (escuro)
Comment=Geany com interface escura
Exec=env GTK_THEME=Adwaita:dark geany %F
Icon=geany
Terminal=false
Categories=Development;IDE;
DESKTOP

cat > "$stage/java_getters_setters.py" <<'PYTHON_GENERATOR'
#!/usr/bin/env python3
"""Filtro para o modelo Java pessoal do Geany. Entrada/saída: UTF-8."""
import re
import sys


def mask(source):
    pattern = r'"""[\s\S]*?"""|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'|//[^\n]*|/\*[\s\S]*?\*/'
    return re.sub(pattern, lambda m: ''.join('\n' if c == '\n' else ' ' for c in m[0]), source)


def generate(source):
    markers = {}
    for label in ['Atributos', 'Construtores', 'Getters', 'Setters', 'Métodos']:
        matches = list(re.finditer(r'(?m)^([ \t]*)// ' + label + r'[ \t]*\r?$', source))
        if len(matches) != 1:
            return source
        markers[label] = matches[0]
    if sorted(markers, key=lambda k: markers[k].start()) != list(markers):
        return source
    clean = mask(source)
    # Aceita uma única classe de nível superior; não transforma classes aninhadas.
    if len(re.findall(r'\b(?:class|interface|enum|record)\s+\w+', clean)) != 1:
        return source
    region = clean[markers['Atributos'].end():markers['Construtores'].start()]
    field = re.compile(
        r'\s*(?P<mods>(?:(?:private|public|protected|static|final|transient|volatile)\s+)*)'
        r'(?P<type>[\w.$]+(?:\s*<[^;={}]+>)?(?:\s*\[\s*\])*)\s+'
        r'(?P<name>[A-Za-z_$][\w$]*)\s*(?:=\s*[^;]*)?;\s*', re.UNICODE)
    fields = []
    for statement in region.split(';')[:-1]:
        if not statement.strip():
            continue
        m = field.fullmatch(statement + ';')
        if not m or '{' in statement or '}' in statement:
            return source
        if 'static' not in m['mods'].split():
            fields.append((m['type'].strip(), m['name'], 'final' in m['mods'].split()))
    if region.rsplit(';', 1)[-1].strip():
        return source
    # Consulta declarações de métodos, não chamadas dentro dos seus corpos.
    declarations = re.findall(r'\b([A-Za-z_$][\w$]*)\s*\(([^()]*)\)\s*(?:throws\s+[\w.,\s]+)?\{', clean)
    existing = {(name, 0 if not args.strip() else len(args.split(','))) for name, args in declarations}
    indent = markers['Getters'][1]
    unit = '\t' if '\t' in indent else '    '
    nl = '\r\n' if '\r\n' in source else '\n'
    getters, setters = [], []
    for typ, name, final in fields:
        suffix = name[0].upper() + name[1:]
        getter = ('is' if typ == 'boolean' else 'get') + suffix
        if (getter, 0) not in existing and ('get' + suffix, 0) not in existing:
            getters.append(f'{indent}public {typ} {getter}() {{{nl}{indent}{unit}return {name};{nl}{indent}}}')
        setter = 'set' + suffix
        if not final and (setter, 1) not in existing:
            setters.append(f'{indent}public void {setter}({typ} {name}) {{{nl}{indent}{unit}this.{name} = {name};{nl}{indent}}}')
    for label, methods in [('Setters', setters), ('Getters', getters)]:
        if methods:
            pos = markers[label].end()
            source = source[:pos] + nl + nl + (nl + nl).join(methods) + source[pos:]
    return source


def main():
    source = sys.stdin.buffer.read().decode('utf-8')
    try:
        result = generate(source)
    except Exception as error:
        print(f'Gerador Java: {error}', file=sys.stderr)
        result = source
    sys.stdout.buffer.write(result.encode('utf-8'))


if __name__ == '__main__':
    main()

PYTHON_GENERATOR


# Integração com o menu Construir do Geany (somente arquivos Java).
# Comandos por tipo de arquivo não interferem nas linguagens C/C++.
if [ -f "$config_root/filedefs/filetypes.java" ]; then
    cp "$config_root/filedefs/filetypes.java" "$stage/filetypes.java"
else
    : > "$stage/filetypes.java"
fi
runner=$config_root/scripts/geany_java_prova.py
runner_quoted=$(printf '%s' "$runner" | sed 's/[$`]/\\&/g')
runner_command="\"$command_path\" \"$runner_quoted\""
set_key "$stage/filetypes.java" build-menu FT_00_LB '_Compilar projeto Java'
set_key "$stage/filetypes.java" build-menu FT_00_CM "$runner_command compile \"%d/%f\""
set_key "$stage/filetypes.java" build-menu FT_00_WD ''
set_key "$stage/filetypes.java" build-menu EX_00_LB '_Executar projeto Java'
set_key "$stage/filetypes.java" build-menu EX_00_CM "$runner_command run \"%d/%f\""
set_key "$stage/filetypes.java" build-menu EX_00_WD ''

cat > "$stage/geany_java_prova.py" <<'JAVA_RUNNER'
#!/usr/bin/env python3
"""Compila/executa Java simples com varias classes (sem Maven/Gradle)."""
import argparse
from pathlib import Path
import re
import subprocess
import sys

PACKAGE = re.compile(r'(?m)^\s*package\s+([\w.]+)\s*;')
MAIN = re.compile(r'\bstatic\s+void\s+main\s*\(')


def source_info(path):
    # Identificacao simples; comentarios com 'main' podem gerar falsos positivos.
    text = path.read_text(encoding='utf-8-sig')
    pkg = PACKAGE.search(text)
    return (pkg.group(1) if pkg else '', bool(MAIN.search(text)))


def project_root(file):
    package, _ = source_info(file)
    root = file.parent
    if package:
        parts = package.split('.')
        for segment in reversed(parts):
            if root.name != segment:
                raise ValueError('Pasta e declaracao package nao combinam: ' + package)
            root = root.parent
    return root


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('action', choices=['compile', 'run'])
    parser.add_argument('file', type=Path)
    args = parser.parse_args()
    file = args.file.resolve()
    if not file.is_file() or file.suffix.lower() != '.java':
        parser.error('Salve primeiro um arquivo .java existente.')
    try:
        root = project_root(file)
        build = root / '.geany-java-build'
        sources = sorted(p for p in root.rglob('*.java')
                         if '.geany-java-build' not in p.parts
                         and '.git' not in p.parts
                         and 'target' not in p.parts
                         and 'build' not in p.parts)
        if len(sources) > 200:
            raise ValueError('Muitos arquivos Java; execute em uma pasta menor.')
        build.mkdir(exist_ok=True)
        cmd = ['javac', '-encoding', 'UTF-8', '-d', str(build)] + [str(p) for p in sources]
        print('Compilando', len(sources), 'arquivo(s) Java...', flush=True)
        status = subprocess.run(cmd, cwd=root).returncode
        if status:
            return status
        print('Compilacao concluida.', flush=True)
        if args.action == 'compile':
            return 0
        candidates = []
        for src in sources:
            pkg, has_main = source_info(src)
            if has_main:
                candidates.append((src, (pkg + '.' if pkg else '') + src.stem))
        current = [qualified for src, qualified in candidates if src == file]
        if current:
            chosen = current[0]
        elif len(candidates) == 1:
            chosen = candidates[0][1]
        else:
            preferred = [qualified for src, qualified in candidates if src.stem in ('Principal', 'Main')]
            if len(preferred) == 1:
                chosen = preferred[0]
            else:
                raise ValueError('Nao foi possivel escolher a classe principal. Abra o arquivo que contem main e pressione F5.')
        print('Executando:', chosen, '\n', flush=True)
        return subprocess.run(['java', '-cp', str(build), chosen], cwd=root).returncode
    except (OSError, UnicodeError, ValueError) as exc:
        print('Erro:', exc, file=sys.stderr)
        return 1


if __name__ == '__main__':
    sys.exit(main())
JAVA_RUNNER

cat > "$stage/Principal.java" <<'PRINCIPAL_TEMPLATE'
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n1 - Consultar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Opcao escolhida");
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (opcao != 0);
    }
}
PRINCIPAL_TEMPLATE

cat > "$stage/Classe.java" <<'CLASS_TEMPLATE'
public class {untitled} {
    // Atributos

    // Construtores

    // Getters

    // Setters

    // Métodos
}
CLASS_TEMPLATE

# Guarda cópias somente dos arquivos modificados, sem copiar sockets do Geany.
mkdir -p "$config_root/backups"
backup=$(mktemp -d "$config_root/backups/configuracao-XXXXXXXX")
install_file() {
    source_file=$1
    destination=$2
    relative_name=$3
    if [ -f "$destination" ]; then
        mkdir -p "$backup/$(dirname "$relative_name")"
        cp -p "$destination" "$backup/$relative_name"
        printf 'Backup: %s -> %s\n' "$destination" "$relative_name" >> "$backup/LEIA-ME.txt"
    else
        printf 'Arquivo novo: %s\n' "$destination" >> "$backup/LEIA-ME.txt"
    fi
    mkdir -p "$(dirname "$destination")"
    cp "$source_file" "$destination"
}
install_file "$stage/filetypes.java" "$config_root/filedefs/filetypes.java" filedefs/filetypes.java
install_file "$stage/geany_java_prova.py" "$runner" scripts/geany_java_prova.py
install_file "$stage/Principal.java" "$config_root/templates/files/Principal.java" templates/files/Principal.java
install_file "$stage/Classe.java" "$config_root/templates/files/Classe.java" templates/files/Classe.java
install_file "$stage/geany.conf" "$config_root/geany.conf" geany.conf
install_file "$stage/session.conf" "$config_root/session.conf" session.conf
install_file "$stage/keybindings.conf" "$config_root/keybindings.conf" keybindings.conf
install_file "$stage/main.java" "$config_root/templates/files/main.java" templates/files/main.java
install_file "$stage/java-escuro.conf" "$config_root/colorschemes/java-escuro.conf" colorschemes/java-escuro.conf
install_file "$stage/saveactions.conf" "$config_root/plugins/saveactions/saveactions.conf" plugins/saveactions/saveactions.conf
install_file "$stage/java_getters_setters.py" "$script_path" scripts/java_getters_setters.py
install_file "$stage/geany-java-escuro.desktop" "$data_root/applications/geany-java-escuro.desktop" applications/geany-java-escuro.desktop
printf '\nPronto! Configuração instalada.\n'
printf 'Abra Geany Java (escuro) no menu de aplicativos.\n'
printf 'Getters/setters: Ctrl+A e depois %s.\n' "$shortcut"
printf 'Autosave: a cada 30 segundos, após o primeiro salvamento.\n'
printf 'Backup: %s\n' "$backup"
printf 'Java: F8 compila e F5 compila/executa (atalhos padrao do Geany).\n'
printf 'Dica: salve cada classe no seu proprio .java e abra a classe Principal antes do F5.\n'
