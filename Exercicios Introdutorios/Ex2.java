import java.util.Scanner;

public class Ex2 {
    
    public static double divisao(int n1, int n2){
        double resul;
        resul = (double)n1 / n2;
        return resul;
    }
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        int n1, n2;        

        System.out.println("Digite um numero inteiro: ");
        n1 = leitor.nextInt();
        System.out.println("Digite outro numero inteiro: ");
        n2 = leitor.nextInt();
        
        System.out.printf("%d / %d = %.2f %n", n1, n2, divisao(n1, n2));

        leitor.close();

    }
}
