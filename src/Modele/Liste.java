package Modele;

import java.util.LinkedList;

/**
 * @param <T> Type des maillon de la liste
 */
public class Liste<T> {
    transient private Maillon<T> premier;
    private String nom;

    //CONSTRUCTEURS
    /**
     * Constructeur de Modele.Liste sans paramètres
     * <p>
     * Le premier maillon prend la valeur nulle
     */
    public Liste() {
        this.premier = null;
    }

    /**
     * Constructeur de Modele.Liste avec paramètre Modele.Liste
     * <p>
     * Chaque maillon de la liste donnée est ajouté à Modele.Liste.
     *
     * @param liste La nouvelle Modele.Liste
     */
    public Liste(Liste<T> liste) {//verifié
        this.nom = liste.nom;
        Maillon p = liste.premier;
        while (p != null) {
            this.ajouter(new Maillon(p.info, null));
            p = p.suiv;
        }
    }


    /**
     * Setter du nom de la liste
     * @param nom nom de la liste
     */
    public Liste(String nom) {
        this.nom = nom;
        this.premier = null;

    }
    //FONCTIONS USUELLES

    /**
     * Verifie si la Modele.Liste est vide
     *
     * @return Vrai si la liste est vide, faux sinon.
     */
     boolean vide() {//retourne si la classe est vide VÉRIFIÉ
        return premier == null;
    }

    /**
     * @return le nom de la Modele.Liste
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return le premier objet de la Modele.Liste
     */
    public Maillon<T> getPremier() {
        return this.premier;
    }

    /**
     * @return la taille de la Modele.Liste
     */
     public int taille() {
        int i = 0;
        for (Maillon<T> p = premier; p != null; p = p.suiv) {
            i++;
        }
        return i;
    }

    /**
     * Ajoute la cellule donnée à la Modele.Liste
     *
     * @param o l'objet à ajouter
     */
    public void ajouter(Object o) {//L'ajoute a la bonne place dans la chaine empeche les doublons
        if (existe(o)) return;
        if (o.getClass() == Maillon.class) {
            ajouterMaillon((Maillon) o);
            return;
        }
        if (o.getClass() == Cellule.class) {
            ajouterMaillon(new Maillon(o, null));
            return;
        }
        //ni un maillon ni une cellule, on va donc ajouter un mallon avec l'info de o et un pointeur null
         ajouterMaillon(new Maillon(o, null));
     }

    /**
     * Ajoute la cellule donnée à la Modele.Liste
     *
     * @param m le maillon à ajouter
     * @return Vrai si la cellule a été ajoutée, faux sinon.
     */
    private boolean ajouterMaillon(Maillon m) {
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
     * Supprime la cellule donnée de la Modele.Liste.
     *
     * @param o L'objet à supprimer de la liste.
     */
    private void supprimer(Object o) {
        if (!existe(o))
            return;//cette syntaxe :o

        if (this.premier.info.equals(o)) {
            this.premier = this.premier.suiv;
        } else {
            for (Maillon<T> p = premier; true; p = p.suiv) {
                if (p.suiv.info.equals(o)) {
                    if (p.suiv.suiv != null) {
                        p.suiv = p.suiv.suiv;
                        return;
                    } else {
                        p.suiv = null;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Verifie si la cellule donnée existe dans la Modele.Liste.
     *
     * @param o L'objet dont l'existence est à vérifier dans la liste.
     * @return Vrai si la cellule existe, faux sinon.
     */
    private boolean existe(Object o) {
        //Une recherche dicothomique serait bien plus rapide,
        // mais impossible avec une liste simplement chainéee, il faudrais une table de hashage
        // et notre logiciel serait bien plus rapide😭
        if (vide()) return false;
        if (o.getClass() != this.premier.info.getClass()) return false;
        for (Maillon p = premier; !(p == null || ((Cellule) p.info).compareTo(o) > 0) ; p = p.suiv) {
            if (((Cellule) p.info).compareTo(o) == 0)
                return true;
        }
        return false;
    }

    /**
     *
     * @return Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder chaine = new StringBuilder("Etat de la chaine ");
        if (this.vide()) System.out.println("VIDE");
        for (Maillon<T> p = premier; p != null; p = p.suiv) {
            chaine.append(String.valueOf(p.info.toString())).append("|");
        }
        return chaine.toString();
    }

    //AUTRES FONCTIONS

    /**
     * Parcours de la Modele.Liste afin de mettre dans un String l'état des cellules afin de générer l'affichage.
     *
     * @return le String correspondant à la carte du jeu.
     */
    private String genererAffichage() {
        String s = "";
        if (this.vide()) {
            s = ".\n";
        } else {
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
            Cellule cellule= new Cellule(colonemini-1,lignemini-1);
            StringBuilder stringBuilder = new StringBuilder(s);
            for (int i = lignemini - 1; i <= lignemaxi + 1; i++) {
                for (int j = colonemini - 1; j <= colonemaxi + 1; j++) {
                    cellule.setCoordonnes(j,i);
                    if (existe(cellule)) stringBuilder.append("0");
                    else stringBuilder.append(".");
                }
                stringBuilder.append("\n");
            }
            s = stringBuilder.toString();
        }
        return s;
    }

    /**
     * Parcours de la Modele.Liste afin de mettre dans un String l'état des cellules afin de générer l'affichage borné.
     *
     * @param hgx coordonées en haut à gauche du début de l'afficahe (première ligne)
     * @param hgy coordonées en haut à gauche du début de l'afficahe (première colone)
     * @param bdx coordonées en bas à droite du début de l'afficahe (denière ligne)
     * @param bdy coordonées en bas à droite du début de l'afficahe (dernière colone)
     * @return le String correspondant à la carte du jeu
     */
    public String genererAffichage(int hgx, int hgy, int bdx, int bdy, boolean pourFenetre) {
        String s = "";
        if (this.taille() == 0) {
            s = ".\n";
        }
        StringBuilder stringbuilder = new StringBuilder(s);
        if (!pourFenetre) {
            for (int k = hgy; k < bdy + 2; k++) {
                stringbuilder.append("-");
            }
        }
        Cellule cellule= new Cellule(hgx,hgy);
        stringbuilder.append("\n");
        for (int i = hgx; i < bdx; i++) {
            if (!pourFenetre) stringbuilder.append("|");
            for (int j = hgy; j < bdy; j++) {
                cellule.setCoordonnes(j,i);
                if (existe(cellule))
                    stringbuilder.append("0");
                else
                    stringbuilder.append(".");
            }
            if (!pourFenetre) {
                stringbuilder.append("|\n");
            } else {
                stringbuilder.append("/");
            }
        }
        if (!pourFenetre) {
            for (int k = hgy; k < bdy + 2; k++) {
                stringbuilder.append("-");
            }
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
     */
    public void afficher(int hgx, int hgy, int bdx, int bdy) {
        System.out.println(genererAffichage(hgx, hgy, bdx, bdy, false));
    }

    /**
     * Retourne la liste des cases vides autour de la cellule donnée.
     *
     * @param cellule La cellule dont on vérifie les voisins
     * @return La liste des cellules voisines vides de la cellule donnée
     */
    private Liste voisinsVide(Cellule cellule) {
        Liste l = new Liste();
        int ligne = cellule.ligne;//Sert juste a rendre le reste un peu plus clair
        int colone = cellule.colone;
        Cellule hd = new Cellule(colone+1, ligne-1);
        Cellule h = new Cellule(colone, ligne-1);
        Cellule hg = new Cellule(colone-1, ligne-1);
        Cellule d = new Cellule(colone+1, ligne);
        Cellule g = new Cellule(colone-1, ligne);
        Cellule bd = new Cellule(colone+1, ligne+1);
        Cellule b = new Cellule(colone, ligne+1);
        Cellule bg = new Cellule(colone-1, ligne+1);

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
     * Retourne la liste des cases vides autour de la cellule donnée.
     *
     * @param cellule La cellule dont on vérifie les voisins
     * @param ha La hauteur du la map
     * @param la La largeur de la map
     * @param ox L'origine x de la map
     * @param oy L'origine y de la map
     * @return La liste des cellules voisines vides de la cellule donnée
     */
    private Liste voisinsVideSphe(Cellule cellule, int ha, int la, int ox, int oy) {

        Liste l = new Liste();
        int ligne = cellule.ligne, colone = cellule.colone;//Sert juste a rendre le reste un peu plus clair
        Cellule h,hd,hg,b,bd,bg,d,g;
        if (ligne==ox && colone!=oy && colone!=oy+(la-1)) {
            h = new Cellule(colone, ligne+(ha-1));
            hd = new Cellule(colone+1, ligne+(ha-1));
            hg = new Cellule(colone-1, ligne+(ha-1));//-----
            d = new Cellule(colone+1, ligne);
            g = new Cellule(colone-1, ligne);
            bd = new Cellule(colone+1, ligne+1);
            b = new Cellule(colone, ligne+1);
            bg = new Cellule(colone-1, ligne+1);

        }else if (ligne==ox && colone==oy) {
            h = new Cellule(colone, ligne+(ha-1));
            hd = new Cellule(colone+1, ligne+(ha-1));
            hg = new Cellule(colone+(la-1), ligne+(ha-1));
            g = new Cellule(colone+(la-1), ligne);//-----
            d = new Cellule(colone+1, ligne);
            bd = new Cellule(colone+1, ligne+1);
            b = new Cellule(colone, ligne+1);
            bg = new Cellule(colone+(la-1), ligne+1);

        }else if (ligne==ox && colone==oy+(la-1)) {
            h = new Cellule(colone, ligne+(ha-1));
            hd = new Cellule(colone-(la-1), ligne+(ha-1));
            hg = new Cellule(colone-1, ligne+(ha-1));
            d = new Cellule(colone-(la-1), ligne);//-----
            g = new Cellule(colone-1, ligne);
            bd = new Cellule(colone-(la-1), ligne+1);
            b = new Cellule(colone, ligne+1);
            bg = new Cellule(colone-1, ligne+1);

        }else if (ligne==ox+(ha-1) && colone!=oy && colone!=oy+(la-1)) {
            b = new Cellule(colone, ligne-(ha-1));
            bd = new Cellule(colone+1, ligne-(ha-1));
            bg = new Cellule(colone-1, ligne-(ha-1));//-----
            hd = new Cellule(colone+1, ligne-1);
            h = new Cellule(colone, ligne-1);
            hg = new Cellule(colone-1, ligne-1);
            d = new Cellule(colone+1, ligne);
            g = new Cellule(colone-1, ligne);

        }else if (ligne==ox+(ha-1) && colone==oy ) {
            b = new Cellule(colone, ligne-(ha-1));
            bd = new Cellule(colone+1, ligne-(ha-1));
            bg = new Cellule(colone+(la-1), ligne-(ha-1));
            g = new Cellule(colone+(la-1), ligne);//-----
            hd = new Cellule(colone+1, ligne-1);
            h = new Cellule(colone, ligne-1);
            hg = new Cellule(colone+(ha-1), ligne-1);
            d = new Cellule(colone+1, ligne);

        }else if (ligne==ox+(ha-1) && colone==oy+(la-1) ) {
            b = new Cellule(colone, ligne-(ha-1));
            bd = new Cellule(colone-(la-1), ligne-(ha-1));
            bg = new Cellule(colone-1, ligne-(ha-1));
            d = new Cellule(colone-(la-1), ligne);//-----
            hd = new Cellule(colone-(la-1), ligne-1);
            h = new Cellule(colone, ligne-1);
            hg = new Cellule(colone-1, ligne-1);
            g = new Cellule(colone-1, ligne);

        }else if (colone==oy && ligne!=ox && ligne!=ox+(ha-1)) {
            g = new Cellule(colone+(la-1), ligne);//-----
            hd = new Cellule(colone+1, ligne-1);
            h = new Cellule(colone, ligne-1);
            hg = new Cellule(colone+(la-1), ligne-1);
            d = new Cellule(colone+1, ligne);
            bd = new Cellule(colone+1, ligne+1);
            b = new Cellule(colone, ligne+1);
            bg = new Cellule(colone+(la-1), ligne+1);

        }else if (colone==oy+(la-1) && ligne!=ox && ligne!=ox+(ha-1)) {
            d = new Cellule(colone-(la-1), ligne);//-----
            hd = new Cellule(colone-(la-1), ligne-1);
            h = new Cellule(colone, ligne-1);
            hg = new Cellule(colone-1, ligne-1);
            g = new Cellule(colone-1, ligne);
            bd = new Cellule(colone-(la-1), ligne+1);
            b = new Cellule(colone, ligne+1);
            bg = new Cellule(colone-1, ligne+1);

        }else {
            hd = new Cellule(colone+1, ligne-1);
            h = new Cellule(colone, ligne-1);
            hg = new Cellule(colone-1, ligne-1);
            d = new Cellule(colone+1, ligne);
            g = new Cellule(colone-1, ligne);
            bd = new Cellule(colone+1, ligne+1);
            b = new Cellule(colone, ligne+1);
            bg = new Cellule(colone-1, ligne+1);
        }

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
     *
     * @param cellule La cellule dont on compte les cellules voisines
     *
     * @return Le nombre de voisins de la cellule
     */
    private int nbVoisins(Cellule cellule) {//retourne le nombre de nbVoisins d'une cellule
        return 8 - voisinsVide(cellule).taille();
    }

    /**
     * Retourne le nombre de voisins de la cellule donnée
     *
     * @param cellule La cellule dont on compte les cellules voisines
     * @param ha La hauteur du la map
     * @param la La largeur de la map
     * @param ox L'origine x de la map
     * @param oy L'origine y de la map
     *
     * @return Le nombre de voisins de la cellule
     */
    private  int nbVoisinsSphe(Cellule cellule, int ha, int la, int ox, int oy) {
        return  8 - voisinsVideSphe(cellule, ha, la, ox, oy).taille();
    }

    /**
     * Suprime tout les points hors des coordonnees donnees en parametres
     *
     * @param hauteur hauteur de la carte
     * @param largeur largeur de la carte
     * @param originex coordonnees x de l'origine
     * @param originey coordonnees y de l'originie
     *
     * @return
     */
    Liste supprimerHorsLimite(int hauteur, int largeur, int originex, int originey) {
        if (this.premier == null) return this;
        Cellule pinfo;
        for (Maillon p = this.premier; p != null; p = p.suiv) {
            pinfo = (Cellule) p.info;
            if (pinfo.colone < originex || pinfo.colone > originex + largeur || pinfo.ligne < originey || pinfo.ligne > originey + hauteur)
                this.supprimer(p.info);
        }
        return this;
    }

    /**
     * Met à jour les maillons de la Modele.Liste selon les règles du jeu de la vie.
     * Prend en paramètre la liste des règles du jeu
     *
     * @return La liste mise à jour
     */
    public Liste<Cellule> maj(LinkedList<Integer> survie, LinkedList<Integer> naissance) {//This est la liste que l'on renvoie
        Liste<Cellule> listesuivante = new Liste<>((Liste<Cellule>) this);


        for (Maillon p = this.premier; p != null; p = p.suiv) {//8-voisinsVide(p.info).taille() retourne le nombre de nbVoisins vivant
            Liste voisinsVide = new Liste(this.voisinsVide((Cellule) p.info));

            //Pour une mort
            if (!(survie.contains((nbVoisins((Cellule) p.info))))) {
                listesuivante.supprimer(p.info);
            }

            //Pour une naissance
            for (Maillon m = voisinsVide.premier; m != null; m = m.suiv) { //parcours des voisins vides de la cellule p
                if (naissance.contains(nbVoisins((Cellule) m.info))) {
                    listesuivante.ajouter(m.info);
                }

            }

        }
        return listesuivante;
    }

    /**
     * Met à jour les maillons de la Modele.Liste selon les règles du jeu de la vie.
     * Prend en paramètre la liste des règles du jeu ainsi que les dimensions de la map
     * pour simuler sur un monde sphérique
     *
     * @param survie Liste contenant les paramètres pour qu'une cellule survivent
     * @param survie Liste contenant les paramètres pour qu'une cellule survivent
     * @param ha La hauteur du la map
     * @param la La largeur de la map
     * @param ox L'origine x de la map
     * @param oy L'origine y de la map
     *
     * @return La liste mise à jour
     */
    public Liste<Cellule> majSphe(LinkedList<Integer> survie, LinkedList<Integer> naissance, int ha, int la, int ox, int oy) {
        Liste<Cellule> listesuivante = new Liste<>((Liste<Cellule>) this);

        for (Maillon p = this.premier; p != null; p = p.suiv) {//8-voisinsVide(p.info).taille() retourne le nombre de nbVoisins vivant
            Liste voisinsVide = new Liste(this.voisinsVideSphe((Cellule) p.info, ha, la, ox, oy));

            //Pour une mort
            if (!(survie.contains((nbVoisinsSphe((Cellule) p.info, ha, la, ox, oy))))) {
                listesuivante.supprimer(p.info);
            }

            //Pour une naissance
            for (Maillon m = voisinsVide.premier; m != null; m = m.suiv) { //parcours des voisins vides de la cellule p
                if (naissance.contains(nbVoisinsSphe((Cellule) m.info, ha, la, ox, oy))) {
                    listesuivante.ajouter(m.info);
                }

            }

        }
        return listesuivante;
    }

    /**
     * Renvoie vrai si la carte donnee en parametre et la meme que this a quelques case de différences
     * @param carte carte a verifié
     * @return vrai si la carte donnee en parametre et la meme que this a quelques case de différences
     */
    boolean equalsDecal(Liste<? extends Cellule> carte) {
        return this.genererAffichage().equals(carte.genererAffichage());
    }

    /**
     * Renvoie vraie si la liste en parametre est la meme que this
     * @param liste liste a comparer
     * @return renvoie vraie si la liste en parmetre est la meme que this
     */
    boolean equals(Liste<? extends Cellule> liste) {
        return liste.toString().equals(this.toString());
    }

    /**
     * Classe interne Maillon caractérisée par une cellule et un maillon.
     */
    class Maillon<t> {
        t info;            /*Information d'une donnée*/
        Maillon<t> suiv;    /*Information vers la donnée suivante*/

        /* Constructeur de la classe Maillon*/

        /**
         * Constructeur Maillon
         *
         * @param i la cellule du Maillon
         * @param s le maillon suivant
         */
        Maillon(t i, Maillon<t> s) {
            info = i;
            suiv = s;
        }

        @Override
        public String toString() {
            return "maillon composé de la cellule[ " + this.info.toString() + "]";
        }

    }


}