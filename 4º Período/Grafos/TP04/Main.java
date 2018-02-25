/**
 * Programa principal.
 * @author Luigi D. C. Soares <luigi.soares@sga.pucminas.br>
 * @author Gabriel L. Gomes <glgomes@sga.pucminas.br>
 * @author Geovane F. S. Santos <geovane.fonseca@sga.pucminas.br>
 * @version 1
 * @since 15/11/2017
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numVertices = Integer.parseInt(br.readLine());
        Grafo grafo = new Grafo();

        for (int i = 0; i < numVertices; i++) {
            grafo.add(new Vertice());
        }
        
        List<Vertice> vertices = grafo.getVertices();
        for (String str = br.readLine(); str.equals("FIM") == false; str = br.readLine()) {
            String[] entrada = str.split(",");
            int v = Integer.parseInt(entrada[0].trim());
            int a = Integer.parseInt(entrada[1].trim());

            vertices.get(v).addAdjacente(vertices.get(a));
            vertices.get(a).addAdjacente(vertices.get(v));
        }

        System.out.println(grafo.getColoracao());
    }
}