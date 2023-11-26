/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Arquivo.LerDoArquivo;
import Grafo.Aresta;
import Grafo.Dijkstra;
import Grafo.Grafo;
import Grafo.Vertice;
import java.util.List;

/**
 *
 * @author joaov
 */
public class Main {

    public static void main(String[] args) {
    Grafo teste = new Grafo();
    teste.setVertices(LerDoArquivo.lerGrafo("src/Arquivo/grafo.txt"));

    Dijkstra dijkstra = new Dijkstra();

    Vertice origem = null;
    Vertice destino = null;
    for (Vertice vertice : teste.getVertices()) {
        if (vertice.getDescricao().equals("NewYork")) {
            origem = vertice;
        } else if (vertice.getDescricao().equals("Philadelphia")) {
            destino = vertice;
        }
    }

    if (origem == null || destino == null) {
        System.out.println("Origem ou destino não encontrado.");
        return;
    }

    List<Vertice> caminho = dijkstra.encontrarMenorCaminhoDijkstra(teste, origem, destino);

    int custo = 0;
    for (int i = 0; i < caminho.size() - 1; i++) {
        Vertice v1 = caminho.get(i);
        Vertice v2 = caminho.get(i + 1);
        for (Aresta aresta : v1.getArestas()) {
            if (aresta.getDestino().equals(v2)) {
                custo += aresta.getPeso();
                break;
            }
        }
    }

    System.out.println("O caminho mais curto de " + origem.getDescricao() + " para " + destino.getDescricao() + " é:");
    for (Vertice vertice : caminho) {
        System.out.println(vertice.getDescricao());
    }
    System.out.println("O custo do caminho é: " + custo);
}


}
