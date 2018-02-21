public class Detection {
    public void detecte(Liste carte, int duree,boolean afficher){//Ca changera la carte
        Liste carte2 = new Liste(carte);
        carte2=carte.maj();
        if(afficher)carte2.afficher();
        for (int i = 0; i < duree/2 ; i++) {
            System.out.println(i);
            if (carte2.taille()==0){
                System.out.println("Mort");
                break;
            }
            if (carte2.equals(carte)){
                System.out.println("Cligno");
                break;
            }
            if (carte2.equalsDecal(carte)){
                System.out.println("Vaisseau");
                break;
            }
            carte= carte.maj();
            carte2= carte2.maj();
            if(afficher)carte2.afficher();
            carte2 =carte2.maj();
            if(afficher)carte2.afficher();
        }

    }
}