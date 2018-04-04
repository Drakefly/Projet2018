import java.util.LinkedList;

public class Liste<T> {
    protected Maillon<T> premier;
    private String nom;

    /**
     *
     * @param nom le nouveau nom de la liste
     */
    public void setNom(String nom) {
        this.nom = nom;
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
     * <p>
     * Chaque maillon de la liste donnée est ajouté à Liste.
     *
     * @param liste La nouvelle Liste
     */
    Liste(Liste<T> liste) {//verifié
        this.nom = liste.nom;
        Maillon p = liste.premier;
        while (p != null) {
            this.ajouter(new Maillon(p.info, null));
            p = p.suiv;
        }
    }

    //MAIN NE SERT QU'AUX TEST
    public static void main(String[] args) {

    }

    //FONCTIONS USUELLES

    /**
     * Verifie si la Liste est vide
     * @return Vrai si la liste est vide, faux sinon.
     */
    public boolean vide() {//retourne si la classe est vide VÉRIFIÉ
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
     * @return la taille de la Liste
     */
    public int taille() {
        int i = 0;
        for (Maillon<T> p = premier; p != null; p = p.suiv) {
            i++;
        }
        return i;
    }

    /**
     * Ajoute la cellule donnée à la Liste
     * @param o l'objet à ajouter
     * @return Vrai si la cellule a été ajoutée, faux sinon.
     */
    public boolean ajouter(Object o) {//L'ajoute a la bonne place dans la chaine empeche les doublons
        if (existe(o)) return false;
        if (o.getClass() == new Maillon(null, null).getClass())
            return ajouterMaillon((Maillon) o);
        if (o.getClass() == new Cellule(0, 0).getClass())
            return ajouterMaillon(new Maillon(o, null));
        //ni un maillon ni une cellule, on va donc ajouter un mallon avec l'info de o et un pointeur null
        return ajouterMaillon(new Maillon(o, null));
        //return false;
    }

    /**
     * Ajoute la cellule donnée à la Liste
     * @param m le maillon à ajouter
     * @return Vrai si la cellule a été ajoutée, faux sinon.
     */
    public boolean ajouterMaillon(Maillon m) {
        if (existe(m)) return false;
        if (this.vide()) {
            this.premier = m;
            return true;
        } else {
            Cellule cl = (Cellule) this.premier.info;
            if (cl.compareTo(m.info) > 0) {
                this.premier = new Maillon(m.info, this.premier); //Rajout du point si avant le premier
                return true;
            }
            for (Maillon ml = premier; ml != null; ml = ml.suiv) {
                if (ml.suiv != null) {
                    if (((Cellule) ml.suiv.info).compareTo(m.info) > 0) {
                        m.suiv = ml.suiv;
                        ml.suiv = m;
                        return true;
                    }
                } else { //suiv==null;
                    ml.suiv = m;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Supprime la cellule donnée de la Liste.
     * @param o L'objet à supprimer de la liste.
     * @return Vrai si la cellule a été supprimée, faux sinon.
     */
    private boolean supprimer(Object o) {
        if (!existe(o))
            return false;

        if (this.premier.info.equals(o)) {
            this.premier = this.premier.suiv;
            return true;
        } else {
            for (Maillon p = premier; p != null; p = p.suiv) {//Iterateur
                if (p.suiv.info.equals(o)) {
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
        System.out.println("non trouvé suppression impossible\n");
        return false;
    }

    /**
     * Verifie si la cellule donnée existe dans la Liste.
     * @param o L'objet dont l'existence est à vérifier dans la liste.
     * @return Vrai si la cellule existe, faux sinon.
     */
    private boolean existe(Object o) {//VERIFIÉ
        if (vide()) return false;

        if (o.getClass() != this.premier.info.getClass()) return false;
        for (Maillon p = premier; p != null; p = p.suiv) {
            if (((Cellule) p.info).compareTo(o) == 0)
                return true;
        }
        return false;
    }

    public void fusion(Liste liste){//Ajout tous les elems de liste dans this
        for (Maillon p = liste.premier; p != null; p = p.suiv) {
            this.ajouter(p.info);
        }
    }

    public String toString() {
        String chaine = "Etat de la chaine ";
        if (this.vide()) System.out.println("VIDE");
        for (Maillon<T> p = premier; p != null; p = p.suiv) {
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
            Cellule pinfo = new Cellule(((Cellule) premier.info).colone, ((Cellule) premier.info).ligne);
            int lignemini = pinfo.ligne;
            int lignemaxi = pinfo.ligne;
            int colonemini = pinfo.colone;
            int colonemaxi = pinfo.colone;
            for (Maillon p = premier; p.suiv != null; p = p.suiv) {
                pinfo = (Cellule) p.suiv.info;
                lignemaxi = ((Cellule) p.suiv.info).ligne;
                if (pinfo.colone < colonemini) colonemini = pinfo.colone; // pinfo = p.suiv.info déshormais.
                if (pinfo.colone > colonemaxi) colonemaxi = pinfo.colone; //
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
     * Parcours de la Liste afin de mettre dans un String l'état des cellules afin de générer l'affichage borné.
     *
     * @param hgx coordonées en haut à gauche du début de l'afficahe (première ligne)
     * @param hgy coordonées en haut à gauche du début de l'afficahe (première colone)
     * @param bdx coordonées en bas à droite du début de l'afficahe (denière ligne)
     * @param bdy coordonées en bas à droite du début de l'afficahe (dernière colone)
     * @return le String correspondant à la carte du jeu
     */
    private String genererAffichage(int hgx, int hgy, int bdx, int bdy) {
        String s = "";
        if (this.taille() == 0) {
            s = ".\n";
        }
        StringBuilder stringbuilder = new StringBuilder(s);
        for (int i = hgx; i < bdx; i++) {
            for (int j = hgy; j < bdy; j++) {
                if (existe(new Cellule(j, i)))
                    stringbuilder.append("0");
                else
                    stringbuilder.append(".");
            }
            stringbuilder.append("\n");
        }
        s = stringbuilder.toString();
        return s;
    }

    /**
     * Affiche à l'écran la carte.
     */
    public void afficher() {
        System.out.println(genererAffichage());
    }

    /**
     * Affiche à l'écran la carte avec bornes.
     *
     * @param hgx coordonées en haut à gauche du début de l'afficahe (première ligne)
     * @param hgy coordonées en haut à gauche du début de l'afficahe (première colone)
     * @param bdx coordonées en bas à droite du début de l'afficahe (denière ligne)
     * @param bdy coordonées en bas à droite du début de l'afficahe (dernière colone)
     *
     */
    public void afficher(int hgx, int hgy, int bdx, int bdy) {
        System.out.println(genererAffichage(hgx, hgy, bdx, bdy));
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
     * Retourne un tableau contenant les 4 listes des bords du la map modifiés pour être collées à leur opposé
     *
     * @param la La largeur de la map
     * @param ha La hauteur de la map
     * @param ox L'origine de la map en x (ligne)
     * @param oy L'origine de la map en y (colone)
     * @return Le tableau des listes des bords reliés
     */
    public Liste[] getLine(int la, int ha, int ox, int oy) { // TODO finir, bug lors de tp, le début marche
        Liste[] tabL = new Liste[4];
        for (int i = 0; i <= 3; i++) {
            tabL[i] = new Liste<Maillon<Cellule>>();
        }
        for (Maillon m = premier; m != null; m = m.suiv) {// on recup la ligne du haut pour aller en bas
            if (((Cellule) m.info).ligne == ox)//ligne haut
                tabL[0].ajouter(new Cellule(((Cellule) m.info).colone, ((Cellule) m.info).ligne + ha));
            if (((Cellule) m.info).colone == oy + la - 1)//colone droite
                tabL[1].ajouter(new Cellule(((Cellule) m.info).colone - la, ((Cellule) m.info).ligne));
            if (((Cellule) m.info).ligne == ox + ha - 1)//ligne bas
                tabL[2].ajouter(new Cellule(((Cellule) m.info).colone, ((Cellule) m.info).ligne - ha));
            if (((Cellule) m.info).colone == oy)//colone gauche
                tabL[3].ajouter(new Cellule(((Cellule) m.info).colone + la, ((Cellule) m.info).ligne));
        } // à ce niveau, 0 est la ligne qui va en dessous, 1 à gauche, 2 au dessus et 3 à droite
        return tabL;
    }

    public Liste supprimerHorsLimite(int hauteur, int largeur, int originex, int originey){
        if (this.premier==null) return this;
        Cellule pinfo;
        for (Maillon p = this.premier; p != null; p = p.suiv) {
            pinfo = (Cellule) p.info;
            if (pinfo.colone < originex || pinfo.colone > originex + largeur || pinfo.ligne < originey || pinfo.ligne > originey + hauteur)
                this.supprimer(p.info);
        }
        return this;
    }

    /**
     * Met à jour les maillons de la Liste selon les règles du jeu de la vie.
     * Prend en paramètre la liste des règles du jeu
     * @return La liste mise à jour
     */
    public Liste maj(LinkedList<Integer> survie,  LinkedList<Integer> naissance) {//This est la liste que l'on renvoie
        Liste listesuivante = new Liste(this);


        for (Maillon p = this.premier; p != null; p = p.suiv) {//8-voisinsVide(p.info).taille() retourne le nombre de nbVoisins vivant
            Liste voisinsVide = new Liste(this.voisinsVide((Cellule) p.info));

            //Pour une mort
            if(!(survie.contains((nbVoisins((Cellule) p.info))))){
                listesuivante.supprimer(p.info);
            }

            //Pour une naissance
            for (Maillon m = voisinsVide.premier; m != null; m = m.suiv){ //parcours des voisins vides de la cellule p
                if(naissance.contains(nbVoisins((Cellule) m.info))){
                    listesuivante.ajouter(m.info);
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

    public boolean equals(Liste liste ) {
        return liste.toString().equals(this.toString());
    }

    /**
     * Classe interne Maillon caractérisée par une cellule et un maillon.
     */
    class Maillon<T> {
        T info;            /*Information d'une donnée*/
        Maillon<T> suiv;    /*Information vers la donnée suivante*/

        /* Constructeur de la classe Maillon*/

        /**
         * Constructeur Maillon
         *
         * @param i la cellule du Maillon
         * @param s le maillon suivant
         */
        Maillon(T i, Maillon<T> s) {
            info = i;
            suiv = s;
        }

        @Override
        public String toString() {
            return "maillon composé de la cellule[ " + this.info.toString() + "]";
        }

    }

}