import java.util.Scanner;
public class Ex1 {
    
    
    public static int somar(int a, int b){

        return a + b;
    } 

    public static int subtracao(int a, int b){
        
        return a - b;
    
    }

    public static int multiplicacao(int a, int b){

        return a * b;

    }

    public static int divisao(int a, int b){

        return a / b;
    }

    public static void main(String[] args){
        int n1, n2, op;
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o primeiro numero: ");
        n1 = leitor.nextInt();
        System.out.println("Digite o segundo numero: ");
        n2 = leitor.nextInt();
        System.out.println("Selecione a operação: ");
        System.out.println("1 - Adiçao")              ;
        System.out.println("2 - Subtração");
        System.out.println("3 - Multiplicação");
        System.out.println("4 - Divisão");
        op = leitor.nextInt();

        switch (op){

            case 1: 
                System.out.printf("%d + %d = %d\n", n1, n2, somar(n1, n2));
                break; 
            case 2:
                System.out.printf("%d - %d = %d\n", n1, n2, subtracao(n1, n2));
                break;
            case 3:
                System.out.printf("%d * %d = %d\n", n1, n2, multiplicacao(n1, n2));
                break;
            case 4:
                System.out.printf("%d / %d = %d\n", n1, n2, divisao(n1, n2));
                break;
            default:
                System.out.println("Opção inválida!");
        }
        
        leitor.close();
    }
}
