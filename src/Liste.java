public class Liste {//TODO diviser en plusieurs class, Liste devrais etre generique.
    protected Maillon premier; //Pourquoi ne pas rajouter un Index?

    class Maillon {//Classe interne
        Cellule info; /*Information d'une donnée*/
        Maillon suiv; /*Information vers la donnée suivante*/

        /* Constructeur de la classe Maillon*/
        Maillon(Cellule i, Maillon s) {
            info = i;
            suiv = s;
        }

        @Override
        public String toString() {
            return "maillon composé de la cellule[ " + this.info.toString() + "]";
        }

        public boolean equals(Maillon m) {
            return (m.info==this.info&&m.suiv==this.suiv);
        }
    }

    //CONSTRUCTEURS
    public Liste() {
        this.premier = null;
    }

    public Liste(Liste liste) {//verifié
        for (Maillon p = liste.premier; p != null; p = p.suiv) {
            this.ajouter(new Cellule(p.info.colone, p.info.ligne));
        }
    }

    //FONCTIONS USUELLES
    public boolean vide() {//retourne si la classe est vide VÉRIFIÉ
        return premier == null;
    }

    public int taille() {
        int i = 0;
        for (Maillon p = premier; p != null; p = p.suiv) {
            i++;
        }
        return i;
    }

    public boolean ajouter(Cellule cellule) {//L'ajoute a la bonne place dans la chaine empeche les doublons
        if (existe(cellule)) return false;
        if (this.vide()){
            this.premier = new Maillon(cellule, null);    //on ne crée le maillon a rajouter comme içi car besoin d'accès au next
            return true;
        } else {
            if (this.premier.info.compareTo(cellule)>0) {//J'ai simplifié avec compareTo que j'ai créé dans Cellule
                this.premier = new Maillon(cellule, this.premier); //Rajout du point si avant le premier
                return true;
            }
            for (Maillon p = premier; p != null; p = p.suiv) {//Iterateur
                if (p.suiv != null) {
                    if (p.suiv.info.compareTo(cellule)>0){
                        p.suiv = new Maillon(cellule, p.suiv);
                        return true;
                    }
                }
                if (p.suiv == null) {
                    p.suiv = new Maillon(cellule, p.suiv); //Rajout du point tout à la fin
                    return true;
                }
            }
        }
        return false;
    }

    private boolean supprimer(Cellule cellule) {
        if (vide()) {
            return false;
        }
        if (this.premier.info.equals(cellule)) {
            this.premier = this.premier.suiv;
            return true;
        } else {
            for (Maillon p = premier; p.suiv != null; p = p.suiv) {//Iterateur
                if (p.suiv != null) {//TODO toujours true ? Alors pourquoi c'est la ?
                    if (p.suiv.info.ligne == cellule.ligne && p.suiv.info.colone == cellule.colone) {
                        if (p.suiv.suiv != null) {
                            p.suiv = p.suiv.suiv;
                            return true;
                        } else {
                            p.suiv = null;
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("non trouvé suppression impossible\n");
        return false;
    }

    private boolean existe(Cellule cellule) {//VERIFIÉ
        if (vide()) return false;
        if (premier.info.colone == cellule.colone && premier.info.ligne == cellule.ligne) {
            return true;
        }
        for (Maillon p = premier; p.suiv != null; p = p.suiv) {
            if (p.suiv.info.colone == cellule.colone && p.suiv.info.ligne == cellule.ligne) {
                return true;
            }
        }
        return false;
    }

    public String toString() {//VERIFIÉ
        String chaine = "Etat de la chaine ";
        if (this.vide()) System.out.println("VIDE");
        for (Maillon p = premier; p != null; p = p.suiv) {
            chaine = chaine + String.valueOf(p.info.toString()) + "|";
        }
        return chaine;
    }

    public Maillon getPremier() {
        return premier;
    }

    //AUTRES FONCTIONS
    public String genererAffichage(){
        String s = "";
        if(this.taille()==0){
            s=".\n";
        }else{
            int lignemini = premier.info.ligne;
            int lignemaxi = premier.info.ligne;
            int colonemini = premier.info.colone;
            int colonemaxi = premier.info.colone;
            for (Maillon p = premier; p.suiv != null; p = p.suiv) {
                lignemaxi = p.suiv.info.ligne;
                if (p.suiv.info.colone < colonemini) colonemini = p.suiv.info.colone;
                if (p.suiv.info.colone > colonemaxi) colonemaxi = p.suiv.info.colone;
            }
            for (int i = lignemini-1; i <= lignemaxi+1; i++) {
                for (int j = colonemini-1; j <= colonemaxi+1; j++) {
                    if (existe(new Cellule(j, i))) s += "O";
                    else s += ".";
                }
                s += "\n";
            }}
        return s;
    }

    public void afficher() {
        System.out.println(genererAffichage());
    }

    public Liste voisinsVide(Cellule cellule) {
        /*
        Retourne la liste des cases vides autour de la cellule
        Ca sera la liste des cellules a verifier pour voir si elles doivent naitre
        Et en utilisant size sur cette methode on peut savoir si la cellule envoyé en parametre doit mourir
        */
        Liste l = new Liste();
        int ligne = cellule.ligne;//Sert juste a rendre le reste un peu plus clair
        int colone = cellule.colone;
        Cellule hd= new Cellule(colone + 1, ligne + 1);
        Cellule h=new Cellule(colone + 1, ligne);
        Cellule hg = new Cellule(colone + 1, ligne - 1);
        Cellule d = new Cellule(colone, ligne + 1);
        Cellule g = new Cellule(colone, ligne - 1);
        Cellule bd = new Cellule(colone - 1, ligne + 1);
        Cellule b = new Cellule(colone - 1, ligne);
        Cellule bg = new Cellule(colone - 1, ligne - 1);

        if (!existe(hd)) l.ajouter(hd);
        if (!existe(h)) l.ajouter(h);
        if (!existe(hg)) l.ajouter(hg);
        if (!existe(g)) l.ajouter(g);
        if (!existe(d)) l.ajouter(d);
        if (!existe(bg)) l.ajouter(bg);
        if (!existe(b)) l.ajouter(b);
        if (!existe(bd)) l.ajouter(bd);
        return l;
    }

    public int voisins(Cellule cellule) {//retourne le nombre de voisins d'une cellule
        return 8 - voisinsVide(cellule).taille();
    }

    public Liste maj() {//This est la liste que l'on renvoie
        Liste listesuivante = new Liste(this);//TODO CHANGER POUR QU'IL PRENNE LA LISTE DES REGLES
        for (Maillon p = this.premier; p != null; p = p.suiv) {//8-voisinsVide(p.info).taille() retourne le nombre de voisins vivant
            if (voisins(p.info) > 3 || voisins(p.info) < 2) {//On pourrais faire des final pour ces valeurs comme ca 'est facile a changer c'est toujours mal de coder en "dur"
                //p doit mourir
                listesuivante.supprimer(p.info);//Ne fonctionne pas
            } else {
            }
            Liste voisinsVide = new Liste(this.voisinsVide(p.info));
            for (Maillon m = voisinsVide.premier; m != null; m = m.suiv) {//pour tous les voisins vide autour de p
                if (voisins(m.info) == 3) {//m doit naitre
                    listesuivante.ajouter(m.info);
                }else {
                }
            }
        }
        return listesuivante;
    }

    public boolean equalsDecal(Liste carte){
        /*Coup de genie vu que notre toString de
         ..**..
         .*....
         est le meme que celui de
        .......
        ........
        .......
        ....**.
        ...*...
        Il suffit de comparer les To string ouais GG a moi meme popur ca !
    */
        return this.genererAffichage().equals(carte.genererAffichage());
    }


    public boolean equals(Liste liste ) {//TODO vérifier ca
        return liste.toString().equals(this.toString());
    }

    //MAIN NE SERT QU'AUX TEST
    public static void main(String[] args) {//
    }
}