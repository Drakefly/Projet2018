package Modele;

import Controleur.FileFormatException;
import Vue.AffichageBD;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Modele.Lecture est la classe permettant de lire les fichiers lif & les dossiers contenant des lifs
 */
     class Lecture {
    final private transient LinkedList<Integer> survie = new LinkedList<>();//je suis con ca devrais aller dans liste ca.
    final private transient LinkedList<Integer> naissance = new LinkedList<>();

    /**
     * Lit les doss et fait une liste chaine de tout les fichiers .lif a l'interieur
     *
     * @param doss le doss à lire
     * @return la liste des fichiers
     */
     static LinkedList<String> lisDoss(String doss) {
        LinkedList<String> fichiers = new LinkedList<>();//Cherchez pas a comprendre ca marche. Je penses je pourrais meme pas vous le rexpliquer
        DirectoryStream<Path> h;
        try {
            h = Files.newDirectoryStream(Paths.get(doss), path -> path.toString().endsWith(".LIF"));//La fleche nouveauté java 8 ;)
            for (Path path : h) {//Le foreach bien trop peu uttilisé </3
                fichiers.add(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fichiers;
    }

    /**
     * @return la liste survie
     */
     LinkedList<Integer> getSurvie() {
        return survie;
    }

    /**
     * @return la liste naissance
     */
     LinkedList<Integer> getNaissance() {
        return naissance;
    }

    /**
     * Lit le fichier lif donné en paramètre pour en faire une carte
     *
     * @param fichier le String contenant le chemin du fichier
     * @return Un objet Modele.Liste correspondant à la carte
     * @throws FileFormatException   si le fichier n'est pas du format .lif
     * @throws FileNotFoundException si le fichier n'est pas trouvé
     */
     Liste lis(String fichier) throws FileFormatException, FileNotFoundException {
        boolean reglesDefinies = false;
        int i, j;
        i = j = 0;
        StringTokenizer str = new StringTokenizer(fichier, ".");
        String extension, ligne;
        extension = " ";
        while (str.hasMoreElements()) {//On regarde le dernier élement du nom du fichier
            extension = str.nextToken();
        }
        if (!(extension.equals("lif") || extension.equals("LIF")))
            throw new FileFormatException();//Si l'extension n'est pas lif on retourne une exception.
        Liste retour ;
        try {
            File fichierNiveau = new File(fichier);
            BufferedReader br = new BufferedReader(new FileReader(fichierNiveau));
            retour = new Liste(fichier);
            while ((ligne = br.readLine()) != null) {
                if (ligne.startsWith("#R")) {
                    reglesDefinies = true;
                    boolean vie = true;
                    char a;
                    for (int x = 3; x < ligne.length(); x++) {
                        a = ligne.charAt(x);
                        if (a == '/') vie = false;
                        if (vie) this.survie.add(Character.getNumericValue(a));
                        if (!vie && (a != '/')) this.naissance.add(Character.getNumericValue(a));
                    }
                }

                if (ligne.startsWith("#D")) {//On affiche la ligne de commentaires
                    System.out.println(ligne.substring(2));
                }

                if (ligne.startsWith("#P")) {//On choisi les valeurs i&j
                    String[] tokens = ligne.split(" ");
                    i = Integer.parseInt(tokens[1]);
                    j = Integer.parseInt(tokens[2]);
                }

                if (!ligne.contains("#") && (ligne.contains("*") || ligne.contains("."))) {//J'espere que ligne contains n'est pas lourd
                    for (int z = 0; z < ligne.length(); z++) {//Pour chaques caracteres on chek
                        if (ligne.charAt(z) == '*') {
                            retour.ajouter(new Cellule(i + z, j));
                        }
                    }
                    j++;
                }
            }
            if (!reglesDefinies) {
                this.survie.add(2);
                this.survie.add(3);
                this.naissance.add(3);
            }
        } catch (IOException e1) {
            AffichageBD.error("Fichier " + fichier + "ne peut etre lu, vous pouvez essayer de lancer le programme sans arguments pour choisir le fichiers vous meme par la suite.");
            throw new FileNotFoundException("Désolé ce fichier ne peut etre lu, vous pouvez essayer de lancer le programme sans arguments pour choisir le fichiers vous meme par la suite");
        }
        return retour;
    }
}
