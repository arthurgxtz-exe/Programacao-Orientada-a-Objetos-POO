import java.util.Scanner;
import java.util.Random;

public class ContaBancaria {
    private String titular;
    private int numeroConta;
    private double saldo;
    static Scanner scanf = new Scanner(System.in);

    public ContaBancaria(String titular){
        Random gerador = new Random();

        this.titular = titular;
        this.numeroConta = gerador.nextInt(9999) + 1000;
        this.saldo = 0.0;
    }

    public void depositar(double valor){

        this.saldo += valor;

    }

    public void sacar(double valor){

        if(this.saldo < valor){
            System.out.println("Erro! Saldo insuficiente!");
        }
        else{
            System.out.println("Saque realizado com sucesso no valor de " + valor);
            this.saldo -= valor;
        }
    }

    public double getSaldo(){

        return this.saldo;
    }

    public String titular(){
        return this.titular;
    }

    public int numConta(){
        return this.numeroConta;
    }

    public static int menu(){
        int op;
        System.out.println("JuliusBank");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Ver saldo");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("0 - Sair");

        op = scanf.nextInt();
        scanf.nextLine();
        return op;

    }

    public static void main(String[] args){ 

        ContaBancaria minhaConta = null;
        int op;
        String nome;
        Double din;

        do{
            op = menu();
            switch(op){

                case 1:
                    System.out.println("Seja bem vindo ao JuliusBank!");
                    System.out.println("Digite seu nome para criar sua conta: ");
                    nome = scanf.nextLine();
                    minhaConta = new ContaBancaria(nome);
                    break;
                case 2:
                    if(minhaConta != null){
                        System.out.println("--- Dados Bancarios ---");
                        System.out.println("Numero da conta: " + minhaConta.numConta());
                        System.out.println("Titular: " + minhaConta.titular());
                        System.out.println("Saldo atual da conta: " +  minhaConta.getSaldo());
                    }
                    else
                        System.out.println("Erro! Por favor crie uma conta primeiro!");
                        
                    break;
                case 3:
                    if(minhaConta != null){
                        System.out.println("Digite a quantia a ser depositada: ");
                        din = scanf.nextDouble();
                        System.out.println("Depositando " + din + " R$");
                        minhaConta.depositar(din);
                    }
                    else
                        System.out.println("Erro! Por favor crie uma conta primeiro!");

                    break;
                case 4:
                    if(minhaConta != null){
                        System.out.println("Digite a quantia a ser sacada: ");
                        din = scanf.nextDouble();
                        minhaConta.sacar(din);
                    }
                    else
                        System.out.println("Erro! Por favor crie uma conta primeiro!");

                    break;
                case 0: System.out.println("Encerrando programa...");
                    break;
                default: 
                    System.out.println("Opção invalida.");
                    break;
            }

        }while(op != 0);

        scanf.close();

    }
}
