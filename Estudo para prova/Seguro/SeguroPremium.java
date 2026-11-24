public class SeguroPremium extends Seguro {
    // Atributos
    private double taxaAdicional;

    // Construtores
	public SeguroPremium(Cliente cliente, double valorBase, double taxaAdicional){
		super(cliente, valorBase);
		this.taxaAdicional = taxaAdicional;
	}
	

    // Getters

    public double getTaxaAdicional() {
        return taxaAdicional;
    }

    // Setters

    public void setTaxaAdicional(double taxaAdicional) {
        this.taxaAdicional = taxaAdicional;
    }

    // Métodos
    @Override
    public double calcularValor(){
		double valorFinal;
		valorFinal = super.calcularValor() + taxaAdicional;
		return valorFinal;
	}
	
}
