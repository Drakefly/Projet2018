import java.awt.Dimension;
import javax.swing.JFrame;

public class Fenetre extends JFrame {

    public static void main(String[] args) {
        new Fenetre();
    }

    private Panneau pan = new Panneau();

    public Fenetre() {
        this.setTitle("Jeu de la vie");
        this.setSize(290, 470);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(pan);
        this.setVisible(true);

    }

    public void go(Liste liste) {
        pan.setL(liste);
        pan.repaint();
    }
}