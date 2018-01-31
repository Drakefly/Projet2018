public class Simulation {
    private int duree;
    private Carte carte;

    public Simulation(int durée, String fichierlif) {
        this.duree = durée;
        this.carte = lecture(fichierlif);
    }

    public Carte lecture(String fichierlif) {
        return new Carte();//POUR LA DURÉE DU TEST LA CARTE DE BASE SERA JUSTE 4 POINTS EN LIGNES
    }

    public void tourne() {
        for (int i = 0; i < duree - 1; i++) {
            //2 metre a jour la carte
            //3 PRINT
        }
    }
}
