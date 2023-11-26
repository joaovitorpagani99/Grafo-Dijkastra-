/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaov
 */
public class Grafo {

    private List<Vertice> grafo = new ArrayList<Vertice>();

    public void setVertices(List<Vertice> vertices) {

        this.grafo.addAll(vertices);
    }

    public void adicionarVertice(Vertice novoVertice) {

        this.grafo.add(novoVertice);
    }

    public List<Vertice> getVertices() {

        return this.grafo;
    }

    public Vertice encontrarVertice(String nome) {

        for (int i = 0; i < this.getVertices().size(); i++) {

            if (nome.equalsIgnoreCase(this.getVertices().get(i).getDescricao())) {

                return this.getVertices().get(i);

            }
        }

        return null;

    }
}
