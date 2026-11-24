public class Aluno {
    // Atributos
    private String nome;
    private boolean bolsista;

    // Construtores
    
    public Aluno(String nome, boolean bolsista){
		this.nome = nome;
		this.bolsista = bolsista;
	}

    // Getters

    public String getNome() {
        return nome;
    }

    public boolean getBolsista() {
        return bolsista;
    }

    // Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setBolsista(boolean bolsista) {
        this.bolsista = bolsista;
    }

    // Métodos
    
}
