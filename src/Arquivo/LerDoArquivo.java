package Arquivo;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joaov
 */
public class LerDoArquivo {

    public static List<Vertice> lerGrafo(String nomeArquivo) {

        Grafo g = new Grafo();
        Vertice v;
        File f = new File(nomeArquivo);
        String vertices[];
        String linha;
        ArrayList<String[]> s1 = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));

            Map<String, Vertice> mapa = new HashMap<>();

            while ((linha = br.readLine()) != null) {
                System.out.println("Lendo a linha: " + linha);
                if (linha.contains(",")) {
                    s1.add(linha.split("/"));
                    vertices = s1.get(0)[0].split(",");

                    v = (Vertice) mapa.get(vertices[0]);
                    if (v == null) {
                        v = new Vertice();
                    }

                    List<Vertice> vizinhosAtual = new ArrayList<>();
                    List<Aresta> arestasAtual = new ArrayList<>();
                    v.setDescricao(vertices[0]);
                    mapa.put(vertices[0], v);

                    if (linha.contains("/")) {

                        String pesoArestas[] = s1.get(0)[1].split(",");

                        for (int i = 1; i < vertices.length; i++) {
                            Vertice vit;
                            vit = mapa.get(vertices[i]);
                            if (vit == null) {
                                vit = new Vertice();
                            }
                            vit.setDescricao(vertices[i]);
                            vizinhosAtual.add(vit);
                            mapa.put(vertices[i], vit);

                            Aresta ait = new Aresta(v, vit);
                            ait.setPeso(Integer.parseInt(pesoArestas[i - 1]));
                            arestasAtual.add(ait);

                        }
                        v.setVizinhos(vizinhosAtual);
                        v.setArestas(arestasAtual);

                    }

                } 
                else {
                    v = (Vertice) mapa.get(linha);
                    if (v == null) {
                        v = new Vertice();
                    }
                    v.setDescricao(linha);
                    mapa.put(linha, v);

                }
                g.adicionarVertice(v);
                s1.clear();
                
            }
            //imprimirGrafo(g);

        } catch (FileNotFoundException e) {
            System.out.println("Nao encontrou o arquivo");
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return g.getVertices();
        
        
    }
    
    public static void imprimirGrafo(Grafo grafo) {
    for (Vertice vertice : grafo.getVertices()) {
        System.out.println("Vértice: " + vertice.getDescricao());
        System.out.println("Arestas:");
        for (Aresta aresta : vertice.getArestas()) {
            System.out.println(" - " + aresta.getDestino().getDescricao() + " com peso " + aresta.getPeso());
        }
        System.out.println();
    }
}

}


