import Vue.AffichageBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

/**
 * Simulation est la classe exécutant une simulation du jeu de la vie
 *
 * Une simulation est caractérisée par sa durée, sa carte, ses regles.
 */
public class Simulation {
    private int duree;
    private Liste carte;
    private LinkedList<Integer> survie;
    private LinkedList<Integer> naissance;
    public String fichier;
    /**
     * Constructeur Simulation
     *
     * @param durée La durée de la simulation
     * @param fichier Le fichier qui est l'objet de cette simulation
     */
    public Simulation(int durée, String fichier) {
        this.duree = durée;
        this.carte= new Liste();
        try {
            Lecture l = new Lecture();
            this.fichier = fichier;
            this.carte = l.lis(fichier);
            this.naissance = l.getNaissance();
            this.survie = l.getSurvie();
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }

    public Simulation() {
        this.duree = 10000;
        this.carte= new Liste();
        try {
            Lecture l = new Lecture();
            this.fichier = String.valueOf(AffichageBD.selectFichier());
            this.carte = l.lis(fichier);
            this.naissance = l.getNaissance();
            this.survie = l.getSurvie();
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     *Renvoie la configuration finale de la simulation de la carte pour la durée donnée.
     * @param html Si le renvoie se fait en html
     * @return La configuration de notre Simulation.
     */
    public String detect(boolean html){//lol ce genre de methode qui serve pas a grand chose stp on dirait un controleur
        Detection d= new Detection();
        return d.detecte(carte,duree,html,survie,naissance);
    }

    /**
     * Methode tourne qui fait avancer la simulation autant de fois que la durée donnée le demande.
     * A chaque tour elle affiche l'évolution de la carte.
     */
    public void tourne() {
    boolean gui;
        Fenetre fenetre = new Fenetre();
        AffichageBD.BoutonListener f = new AffichageBD.BoutonListener();
        f.actionPerformed(new ActionEvent(12 ,duree,"c"));//j'ai honettement mis de la merde dans le action event parce que je sais pas ce que c'est
        gui = f.isActive();

        if(gui){
            fenetre.setVisible(true);
            fenetre.go(carte,0);
        }else {
            System.out.println("Voici la carte ");
            carte.afficher();
        }

        for (int i = 1; i < this.duree; i++) {
            carte =carte.maj(survie, naissance);
            if (carte.vide()){
                System.out.println("Deces de la totalité des cellules");
                AffichageBD.information("Deces de la totalité des cellules");
                break;
            }


            if (gui){
                fenetre.go(carte,i);

            }else{
                carte.afficher();
                System.out.println(i);
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

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
        Liste lNord;
        Liste lSud;
        Liste lEst;
        Liste lOuest;
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

    public void simulationlimité(int hauteur, int largeur, int originex, int originey){//Il y a moyen d'alleger le code et de bcp.
        carte = carte.supprimerHorsLimite(hauteur, largeur, originex, originey);
        new Liste().afficher();
        carte.afficher(originex, originey, originex + largeur, originey + hauteur);
        if(this.carte.vide()) {
            System.out.println("carte vide.");//z
        }
        System.out.println("Voici la carte ");
        carte.afficher(originex, originey, originex + largeur, originey + hauteur);
        for (int i = 1; i < this.duree; i++) {
            carte =carte.maj(survie,naissance);
            carte=carte.supprimerHorsLimite(hauteur,largeur,originex,originey);
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
