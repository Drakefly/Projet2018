package Modele;

/**
 *Objet cellules
 */
public class Cellule implements Comparable {
    int colone;
    int ligne;

    /**
     * Constructeur à paramètres
     *
     * @param colone colone de la cellule
     * @param ligne ligne de la cellule
     */
     Cellule(int colone, int ligne) {
        this.colone = colone;
        this.ligne = ligne;
    }

    /**
     * Setter of cellule
     *
     * @param colone la colone de this
     * @param ligne la ligne de this
     */
    void setCoordonnes(int colone,int ligne) {
        this.colone = colone;
        this.ligne = ligne;
    }

    /**
     * Le ToString de la method
     *
     * @return string de la method
     */
    @Override
    public String toString() {
        return "colone"+this.colone+"ligne"+this.ligne;
    }

    /**
     * Compare la cellule à unun objet donné (cellule normalement)
     *
     * @param o l'objet cellule à comparer à la cellule
     * @return 0 si les cellules ont les mêmes pos, 1 si this est placé 'plus loin' que o, -1 si this est plus près
     */
    @Override
    public int compareTo( Object o) {
        if (this == o)
            return 0;
        Cellule cellule = new Cellule(((Cellule) o).colone, ((Cellule) o).ligne);
        if (this.ligne == cellule.ligne && this.colone == cellule.colone) return 0; //equals
        if (this.ligne < cellule.ligne) {//this est plus petit
            return -1;
        } else if (this.ligne == cellule.ligne) {//meme ligne on verifie les colones
            if (this.colone < cellule.colone) return -1;//this est plus petit
        }

        return 1;//dans tout les autres cas this est plus grand
    }
}
