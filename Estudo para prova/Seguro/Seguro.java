public class Seguro {
    // Atributos
    private Cliente cliente;
    private double valorBase;

    // Construtores
    public Seguro(Cliente cliente, double valorBase){
		this.cliente = cliente;
		this.valorBase = valorBase;
	}

    // Getters

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorBase() {
        return valorBase;
    }

    // Setters

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    // Métodos
    
	public double calcularValor(){
		double valorFinal;
		if(cliente.getPossuiDesconto() == true){
			valorFinal = valorBase - (valorBase * (20.0/100));
			return valorFinal;
		}
		else{
			valorFinal = valorBase;
			return valorFinal;
		}
			
	}
}
