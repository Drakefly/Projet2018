import static java.lang.Thread.sleep;

public class Simulation {
    private int duree;
    private Liste carte;

    public Simulation(int durée, String fichierlif) {
        this.duree = durée;
        this.carte = lecture(fichierlif);
        this.carte.ajouter(new Cellule(2,1 ));//Ajout a la main car lecture eciste pas encore
        this.carte.ajouter(new Cellule(3, 1));
        this.carte.ajouter(new Cellule(1, 2));
        this.carte.ajouter(new Cellule(2, 2));
        this.carte.ajouter(new Cellule(2, 3));
    }

    public Liste lecture(String fichierlif) {
        System.out.println("ta mere");
        return new Liste();
    }

    public void tourne() {
        for (int i = 1; i < duree ; i++) {
            carte=carte.maj();
            carte.afficher();
            System.out.println(i);
            try {
                sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
