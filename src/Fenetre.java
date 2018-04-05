import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame {

    public static void main(String[] args) {
        new Fenetre();
    }

    private Panneau pan = new Panneau();
    private JButton dezoom = new JButton("Dezoom");
    private JButton zoom = new JButton("Zoom");


    public Fenetre() {
        this.setTitle("Jeu de la vie");
        this.setSize(420, 340);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(pan);
        this.setResizable(false);
        this.setVisible(true);
        pan.setDimm(15);
        pan.setNombre(20);
        dezoom.setBounds(pan.getNombre()*pan.getDimm()+10,40,100,30);
        zoom.setBounds(pan.getNombre()*pan.getDimm()+10,75,100,30);
        pan.add(zoom);
        pan.add(dezoom);
    }
//x largeur width
    public void go(Liste liste,int numeroSim) {
        pan.setL(liste);
        pan.setNumeroSim(numeroSim);
        pan.repaint();
    }
}