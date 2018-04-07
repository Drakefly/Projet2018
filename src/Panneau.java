import java.awt.*;
import javax.swing.*;

public class Panneau extends JPanel {

    private Liste<Cellule> l = new Liste<>();
    private int dimm;//Dimmensions
    private int nombre;//nombres de cellules a afficher
    private transient int numeroSim;
    private int originx;
    private int originy;

    public int getOriginx() {
        return originx;
    }

    public void setOriginx(int originx) {
        this.originx = originx;
    }

    public int getOriginy() {
        return originy;
    }

    public void setOriginy(int originy) {
        this.originy = originy;
    }

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
        // Grand rectangle blanc pour reinitialiser cette partie de la fenetre
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        String string=l.genererAffichage(originy, originx,originy+nombre,originx+nombre,true);
        int y=0;//hauteur
        int x=0;//largeur
        int taille = dimm/nombre;

        //affichage grille
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
        //affichage numero de sim
        g.setColor(Color.black);//
        g.drawString("Sim n°"+String.valueOf(numeroSim), this.getDimm()+20, 20);
    }
}