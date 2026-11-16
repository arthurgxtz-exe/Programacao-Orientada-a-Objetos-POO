package heranca;
public class Gerente extends Funcionario {
    public Gerente(String nome){
        super(nome);
    }
    
    public void realizarReuniao(){
        System.out.println("Gerente realizando reunião");

    }
    @Override
    public void trabalhar(){
        System.out.println("Gerente " + getNome() + " coordenando a equipe!");
    }
}
