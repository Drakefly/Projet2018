public class Detection {
    public String detecte(Liste carte, int duree,boolean afficher) {//Bah la c'est simple et propre.
        Liste carte2 = new Liste(carte);
        carte2 = carte.maj();
        if (afficher) carte2.afficher();
        for (int i = 0; i < duree / 2; i++) {

            if (afficher) System.out.println(i);

            if (carte2.taille() == 0) {
                return "Mort";//Rapide mais on s'est pas quand ca meurt.
            }

            if (carte.equals(carte.maj())) {
                return "Stable";
            }

            if (carte2.equals(carte)) {
                return "Cligno\nPeriode " + (i + 1);
            }

            if (carte2.equalsDecal(carte)) {
                return "Vaisseau\nPeriode " + (i + 1);
            }

            carte = carte.maj();
            carte2 = carte2.maj();
            if (afficher) carte2.afficher();
            carte2 = carte2.maj();
            if (afficher) carte2.afficher();

        }
        return "RIEN TROUVÉ";
    }
}