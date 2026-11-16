public class Carro {
    private String marca;
    private String modelo;
    private int ano;
    private int quilometragem;
    
    public Carro(String marca, String modelo, int ano){
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometragem = 0;

    }
    
    public Carro(String marca, String modelo){
        this.marca = marca;
        this.modelo = modelo;
        this.ano = 2026;
        this.quilometragem = 0;

    }

    public double consultarDiaria(String categoria) {
        if(categoria.equals("BASICO")){
            return 80.0;
        }
        if(categoria.equals("INTERMEDIARIO")){
            return 120.0;
        }
        if(categoria.equals("PREMIUM")){
            return 200.0;
        }
        else{
            return 0.0;
        }
    }
    
    public void dirigir(int km){
        if(km > 0){
            this.quilometragem = this.quilometragem + km;
        }
    }
    public int getQuilometragem(){
        return this.quilometragem;
    }
    public void exibirInformacoes(){
       System.out.println("-------- Informações do veiculo ------");
       System.out.println("Marca: " + marca);
       System.out.println("Modelo: " + modelo);
       System.out.println("Ano: " + ano);
    }

    public int calcularIdade(){
        int idade = 2026 - ano;
        return idade;
    }

    public double calcularViagem(int km, double precoPorKm){
        
        return km * precoPorKm;

    }

    public double calcularViagem(int km){
        return km * 0.50;
    }
    public double realizarViagem(int km, double precoPorKm){
        if(km > 0){
            double total = calcularViagem(km, precoPorKm);
            this.quilometragem += km;
            return total;
        }
        else return 0.0;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }
    public int getAno(){
        return ano;
    }
    public void setAno(int novoAno){
        if(novoAno >= 1886 && novoAno <= 2026){
            this.ano = novoAno;
        }
        else{
            System.out.println("Ano invalido.");
        }
    }
}
