package jogomemoria;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Recordjogador extends JFrame implements ActionListener{
    
    JLabel lblnomj, lbltempoj, lblhorainicio, lblfechaj,etiqueta, imgf;
    JButton volte;
    public Recordjogador(){
        this.setTitle("Recorde do Jogador");
        this.setSize(1010, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        //adiciona painel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        
        etiqueta = new JLabel("Seu recorde ");
        etiqueta.setBounds(380,60,250,60);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 20));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        etiqueta = new JLabel("Nome Jogador: ");
        etiqueta.setBounds(320,180,150,40);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 20));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        //este rÃ³tulo receberÃ¡ o nome do jogador colocado anteriormente
        lblnomj = new JLabel("Nome ");
        lblnomj.setBounds(480,180,150,40);
        lblnomj.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblnomj.setForeground(Color.WHITE);
        lblnomj.setVisible(true);
        panel.add(lblnomj);
        
        etiqueta = new JLabel("Tempo Final: ");
        etiqueta.setBounds(320,240,150,40);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 20));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        //este rÃ³tulo obterÃ¡ o tempo final que obteve, ou melhor, em quanto tempo
        //quanto tempo o jogador demorou para terminar o jogo
        lbltempoj = new JLabel("tempo");
        lbltempoj.setBounds(480,240,150,40);
        lbltempoj.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbltempoj.setForeground(Color.WHITE);
        lbltempoj.setVisible(true);
        panel.add(lbltempoj);
        
        etiqueta = new JLabel("Hora inicial: ");
        etiqueta.setBounds(320,300,150,40);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 20));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        //este rÃ³tulo mostra a hora de inÃ­cio
        lblhorainicio = new JLabel("hora");
        lblhorainicio.setBounds(480,300,150,40);
        lblhorainicio.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblhorainicio.setForeground(Color.WHITE);
        lblhorainicio.setVisible(true);
        panel.add(lblhorainicio);
        
        etiqueta = new JLabel("Data: ");
        etiqueta.setBounds(320,360,150,40);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 20));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        //neste rÃ³tulo serÃ¡ mostrada a data
        lblfechaj = new JLabel("fecha");
        lblfechaj.setBounds(480,360,150,40);
        lblfechaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblfechaj.setForeground(Color.WHITE);
        lblfechaj.setVisible(true);
        panel.add(lblfechaj);
        
        volte = new JButton("volte a Menu");
        volte.setBounds(370,560,250,40);
        volte.setFont(new Font("Tahoma", Font.PLAIN, 16));
        volte.addActionListener(this);
        panel.add(volte);
        
        imgf = new JLabel();
        imgf.setSize(1080, 720);
        imgf.setLocation(0, 0);
        imgf.setIcon(new ImageIcon("ImgFundo/7.png"));
        imgf.setVisible(true);
        panel.add(imgf);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //o botÃ£o retornar nos levarÃ¡ de volta Ã  janela do menu
        if(e.getSource() == volte){
            menuinicio janela = new menuinicio();
            JogoMemoria janela2 = new JogoMemoria();
            janela2.setVisible(false);
            janela.setVisible(true);
            this.setVisible(false);
        
        }
    }
    
}