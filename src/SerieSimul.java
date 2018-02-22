import java.util.LinkedList;

public class SerieSimul {
    public static void main(String[] args) {
        String doss = "fichier_pour_test";
        int duree =100;
        LinkedList <String> fichiers = new LinkedList<>();
        try {
            fichiers = Lecture.lisDoss(doss);
            for(int i = 0; i < fichiers.size(); i++){//TODO Debug
                System.out.println("\n \n");
                System.out.println(fichiers.get(i));
                new Simulation(duree,fichiers.get(i),true).tourne();
            }
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }
}
