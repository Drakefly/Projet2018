import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

public class Simulation {
    private int duree;
    private Liste carte;

    public Simulation(int durée, String fichier) {
        this.duree = durée;
        this.carte= new Liste();
        try {
            this.carte = Lecture.lis(fichier);
         } catch (FileFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
