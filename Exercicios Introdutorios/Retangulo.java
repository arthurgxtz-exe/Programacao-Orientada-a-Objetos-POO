import java.util.Scanner;
public class Retangulo {
    private double comprimento, tamanho = 1;
    static Scanner scanf = new Scanner(System.in);


    public double Area(){
        double area = this.comprimento * this.tamanho;
        return area;
    }

    public double Perimetro(){
        double perimetro = 2 * (this.comprimento + this.tamanho);
        return perimetro;
    }
    public void setComprimento(double com){
        this.comprimento = com;
    }
    public void setTamanho(double tam){
        this.tamanho = tam;
    }
    public static int menu(){
        int op;
        System.out.println("---MENU---");
        System.out.println("1 - Calcular area de um retangulo");
        System.out.println("2 - Calcular Perimetro de um retangulo");
        System.out.println("0 - Sair");
        op = scanf.nextInt();
        scanf.nextLine();
        return op;
    }
    public static void main(String[] args){

        Retangulo MyRetangulo = new Retangulo();
        int op;
        double tam, com;

        do{
            op = menu();
            switch(op){
                case 1: 
                    System.out.println("Digite o tamanho do retangulo: ");
                    tam = scanf.nextDouble();
                    MyRetangulo.setTamanho(tam);
                    System.out.println("Digite o comprimento do retangulo: ");
                    com = scanf.nextDouble();
                    MyRetangulo.setComprimento(com);
                    System.out.println("A area do retangulo é:" + MyRetangulo.Area());
                    break;
                case 2:
                    System.out.println("Digite o tamanho do retangulo: ");
                    tam = scanf.nextDouble();
                    MyRetangulo.setTamanho(tam);
                    System.out.println("Digite o comprimento do retangulo: ");
                    com = scanf.nextDouble();
                    MyRetangulo.setComprimento(com);
                    System.out.println("O perimetro do retangulo é:" + MyRetangulo.Perimetro());
                    break;
            }
        }while(op != 0);

        scanf.close();
    }
   


}
