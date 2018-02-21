public class Detection {
    public void detecte(Liste carte, int duree,boolean afficher){//Bah la c'est simple et propre.
        Liste carte2 = new Liste(carte);
        boolean b = false;
        carte2=carte.maj();
        if(afficher)carte2.afficher();
        for (int i = 0; i < duree/2 ; i++) {
            if(afficher)System.out.println(i);
            if (carte2.taille()==0){//Methode rapide, mais on a pas le moment ou ca meurt
                System.out.println("Mort");
                break;
            }
            if (carte.equals(carte.maj())){
                System.out.println("Stable");
                b=true;
                break;
            }
            if (carte2.equals(carte)){
                System.out.println("Cligno\nPeriode "+(i+1));
                b=true;
                break;
            }
            if (carte2.equalsDecal(carte)){
                System.out.println("Vaisseau\nPeriode "+(i+1));
                b=true;
                break;
            }
            carte= carte.maj();
            carte2= carte2.maj();
            if(afficher)carte2.afficher();
            carte2 =carte2.maj();
            if(afficher)carte2.afficher();
        }
        if (!b)System.out.println("Rien trouvé");
    }
}