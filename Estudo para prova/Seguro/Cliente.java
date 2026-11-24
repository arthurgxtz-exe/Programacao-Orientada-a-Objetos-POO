public class Cliente {

    // Atributos
    private String nome;
    private boolean possuiDesconto;

    // Construtores
    public Cliente(String nome, boolean possuiDesconto){
		this.nome = nome;
		this.possuiDesconto = possuiDesconto;
	}
		

    // Getters

    public String getNome() {
        return nome;
    }

    public boolean getPossuiDesconto() {
        return possuiDesconto;
    }

    // Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPossuiDesconto(boolean possuiDesconto) {
        this.possuiDesconto = possuiDesconto;
    }

    // Métodos

    // Método principal
    public static void main(String[] args) {

    }
}
