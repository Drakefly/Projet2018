public class Detection {
    public String detecte(Liste carte, int duree,boolean afficher,boolean html) {//Bah la c'est simple et propre.
        Liste carte2 = new Liste(carte);
        carte2 = carte.maj();
        String rhtml ="";
        if (html){
            String[] tokens = carte.getNom().split("/");
            rhtml="<h2>"+tokens[1]+"</h2>\n";
        }
        if (afficher) carte2.afficher();
        for (int i = 0; i < duree / 2; i++) {

            if (afficher) System.out.println(i);

            if (carte2.taille() == 0) {//Rapide mais on s'est pas quand ca meurt.
                if(html)return rhtml+"<p style=\"color: red;\">Il s'agit d'une mort </p>\n";
                return "Mort\n";
            }

            if (carte.equals(carte.maj())) {
                if(html)return rhtml+"<p style=\"color: blue;\">Il s'agit d'une structure stable </p>\n";
                return "Stable\n";
            }

            if (carte2.equals(carte)) {
                if(html)return rhtml+"<p style=\"color: purple;\">Il s'agit d'un clignotant de periode " + (i + 1)+" </p>\n";
                return "Cligno Periode " + (i + 1)+"\n";
            }

            if (carte2.equalsDecal(carte)) {
                if(html)return rhtml+"<p style=\"color: navy;\">Il s'agit d'un vaisseau de periode " + (i + 1)+" </p>\n";
                return "Vaisseau\nPeriode " + (i + 1)+"\n";
            }

            carte = carte.maj();
            carte2 = carte2.maj();
            if (afficher) carte2.afficher();
            carte2 = carte2.maj();
            if (afficher) carte2.afficher();

        }
        if(html)return rhtml+"<p>Le type n'a pas été trouvé.</p>\n";
        return "RIEN TROUVÉ\n";
    }
}