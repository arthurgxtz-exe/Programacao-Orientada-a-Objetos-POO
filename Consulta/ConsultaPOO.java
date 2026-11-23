import java.util.Scanner;

/*
 * ============================================================================
 *  CONSULTA RÁPIDA — POO EM JAVA
 *  Modelos, cálculos, validações, menu e macetes em um único arquivo.
 * ============================================================================
 *
 *  COMPILAR:        javac -encoding UTF-8 ConsultaPOO.java
 *  COMPILAR VÁRIOS: javac Classe.java Principal.java
 *  EXECUTAR:        java ConsultaPOO
 *  VERIFICAR JAVA:  java -version
 *  VERIFICAR JDK:   javac -version
 *
 *  ÍNDICE — use Ctrl + F
 *    [CLASSE] [CONSTRUTOR] [SOBRECARGA] [VALIDAÇÃO] [STRING]
 *    [PERCENTUAL] [LOCAÇÃO] [HOSPEDAGEM] [RENDA] [IMC]
 *    [TESTES] [SCANNER] [SELEÇÃO] [MENU] [ERROS] [CHECKLIST]
 *
 *  UML -> JAVA
 *    - nome: String                 private String nome;
 *    + calcular(qtd: int): Double  public Double calcular(int qtd) { ... }
 *    + Classe(...):                public Classe(...) { ... }
 *
 *  SINAIS UML:  - private   + public   # protected
 * ============================================================================
 */
public class ConsultaPOO {

    // ------------------------------------------------------------------------
    // 1. MODELO DE CLASSE [CLASSE] [CONSTRUTOR] [SOBRECARGA]
    // ------------------------------------------------------------------------
    static class Item {
        private int codigo;
        private String nome;
        private String tipo;
        private Double preco;
        private int quantidade;

        // Construtor curto: aplica um valor padrão.
        public Item(int codigo, String nome, String tipo, Double preco) {
            this(codigo, nome, tipo, preco, 0);
        }

        // Construtor completo.
        public Item(int codigo, String nome, String tipo, Double preco,
                    int quantidade) {
            this.codigo = codigo;
            this.nome = nome;
            this.tipo = tipo;
            this.preco = preco;
            this.quantidade = quantidade;
        }

        // Sobrecarga: mesmo nome, parâmetros diferentes.
        public Double calcularTotal() {
            return preco * quantidade;
        }

        public Double calcularTotal(Double impostoPercentual) {
            Double total = calcularTotal();
            return total + total * impostoPercentual / 100;
        }

        public Double calcularTotal(Double desconto, Double acrescimo) {
            return calcularTotal() - desconto + acrescimo;
        }

        // [VALIDAÇÃO] Valide antes de alterar o objeto.
        public boolean adicionar(int quantidade) {
            if (quantidade <= 0) {
                return false;
            }
            this.quantidade += quantidade;
            return true;
        }

        public boolean retirar(int quantidade) {
            if (quantidade <= 0 || quantidade > this.quantidade) {
                return false;
            }
            this.quantidade -= quantidade;
            return true;
        }

        // [STRING] Compare conteúdo com equals ou equalsIgnoreCase.
        public Double valorPorTipo() {
            if ("ECONOMICO".equalsIgnoreCase(tipo)) {
                return 100.0;
            }
            if ("SUV".equalsIgnoreCase(tipo)) {
                return 150.0;
            }
            if ("LUXO".equalsIgnoreCase(tipo)) {
                return 250.0;
            }
            return 0.0;
        }

        public String exibir() {
            return codigo + " - " + nome;
        }

        public String exibir(boolean detalhado) {
            if (detalhado) {
                return exibir()
                    + " | Tipo: " + tipo
                    + " | Preço: R$ " + preco
                    + " | Quantidade: " + quantidade;
            }
            return exibir();
        }

        // Getter consulta; setter altera com controle.
        public int getQuantidade() {
            return quantidade;
        }

        public void setPreco(Double preco) {
            if (preco >= 0) {
                this.preco = preco;
            }
        }
    }

    // ------------------------------------------------------------------------
    // 2. CÁLCULOS ÚTEIS
    // ------------------------------------------------------------------------

    // [PERCENTUAL] Acréscimo: total + total * percentual / 100.
    public static Double acrescentarPercentual(Double valor,
                                                Double percentual) {
        return valor + valor * percentual / 100;
    }

    // [PERCENTUAL] Desconto: total - total * percentual / 100.
    public static Double descontarPercentual(Double valor,
                                              Double percentual) {
        return valor - valor * percentual / 100;
    }

    // Desconto em reais: total - desconto. Não confunda com percentual.
    public static Double descontarValor(Double valor, Double desconto) {
        return valor - desconto;
    }

    // Venda ou estoque: quantidade * preço.
    public static Double calcularQuantidade(int quantidade, Double preco) {
        return quantidade * preco;
    }

    // [LOCAÇÃO] dias * diária + km * taxa + seguro por dia.
    public static Double calcularLocacao(int dias, int km, Double diaria,
                                         boolean seguro) {
        if (dias <= 0 || km < 0 || diaria < 0) {
            return 0.0;
        }
        Double total = dias * diaria + km * 0.10;
        if (seguro) {
            total += dias * 50.0;
        }
        return total;
    }

    // [HOSPEDAGEM] dias * (diária + refeição) + limpeza uma única vez.
    public static Double calcularHospedagem(int dias, boolean refeicoes,
                                             Double taxaLimpeza) {
        if (dias <= 0 || taxaLimpeza < 0) {
            return 0.0;
        }
        Double valorPorDia = 200.0;
        if (refeicoes) {
            valorPorDia += 50.0;
        }
        return dias * valorPorDia + taxaLimpeza;
    }

    // [RENDA] simples = base; integrada = base * 1.5.
    public static Double calcularRenda(int passageiros, Double tarifa,
                                       String tipoTarifa) {
        if (passageiros < 0 || tarifa < 0) {
            return 0.0;
        }
        Double renda = passageiros * tarifa;
        if ("SIMPLES".equalsIgnoreCase(tipoTarifa)) {
            return renda;
        }
        if ("INTEGRADA".equalsIgnoreCase(tipoTarifa)) {
            return renda * 1.5;
        }
        return 0.0;
    }

    public static Double media(Double valor1, Double valor2) {
        return (valor1 + valor2) / 2;
    }

    // [IMC] peso / (altura * altura).
    public static Double calcularImc(Double peso, Double altura) {
        if (peso <= 0 || altura <= 0) {
            return 0.0;
        }
        return peso / (altura * altura);
    }

    // Capacidade: só aceite se todos couberem.
    public static boolean cabeNaCapacidade(int ocupacao, int quantidade,
                                            int capacidade) {
        return quantidade > 0 && ocupacao + quantidade <= capacidade;
    }

    public static Double arredondar2Casas(Double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // 3. TESTES RÁPIDOS [TESTES]
    // ------------------------------------------------------------------------
    public static void executarTestesRapidos() {
        Item teste = new Item(99, "Teste", "SUV", 100.0, 3);

        System.out.println("\n--- TESTES: esperado | obtido ---");
        System.out.println("Total:       300.0 | " + teste.calcularTotal());
        System.out.println("Imposto 10%: 330.0 | "
            + teste.calcularTotal(10.0));
        System.out.println("Retirar 2:    true | " + teste.retirar(2));
        System.out.println("Quantidade:      1 | " + teste.getQuantidade());
        System.out.println("Retirar 9:   false | " + teste.retirar(9));
        System.out.println("Locação:     320.0 | "
            + calcularLocacao(2, 200, 100.0, true));
        System.out.println("Hospedagem:  780.0 | "
            + calcularHospedagem(3, true, 30.0));
    }

    // ------------------------------------------------------------------------
    // 4. SCANNER E SELEÇÃO DE OBJETOS [SCANNER] [SELEÇÃO]
    // ------------------------------------------------------------------------

    // Use imediatamente após nextInt() ou nextDouble() para ler uma frase.
    public static String lerLinhaDepoisDeNumero(Scanner entrada) {
        entrada.nextLine();              // consome o Enter pendente
        return entrada.nextLine();       // lê a frase completa
    }

    public static Item escolherItem(int escolha, Item item1, Item item2,
                                     Item item3) {
        if (escolha == 1) {
            return item1;
        }
        if (escolha == 2) {
            return item2;
        }
        if (escolha == 3) {
            return item3;
        }
        return null;
    }

    // ------------------------------------------------------------------------
    // 5. MENU COM REPETIÇÃO [MENU]
    // ------------------------------------------------------------------------
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Item item1 = new Item(1, "Notebook", "ECONOMICO", 2500.0, 3);
        Item item2 = new Item(2, "Monitor", "SUV", 900.0, 5);
        Item item3 = new Item(3, "Teclado", "LUXO", 150.0);
        int opcao;

        // executarTestesRapidos(); // Descomente para conferir os cálculos.

        do {
            System.out.println("\n1-Exibir  2-Adicionar  3-Retirar");
            System.out.println("4-Calcular  5-Sair");
            opcao = entrada.nextInt();

            Item item = null;
            if (opcao >= 1 && opcao <= 4) {
                System.out.print("Escolha o item (1, 2 ou 3): ");
                item = escolherItem(entrada.nextInt(), item1, item2, item3);
                if (item == null) {
                    System.out.println("Item inválido.");
                    continue;
                }
            }

            switch (opcao) {
                case 1:
                    System.out.println(item.exibir(true));
                    break;
                case 2:
                    System.out.print("Quantidade: ");
                    System.out.println(item.adicionar(entrada.nextInt()));
                    break;
                case 3:
                    System.out.print("Quantidade: ");
                    System.out.println(item.retirar(entrada.nextInt()));
                    break;
                case 4:
                    System.out.println("Total: R$ " + item.calcularTotal());
                    break;
                case 5:
                    System.out.println("Fim.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);

        entrada.close();
    }
}

/*
 * ============================================================================
 *  MACETES DE PROVA
 * ============================================================================
 *  1. Construtor tem o mesmo nome da classe e NÃO possui retorno nem void.
 *  2. this.atributo = parametro; diferencia atributo de parâmetro.
 *  3. Sobrecarga exige parâmetros diferentes; só mudar o retorno não funciona.
 *  4. String: use equals() ou equalsIgnoreCase(). Nunca use == para conteúdo.
 *  5. boolean: true no sucesso, false na falha; não altere o estado na falha.
 *  6. Valide estoque, capacidade e quantidade ANTES de somar ou subtrair.
 *  7. Percentual: valor * percentual / 100.
 *  8. Taxa única fica fora da multiplicação por dias ou quantidade.
 *  9. Cada public class deve ficar em arquivo com o mesmo nome.
 * 10. No main: Scanner -> objetos -> do/while -> menu -> métodos -> close().
 * 11. nextInt() lê int; nextDouble() lê Double; next() lê uma palavra.
 * 12. Após nextInt(), use uma chamada extra de nextLine() antes de ler frase.
 *
 *  ERROS COMUNS [ERROS]
 *    ERRADO                              CERTO
 *    public void Item(...)               public Item(...)
 *    texto == "SUV"                      texto.equals("SUV")
 *    estoque -= qtd; depois validar      validar; depois estoque -= qtd
 *    total * 10                          total * 10 / 100
 *    dias * (diária + taxa única)        dias * diária + taxa única
 *    duas versões com mesmos parâmetros  altere quantidade/tipo dos parâmetros
 *    atributo = atributo;                this.atributo = atributo;
 *
 *  FORMAS RÁPIDAS
 *    somar:       total += valor;
 *    subtrair:    estoque -= quantidade;
 *    incrementar: contador++;
 *    resto:       numero % 2        // 0 = par
 *    máximo:      Math.max(a, b);
 *    mínimo:      Math.min(a, b);
 *    potência:    Math.pow(base, expoente);
 *    raiz:        Math.sqrt(valor);
 *    condição:    String texto = ativo ? "Sim" : "Não";
 *
 *  CHECKLIST FINAL [CHECKLIST]
 *    [ ] Nome da classe e do arquivo estão iguais.
 *    [ ] Atributos estão private e com os tipos da UML.
 *    [ ] Construtor não tem void e inicializa todos os atributos.
 *    [ ] Sobrecargas possuem parâmetros diferentes.
 *    [ ] Cada método retorna exatamente o tipo pedido.
 *    [ ] Operações inválidas não alteram o objeto.
 *    [ ] String foi comparada com equals/equalsIgnoreCase.
 *    [ ] Percentuais foram divididos por 100.
 *    [ ] Taxas únicas ficaram fora da multiplicação.
 *    [ ] O main criou a quantidade de objetos solicitada.
 *    [ ] O menu está dentro do do/while e possui opção de saída.
 *    [ ] O Scanner foi fechado ao final.
 * ============================================================================
 */
