import java.util.Scanner;

public class Principal {
	
	public static void consultar(Seguro[] seguros){
		double total = 0;
		for (int i = 0; i < seguros.length; i++){
			total += seguros[i].calcularValor();
			System.out.println("Seguro " + (i + 1) + ": " + seguros[i].calcularValor() + " R$" );
		}
		System.out.println("Total: " + total + " R$");
		
	}
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        Cliente c1 = new Cliente("Carlos", true);
		Cliente c2 = new Cliente("Ana", false);
		
		Seguro[] seguros = new Seguro[4];
		
		seguros[0] = new Seguro(c1, 200.0);
		seguros[1] = new SeguroPremium(c1, 300.0, 50.0);
		seguros[2] = new SeguroPremiumPlus(c1, 200.0, 50.0, true);
		seguros[3] = new SeguroPremiumPlus(c2, 400.0, 100.0, false);
        
        int opcao;
        do {
            System.out.println("\n1 - Consultar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanf.nextInt();
            switch (opcao) {
                case 1:
                    consultar(seguros);
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
