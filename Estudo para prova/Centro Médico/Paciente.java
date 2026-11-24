public class Paciente {
    // Atributos
    private String nome;
    private boolean convenio;

    // Construtores
    
    public Paciente(String nome, boolean convenio){
		this.nome = nome;
		this.convenio = convenio;
		
	}

    // Getters

    public String getNome() {
        return nome;
    }

    public boolean getConvenio() {
        return convenio;
    }

    // Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setConvenio(boolean convenio) {
        this.convenio = convenio;
    }

    // Métodos
}
