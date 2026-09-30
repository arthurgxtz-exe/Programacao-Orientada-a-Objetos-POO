import java.util.Scanner;

public class Veiculo{
    private String placa;
    private String modelo;
    private String tipo;
    private int quilometragem;
}

public Veiculo(String placa, String modelo, String tipo, int quilometragem){
    this.placa = placa;
    this.modelo = modelo;
    this.tipo = tipo;
    this.quilometragem = quilometragem;
}

public Veiculo(String placa, String modelo, String tipo){
    this.placa = placa;
    this.modelo = modelo;
    this.tipo = tipo;
    this.quilometragem = 0;
}

public Double calcularLocacao(int dias){
    double valorDiaria = 0.0;
    if(this.tipo.equals("ECONOMICO")){
        valorDiaria = 100 + (0.10 * 100);
        
    }
    if(this.tipo.equals("SUV")){
        valorDiaria = 150 + (0.10 * 100);
        
    }
    if(this.tipo.equals("LUXO")){
        valorDiaria = 250 + (0.10 * 100);
        
    }

    this.quilometragem += 100 * dias;
    return valorDiaria * dias;
    
}

public Double calcularLocacao(int dias, int kmRodados){
    double valorDiaria = 0.0;
    
    if(this.tipo.equals("ECONOMICO")){
        valorDiaria = 100 + (0.10 * (kmRodados / dias));
        
    }
    if(this.tipo.equals("SUV")){
        valorDiaria = 150 + (0.10 * (kmRodados / dias));
        
    }
    if(this.tipo.equals("LUXO")){
        valorDiaria = 250 + (0.10 * (kmRodados / dias));
        
    }

    this.quilometragem += kmRodados;
    return valorDiaria * dias;   

}
public Double calcularLocacao(int dias, int kmRodados, boolean seguro){
    double valorDiaria = 0.0;
    
    if(this.tipo.equals("ECONOMICO")){
        valorDiaria = 100 + (0.10 * (kmRodados / dias));
    }
    if(this.tipo.equals("SUV")){
        valorDiaria = 150 + (0.10 * (kmRodados / dias));
        
    }
    if(this.tipo.equals("LUXO")){
        valorDiaria = 250 + (0.10 * (kmRodados / dias));
        
    }
    if(seguro){
        int taxaSeguro = 50 * dias;
        this.quilometragem += kmRodados;
        return (valorDiaria * dias) + taxaSeguro;   

    }
    else{
        this.quilometragem += kmRodados;
        return valorDiaria * dias;   

    }

}

public String exibirDetalhes(){
    double valorDiaria = 0.0;

    if(this.tipo.equals("ECONOMICO")){
        valorDiaria = 100.0;
    }
    if(this.tipo.equals("SUV")){
        valorDiaria = 150.0;
    }
    if(this.tipo.equals("LUXO")){
        valorDiaria = 250.0;
    }

    return "Detalhes do Veiculo:\n" +
            "Placa:\n " + this.placa +
            "Modelo:\n " + this.modelo +
            "Tipo:\n " + this.tipo +
            "Quilometragem:\n " + this.quilometragem +
            "Valor por dia:\n " + valorDiaria;
    
}

public class Locadora{
    public static void main(String[] args){
        Scanner printf = new Scanner(System.in);

        
    }
}



