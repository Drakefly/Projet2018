import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Panneau extends JPanel {

    private Liste<Cellule> l = new Liste<>();

    public void paintComponent(Graphics g) {
        // On décide d'une couleur de fond pour notre rectangle
        g.setColor(Color.white);
        // On dessine celui-ci afin qu'il prenne tout la surface
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        String string = "";
        string=l.genererAffichage();

        String[] parts = string.split("\n");
        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.black);
        for (int i = 0; i < parts.length; i++) {
            System.out.println(parts[i]);
            g.drawString(parts[i], 10, 20*(i+1));
        }
    }

    public Liste<Cellule> getL() {
        return l;
    }

    public void setL(Liste<Cellule> l) {
        this.l = l;
    }
}