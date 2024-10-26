package jogomemoria;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import pro.mongocrud.conectaMongo;


public class menuinicio extends JFrame implements ActionListener{
    JLabel nomjogador,etiqueta, imgFundo;
    JButton iniciarjogo,sair,nome;
    JTextField txtnomjogador;


    public menuinicio(){
        this.setTitle("Menu");
        this.setSize(1080, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
             
        componentes();
       
}
    //método que contém todos os componentes da janela
    public void componentes(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        
        
        etiqueta = new JLabel("JOGADOR: ");
        etiqueta.setBounds(480,180,550,60);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 30));
        etiqueta.setForeground(Color.WHITE);
        panel.add(etiqueta);
        
//        etiqueta = new JLabel("Nome do jogador");
//        etiqueta.setBounds(380,250,250,40);
//        etiqueta.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        panel.add(etiqueta);
//        
        //texto onde o nome do jogador é colocado
        txtnomjogador = new JTextField();
        txtnomjogador.setBounds(330,300,400,40);
        txtnomjogador.setHorizontalAlignment(JTextField.CENTER);
        txtnomjogador.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(txtnomjogador);
        
        //botão iniciar o jogo, isso enviará diretamente o jogo para iniciar
        iniciarjogo = new JButton("Iniciar Jogo");
        iniciarjogo.setBounds(400,400,250,40);
        iniciarjogo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        iniciarjogo.addActionListener(this); 
        panel.add(iniciarjogo);
        
        //Botão sair
        sair = new JButton("Sair");
        sair.setBounds(400,480,250,40);
        sair.setFont(new Font("Tahoma", Font.PLAIN, 15));
        sair.addActionListener(this);
        panel.add(sair);
        
        imgFundo = new JLabel();
        imgFundo.setSize(1080, 720);
        imgFundo.setLocation(0, 0);
        imgFundo.setIcon(new ImageIcon("ImgFundo/13.png"));
        imgFundo.setVisible(true);
        panel.add(imgFundo);
        
    }

    //eventos de ação

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //este evento executa a ação do botão iniciar jogo
        if (e.getSource() == iniciarjogo) {

            //é colocado um if para colocar uma exceção ao iniciar o jogo 
            if (txtnomjogador.getText().equals("")) {
                //se nenhum valor for encontrado no txtnomjogador
                JOptionPane.showMessageDialog(null, "Digite nome do jogador");
                // enviará uma mensagem dizendo que você precisa digitar um nome
            } else {

                // Obter o nome do jogador do campo de texto
                String nomeJogador = txtnomjogador.getText();

                // Inserir o nome do jogador no banco de dados
                conectaMongo dbConexao = new conectaMongo();
                dbConexao.insertValues(nomeJogador, "professorPlaceholder", 0, true);

                //caso contrário, o jogo da memória é inicializado 
                //a classe jogomemoria é chamada
                JogoMemoria janela = new JogoMemoria();
                //dizemos aquele momej da classe 
                //obterá o valor que foi colocado no txtnomjogador
                janela.nomej.setText(txtnomjogador.getText());

                //isto é colocado para inicializar o tempo da classe MemoryGame
                janela.tempo.start();
                //mostra a janela da classe do jogomemoria
                janela.setVisible(true);
                //oculta a janela do menu inicial
                this.setVisible(false);

            }
        }
       
       //este é o evento do botão de saída
       if(e.getSource() == sair){
          //será exibida uma janela onde você faz uma pergunta e tem a opção de sair ou não
           if (JOptionPane.showConfirmDialog(rootPane, "Tem certeza de que deseja sair do aplicativo?",
                "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION)
                System.exit(0);
       }
      
    }
}