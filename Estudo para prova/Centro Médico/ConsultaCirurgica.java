public class ConsultaCirurgica extends ConsultaEspecialista{
    // Atributos
    private boolean taxaAnestesia;

    // Construtores
    public ConsultaCirurgica(Paciente paciente, double valorConsulta, boolean retornoIncluso, boolean taxaAnestesia){
		super(paciente, valorConsulta, retornoIncluso);
		this.taxaAnestesia = taxaAnestesia;
	}
    
    
    

    // Getters


    public boolean getTaxaAnestesia() {
        return taxaAnestesia;
    }

    // Setters


    public void setTaxaAnestesia(boolean taxaAnestesia) {
        this.taxaAnestesia = taxaAnestesia;
    }

    // Métodos
    @Override 
    public double calculaPrecoFinal(){
		double precoFinal;
		if(taxaAnestesia){
			precoFinal = super.calculaPrecoFinal() + 500.0;
			return precoFinal;
		}
		else{
			precoFinal = super.calculaPrecoFinal();
			return precoFinal;
		}
	}
}
