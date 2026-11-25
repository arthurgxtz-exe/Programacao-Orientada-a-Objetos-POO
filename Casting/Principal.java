public class Principal {
    
    public static void buscar(Animal[] animais, String alvo){
        for(int i = 0; i < animais.length; i++){
            if(animais[i] != null){

                if(alvo.equals(animais[i].getNome())){
                    System.out.println("Animal encontrado: " + alvo);
                    if(animais[i] instanceof Cachorro){
                        Cachorro c1 = (Cachorro) animais[i];
                        System.out.println("Idade: " + c1.getIdade());
                    }
                    else if(animais[i] instanceof Gato){
                        Gato g1 = (Gato) animais[i];
                        g1.derrubarCopo();
                    }
                    
                    return;
                }
            }
            
        }
        System.out.println("Animal não encontrado!");
    }

    public static void remover(Animal[] animais, String alvo){
        for(int i = 0; i < animais.length; i++){
            if(animais[i] != null){
                
                if(alvo.equals(animais[i].getNome())){
                    animais[i] = null;
                    System.out.println(alvo + " foi apagado com sucesso!");

                    for(int j = i; j < animais.length - 1; j++){
                        animais[j] = animais[j + 1];
                       
                    }
                    animais[animais.length - 1] = null;
                    return;

                }
            }       
              
        }
        System.out.println("Animal não encontrado ou já excluido !");
    }
    public static void main(String[] args){
        Animal[] animais = new Animal[3];

        animais[0] = new Cachorro("Rex", 2);
        animais[1] = new Gato("Mingau");
        animais[2] = new Cachorro("Max", 4);

       
        remover(animais, "Max");

        

    }
}
