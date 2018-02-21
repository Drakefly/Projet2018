import java.util.LinkedList;

public class SerieSimul {
    public void part3(String doss, int duree){
        LinkedList <String> fichiers = new LinkedList<>();
        try {
            fichiers = Lecture.lisDoss(doss);
            for(int i = 0; i < fichiers.size(); i++){
                System.out.println(fichiers.get(i));
                new Simulation(duree,fichiers.get(i)).detect();
            }
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }
}
