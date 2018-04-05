package Vue;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    private int posX = -50;
    private int posY = -50;
    private Liste l = new Liste();//TODO N'arrive pas a chopper dans l'autre package :/
    public void paintComponent(Graphics g){
        //Vous verrez cette phrase chaque fois que la méthode sera invoquée
        System.out.println("Je suis exécutée !");
        g.fillOval(20, 20, 75, 75);
    }

}
