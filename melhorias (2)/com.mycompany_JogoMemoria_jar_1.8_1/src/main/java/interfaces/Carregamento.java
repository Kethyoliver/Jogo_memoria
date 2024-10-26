package interfaces;
import java.awt.Color;

import jogomemoria.*;


public class Carregamento extends javax.swing.JFrame implements Runnable{

    public Carregamento() {
        
	
        initComponents();
        Thread t = new Thread(this);
        t.start();
        
    }
    
    @Override
    public void run() {
        
        for(int i = 1; i <=100;i++){
            try {
               Thread.sleep(20); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            progressbar.setValue(i);
        }
        dispose();
        new menuinicio().setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PANEL = new javax.swing.JPanel();
        progressbar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PANEL.setLayout(null);

        progressbar.setBackground(new java.awt.Color(255, 255, 255));
        progressbar.setForeground(new java.awt.Color(102, 102, 102));
        progressbar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PANEL.add(progressbar);
        progressbar.setBounds(20, 390, 290, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\melhorias (2)\\melhorias (2)\\com.mycompany_JogoMemoria_jar_1.8_1\\src\\main\\java\\lip\\jogo\\imagens\\carregamento.png")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(32767, 32767));
        PANEL.add(jLabel1);
        jLabel1.setBounds(0, 0, 340, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
 
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Carregamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PANEL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar progressbar;
    // End of variables declaration//GEN-END:variables

    
}
