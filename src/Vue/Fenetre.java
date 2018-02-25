package Vue;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    Fenetre(){
        this.setTitle("Le jeu dela vie ");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //Instanciation d'un objet JPanel
        JPanel pan = new JPanel();
        //Définition de sa couleur de fond
        //pan.setBackground(Color.ORANGE);
        //On prévient notre JFrame que notre JPanel sera son content pane


        JLabel tf = new JLabel("MARCHE PAS");
        tf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,10));
        tf.setBounds(100,150,200,200);
        tf.setBackground(Color.BLACK);
        add(tf);
        Echiquier echiquier = new Echiquier();
        echiquier.setBounds(10,10,600,600);
        add(echiquier);
        this.setVisible(true);
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
    //JFrame fenetre = new JFrame();

/*//Propriété de la fenetre
        fenetre.setTitle("Le jeu dela vie ");

                this.setLocationRelativeTo(null);

                //Instanciation d'un objet JPanel
                JPanel pan = new JPanel();
                //Définition de sa couleur de fond
                pan.setBackground(Color.ORANGE);

                this.setContentPane(pan);
        /*Echiquier ecq = new Echiquier();
        ecq.setBounds(10,10,452,465);
        this.add(ecq);
        //
        JTextField tf = new JTextField();
        tf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,10));
        tf.setBounds(500,150,60,45);
        tf.setBackground(Color.BLACK);
        this.add(tf);

                fenetre.setVisible(true);*/