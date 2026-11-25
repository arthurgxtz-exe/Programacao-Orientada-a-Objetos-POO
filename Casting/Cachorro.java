public class Cachorro extends Animal {
    private int idade;
    
    public Cachorro(String nome, int idade){
        super(nome);
        this.idade = idade;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    @Override 
    public void emitirSom(){
        System.out.println("Au Au!");
    }
    
    public void abanarRabo(){
        System.out.println("O cachorro está abanando o rabo!");
    }
}
