public class CursoOnline extends Curso {
    // Atributos
    private double taxaPlataforma;

    // Construtores
    public CursoOnline(Aluno aluno, double valorBase, double taxaPlataforma){
		super(aluno, valorBase);
		this.taxaPlataforma = taxaPlataforma;
	}

    // Getters

    public double getTaxaPlataforma() {
        return taxaPlataforma;
    }

    // Setters

    public void setTaxaPlataforma(double taxaPlataforma) {
        this.taxaPlataforma = taxaPlataforma;
    }

    // Métodos
    @Overrride
	public double calcularValor(){
		double valorFinal;
		valorFinal = super.calcularValor() + taxaPlataforma;
		return valorFinal;
	}
}
