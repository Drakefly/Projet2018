package Modele;

import java.util.LinkedList;

/**
 * La classe Modele.Detection permet de detecter la configuration qu'atteindra la carte
 */
class Detection {//TODO BUG la queue vaut toujours 0

    /**
     * Detecte la configuration qu'atteindra la carte donnee, pour un temps donne.
     * Les booleens afficher et html en parametre, permettent respectivement de savoir si la carte doit être affichee et
     * si la configuration finale doit être retournee en html ou non.
     *
     * @param carte La carte du jeu qui est l'objet de la simulation
     * @param duree La duree de la simulation
     * @param html  Si le retour est en html
     * @return La configuration qu'atteint le jeu
     */
    String detecte(Liste<Cellule> carte, int duree, boolean html, LinkedList<Integer> survie, LinkedList<Integer> naissance) {//Bah la c'est simple et propre.
        Liste<Cellule> carte2 = carte.maj(survie, naissance);
        Liste<Cellule> carte3 = new Liste<>(carte);
        String rhtml = "";
        if (html) {//Car le format n'est pas le meme sous MacOS et Windows
            String[] tokens = carte.getNom().split("/");
            if (tokens.length == 1) tokens = carte.getNom().split("\\\\");
            rhtml = "<h2>" + tokens[1] + "</h2>\n";
        }
        for (int i = 0; i < duree / 2; i++) {

            System.out.println("\n\n\n\n\n\n" + (int) ((double) i / (double) duree * 200) + "%");//Affiche le pourcentage

            if (carte2.taille() == 0) {//Rapide mais on s'est pas quand ca meurt.
                if (html) return rhtml + "<p style=\"color: red;\">Il s'agit d'une mort😵 </p>\n";
                return "Il s'agit d'une mort😵\n";
            }

            if (carte.equals(carte.maj(survie, naissance))) {
                if (html) return rhtml + "<p style=\"color: blue;\">Il s'agit d'une structure stable </p>\n";
                return "Il s'agit d'une structure stable \n";
            }
            if (carte2.equals(carte)) {
                int queue = 0;
                for (int j = 0; j < i; j++) {
                    carte3.maj(survie, naissance);
                    if (carte3.equals(carte)) {
                        queue = j;
                        break;
                    }
                }
                if (html)
                    return rhtml + "<p style=\"color: purple;\">Il s'agit d'un clignotant de periode " + (i + 1) + "La queue est de " + queue + "️</p>\n";
                return "Il s'agit d'un clignotant de periode " + (i + 1) + "et la queue est de" + queue;
            }

            if (carte2.equalsDecal(carte)) {
                int queue = 0;
                for (int j = 0; j < i; j++) {
                    carte3.maj(survie, naissance);
                    if (carte3.equalsDecal(carte)) {
                        queue = j;
                        break;
                    }
                }
                int x = (((carte2.getPremier()).info).ligne) - (carte.getPremier().info).ligne;
                int y = (((carte2.getPremier()).info).colone) - (((carte.getPremier()).info).colone);
                if (html)
                    return rhtml + "<p style=\"color: navy;\">Il s'agit d'un vaisseau de periode " + (i + 1) + " 🚀 De deplacement x " + x + " et y " + y + ". Dont la queue est de de " + queue + "</p>\n";
                return "Il s'agit d'un vaisseau de periode " + (i + 1) + " \uD83D\uDE80 \n De deplacement x" + x + " et y" + y + " La queue est de " + queue;
            }

            carte = carte.maj(survie, naissance);
            carte2 = carte2.maj(survie, naissance);
            carte2 = carte2.maj(survie, naissance);

        }
        if (html) return rhtml + "<p>Desole mais nous n'arrivons pas a determiner le type \uD83D\uDE30</p>\n";

        return "Désolé mais nous n'arrivons pas a determiner le type 🙏\n";
    }
}