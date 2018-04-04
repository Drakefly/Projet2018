import javax.swing.*;
import java.util.LinkedList;

/**
 * La classe Detection permet de détecter la configuration qu'atteindra la carte
 */
public class Detection {

    /**
     *Detecte la configuration qu'atteindra la carte donnée, pour un temps donné.
     *Les booléens afficher et html en paramètre, permettent respectivement de savoir si la carte doit être affichée et
     * si la configuration finale doit être retournée en html ou non.
     *
     * @param carte La carte du jeu qui est l'objet de la simulation
     * @param duree La durée de la simulation
     * @param afficher Si l'affichage de la carte est nécessaire
     * @param html Si le retour est en html
     * @return La configuration qu'atteint le jeu
     */
    public String detecte(Liste carte, int duree,boolean afficher,boolean html, LinkedList<Integer> survie,  LinkedList<Integer> naissance) {//Bah la c'est simple et propre.
        Liste carte2 = new Liste(carte);
        carte2 = carte.maj(survie,naissance);
        String rhtml ="";
        afficher=false;
        if (html) {
            String[] tokens = carte.getNom().split("/");
            if (tokens.length == 1) tokens = carte.getNom().split("\\\\");
            rhtml = "<h2>" + tokens[1] + "</h2>\n";
        }
        JOptionPane jop1 = null;

        if (afficher) carte2.afficher();
        for (int i = 0; i < duree / 2; i++) {

            if (afficher) System.out.println(i);

            if (carte2.taille() == 0) {//Rapide mais on s'est pas quand ca meurt.
                if(html)return rhtml+"<p style=\"color: red;\">Il s'agit d'une mort😵 </p>\n";
                jop1.showMessageDialog(null, "Il s'agit d'une mort😵", "Detection", JOptionPane.INFORMATION_MESSAGE);
                return "Mort\n";
            }

            if (carte.equals(carte.maj(survie,naissance))){
                if(html)return rhtml+"<p style=\"color: blue;\">Il s'agit d'une structure stable👍 </p>\n";
                jop1.showMessageDialog(null, "Il s'agit d'une structure stable", "Detection", JOptionPane.INFORMATION_MESSAGE);
                return "Stable\n";
            }

            if (carte2.equals(carte)) {
                if(html)return rhtml+"<p style=\"color: purple;\">Il s'agit d'un clignotant de periode " + (i + 1)+" ✴️</p>\n";
                jop1.showMessageDialog(null, "Il s'agit d'un clignotant de periode " + (i + 1)+ " ✴", "Detection", JOptionPane.INFORMATION_MESSAGE);
                return "Cligno Periode " + (i + 1)+"\n";
            }

            if (carte2.equalsDecal(carte)) {
//                todo int x = (Cellule)carte.premier.info.ligne - (Cellule)carte2.premier.info.ligne;
                if(html)return rhtml+"<p style=\"color: navy;\">Il s'agit d'un vaisseau de periode " + (i + 1)+" 🚀</p>\n";
                jop1.showMessageDialog(null, "Il s'agit d'un vaisseau de periode " + (i + 1)+ " \uD83D\uDE80", "Detection", JOptionPane.INFORMATION_MESSAGE);
                return "Il s'agit d'un vaisseau de periode " + (i + 1)+" \uD83D\uDE80 \n";
            }

            carte = carte.maj(survie,naissance);
            carte2 = carte2.maj(survie,naissance);
            if (afficher) carte2.afficher();
            carte2 = carte2.maj(survie,naissance);
            if (afficher) carte2.afficher();

        }
        if(html)return rhtml+"<p>Desolé mais on arrive pas a determiner le type \uD83D\uDE30</p>\n";
        jop1.showMessageDialog(null, "Desolé mais nous n'arrivons pas a determiner le type \uD83D\uDE30", "Detection", JOptionPane.INFORMATION_MESSAGE);
        return "RIEN TROUVÉ\n";
    }
}