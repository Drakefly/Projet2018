import java.io.FileNotFoundException;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class Simulation {
    private int duree;
    private Liste carte;
    private LinkedList<Integer> survie;
    private LinkedList<Integer> naissance;

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
    public String detect(boolean html){//lol ce genre de methode qui serve pas a grand chose stp on dirait un controleur
        Detection d= new Detection();
        return d.detecte(carte,duree,false,html);
    }

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
