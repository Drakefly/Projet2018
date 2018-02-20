public class Liste {//Une liste simplement chainné est le truc le plus stupide pour ce qu'on en fait ...
    //TODO essayer l'objet tout seul avec un main dans cette classe voire si tout ce passe correctement puis debugger.
    protected Maillon premier; //Pourquoi ne pas rajouter un Index

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
        //System.out.println("\n Tentative du rajout de la cellule " + cellule.toString());
        if (existe(cellule)) return false;
        if (this.vide()){
            // System.out.println("Liste  etais vide");
            this.premier = new Maillon(cellule, null);    //on ne crée le maillon a rajouter comme içi car besoin d'accès au next
            // System.out.println("Ajout du maillon");
            return true;
        } else {
            if (this.premier.info.compareTo(cellule)>0) {//J'ai simplifié avec compareTo que j'ai créé dans Cellule
                this.premier = new Maillon(cellule, this.premier); //Rajout du point si avant le premier
                // System.out.println("Rajout Réussi ");
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
                    //System.out.print("Rajout Réussi ");
                    return true;
                }
            }
        }
        //System.out.print("Echec du Rajout");
        return false;
    }

    private boolean supprimer(Cellule cellule) {
       // System.out.println("\nEssai de suppression de" + cellule);
        if (vide()) {
        //    System.out.println("Liste vide impossible de supprimer\n");
            return false;
        }
        if (this.premier.info.equals(cellule)) {
         //   System.out.println("trouvé premier ellement, suppression\n");
            this.premier = this.premier.suiv;
            return true;
        } else {
            for (Maillon p = premier; p.suiv != null; p = p.suiv) {//Iterateur
                if (p.suiv != null) {
                    if (p.suiv.info.ligne == cellule.ligne && p.suiv.info.colone == cellule.colone) {
                       // System.out.println("Trouvé");
                        if (p.suiv.suiv != null) {
                            p.suiv = p.suiv.suiv;
                        //    System.out.println("Suprimmé");
                            return true;
                        } else {
                            p.suiv = null;
                        //    System.out.println("Suprimmé");
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("non trouvé suppression impossible\n");
        return false;
    }

    public boolean existe(Cellule cellule) {//VERIFIÉ
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
    public void afficher() {
        int lignemini = premier.info.ligne;
        int lignemaxi = premier.info.ligne;
        int colonemini = premier.info.colone;
        int colonemaxi = premier.info.colone;
        String s = "";
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
        }

        System.out.println(s);
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
        //Tous ces new c'est barbare et ca va violer la ram
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
        //System.out.println("tentative de maj");
        Liste listesuivante = new Liste(this);
       // System.out.println("Liste initailisée" + listesuivante);
        for (Maillon p = this.premier; p != null; p = p.suiv) {//8-voisinsVide(p.info).taille() retourne le nombre de voisins vivant
          //  System.out.println("analyse de" + p.toString() + "doit'il mourir?");
           // System.out.println("il a " + voisins(p.info) + "voisins");
            if (voisins(p.info) > 3 || voisins(p.info) < 2) {//On pourrais faire des final pour ces valeurs comme ca 'est facile a changer c'est toujours mal de coder en "dur"
                //p doit mourir
             //   System.out.println("Oui" + p.info);
                listesuivante.supprimer(p.info);//Ne fonctionne pas
            } else {
            //    System.out.println("non");
            }
            Liste voisinsVide = new Liste(this.voisinsVide(p.info));
            for (Maillon m = voisinsVide.premier; m != null; m = m.suiv) {//pour tous les voisins vide autour de p
               // System.out.println("Estce que la cellule " + m + "doit naitre?");
                if (voisins(m.info) == 3) {//m doit naitre
                    listesuivante.ajouter(m.info);
              //      System.out.println("oui" + m.info);
                }else {
                 //   System.out.println("non");
                }
            }
        }
        return listesuivante;
    }
    //MAIN NE SERT QU'AU TEST
    public static void main(String[] args) {//
    }
}