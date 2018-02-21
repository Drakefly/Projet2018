public class Detection {
    public Structures detect(Liste carte, int duree){//Ca changera la carte
        Liste carte2 = new Liste(carte);
        carte2=carte.maj();
        for (int i = 0; i < duree/2 ; i++) {
            if (carte2.taille()==0)return Structures.MORT;
            if (carte2.equals(carte)) return Structures.CLIGNO;
            if (carte2.equalsDecal(carte))return  Structures.VAISSEAU;
            carte=carte.maj();
            carte2=carte2.maj().maj();
        }
        return Structures.MORT;
    }
}