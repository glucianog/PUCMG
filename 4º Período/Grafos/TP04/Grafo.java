/**
 * Grafo para resolução de problemas referentes a minimizar
 * tempo gasto por tarefas.
 * @author Luigi D. C. Soares <luigi.soares@sga.pucminas.br>
 * @author Gabriel L. Gomes <glgomes@sga.pucminas.br>
 * @author Geovane F. S. Santos <geovane.fonseca@sga.pucminas.br>
 * @version 1
 * @since 15/11/2017
 */
 
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Grafo {
    // Lista de vértices representando cada tarefa.
    private List<Vertice> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    public List<Vertice> getVertices() {
        return this.vertices;
    }

    public void add(Vertice vertice) {
        this.vertices.add(vertice);
    }

    public int getColoracao() {
        int i = 0;
        int k = 0;

        // Ordenar pelo maior grau
        sort();

        List<Integer> cores = new ArrayList<>();
        cores.add(k);
        
        // Seta cor do primeiro vértice.
        this.vertices.get(i).setCor(k);

        for (Vertice v : this.vertices.subList(1, this.vertices.size())) {
            k = -1;
            
            for (Integer cor : cores) {
                if (v.verificarCor(cor) == false) {
                    k = cor;
                    break;
                }
            }

            if (k == -1) {
                i++;
                k = i;
                cores.add(k);
            }

            v.setCor(k);
        }

        return i + 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.vertices.size(); i++) {
            Vertice v = this.vertices.get(i);
            sb.append("[i: " + i + ", c: " + v.getCor() + ", a: " + v.getAdjacentes() + "]");
        }

        return sb.toString();
    }

    public void sort() {
        Collections.sort(vertices);
        Collections.reverse(vertices);
    }
}