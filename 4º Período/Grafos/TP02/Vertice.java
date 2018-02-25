
import java.util.*;
class Vertice{

    public String cor;
    public Vertice pai;
    public int peso;
    public int distancia;
    public int vertice;
    Vertice(){
        this.cor = "branco";
        this.pai = null;
        this.peso = 0;
        this.distancia = Integer.MAX_VALUE;
        this.vertice = 0;
    }
    Vertice(int peso){
        this.peso = peso;
        this.cor = "branco";
        this.pai = null;
        this.distancia = this.vertice = 0;
    }

}


