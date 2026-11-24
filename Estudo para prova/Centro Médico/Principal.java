import java.util.Scanner;

public class Principal {
	public static void consultar(Consulta[] consultas){
		System.out.println("Casos para teste: ");
		for (int i = 0; i < 6; i++){
			System.out.println("Caso " + (i + 1) + " Saida: " + consultas[i].calculaPrecoFinal() + " R$");		
		}
		
	}
    public static void main(String[] args) {
        System.out.println(ConsultaEspecialista.class.getProtectionDomain().getCodeSource().getLocation()
);
        Scanner scanf = new Scanner(System.in);
        
        Paciente p1 = new Paciente("Ana", false);
        Paciente p2 = new Paciente("Carlos", true);
        Paciente p3 = new Paciente("Beatriz", true);
        Paciente p4 = new Paciente("João", false);
        Paciente p5 = new Paciente("Maria", true);
        Paciente p6 = new Paciente("Lucas", true);
        
        Consulta[] consultas = new Consulta[6];
        consultas[0] = new Consulta(p1, 200);
        consultas[1] = new Consulta(p2, 200);
        consultas[2] = new ConsultaExame(p3, 200, 3);
        consultas[3] = new ConsultaEspecialista(p4, 300, true);
        consultas[4] = new ConsultaEspecialista(p5, 300, true);
        consultas[5] = new ConsultaCirurgica(p6, 1000, true, true);
        
        int opcao;
        do {
            System.out.println("\n1 - Consultar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanf.nextInt();
            switch (opcao) {
                case 1:
                    consultar(consultas);
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
