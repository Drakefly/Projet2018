package Vue;

import Modele.Cellule;
import Modele.Liste;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
class Panneau extends JPanel {

    private Liste<Cellule> l = new Liste<>();
    private int dimm;//Dimmensions
    private int nombre;//nombres de cellules a afficher
    private transient int numeroSim;
     int originx;
     int originy;
     private boolean termine= false;

    /**
     * setter
     * @param numeroSim numero de la simulation actuelle
     */
     void setNumeroSim(int numeroSim) {
        this.numeroSim = numeroSim;
    }

    /**
     * setter
     * @param dimm dimmension en pixel du carre a ne pas depasser
     */
     void setDimm(int dimm) {
        this.dimm = dimm;
    }

    /**
     * getter
     * @return nombre de case de cotes
     */
     int getNombre() {
        return nombre;
    }

    public void setTermine(boolean termine) {
        this.termine = termine;
    }

    /**
     * setter
     * @param nombre nombre de case affiche sur une ligne
     */
     void setNombre(int nombre) {
        this.nombre = nombre;
    }

    /**
     * setter
     * @param l liste
     */
    public void setL(Liste<Cellule> l) {
        this.l = l;
    }

    /**
     * Genere le pannel
     * @param g objet grahic donne par le sustem
     */
    public void paintComponent(Graphics g) {
        // Grand rectangle blanc pour reinitialiser cette partie de la fenetre
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        String string = l.genererAffichage(originy, originx, originy + nombre, originx + nombre, true);
        int y = 0;//hauteur
        int x = 0;//largeur
        int taille = dimm / nombre;

        //affichage grille
        for (char ch : string.toCharArray()) {
            switch (ch) {
                case '0':
                    g.setColor(Color.black);
                    g.fillRect(x * taille, y * taille, taille, taille);
                    x++;
                    break;
                case '.':
                    g.setColor(Color.red);
                    g.drawRect(x * taille, y * taille, taille, taille);
                    x++;
                    break;
                case '/':
                    y++;
                    x = 0;
                    break;
                default:
                    break;
            }
        }
        //affichage numero de sim
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(dimm-100,0,100,25);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 17));
        g.drawString("Sim n°" + String.valueOf(numeroSim), dimm -90, 19);

        //Affichage terminé
        if(termine){
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.drawString("Terminé", dimm /2, dimm/2);
        }



    }
}