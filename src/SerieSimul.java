import java.util.LinkedList;

public class SerieSimul {
    public static void simulations(int duree,String doss) {
        LinkedList <String> fichiers = new LinkedList<>();
        try {
            fichiers = Lecture.lisDoss(doss);
            for(int i = 0; i < fichiers.size(); i++){
                System.out.println("\n \n");
                System.out.println(fichiers.get(i));
                new Simulation(duree,fichiers.get(i),true).detect();
            }
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }
}
