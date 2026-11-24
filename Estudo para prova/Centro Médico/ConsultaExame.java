public class ConsultaExame extends Consulta {
    // Atributos
    private int quantidadeExames;

    // Construtores
    public ConsultaExame(Paciente paciente, double valorConsulta, int quantidadeExames){
		super(paciente, valorConsulta);
		this.quantidadeExames = quantidadeExames;
	}

    // Getters


    public int getQuantidadeExames() {
        return quantidadeExames;
    }

    // Setters


    public void setQuantidadeExames(int quantidadeExames) {
        this.quantidadeExames = quantidadeExames;
    }

    // Métodos
    @Override 
	public double calculaPrecoFinal(){
		double precoFinal;
		precoFinal = super.calculaPrecoFinal() + (quantidadeExames * 50.0);
		return precoFinal;
	}
		
}
