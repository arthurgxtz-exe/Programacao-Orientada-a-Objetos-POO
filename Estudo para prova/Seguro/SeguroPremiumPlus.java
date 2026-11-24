public class SeguroPremiumPlus extends SeguroPremium{
    // Atributos
    private boolean assistencia24h;
    

    // Construtores
    public SeguroPremiumPlus(Cliente cliente, double valorBase, double taxaAdicional, boolean assistencia24h){
		super(cliente, valorBase, taxaAdicional);
		this.assistencia24h = assistencia24h;
	}

    // Getters

    public boolean getAssistencia24h() {
        return assistencia24h;
    }

    // Setters

    public void setAssistencia24h(boolean assistencia24h) {
        this.assistencia24h = assistencia24h;
    }

    // Métodos
    @Override
    public double calcularValor(){
		double valorFinal;
		if(assistencia24h == true){
			valorFinal = super.calcularValor() + 80.0;
			return valorFinal;
		}
		else{
			valorFinal = super.calcularValor();
			return valorFinal;
		}
	}
}
