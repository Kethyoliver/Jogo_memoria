package jogomemoria;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class JogoMemoria extends JFrame implements ActionListener{
    JPanel panel;
    JLabel matriz [][],etiqueta,etique,nomej,cronometro,lblfecha,lblhora,imgfund;
    int mat [][] = new int[4][5];
    int mat2 [][] = new int[4][5];
    Random ran;
    int contador,ban,ban1,annum,anposx,anposy,acnum,acposx,acposy;
    Timer espera, espera2,tempo;
    int consegund,seg,min;
    int hora,minutos,segundos;
    JButton reiniciar;
    //Thread hilo;

    
    public JogoMemoria(){
        this.setTitle("Jogo de Memoria");
        this.setSize(1010, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        //tempo.start();
        
        //um painel é colocado na janela
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        
        //objetivo do mat2, que as cartas pareçam viradas
        ran = new Random();
        this.numaleatorios();
        
        //matriz de imagens 
        //isso fará uma matriz de 4 linhas por 5 colunas onde mostrará as imagens 
        matriz = new JLabel[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JLabel();
                matriz[i][j].setSize(matriz[i][j].getWidth(), matriz[i][j].getHeight());
                //aqui o resto dos espaços para as imagens para que elas não apareçam grudadas
                matriz[i][j].setBounds(250+(j*125), 0+(i*156), 180, 222);
                //declaramos as imagens que possuem nomes de 1 a 10
                matriz[i][j].setIcon(new ImageIcon("ImgCartas/"+mat2[i][j]+".PNG"));
                //colocamos a matriz na tela
                matriz[i][j].setVisible(true);
                //aqui eles são somados junto com o 0
                panel.add(matriz[i][j],0);
                
                
            } 

        }

        
        seg = 0;
        min = 0;        
        
        //colocamos isso para que possamos mostrar a hora que
        //acontece durante o jogo
        tempo = new Timer (1000, new ActionListener()
        {
          
            public void actionPerformed(ActionEvent e) 
            {
                
                seg++;
                if(seg == 60){
                    min++;
                    seg=0;
                }
                
            //declara o tempo que passa em uma variável de cronômetro
            cronometro.setText(min+":"+seg); 
            }});
        
        //declaramos na variável wait qual é outro horário que usamos
        //para definir um horário em que as cartas serão viradas
        consegund = 0;
        espera = new Timer (1000, new ActionListener()
        {
          
            public void actionPerformed(ActionEvent e) 
            {
                consegund++;
            }});
        espera.start();
        espera.stop();
        consegund = 0;
        ban=0;
        ban1=0;
        
        //clica no evento nos cartões
        contador = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                if(e.getSource() == matriz[k][l]){
                                   // System.out.println(k+" "+l);
                                   
                                   //quando você clica na letra ela vai virar
                                   if(mat2[k][l] == 0 && contador !=2){
                                       mat2[k][l] = mat[k][l];
                                       matriz[k][l].setIcon(new ImageIcon("ImgCartas/"+mat2[k][l]+".png"));
                                       contador++;
                                       acnum = mat[k][l];
                                       acposx = k;
                                       acposy = l;
                                       if(contador == 1){
                                            annum = mat[k][l];
                                            anposx = k;
                                            anposy = l;
                                       }
                                       
                                       //tempo que leva para virar
                                       espera2 = new Timer (500, new ActionListener()
                                       {
          
                                        public void actionPerformed(ActionEvent e) {

                                           if(contador == 2 && ban1 == 0){
                                               espera.restart();
                                               ban1=1;
                                            }
                                            if(contador == 2 && consegund == 2){
                                                espera.stop();
                                                consegund = 0;
                                                
                                                //As cartas iguais desaparecem e deixam as que ainda não foram encontradas
                                                if(mat2[acposx][acposy]==mat2[anposx][anposy]){
                                                
                                                    mat2[acposx][acposy] = -1;
                                                    mat2[anposx][anposy] = -1;
                                                    matriz[acposx][acposy].setIcon(new ImageIcon("ImgCartas/"+mat2[acposx][acposy]+".png"));
                                                    matriz[anposx][anposy].setIcon(new ImageIcon("ImgCartas/"+mat2[anposx][anposy]+".png"));
                                                    contador=0;
                                                    //ganha se todo mat2 for -1
                                                    int acum = 0;
                                                    for (int m = 0; m < 4; m++) {
                                                       for (int n = 0; n < 5; n++) {
                                                          if (mat2[m][n] == -1)
                                                              acum++;
                                                          }
                                                    }
                                                    //quando nenhum for encontrado, então aparecerá
                                                    //uma mensagem dizendo que ganhei e executando automaticamente
                                                    //a janela do toca-discos
                                                       if(acum == 20){
                                                           JOptionPane.showMessageDialog(panel, "PARABÉNS, VOCÊ GANHOU!!");
                                                           
                                                            Recordjogador janela = new Recordjogador();
                                                            janela.setVisible(true);
                                                            tempo.stop();
                                                            janela.lbltempoj.setText(min+":"+seg);
                                                            janela.lblnomj.setText(nomej.getText());
                                                            janela.lblhorainicio.setText(lblhora.getText());
                                                            janela.lblfechaj.setText(lblfecha.getText());
                                                           
                                                       }
                                                }
                                                for (int m = 0; m < 4; m++) {
                                                    for (int n = 0; n < 5; n++) {
                                                        //valor -1 é colocado em cartas pares
                                                        if(mat2[m][n]!=0 && mat2[m][n]!=-1){
                                                            mat2[m][n] = 0;
                                                            matriz[m][n].setIcon(new ImageIcon("ImgCartas/"+mat2[m][n]+".png"));
                                                            contador=0;

                                                        }
                                                        

                                                    }

                                                }
                                                espera2.stop();
                                                ban1=0;
                                            }
                                        }});
                                       if(ban == 0)
                                           espera2.start();
                                           ban = 1;
                                       if(contador == 2)
                                               espera2.restart();
                                   }    
  
                                }
                                
                            }
                            
                        }
                    }

                   
                });
                
            }
            
        }
        
        
        componentes();
        lblfecha.setText(fecha());
        hora();
        //hilo = new Thread(this);  
    }
    
   //este método obterá a hora atual do dispositivo
    private void hora(){ 
      Calendar calendario = new GregorianCalendar();
      hora= calendario.get(Calendar.HOUR_OF_DAY);
      minutos = calendario.get(Calendar.MINUTE);
      segundos = calendario.get(Calendar.SECOND);
      lblhora.setText(hora + ":" + minutos + ":" + segundos);   
    } 
    
    //este método obtém a data do dia através do dispositivo
    private String fecha(){
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
        
    }
    
    //este método é feito para obter as cartas aleatoriamente
    //toda vez que o jogo começar as cartas aparecerão
    //diferentes lugares.
    //também para obter o mesmo cartão duas vezes
    private void numaleatorios(){
        int acumulador = 0;
         for (int i = 0; i < 4; i++) 
            for (int j = 0; j < 5; j++){
                mat[i][j] = 0;
              //  mat2[i][j] = 0;
                
            }    
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = ran.nextInt(10)+1;
                
                do{
                    acumulador = 0;
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                             if(mat[i][j]== mat[k][l]){
                                acumulador +=1;
                            }
                        }
                    }
                //isto é colocado de forma que o cartão só seja duplicado duas vezes
                if(acumulador == 3){
                    mat[i][j] = ran.nextInt(10)+1;
                }
                }while(acumulador == 3); 
            }   
        }
        
       
            
        
         
    }
    
    
   
   //estes são os componentes do programa
    private void componentes(){
        etiqueta = new JLabel("Jogador: ");
        etiqueta.setBounds(40,40,150,40);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 15));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        nomej = new JLabel();
        nomej.setBounds(135,40,150,40);
        nomej.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nomej.setForeground(Color.WHITE);
        nomej.setVisible(true);
        panel.add(nomej);
        
        etiqueta = new JLabel("Tempo: ");
        etiqueta.setBounds(40,80,150,40);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 15));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        //neste JLabel aparecerá o tempo decorrido durante o jogo
        cronometro = new JLabel();
        cronometro.setBounds(135,80,150,40);
        cronometro.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cronometro.setForeground(Color.WHITE);
        cronometro.setVisible(true);
        panel.add(cronometro);
        
        etiqueta = new JLabel("Hora inicio: ");
        etiqueta.setBounds(40,120,150,40);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 15));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        //neste JLabel será exibida a data
        lblfecha = new JLabel();
        lblfecha.setBounds(135,160,150,40);
        lblfecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblfecha.setForeground(Color.WHITE);
        lblfecha.setVisible(true);
        panel.add(lblfecha);
        
        etiqueta = new JLabel("Data: ");
        etiqueta.setBounds(40,160,150,40);
        etiqueta.setFont(new Font("Stencil", Font.PLAIN, 15));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setVisible(true);
        panel.add(etiqueta);
        
        //neste JLabel será exibido ou agendado
        lblhora = new JLabel();
        lblhora.setBounds(135,120,150,40);
        lblhora.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblhora.setForeground(Color.WHITE);
        lblhora.setVisible(true);
        panel.add(lblhora);
        
        //este botão irá reiniciar o jogo
        reiniciar = new JButton("Reiniciar");
        reiniciar.setBounds(115,560,120,40);
        reiniciar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        reiniciar.addActionListener(this);
        panel.add(reiniciar);
        
        imgfund = new JLabel();
        imgfund.setSize(1010, 720);
        imgfund.setLocation(0, 0);
        imgfund.setIcon(new ImageIcon("ImgFundo/1.png"));
        imgfund.setVisible(true);
        panel.add(imgfund);
    }
    
    
    //estes são os eventos de ação
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //é colocado que o botão reiniciar mostrará uma mensagem perguntando se você tem certeza que deseja fazer isso
        //se sim, ele irá reiniciar desde o início e se não, continuará no jogo
         if(e.getSource() == reiniciar){
              if (JOptionPane.showConfirmDialog(rootPane, "Tem certeza de que deseja reiniciar o jogo? Fazer isso o enviará para o menu Iniciar e seu caminho desaparecerá.",
                "Reiniciar o jogo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION)
        {
            menuinicio janela = new menuinicio();
            janela.setVisible(true);
            this.setVisible(false);
        }
        else{
                setDefaultCloseOperation(0);
        }
         }
    }
        
}