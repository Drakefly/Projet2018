package Vue;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    Fenetre(){
        JFrame fenetre = new JFrame();

        //Propriété de la fenetre
        fenetre.setTitle("Le jeu dela vie ");
        fenetre.setSize(800, 600);
        fenetre.setResizable(false);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(null);
        //
        JPanel pan = new JPanel();
        pan.setBackground(Color.ORANGE);

        /*Echiquier ecq = new Echiquier();
        ecq.setBounds(10,10,452,465);
        this.add(ecq);*/
        //
        JTextField tf = new JTextField();
        tf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,10));
        tf.setBounds(500,150,60,45);
        tf.setBackground(Color.BLACK);
        this.add(tf);

        fenetre.setVisible(true);
    }
    public static void main(String[] args){
    EventQueue.invokeLater(new Runnable(){
        public void run(){
            UIManager.put("swing.boldMetal",Boolean.FALSE);
            new Fenetre();
        }
    });

    }
}
