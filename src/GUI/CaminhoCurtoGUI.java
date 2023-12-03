package GUI;

import Arquivo.LerDoArquivo;
import Grafo.Aresta;
import Grafo.Dijkstra;
import Grafo.Grafo;
import Grafo.Vertice;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;
import javax.swing.ImageIcon;

public class CaminhoCurtoGUI extends javax.swing.JFrame {

    private DrawPanel drawPanel = new DrawPanel();
    private Grafo teste;
    
    
    public void adicionarImagem(){
        ImageIcon icon = new ImageIcon("src/imagem/rotas.jpg");
        icon.setImage(icon.getImage().getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), 1));
        lblImagem.setIcon(icon);
    }
    

    public CaminhoCurtoGUI() {
        initComponents();
        setLocationRelativeTo(null);
        adicionarImagem();
        teste = new Grafo();
        try {
            teste.setVertices(LerDoArquivo.lerGrafo("src/Arquivo/grafo.txt"));
            Container c = this.getContentPane();
            c.add(drawPanel, BorderLayout.CENTER);
            drawPanel.draw(teste.getVertices());
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        for (Vertice vertice : teste.getVertices()) {
            cbOrigem.addItem(vertice.getDescricao());
            cbDestino.addItem(vertice.getDescricao());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbOrigem = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbDestino = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        lblImagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Origem ");

        jLabel2.setText("Destino");

        jButton1.setText("Melhor rota");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        area.setColumns(20);
        area.setRows(5);
        jScrollPane1.setViewportView(area);

        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/rotas.jpg"))); // NOI18N
        lblImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 386, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        Grafo teste = new Grafo();
        try {
            teste.setVertices(LerDoArquivo.lerGrafo("src/Arquivo/grafo.txt"));
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        String origem = cbOrigem.getSelectedItem().toString();
        String destino = cbDestino.getSelectedItem().toString();

        Vertice vOrigem = null;
        Vertice vDestino = null;
        for (Vertice vertice : teste.getVertices()) {
            if (vertice.getDescricao().equals(origem)) {
                vOrigem = vertice;
            } else if (vertice.getDescricao().equals(destino)) {
                vDestino = vertice;
            }
        }

        Dijkstra dijkstra = new Dijkstra();
        List<Vertice> caminho = dijkstra.encontrarMenorCaminhoDijkstra(teste, vOrigem, vDestino);

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

        area.setText("");
        for (Vertice vertice : caminho) {
            area.append(vertice.getDescricao() + "\n");
        }
        area.append("O custo do caminho é: " + custo);

    }// GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CaminhoCurtoGUI.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaminhoCurtoGUI.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaminhoCurtoGUI.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaminhoCurtoGUI.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaminhoCurtoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JComboBox<String> cbDestino;
    private javax.swing.JComboBox<String> cbOrigem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagem;
    // End of variables declaration//GEN-END:variables
}
