import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class Cellule implements Comparable {
    int colone;
    int ligne;

    /**
     * Constructeur à paramètres
     * @param colone
     * @param ligne
     */
    public Cellule(int colone, int ligne) {
        this.colone = colone;
        this.ligne = ligne;
    }

    @Override
    public String toString() {
        return "(" + ligne +        //affichage plus concentré sous forme de coordonnées d'une repère orthonormé
                ";" + colone +
                ')';
    }

    /**
     *Compare la cellule à une cellule donnée
     * @param cellule la cellule à comparer
     * @return vrai si les cellules ont les mêmes attributs, faux sinon;
     */
    public boolean equals(Cellule cellule) {
        return this.ligne == cellule.ligne && this.colone == cellule.colone;
    }


    /*public int compareTo(Cellule cellule){//0 si c'est egal, inferieur a 0 quand le param est plus petit que this.
        if(this.ligne == cellule.ligne && this.colone == cellule.colone)return 0; //equals
        if (this.ligne<cellule.ligne){//this est plus petit
            return-1;
        }else if(this.ligne==cellule.ligne){//meme ligne on verifie les colones
            if(this.colone<cellule.colone)return -1;//this est plus petit
        }
      return 1;//dans tout les autres cas this est plus grand
    }*/

    /**
     * Compare la cellule à unun objet donné (cellule normalement)
     *
     * @param o l'objet cellule à comparer à la cellule
     * @return 0 si les cellules ont les mêmes pos, 1 si this est placé 'plus loin' que o, -1 si this est plus près
     */
    @Override
    public int compareTo(@NotNull Object o) {
        if (this == o)
            return 0;
        Cellule cellule = new Cellule(((Cellule) o).colone, ((Cellule) o).ligne);
        if(this.ligne == cellule.ligne && this.colone == cellule.colone)return 0; //equals
        if (this.ligne<cellule.ligne){//this est plus petit
            return-1;
        }else if(this.ligne==cellule.ligne){//meme ligne on verifie les colones
            if(this.colone<cellule.colone)return -1;//this est plus petit
        }
        return 1;//dans tout les autres cas this est plus grand
    }
}
