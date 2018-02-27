/**
 * La classe liste est caractérisée par un maillon premier, est un nom.
 */
public class Liste {//TODO diviser en plusieurs class, Liste devrais etre generique.
    protected Maillon premier;
    private String nom;

    /**
     *
     * @param nom le nouveau nom de la liste
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Classe interne Maillon caractérisée par une cellule et un maillon.
     */
    class Maillon {//Classe interne
        Cellule info; /*Information d'une donnée*/
        Maillon suiv; /*Information vers la donnée suivante*/

        /* Constructeur de la classe Maillon*/

        /**
         * Constructeur Maillon
         * @param i la cellule du Maillon
         * @param s le maillon suivant
         */
        Maillon(Cellule i, Maillon s) {
            info = i;
            suiv = s;
        }

        @Override
        public String toString() {
            return "maillon composé de la cellule[ " + this.info.toString() + "]";
        }

       /* public boolean equals(Maillon m) {
            return (m.info==this.info&&m.suiv==this.suiv);
        }*/
    }

    //CONSTRUCTEURS

    /**
     * Constructeur de Liste sans paramètres
     *
     * Le premier maillon prend la valeur nulle
     */
    Liste() {
        this.premier = null;
    }

    /**
     * Constructeur de Liste avec paramètre Liste
     *
     *Chaque maillon de la liste donnée est ajouté à Liste.
     *
     * @param liste La nouvelle Liste
     */
    Liste(Liste liste) {//verifié
        this.nom=liste.nom;
        for (Maillon p = liste.premier; p != null; p = p.suiv) {
            this.ajouter(new Cellule(p.info.colone, p.info.ligne));
        }
    }

    //FONCTIONS USUELLES

    /**
     * Verifie si la Liste est vide
     * @return Vrai si la liste est vide, faux sinon.
     */
    private boolean vide() {//retourne si la classe est vide VÉRIFIÉ
        return premier == null;
    }

    /**
     *
     * @return le nom de la Liste
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return la taille de la liste
     */
    public int taille() {
        int i = 0;
        for (Maillon p = premier; p != null; p = p.suiv) {
            i++;
        }
        return i;
    }

    /**
     * Ajoute la cellule donnée à la Liste
     * @param cellule la cellule à ajouter
     * @return Vrai si la cellule a été ajoutée, faux sinon.
     */
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

    /**
     * Supprime la cellule donnée de la Liste.
     * @param cellule La cellule à supprimer
     * @return Vrai si la cellule a été supprimée, faux sinon.
     */
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

    /**
     * Verifie si la cellule donnée existe dans la Liste.
     * @param cellule La cellule dont l'existence est à vérifier
     * @return Vrai si la cellule existe, faux sinon.
     */
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


    public String toString() {
        String chaine = "Etat de la chaine ";
        if (this.vide()) System.out.println("VIDE");
        for (Maillon p = premier; p != null; p = p.suiv) {
            chaine = chaine + String.valueOf(p.info.toString()) + "|";
        }
        return chaine;
    }

    //AUTRES FONCTIONS

    /**
     * Parcours de la Liste afin de mettre dans un String l'état des cellules afin de générer l'affichage.
     *
     * @return le String correspondant à la carte du jeu.
     */
    private String genererAffichage(){
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
            StringBuilder stringBuilder = new StringBuilder(s);
            for (int i = lignemini-1; i <= lignemaxi+1; i++) {
                for (int j = colonemini-1; j <= colonemaxi+1; j++) {
                    if (existe(new Cellule(j, i))) stringBuilder.append("0");
                    else stringBuilder.append(".");
                }
                stringBuilder.append("\n");
            }
            s=stringBuilder.toString();
        }
        return s;
    }

    /**
     * Affiche à l'écran la carte.
     */
    public void afficher() {
        System.out.println(genererAffichage());
    }


    /**
     * Retourne la liste des cases vides autour de la cellule donnée.
     * @param cellule La cellule dont on vérifie les voisins
     * @return La liste des cellules voisines vides de la cellule donnée
     */
    private Liste voisinsVide(Cellule cellule) {
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

    /**
     * Retourne le nombre de voisins de la cellule donnée
     * @param cellule La cellule dont on compte les cellules voisines
     * @return Le nombre de voisins de la cellule
     */
    private int nbVoisins(Cellule cellule) {//retourne le nombre de nbVoisins d'une cellule
        return 8 - voisinsVide(cellule).taille();
    }

    /**
     * Met à jour les maillons de la Liste selon les règles du jeu de la vie.
     * @return La liste mise à jour
     */
    public Liste maj() {//This est la liste que l'on renvoie
        Liste listesuivante = new Liste(this);//TODO CHANGER POUR QU'IL PRENNE LA LISTE DES REGLES
        for (Maillon p = this.premier; p != null; p = p.suiv) {//8-voisinsVide(p.info).taille() retourne le nombre de nbVoisins vivant
            Liste voisinsVide = new Liste(this.voisinsVide(p.info));

            if (nbVoisins(p.info) > 3 || nbVoisins(p.info) < 2) {//On pourrais faire des final pour ces valeurs comme ca 'est facile a changer c'est toujours mal de coder en "dur"
                //p doit mourir
                listesuivante.supprimer(p.info);
            }
            //pour tous les nbVoisins vide autour de p
            for (Maillon m = voisinsVide.premier; m != null; m = m.suiv)//m doit naitre
                if (nbVoisins(m.info) == 3) listesuivante.ajouter(m.info);
        }
        return listesuivante;
    }

    /**
     *
     * @param carte
     * @return
     */
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

    /**
     * Compare la liste à une liste donnée.
     * @param liste La liste à comparer
     * @return vrai si les listes sont égales, faux sinon.
     */
    public boolean equals(Liste liste ) {
        return liste.toString().equals(this.toString());
    }

    //MAIN NE SERT QU'AUX TEST
    public static void main(String[] args) {//
    }
}