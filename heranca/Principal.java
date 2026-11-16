package heranca;

public class Principal {
    public static void main(String[] args){
        Funcionario[] equipe = new Funcionario[3];

        equipe[0] = new Funcionario("Ana");
        equipe[1] = new Gerente("Carlos");
        equipe[2] = new Gerente("Marcos");

        for(int i = 0; i < equipe.length; i++){
            equipe[i].trabalhar();
        }

    }

}
