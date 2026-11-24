public class Consulta {
    // Atributos
    private Paciente paciente;
    private double valorConsulta;

    // Construtores
    public Consulta(Paciente paciente, double valorConsulta){
		this.paciente = paciente;
		this.valorConsulta = valorConsulta;
	}

    // Getters

    public Paciente getPaciente() {
        return paciente;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    // Setters

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    // Métodos
    
    public double calculaPrecoFinal(){
		double precoFinal;
		if(paciente.getConvenio()){
			precoFinal = valorConsulta - (valorConsulta * 0.50);
			return precoFinal;
		}
		else{
			precoFinal = valorConsulta;
			return precoFinal;
		}
	}
}
