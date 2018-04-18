package Modele;

import java.util.LinkedList;

/**
 * La classe Modele.Detection permet de détecter la configuration qu'atteindra la carte
 */
 class Detection {

    /**
     * Detecte la configuration qu'atteindra la carte donnée, pour un temps donné.
     * Les booléens afficher et html en paramètre, permettent respectivement de savoir si la carte doit être affichée et
     * si la configuration finale doit être retournée en html ou non.
     *
     * @param carte La carte du jeu qui est l'objet de la simulation
     * @param duree La durée de la simulation
     * @param html  Si le retour est en html
     * @return La configuration qu'atteint le jeu
     */
     String detecte(Liste carte, int duree, boolean html, LinkedList<Integer> survie, LinkedList<Integer> naissance) {//Bah la c'est simple et propre.
         Liste carte2 = carte.maj(survie, naissance);
        String rhtml = "";
        if (html) {
            String[] tokens = carte.getNom().split("/");
            if (tokens.length == 1) tokens = carte.getNom().split("\\\\");
            rhtml = "<h2>" + tokens[1] + "</h2>\n";
        }
        for (int i = 0; i < duree / 2; i++) {

            if (true)
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
                if (html)
                    return rhtml + "<p style=\"color: purple;\">Il s'agit d'un clignotant de periode " + (i + 1) + "️</p>\n";
                return "Il s'agit d'un clignotant de periode " + (i + 1) + "\n";
            }

            if (carte2.equalsDecal(carte)) {
                int x = (((Cellule) ((Liste.Maillon) carte.getPremier()).info).ligne) - (((Cellule) ((Liste.Maillon) carte2.getPremier()).info).ligne);
                if (html)
                    return rhtml + "<p style=\"color: navy;\">Il s'agit d'un vaisseau de periode " + (i + 1) + " 🚀</p>\n";
                return "Il s'agit d'un vaisseau de periode " + (i + 1) + " \uD83D\uDE80 \n";
            }

            carte = carte.maj(survie, naissance);
            carte2 = carte2.maj(survie, naissance);
            carte2 = carte2.maj(survie, naissance);

        }
        if (html) return rhtml + "<p>Désolé mais nous n'arrivons pas a determiner le type \uD83D\uDE30</p>\n";

        return "Désolé mais nous n'arrivons pas a determiner le type\n";
    }
}