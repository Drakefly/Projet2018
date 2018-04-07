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
    private JButton north = new JButton("north");
    private boolean active = false;

    public Fenetre() {

        this.setTitle("Jeu de la vie");
        this.setSize(TAILLE+120, TAILLE+25);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(pan);
        this.setResizable(false);
        this.setVisible(false);
        pan.setDimm(TAILLE);
        pan.setNombre(20);

        //Boutons
        dezoom.setBounds(TAILLE-40,40,100,30);//Il vaudrait mieux diviser la tailler toale en nombre de case demander
        dezoom.addActionListener( this);
        pan.add(dezoom);

        zoom.setBounds(TAILLE-40,75,100,30);
        north.setBounds(TAILLE-40,75,100,30);
        zoom.addActionListener(this);
        pan.add(zoom);

        //KeyListener
        addKeyListener(this);

    }
//x largeur width
    public void go(Liste liste,int numeroSim) {
        pan.setL(liste);
        pan.setNumeroSim(numeroSim);
        pan.repaint();
    }
    public void actionPerformed(ActionEvent arg0) {
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
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}