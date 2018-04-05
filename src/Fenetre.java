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
        this.setSize(520, 425);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(pan);
        this.setResizable(false);
        this.setVisible(true);
        pan.setDimm(400);
        pan.setNombre(20);

        //Boutons
        dezoom.setBounds(pan.getDimm()+10,40,100,30);//Il vaudrait mieux diviser la tailler toale en nombre de case demander
        dezoom.addActionListener( this);
        pan.add(dezoom);

        zoom.setBounds(pan.getDimm()+10,75,100,30);
        zoom.addActionListener(this);
        pan.add(zoom);


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

        pan.repaint();
    }
}