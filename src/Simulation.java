import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Simulation {
    private int duree;
    private Liste carte;
    private LinkedList<Integer> survie;
    private LinkedList<Integer> naissance;

    public Simulation(int durée, String fichier) {
        this.duree = durée;
        this.carte= new Liste();
        try {
            Lecture l = new Lecture();
            this.carte = l.lis(fichier);
            this.naissance = l.getNaissance();//TODO Ne fonctionnes pas pour les regles personalisées
            this.survie = l.getSurvie();
         } catch (FileFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void tourne() {
        System.out.println("Voici la carte ");
        carte.afficher();
        Detection d = new Detection();
        d.detecte(carte,duree);
        /*for (int i = 1; i < this.duree; i++) {
            carte =carte.maj();
            carte.afficher();
            System.out.println(i);
            try {
                sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }
}
