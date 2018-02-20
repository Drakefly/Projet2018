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
        int i,j;
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
        List <Integer> survie = new LinkedList();
        List <Integer> naissance = new LinkedList();
        boolean premierbloc= true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichierNiveau));
            while ((ligne = br.readLine()) != null) {

                if (ligne.contains("#N")) {//Regles Normales
                    survie.add(2);
                    survie.add(3);
                    naissance.add(3);
                }

                if (ligne.contains("#R")) {//Regles Perso
                    boolean vie = true;
                    char a ;
                    for (int i = 3; i <= ligne.length(); i++) {
                        a= ligne.charAt(i);
                        if (vie) survie.add((int) a);
                        if(a=='/')vie=false;
                        if (!vie) naissance.add((int) a);
                    }
                }

                if (ligne.contains("#D")){//On affiche la ligne de commentaires
                    System.out.println(ligne);
                }

                if (ligne.contains("#P")) {//On choisi les valeurs i&j

                    if (!premierbloc) {//TODO verifier ce if  trouver une facon plus optmisée.
                        if (ligne.charAt(3)=='-') {
                            i = -ligne.charAt(4);
                            if (ligne.charAt(6) == '-') {
                                j = -ligne.charAt(7);
                            } else {
                                j = ligne.charAt(6);
                            }
                        }else {
                            i = ligne.charAt(3);
                            if (ligne.charAt(5) == '-') {
                                j = -ligne.charAt(6);
                            } else {
                                j = ligne.charAt(5);
                            }
                        }
                    }else{
                            i=0;
                            j=0;
                        }
                    premierbloc=false;
                }

//TODO Là 2 Boucles for & c'est calé
            }

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
