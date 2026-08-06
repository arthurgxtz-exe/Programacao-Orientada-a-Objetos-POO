import java.util.Random;
public class Ex4 {
    public static void main(String[] args){
        Random gerador = new Random();

        int[] vet = new int[6];
        int maior = 0;
        System.out.println("Numeros gerados:");
        for(int i = 0; i < vet.length; i++){
            vet[i] = gerador.nextInt(401) + 100;
            System.out.println(vet[i]);
            if(vet[i] > maior){
                maior = vet[i];
            }
        }

        System.out.println("O maior valor:"+ maior);


    }
}
