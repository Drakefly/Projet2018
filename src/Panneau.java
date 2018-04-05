import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Panneau extends JPanel {

    private Liste<Cellule> l = new Liste<>();
    private int dimm;//Dimmensions
    private int nombre;//nombres de cellules a afficher
    private int numeroSim;

    public void setNumeroSim(int numeroSim) {
        this.numeroSim = numeroSim;
    }

    public int getDimm() {
        return dimm;
    }

    public void setDimm(int dimm) {
        this.dimm = dimm;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void setL(Liste<Cellule> l) {
        this.l = l;
    }

    public void paintComponent(Graphics g) {
        // On décide d'une couleur de fond pour notre rectangle
        g.setColor(Color.white);
        // On dessine celui-ci afin qu'il prenne tout la surface
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        String string=l.genererAffichage(-nombre/2,-nombre/2,nombre/2,nombre/2,true);
        g.setColor(Color.black);
        g.drawString("Sim n°"+String.valueOf(numeroSim), this.getDimm()+20, 20);
        int y=0;//hauteur
        int x=1;//largeur
        int taille = dimm/nombre;

        for (char ch: string.toCharArray()) {
            switch (ch){
                case '0':
                    g.setColor(Color.black);
                    g.fillRect(x*taille, y*taille, taille, taille);
                    x++;
                    break;
                case '.':
                    g.setColor(Color.red);
                    g.drawRect(x*taille,y*taille,taille,taille);
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
}