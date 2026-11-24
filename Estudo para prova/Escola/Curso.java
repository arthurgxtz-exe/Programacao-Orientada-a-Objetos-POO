public class Curso {
    // Atributos
	private Aluno aluno;
	private double valorBase;
    // Construtores
    public Curso(Aluno aluno, double valorBase){
		this.aluno = aluno;
		this.valorBase = valorBase;
	}
	

    // Getters

    public Aluno getAluno() {
        return aluno;
    }

    public double getValorBase() {
        return valorBase;
    }

    // Setters

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    // Métodos
    
    public double calcularValor(){
		double valorFinal;
		if(aluno.getBolsista()){
			valorFinal = valorBase - (valorBase * 0.30);
			return valorFinal;
		}
		else{
			valorFinal = valorBase;
			return valorFinal;
		}
	}
}
