package Modele;

import Controleur.FileFormatException;
import Vue.AffichageBD;
import Vue.Fenetre;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

/**
 * Modele.Simulation est la classe exécutant une simulation du jeu de la vie
 * <p>
 * Une simulation est caractérisée par sa durée, sa carte, ses regles.
 */
public class Simulation {
    public String fichier;
    final private int duree;
    private transient Liste carte;
    private transient LinkedList<Integer> survie;
    private transient LinkedList<Integer> naissance;
    public boolean gui;

    /**
     * Constructeur Modele.Simulation
     *
     * @param duree   La duree de la simulation
     * @param fichier Le fichier qui est l'objet de cette simulation
     */
    public Simulation(int duree, String fichier) {
        this.duree = duree;
        this.carte = new Liste();
        try {
            Lecture l = new Lecture();
            this.fichier = fichier;
            this.carte = l.lis(fichier);
            this.naissance = l.getNaissance();
            this.survie = l.getSurvie();
        } catch (FileFormatException | FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Constructeur de Modele.Simu dans le cas ou l'uttilisateur na pas rentré au debut de fichier
     */
    public Simulation() {
        this.duree = 500;
        this.carte = new Liste();
        try {
            Lecture l = new Lecture();
            this.fichier = String.valueOf(AffichageBD.selectFichier());
            this.carte = l.lis(fichier);
            this.naissance = l.getNaissance();
            this.survie = l.getSurvie();
        } catch (FileFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }
     }

    /**
     * Renvoie la configuration finale de la simulation de la carte pour la durée donnée.
     *
     * @param html Si le renvoie se fait en html
     * @return La configuration de notre Modele.Simulation.
     */
    public String detect(boolean html) {//lol ce genre de methode qui serve pas a grand chose stp on dirait un controleur
        Detection d = new Detection();
        return d.detecte(carte, duree, html, survie, naissance);
    }

    /**
     * Methode tourne qui fait avancer la simulation autant de fois que la durée donnée le demande.
     * A chaque tour elle affiche l'évolution de la carte.
     */
    public void tourne() {
        Fenetre fenetre = new Fenetre();
        long start = System.currentTimeMillis();
        if (gui) {
            fenetre.setVisible(true);
        } else {
            System.out.println("Voici la carte ");
            carte.afficher();
        }

        for (int i = 1; i < 2000; i++) {
            carte = carte.maj(survie, naissance);
            if (carte.vide()) {
                System.out.println("Deces de la totalité des cellules");
                AffichageBD.information("Deces de la totalité des cellules");
                break;
            }

            if (gui) {
                fenetre.go(carte, i);

            } else {
                //carte.afficher();
                System.out.println(i);
            }
//            try {
//                sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        long end = System.currentTimeMillis();
        System.out.println(Math.round(end-start));

    }

    /**
     * Lance une simulation sphérique
     * @param hauteur coordonnes de la carte
     * @param largeur coordonnes de la carte
     * @param originex coordonnes de la carte
     * @param originey coordonnes de la carte
     */
     public void simuSpherique(int hauteur, int largeur, int originex, int originey) {
        carte.afficher(originex, originey, originex + largeur, originey + hauteur);
        for (int tour = 1; tour < this.duree; tour++) {
            System.out.println("Tour n°" + tour);
            Liste[] tabL = carte.getLine(largeur, hauteur, originex, originey);
            for (int i = 0; i <= 3; i++) {
                carte.fusion(tabL[i]);
            }
            carte = carte.maj(survie, naissance);
            carte = carte.supprimerHorsLimite(hauteur, largeur, originex, originey);
            carte.afficher(originex, originey, originex + largeur, originey + hauteur);
            try {
                sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*carte=carte.supprimerHorsLimite(hauteur,largeur,originex,originey);
        Modele.Liste lNord;
        Modele.Liste lSud;
        Modele.Liste lEst;
        Modele.Liste lOuest;
        for (int i = 1; i < this.duree; i++) {
            lNord = carte.getLigne(1);
            lSud = carte.getLigne(2);
            lEst = carte.getLigne(3);
            lOuest = carte.getLigne(4);
            carte.fusion(lNord);
            carte.fusion(lSud);
            carte.fusion(lOuest);
            carte.fusion(lEst);
            carte = carte.maj(survie, naissance);
            carte = carte.supprimerHorsLimite(hauteur, largeur, originex, originey);
            carte.afficher(originex, originey, originex + largeur, originey + hauteur);
            System.out.println(i);
            try {
                sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

    /**
     * Lance une simulation limitée
     * @param hauteur coordonnes de la carte
     * @param largeur coordonnes de la carte
     * @param originex coordonnes de la carte
     * @param originey coordonnes de la carte
     */
     public void simulation(int hauteur, int largeur, int originex, int originey) {//Il y a moyen d'alleger le code et de bcp.
        carte = carte.supprimerHorsLimite(hauteur, largeur, originex, originey);
        new Liste().afficher();
        carte.afficher(originex, originey, originex + largeur, originey + hauteur);
        if (this.carte.vide()) {
            System.out.println("carte vide.");//z
        }
        System.out.println("Voici la carte ");
        carte.afficher(originex, originey, originex + largeur, originey + hauteur);
        for (int i = 1; i < this.duree; i++) {
            carte = carte.maj(survie, naissance);
            carte = carte.supprimerHorsLimite(hauteur, largeur, originex, originey);
            carte.afficher(originex, originey, originex + largeur, originey + hauteur);
            System.out.println(i);
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
