import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel {
    Graphics g;
    public void paintComponent(Graphics g){
        this.g=g;
        /*Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp, gp2, gp3, gp4, gp5, gp6;
        gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);
        gp2 = new GradientPaint(20, 0, Color.magenta, 40, 0, Color.blue, true);
        gp3 = new GradientPaint(40, 0, Color.blue, 60, 0, Color.green, true);
        gp4 = new GradientPaint(60, 0, Color.green, 80, 0, Color.yellow, true);
        gp5 = new GradientPaint(80, 0, Color.yellow, 100, 0, Color.orange, true);
        gp6 = new GradientPaint(100, 0, Color.orange, 120, 0, Color.red, true);

        g2d.setPaint(gp);
        g2d.drawRect(0, 0, 20, this.getHeight());
        g2d.setPaint(gp2);
        g2d.fillRect(20, 0, 20, this.getHeight());
        g2d.setPaint(gp3);
        g2d.fillRect(40, 0, 20, this.getHeight());
        g2d.setPaint(gp4);
        g2d.fillRect(60, 0, 20, this.getHeight());
        g2d.setPaint(gp5);
        g2d.fillRect(80, 0, 20, this.getHeight());
        g2d.setPaint(gp6);
        g2d.fillRect(100, 0, 40, this.getHeight());*/
    }

    public void afficherMap(Liste liste){
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