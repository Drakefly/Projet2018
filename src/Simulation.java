import java.io.*;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Thread.sleep;

public class Simulation {
    private int duree;
    private Liste carte;

    public Simulation(int durée, String fichier) {
        this.duree = durée;
        this.carte= new Liste();
        try {
            this.carte = lecture(fichier);
        } catch (FileFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.carte.ajouter(new Cellule(2,1 ));//Ajout a la main car lecture eciste pas encore
        this.carte.ajouter(new Cellule(3, 1));
        this.carte.ajouter(new Cellule(1, 2));
        this.carte.ajouter(new Cellule(2, 2));
        this.carte.ajouter(new Cellule(2, 3));
    }

    public Liste lecture(String fichier) throws FileFormatException, FileNotFoundException {
        StringTokenizer str = new StringTokenizer(fichier, ".");
        String extension = "";
        while (str.hasMoreElements()) {//On regarde le dernier élement du nom du fichier
            extension = str.nextToken();
        }
        if (!extension.equals("lif")){
            throw new FileFormatException();//Si l'extension n'est pas lif on retourne une exception.
        }
        File fichierNiveau = new File(fichier);//Quesque c'est que ce bug?
        String ligne = "";
        List survie = new LinkedList();
        List naissance = new LinkedList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichierNiveau));
            while ((ligne = br.readLine()) != null) {
                if (ligne.contains("#N")) {//Dans ce cas ce n'es qu'un commentaire on sn'en fous.
                    survie.add(2);
                    survie.add(3);
                    naissance.add(3);
                }
                if (ligne.contains("#R")) {//Buff
                        //TODO Buff sur la ligne pour chaoper tous les conditions de survie et de naissance.
                }
                if (ligne.contains("#D")){//On affiche la ligne de commentaires
                    System.out.println(ligne);
                }
//TODO Là 2 Boucles for & c'est calé
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void tourne() {
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
