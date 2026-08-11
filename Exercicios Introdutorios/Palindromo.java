import java.util.Scanner;
public class Palindromo {

    public static int palindromo(String string){
        String nome = string.replace(" ", "").toLowerCase();
        int tamanho = nome.length();
        int sinal = 1;
        
        for(int i = 0; i < nome.length() / 2; i++ ){
    
            if(nome.charAt(i) != nome.charAt(tamanho - 1)){
                sinal = 0;
                return sinal;                           
                
            }
            else{
                tamanho--;
            }
        }
       return sinal;
    }
    public static void main(String[] args){
        Scanner scanf = new Scanner(System.in);
        String nome;
        while(true){
            System.out.println("Digite uma palavra: (ou 0 para sair) ");
            nome = scanf.nextLine();
    
            if(nome.equals("0")){
                System.out.println("Encerrando programa...");
                break;
            }
            if(palindromo(nome) == 1){
                System.out.println("Essa string é um palindromo.");
    
            }
            else
                System.out.println("Essa string não é um palindromo.");
    
        }
        
        scanf.close();
    }
}
