public class CursoOnlinePremium extends CursoOnline {
    // Atributos
    private boolean mentoria;

    // Construtores
    public CursoOnlinePremium(Aluno aluno, double valorBase, double taxaPlataforma, boolean mentoria){
		super(aluno, valorBase, taxaPlataforma);
		this.mentoria = mentoria;
	}

    // Getters

    public boolean getMentoria() {
        return mentoria;
    }

    // Setters

    public void setMentoria(boolean mentoria) {
        this.mentoria = mentoria;
    }

    // Métodos
    @Override
    public double calcularValor(){
		double valorFinal;
		if(mentoria){
		valorFinal = super.calcularValor() + 150.0;
		return valorFinal;
		}
		else{
			valorFinal = super.calcularValor();
			return valorFinal;
		}
	}
}
