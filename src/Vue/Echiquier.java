package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Echiquier extends JComponent {
    Echiquier(){

    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int caseDim = 50;
        Rectangle2D.Double f= new Rectangle2D.Double();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g2.setPaint(Color.BLACK);
                g2.fillRect(((i+1)*caseDim), ((j+1)*caseDim), caseDim,caseDim);
            }
        }
        g2.dispose();
    }

}
