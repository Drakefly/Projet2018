public class Carte {//C'est la liste chainée d'objets cellules

    protected Liste cellules;

    public Carte miseAjour() {
        //TODO
        /*foreach cellules
        if nb de voisinsvides.taille est dans l'intervalle
            tuons la cellule suprimons l'element de liste+1
            foreach voisinesvides vv
                if voisinesvides.taille est dans l'intervalle
                ajouter vv
        */
        Liste cellulesToursSuiv = new Liste(cellules);//Créer se constructeur dans Liste qui fait une copie
        return new Carte();
    }

    public static void main(String[] args) {
    }
}
