import java.util.Random;

public class Ex3 {
    public static void main(String[] args){
        Random gerador =  new Random();
        int[] numeros = new int[5];
        int total = 0;

        System.out.println("Array criada, com os seguintes numeros: ");
        for(int i = 0; i < numeros.length; i++){

            numeros[i] = gerador.nextInt(11);
            total += numeros[i];
            System.out.printf("%d%n", numeros[i]);

        }

        System.out.printf("Total dos numeros somados:%n%d%n", total);
    }
}
