public class Curso {
    // Atributos
	private Aluno aluno;
	private valorBase;
    // Construtores
    public Aluno(Aluno aluno, double valorBase){
		this.aluno = aluno;
		this.valorBase = valorBase;
	}
	

    // Getters

    public Aluno getAluno() {
        return aluno;
    }

    public private getValorBase() {
        return valorBase;
    }

    // Setters

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setValorBase(private valorBase) {
        this.valorBase = valorBase;
    }

    // Métodos
    
    public double calcularValor(){
		double valorFinal;
		if(aluno.bolsista){
			valorFinal = valorBase - (valorBase * 0.30);
			return valorFinal;
		}
		else{
			valorFinal = valorBase;
			return valorFinal;
		}
	}
}
