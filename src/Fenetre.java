import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener {

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
        dezoom.addActionListener( this);
        zoom.addActionListener(this);

        pan.add(zoom);
        pan.add(dezoom);
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
            pan.setNombre((int)(pan.getNombre()*1.5));
            pan.setDimm((int)(pan.getDimm()*0.7));
        }

        if(arg0.getSource() == zoom) {
            pan.setNombre((int) (pan.getNombre() * 0.5));
            pan.setDimm((int) (pan.getDimm() * 1.7));
        }

        pan.repaint();
    }
}