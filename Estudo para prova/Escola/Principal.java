import java.util.Scanner;

public class Principal {
	public static void consultar(Curso[] cursos){
		double total = 0;
		for (int i = 0; i < cursos.length; i++){
			total += cursos[i].calcularValor();
			System.out.println("Valor: " + cursos[i].calcularValor()+ " R$");
			
		}
		System.out.println("Total: " + total + " R$");
	}
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        
        Aluno a1 = new Aluno("Arthur", true);
        Aluno a2 = new Aluno("João", false);
        
        Curso[] cursos = new Curso[4];
        
        cursos[0] = new Curso(a1, 200);
        cursos[1] = new Curso(a2, 200);
        cursos[2] = new CursoOnline(a1, 200, 20);
        cursos[3] = new CursoOnlinePremium(a2, 200, 50, true);
        
        int opcao;
        do {
            System.out.println("\n1 - Consultar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanf.nextInt();
            switch (opcao) {
                case 1:
                    consultar(cursos);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (opcao != 0);
        scanf.close();
    }
}
