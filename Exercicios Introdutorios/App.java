public class App {
    
    String nome;
    String curso;

    public App(String nome, String curso){
        this.nome = nome;
        this.curso = curso;
    }

    public void apresentar(){
        System.out.println("Opa, sou " + nome + " e faço " + curso + ".");

    }

    public static void main(String[] args) {
        
        App aluno1 = new App("Arthur", "BSI");
        App aluno2 = new App("Jean", "BSI");

        aluno1.apresentar();
        aluno2.apresentar();
    }  
       

}
