import java.io.FileNotFoundException;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

/**
 * Simulation est la classe exécutant une simulation du jeu de la vie
 *
 * Une simulation est caractérisée par sa durée, sa carte, la
 */
public class Simulation {
    private int duree;
    private Liste carte;
    private LinkedList<Integer> survie;
    private LinkedList<Integer> naissance;

    /**
     * Constructeur Simulation
     *
     * @param durée La durée de la simulation
     * @param fichier Le fichier qui est l'objet de cette simulation
     * @param b Renseigne si le chemin donné pour le fichier est complet ou non
     */
    public Simulation(int durée, String fichier,Boolean b) {
        this.duree = durée;
        this.carte= new Liste();
        try {
            Lecture l = new Lecture();
            this.carte = l.lis(fichier,b);
            this.naissance = l.getNaissance();//TODO Ne fonctionnes pas pour les regles personalisées
            this.survie = l.getSurvie();
         } catch (FileFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
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
        return d.detecte(carte,duree,false,html);
    }

    /**
     * Methode tourne qui fait avancer la simulation autant de fois que la durée donnée le demande.
     * A chaque tour elle affiche l'évolution de la carte.
     */
    public void tourne() {
        System.out.println("Voici la carte ");
        carte.afficher();
        for (int i = 1; i < this.duree; i++) {
            carte =carte.maj();
            carte.afficher();
            System.out.println(i);
            try {
                sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
