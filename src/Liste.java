import java.util.Scanner;

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
    }

    public Liste() {
        this.premier = null;
    }

    //FONCTIONS USUELLES
    public boolean vide() {//retourne si la classe est vide
        return premier == null;
    }

    public void ajouter(Cellule cellule) {//L'ajoute a la bonne place dans la chaine
        System.out.println("hey");
        Maillon maillon = new Maillon(cellule, null);
        if (this.vide()){
            System.out.println("liste vide");
            this.premier=maillon;
            System.out.println("Ajout du maillon");;
        }
        for (Maillon p = premier; p != null; p = p.suiv) {//Iterateur
            if (p.suiv != null) {//TODO Verifier je suis pas sur de ce que j'ai ecris là
                if (p.suiv.info.ligne > cellule.ligne) {
                    if (p.suiv.info.colone > cellule.colone) {
                        maillon.suiv = p.suiv;
                        p.suiv = maillon;
                    }
                }
            }
        }
    }//MARQUEUR ROUGE PARCE QUE JE NE SUIS  PAS SUR DE MOI GROS RISQUE DE BUG

    public void suprimer(Cellule cellule) throws Exception {
        if (vide()) {
            throw new Exception("Liste vide");
        }
        for (Maillon p = premier; p != null; p = p.suiv) {
            if (p.suiv.info == cellule) {
                p.suiv = p.suiv.suiv;
            }
        }
    }

    public String toString() {
        String chaine = "Voici la chaine ";
        for (Maillon p = premier; p != null; p = p.suiv) {
            chaine = chaine + String.valueOf(p.info.toString()) + "|";
        }
        return chaine;
    }

    public Liste voisins(Cellule cellule) {

        /*Recupere la liste des voisins en utilisation couplée avec size on saura si on doit tuer la cellules
        C'est bcp moins clair a dire que dans ma tete mais:
        je penses que ca serais encore plus inteligent que cette fonction retourne la liste des cases vides
        comme ca avec size et une soustraction par 9 on pourra savoir si la cellule envoyée en parametre doit mourir

        Puis chaques cellules de cette liste rendue on pourra voire si elle doit naitre, l'analiser.
        et pour l'analiser on pourra encore une fois uttiliser cette meme methode avec size.
        */

        Liste l = new Liste();
        if (!existe(cellule)) return l;
        int ligne = cellule.ligne;//Sert juste a rendre le reste un peu plus clair
        int colone = cellule.colone;
        //Tous ces new c'est barbare et ca va violer la ram
        if (existe(new Cellule(colone + 1, ligne + 1))) l.ajouter(new Cellule(colone + 1, ligne + 1));
        if (existe(new Cellule(colone + 1, ligne))) l.ajouter(new Cellule(colone + 1, ligne));
        if (existe(new Cellule(colone + 1, ligne - 1))) l.ajouter(new Cellule(colone + 1, ligne - 1));
        if (existe(new Cellule(colone, ligne + 1))) l.ajouter(new Cellule(colone, ligne + 1));
        if (existe(new Cellule(colone, ligne - 1))) l.ajouter(new Cellule(colone, ligne - 1));
        if (existe(new Cellule(colone - 1, ligne + 1))) l.ajouter(new Cellule(colone - 1, ligne + 1));
        if (existe(new Cellule(colone - 1, ligne))) l.ajouter(new Cellule(colone - 1, ligne));
        if (existe(new Cellule(colone - 1, ligne - 1))) l.ajouter(new Cellule(colone - 1, ligne - 1));
        return l;
    }

    public boolean existe(Cellule cellule) {
        if (vide()) return false;
        for (Maillon p = premier; p != null; p = p.suiv) {
            if (p.suiv.info == cellule) {
                return true;
            }
        }
        return false;
    }

    public int taille() {
        int i = 0;
        for (Maillon p = premier; p != null; p = p.suiv) {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        Liste l = new Liste();
        Scanner sc = new Scanner(System.in);
        int c= sc.nextInt();
        int ligne = sc.nextInt();
        Cellule cellule = new Cellule(c,ligne);
        System.out.println(cellule);
        l.ajouter(cellule);
        l.ajouter(new Cellule(7,5));
        System.out.println(l.toString());
    }

}