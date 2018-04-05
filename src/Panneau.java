import java.awt.*;
import javax.swing.JPanel;

public class Panneau extends JPanel {

    private int posX = -50;
    private int posY = -50;
    private Liste<Cellule> l = new Liste<>();

    public void paintComponent(Graphics g) {
        // On décide d'une couleur de fond pour notre rectangle
        g.setColor(Color.white);
        // On dessine celui-ci afin qu'il prenne tout la surface
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        String string = "";
        string=l.genererAffichage();
        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString(string, 10, 20);

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public Liste<Cellule> getL() {
        return l;
    }

    public void setL(Liste<Cellule> l) {
        this.l = l;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}