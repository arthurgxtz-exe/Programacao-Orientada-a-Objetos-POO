package heranca;

public class Funcionario {
    private String nome;

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public void trabalhar() {
        System.out.println(nome + " está trabalhando!");
    }
    public String getNome(){
        return nome;
    }
}