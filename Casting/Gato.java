public class Gato extends Animal {
    public Gato(String nome){
        super(nome);
    }

    @Override 
    public void emitirSom(){
        System.out.println("Miau, Miau!");
    }
    
    public void derrubarCopo(){
        System.out.println("O gato derrubou o copo!");
    }
}


