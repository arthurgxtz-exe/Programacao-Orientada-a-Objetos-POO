import java.util.Random;
public class Array {
    public static void main(String[] args){
        Random gerador = new Random();
        int[] numeros = new int[5];

        for(int i = 0;i < numeros.length; i++){
            numeros[i] = gerador.nextInt(100) + 1;
            
        }

        for(int i = 0; i < numeros.length; i++){

            System.out.printf("%d %n",numeros[i]);

        }
    }
    
}
