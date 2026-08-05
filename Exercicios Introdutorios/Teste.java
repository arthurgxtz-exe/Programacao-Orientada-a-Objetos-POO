import java.util.Scanner;

public class Teste {
    public static void main(String[] args){

        Scanner leitor = new Scanner(System.in);

        System.out.println("Digite seu nome: ");

        String nome = leitor.nextLine();

        System.out.println("Digite a sua idade: ");

        int idade = leitor.nextInt();

        if(idade >= 18){
            System.out.printf("Seu nome é %s e você é maior de idade.%n", nome);

        }
        else{
            System.out.printf("Seu nome é %s e você é menor de idade.%n");
        }

        leitor.close();
    }
}
