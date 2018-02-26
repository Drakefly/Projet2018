/**
 *
 */
public class Cellule {
    int colone;
    int ligne;

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

    public boolean equals(Cellule cellule) {
        return this.ligne == cellule.ligne && this.colone == cellule.colone;
    }
    public int compareTo(Cellule cellule){//0 si c'est egal, inferieur a 0 quand le param est plus petit que this.
        if(this.ligne == cellule.ligne && this.colone == cellule.colone)return 0; //equals
        if (this.ligne<cellule.ligne){//this est plus petit
            return-1;
        }else if(this.ligne==cellule.ligne){//meme ligne on verifie les colones
            if(this.colone<cellule.colone)return -1;//this est plus petit
        }
      return 1;//dans tout les autres cas this est plus grand
    }
}
