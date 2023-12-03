package GUI;

import Grafo.Aresta;
import Grafo.Vertice;
import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

public class DrawPanel extends JPanel {

    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphModelAdapter<String, DefaultEdge> jgAdapter;

    public DrawPanel() {
        this.setBackground(Color.white);
        this.setSize(500, 400);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void draw(List<Vertice> vertices) {
        ListenableGraph<String, DefaultEdge> g = new ListenableUndirectedGraph<>(DefaultEdge.class);

        jgAdapter = new JGraphModelAdapter<>(g);

        JGraph jgraph = new JGraph(jgAdapter);
        adjustDisplaySettings(jgraph);
        
        

        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jgraph, BorderLayout.CENTER);

        resize(DEFAULT_SIZE);

        for (Vertice v : vertices) {
            g.addVertex(v.getDescricao().trim());
        }

        for (Vertice v1 : vertices) {
            for (Aresta a : v1.getArestas()) {
                String v2Descricao = a.getDestino().getDescricao().trim();

                if (!g.containsEdge(v1.getDescricao().trim(), v2Descricao)
                        && !g.containsEdge(v2Descricao, v1.getDescricao().trim())) {
                    MyEdge edge = new MyEdge(a.getPeso());
                    g.addEdge(v1.getDescricao().trim(), v2Descricao, edge);
                }
            }
        }

        int size = vertices.size();
        int i = 0;
        int x = 50;
        int y = 50;

        for (Vertice v : vertices) {
            positionVertexAt(v.getDescricao().trim(), x, y);
            x += 150;
            if (i == size / 2) {
                x = 20;
                y += 200;
            }
            i++;
        }
    }

    private void adjustDisplaySettings(JGraph jg) {
        jg.setPreferredSize(DEFAULT_SIZE);
        Color c = Color.white;
        jg.setBackground(c);
    }

    @SuppressWarnings("unchecked")
    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds = new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth(),
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }
}
