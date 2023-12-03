/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo;

/**
 *
 * @author joaov
 */
public class Aresta {

    private int peso;
    private Vertice origem;
    private Vertice destino;

    public Aresta(Vertice v1, Vertice v2) {
        this.peso = 1;
        this.origem = v1;
        this.destino = v2;
    }

    public void setPeso(int novoPeso) {

        this.peso = novoPeso;
    }

    public int getPeso() {

        return peso;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getOrigem() {
        return origem;
    }
    
    

}
