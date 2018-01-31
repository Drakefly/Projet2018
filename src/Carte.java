public class Carte {//C'est la liste chainée d'objets cellules

    protected Maillon premier;

    public Carte() {//Constructeur créer un liste vide.
        this.premier = null;
    }

    //FONCTIONS USUELLES
    public boolean vide() {//retourne si la classe est vide
        return premier == null;
    }

    public void ajouter(Cellule cellule) {//L'ajoute a la bonne place dans la chaine
        Maillon m = new Maillon(cellule, null);
        if (premier == null) {// Si la liste est vide le premier maillon c'est celui qui viens d'etre crée
            premier = m;
        } else {
            Maillon i = new Maillon(premier.info, premier.suiv);//Servira d'iterateur
            while (i.suiv != null) {//tant que l'itterateur n'a pas fini d'avancer
                if (i.info.ligne < m.info.ligne) {
                    i = i.suiv;
                } else {
                    if (i.info.colone < m.info.colone) {
                        i = i.suiv;
                    } else {//C'est a cette place qu'il faut faire rentrer le nv maillon
                        m.suiv = i.suiv;
                        //TODO HELP PLZ J'ai un gros doute sur comment je peux faire pointer le maillon de la vraie chaine sur le nouveau. Je me demande si on pourrais pas numeroter les maillons ?
                        // Si quelqu'un a une reponse ou un vieux tp de L1 ca serais cool ...
                    }
                }
            }
        }
    }

    public Carte miseAjour() {
        //TODO
        return new Carte();
    }

    class Maillon {//Classe interne
        Cellule info; /*Information d'une donnée*/
        Maillon suiv; /*Information vers la donnée suivante*/

        /* Constructeur de la classe Maillon*/
        Maillon(Cellule i, Maillon s) {
            info = i;
            suiv = s;
        }
    }


}
