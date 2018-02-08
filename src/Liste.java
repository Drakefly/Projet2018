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

    public static void main(String[] args) {
        Liste l = new Liste();
        Scanner sc = new Scanner(System.in);
        System.out.println(l.toString());//JUSQU'A LA TOUT VA BIEN
        l.ajouter(new Cellule(7, 5));
        System.out.println(l.toString());
        l.ajouter(new Cellule(5, 2));
        System.out.println(l);
        l.ajouter(new Cellule(2, 1));
        System.out.println(l);
        l.suprimer(new Cellule(5, 2));
        System.out.println(l);
        l.suprimer((new Cellule(7, 5)));
        System.out.println(l);
    }

    public boolean ajouter(Cellule cellule) {//L'ajoute a la bonne place dans la chaine
        System.out.println("\n Tentative du rajout de la cellule " + cellule.toString());
        if (this.vide()){
            System.out.println("Liste  etais vide");
            this.premier = new Maillon(cellule, null);    //on ne crée le maillon a rajouter comme içi car besoin d'accès au next
            System.out.println("Ajout du maillon");
            return true;
        } else {
            if ((this.premier.info.ligne > cellule.ligne) || (this.premier.info.ligne == cellule.ligne && this.premier.info.colone > cellule.colone)) {
                this.premier = new Maillon(cellule, this.premier); //Rajout du point si avant le premier
                System.out.println("Rajout Réussi ");
                return true;
            }
            for (Maillon p = premier; p != null; p = p.suiv) {//Iterateur
                if (p.suiv != null) {//TODO Verifier je suis pas sur de ce que j'ai ecris là
                    if (p.suiv.info.ligne > cellule.ligne) {
                        p.suiv = new Maillon(cellule, p.suiv); //Rajout du point si le point suivant est dans une ligne au dessus
                        System.out.println("Rajout Réussi ");
                        return true;
                    }
                    if (p.suiv.info.colone > cellule.colone) {
                        p.suiv = new Maillon(cellule, p.suiv); //Rajout du point si le point suivant est dans une colone plus loin
                        System.out.println("Rajout Réussi ");
                        return true;
                    }
                }
                if (p.suiv == null) {
                    p.suiv = new Maillon(cellule, p.suiv); //Rajout du point tout à la fin
                    System.out.print("Rajout Réussi ");
                    System.out.println("itérateur : " + p.info + "+" + p.suiv.toString());
                    return true;
                }
            }
        }
        System.out.print("Echec du Rajout");
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
        if(premier.info==cellule){
            return true;
        }
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

    public boolean suprimer(Cellule cellule) {
        System.out.println("\n Essai de suppression de" + cellule);
        if (vide()) {
            System.out.println("Liste vide impossible de supprimer\n");
            return false;
        }
        if (premier.info == cellule) {
            System.out.println("trouvé premier ellement, suppression\n");
            premier = null;
            return true;
        } else {
            for (Maillon p = premier; p.suiv != null; p = p.suiv) {//Iterateur
                if (p.suiv != null) {
                    if (p.suiv.info.ligne == cellule.ligne && p.suiv.info.colone == cellule.colone) {
                        System.out.println("Trouvé");
                        if (p.suiv.suiv != null) {
                            p.suiv = p.suiv.suiv;
                            System.out.println("Suprimmé");
                            return true;
                        } else {
                            System.out.println("Trouvé dernier element");
                            p.suiv = null;
                            System.out.println("Suprimmé");
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("non trouvé suppression impossible\n");
        return false;
    }

}