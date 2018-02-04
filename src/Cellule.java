public class Cellule {
    int colone;
    int ligne;

    public Cellule(int colone, int ligne) {
        this.colone = colone;
        this.ligne = ligne;
    }

    @Override
    public String toString() {
        return "Cellule{" +
                "colone=" + colone +
                ", ligne=" + ligne +
                '}';
    }
}
