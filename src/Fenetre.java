import Vue.AffichageBD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Fenetre extends JFrame implements ActionListener,KeyListener {
private final int TAILLE =400;
    private Panneau pan = new Panneau();
    private JButton dezoom = new JButton("Dezoom");
    private JButton zoom = new JButton("Zoom");

    public Fenetre() {

        this.setTitle("Jeu de la vie");
        this.setSize(TAILLE+120, TAILLE+25);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setFocusable(true);

        pan.setDimm(TAILLE);
        pan.setNombre(20);

        //KeyListener
        addKeyListener(this);

        //Boutons
        dezoom.setBounds(TAILLE-40,40,100,30);//Il vaudrait mieux diviser la tailler toale en nombre de case demander
        dezoom.addActionListener( this);
        pan.add(dezoom);

        zoom.setBounds(TAILLE-40,75,100,30);
        zoom.addActionListener(this);
        pan.add(zoom);



        this.setContentPane(pan);
    }

    public static void main(String[] args) {
        Fenetre f =new Fenetre();
        f.setVisible(true);
}

    //x largeur width
    public void go(Liste liste,int numeroSim) {
        pan.setL(liste);
        pan.setNumeroSim(numeroSim);
        pan.repaint();
    }
    public void actionPerformed(ActionEvent arg0) {//TODO grisé entre certains seuils
        //Lorsque l'on clique sur le bouton, on met à jour le JLabel
        if(arg0.getSource() == dezoom){
            pan.setNombre(pan.getNombre()+10);
        }

        if(arg0.getSource() == zoom) {
            pan.setNombre(pan.getNombre()-10);
        }
        if(pan.getNombre()==100)AffichageBD.information("Attention trop dezoomer est gourmand en ressources");
        pan.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {//Todo accellerer ralentir pause
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Deplacement droite");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Deplacement haut");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Deplacement bas");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Deplacement gauche");
        }
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            AffichageBD.information("Uttilisez les touches directionnelles pour vous deplacer, + ou - du pavé numéfique pour zommer ou dezzommer et f12 pour afficher" +
                    "les credits ");
        }
        if (e.getKeyCode() == KeyEvent.VK_F12) {
            AffichageBD.information("\"SENAT Clement\\nDOUCHET Loic\\nHERVE Camille\\n\"");
        }
        if (e.getKeyCode() == 107) {
            pan.setNombre(pan.getNombre()-10);
        }
        if (e.getKeyCode() == 109) {
            pan.setNombre(pan.getNombre()+10);
        }
        pan.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}