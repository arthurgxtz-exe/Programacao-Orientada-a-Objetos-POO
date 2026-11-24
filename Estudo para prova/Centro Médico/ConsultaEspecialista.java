public class ConsultaEspecialista extends Consulta{
    // Atributos
	private boolean retornoIncluso;
    // Construtores
    
    public ConsultaEspecialista(Paciente paciente, double valorConsulta, boolean retornoIncluso){
		super(paciente, valorConsulta);
		this.retornoIncluso = retornoIncluso;
	}

    // Getters


    public boolean getRetornoIncluso() {
        return retornoIncluso;
    }

    // Setters


    public void setRetornoIncluso(boolean retornoIncluso) {
        this.retornoIncluso = retornoIncluso;
    }

    // Métodos
    
    public double calculaPrecoFinal(){
		double precoFinal;
		if(retornoIncluso){
			precoFinal = super.calculaPrecoFinal() + (super.calculaPrecoFinal() * 0.20);
			return precoFinal;
		}
		else{
			precoFinal = super.calculaPrecoFinal();
			return precoFinal;
		}
	}
}
