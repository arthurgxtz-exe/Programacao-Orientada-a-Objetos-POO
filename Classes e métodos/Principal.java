import java.util.Scanner;

public class Principal {
    public static int menu(Scanner scanf){
        
        int op;
        System.out.println("===== MENU CARRO =====");
        System.out.println("1 - EXIBIR INFORMAÇÕES");
        System.out.println("2 - CONSULTAR QUILOMETRAGEM");
        System.out.println("0 - SAIR");
        System.out.println("Escolha uma opção: ");
        op = scanf.nextInt();
        return op;
    }
    public static int consulta(Carro[] c, Scanner scanf){
        int op;
        System.out.println("Qual carro deseja consultar ?");
        for(int i = 0; i < c.length; i++){
            System.out.println(i+1 + " - " + c[i].getModelo());        
        }        
        
        System.out.println("Escolha: ");
        op = scanf.nextInt();
        return op;
    }
    public static void main(String[] args){
        Scanner scanf = new  Scanner(System.in);
        Carro[] carros = new Carro[3];
        int op;       
        carros[0] = new Carro("Honda", "Civic", 2020);
        carros[1] = new Carro("Fiat", "Pulse");
        carros[2] = new Carro("Toyota", "Corolla", 2023);
        do{
            op = menu(scanf);
            switch(op){
                case 1:
                    int op2;
                    op2 = consulta(carros, scanf);
                    if(op2 <= carros.length && op2 > 0){

                        carros[op2 - 1].exibirInformacoes();
                    }
                    else{
                        System.out.println("Opção invalida!");
                    }
                    break;
                case 2: 
                    op2 = consulta(carros, scanf);
                    if(op2 <= carros.length && op2 > 0){
                        System.out.println("Quilometragem: " + carros[op2 - 1].getQuilometragem());
                    }
                    else{
                    System.out.println("Opção invalida!");

                    }
                    break;
                case 0:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }

        }while(op != 0);
        scanf.close();
    }
}
