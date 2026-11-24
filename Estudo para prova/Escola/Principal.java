import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        Curso[]
        int opcao;
        do {
            System.out.println("\n1 - Consultar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanf.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Opcao escolhida");
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (opcao != 0);
        scanf.close()
    }
}
