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

        @Override
        public String toString() {
            return "maillon composé de la cellule[ " + this.info.toString() + "]";
        }
    }

    //CONSTRUCTEURS
    public Liste() {
        this.premier = null;
    }

    public Liste(Liste liste) {//verifié
        for (Maillon p = liste.premier; p != null; p = p.suiv) {
            this.ajouter(new Cellule(p.info.colone, p.info.ligne));
        }
    }

    //FONCTIONS USUELLES
    public boolean vide() {//retourne si la classe est vide VÉRIFIÉ
        return premier == null;
    }

    //MAIN NE SERT QU'AU TEST
    public static void main(String[] args) {
        Liste l = new Liste();
        Scanner sc = new Scanner(System.in);
        l.ajouter(new Cellule(1, 3));
        l.ajouter(new Cellule(1, 2));
        l.ajouter(new Cellule(1, 1));
        System.out.println(l);
        System.out.println(l.maj());
    }

    public int taille() {
        int i = 0;
        for (Maillon p = premier; p != null; p = p.suiv) {
            i++;
        }
        return i;
    }

    public boolean ajouter(Cellule cellule) {//L'ajoute a la bonne place dans la chaine empeche les doublons
        //System.out.println("\n Tentative du rajout de la cellule " + cellule.toString());
        if (existe(cellule)) return false;
        if (this.vide()){
            // System.out.println("Liste  etais vide");
            this.premier = new Maillon(cellule, null);    //on ne crée le maillon a rajouter comme içi car besoin d'accès au next
            // System.out.println("Ajout du maillon");
            return true;
        } else {
            if ((this.premier.info.ligne > cellule.ligne) || (this.premier.info.ligne == cellule.ligne && this.premier.info.colone > cellule.colone)) {
                this.premier = new Maillon(cellule, this.premier); //Rajout du point si avant le premier
                // System.out.println("Rajout Réussi ");
                return true;
            }
            for (Maillon p = premier; p != null; p = p.suiv) {//Iterateur
                if (p.suiv != null) {
                    if (p.suiv.info.ligne > cellule.ligne) {
                        p.suiv = new Maillon(cellule, p.suiv); //Rajout du point si le point suivant est dans une ligne au dessus
                        //  System.out.println("Rajout Réussi ");
                        return true;
                    }
                    if (p.suiv.info.colone > cellule.colone) {
                        p.suiv = new Maillon(cellule, p.suiv); //Rajout du point si le point suivant est dans une colone plus loin
                        // System.out.println("Rajout Réussi ");
                        return true;
                    }
                }
                if (p.suiv == null) {
                    p.suiv = new Maillon(cellule, p.suiv); //Rajout du point tout à la fin
                    //System.out.print("Rajout Réussi ");
                    return true;
                }
            }
        }
        //System.out.print("Echec du Rajout");
        return false;
    }

    public boolean suprimer(Cellule cellule) {
        System.out.println("\nEssai de suppression de" + cellule);
        if (vide()) {
            System.out.println("Liste vide impossible de supprimer\n");
            return false;
        }
        if (this.premier.info.equals(cellule)) {
            System.out.println("trouvé premier ellement, suppression\n");
            this.premier = this.premier.suiv;
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

    public boolean existe(Cellule cellule) {//VERIFIÉ
        if (vide()) return false;
        if (premier.info.colone == cellule.colone && premier.info.ligne == cellule.ligne) {
            return true;
        }
        for (Maillon p = premier; p.suiv != null; p = p.suiv) {
            if (p.suiv.info.colone == cellule.colone && p.suiv.info.ligne == cellule.ligne) {
                return true;
            }
        }
        return false;
    }

    //AUTRES FONCTIONS

    public String toString() {//VERIFIÉ
        String chaine = "Etat de la chaine ";
        if (this.vide()) System.out.println("VIDE");
        for (Maillon p = premier; p != null; p = p.suiv) {
            chaine = chaine + String.valueOf(p.info.toString()) + "|";
        }
        return chaine;
    }

    public Liste voisinsVide(Cellule cellule) {//TODO NON VERIFIÉ

        /*
        Retourne la liste des cases vides autour de la cellule
        Ca sera la liste des cellules a verifier pour voir si elles doivent naitre
        Et en utilisant size sur cette methode on peut savoir si la cellule envoyé en parametre doit mourir
        */

        Liste l = new Liste();
        int ligne = cellule.ligne;//Sert juste a rendre le reste un peu plus clair
        int colone = cellule.colone;
        //Tous ces new c'est barbare et ca va violer la ram
        if (!existe(new Cellule(colone + 1, ligne + 1))) l.ajouter(new Cellule(colone + 1, ligne + 1));
        if (!existe(new Cellule(colone + 1, ligne))) l.ajouter(new Cellule(colone + 1, ligne));
        if (!existe(new Cellule(colone + 1, ligne - 1))) l.ajouter(new Cellule(colone + 1, ligne - 1));
        if (!existe(new Cellule(colone, ligne + 1))) l.ajouter(new Cellule(colone, ligne + 1));
        if (!existe(new Cellule(colone, ligne - 1))) l.ajouter(new Cellule(colone, ligne - 1));
        if (!existe(new Cellule(colone - 1, ligne + 1))) l.ajouter(new Cellule(colone - 1, ligne + 1));
        if (!existe(new Cellule(colone - 1, ligne))) l.ajouter(new Cellule(colone - 1, ligne));
        if (!existe(new Cellule(colone - 1, ligne - 1))) l.ajouter(new Cellule(colone - 1, ligne - 1));
        return l;
    }

    public int voisins(Cellule cellule) {
        return 8 - voisinsVide(cellule).taille();
    }

    public Liste maj() {//This est la liste que l'on renvoie
        System.out.println("tentative de maj");
        Liste listesuivante = new Liste(this);
        System.out.println("Liste initailisée" + listesuivante);
        for (Maillon p = this.premier; p != null; p = p.suiv) {//8-voisinsVide(p.info).taille() retourne le nombre de voisins vivant
            System.out.println("analyse de" + p.toString() + "doit'il mourir?");
            System.out.println("il a " + voisins(p.info) + "voisins");
            if (voisins(p.info) > 3 || voisins(p.info) < 2) {//On pourrais faire des final pour ces valeurs comme ca 'est facile a changer c'est toujours mal de coder en "dur"
                //p doit mourir
                System.out.println("Oui" + p.info);
                listesuivante.suprimer(p.info);//Ne fonctionne pas
            } else {
                System.out.println("non");
            }
            Liste voisinsVide = new Liste(this.voisinsVide(p.info));
            for (Maillon m = voisinsVide.premier; m != null; m = m.suiv) {//pour tous les voisins vide autour de p
                System.out.println("Estce que la cellule" + m + "doit naitre?");
                if (voisins(m.info) == 3) {//m doit naitre
                    listesuivante.ajouter(m.info);
                    System.out.println("oui" + m.info);
                }
            }
        }
        return listesuivante;
    }
}