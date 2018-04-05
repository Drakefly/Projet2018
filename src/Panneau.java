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
        g.setColor(Color.black);
        String string=l.genererAffichage(-10,-10,10,10,true);//TODO ne pas coder en dur
        int y=1;
        int x=1;
        for (char ch: string.toCharArray()) {
            switch (ch){
                case '0':
                    g.setColor(Color.black);
                    g.fillRect(x*10, y*10, 10, 10);
                    x++;
                    break;
                case '.':
                    g.setColor(Color.red);
                    g.drawRect(x*10,y*10,10,10);
                    x++;
                    break;
                case '/':
                    y++;
                    x=0;
                    break;
                    default:
                        break;
            }

        }
    }

    public Liste<Cellule> getL() {
        return l;
    }

    public void setL(Liste<Cellule> l) {
        this.l = l;
    }
}