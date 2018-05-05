package Vue;

import Modele.Cellule;
import Modele.Liste;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
class Panneau extends JPanel {

    int originx;
    int originy;
    private Liste<Cellule> liste = new Liste<>();
    private transient int numeroSim;
    private int dimmension;//Dimmensions
    private int nombreCases;//nombres de cellules a afficher
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
     * @param dimmension dimmension en pixel du carre a ne pas depasser
     */
     void setDimmension(int dimmension) {
        this.dimmension = dimmension;
    }

    /**
     * getter
     * @return nombreCases de case de cotes
     */
     int getNombreCases() {
        return nombreCases;
    }

    void setTermine(boolean termine) {
        this.termine = termine;
    }

    /**
     * setter
     * @param nombreCases nombreCases de case affiche sur une ligne
     */
     void setNombreCases(int nombreCases) {
        this.nombreCases = nombreCases;
    }

    /**
     * setter
     * @param liste liste
     */
    public void setListe(Liste<Cellule> liste) {
        this.liste = liste;
    }

    /**
     * Genere le pannel
     * @param g objet grahic donne par le sustem
     */
    public void paintComponent(Graphics g) {
        // Grand rectangle blanc pour reinitialiser cette partie de la fenetre
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        String string = liste.genererAffichage(originy, originx, originy + nombreCases, originx + nombreCases, true);
        int y = 0;//hauteur
        int x = 0;//largeur
        int taille = dimmension / nombreCases;

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
        g.fillRect(dimmension -100,0,100,25);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 17));
        g.drawString("Sim n°" + String.valueOf(numeroSim), dimmension -90, 19);

        //Affichage terminé
        if(termine){
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.drawString("Terminé", dimmension /2, dimmension /2);
        }



    }
}