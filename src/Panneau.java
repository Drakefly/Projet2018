import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel {
    Graphics g;
    public void paintComponent(Graphics g){
        this.g=g;
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp;
        gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);


        g2d.setPaint(gp);
        g2d.drawRect(0, 0, 20, this.getHeight());

    }

    public void afficherMap(Liste liste){
        Graphics2D g2d = (Graphics2D)g;
        this.g=g;
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp;
        gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);

        int lignemini = liste.premier.info.ligne;
        int lignemaxi = liste.premier.info.ligne;
        int colonemini = liste.premier.info.colone;
        int colonemaxi = liste.premier.info.colone;
        for (Liste.Maillon p = liste.premier; p.suiv != null; p = p.suiv) {
            lignemaxi = p.suiv.info.ligne;
            if (p.suiv.info.colone < colonemini) colonemini = p.suiv.info.colone;
            if (p.suiv.info.colone > colonemaxi) colonemaxi = p.suiv.info.colone;
        }

        for (int i = lignemini-1; i <= lignemaxi+1; i++) {
            for (int j = colonemini-1; j <= colonemaxi+1; j++) {
                if (liste.existe(new Cellule(j, i))) g.drawRect(i*10,j*10,10,10);
                else g.fillRect(i*10,i*10,10,10);
            }
        }
    }
}