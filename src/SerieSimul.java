import java.io.*;
import java.util.LinkedList;

public class SerieSimul {
    public static void simulations(int duree,String doss) {//Propablement divisions en deux methodes. Parce que la c'est deg
        LinkedList <String> fichiers ;
        try {
            fichiers = Lecture.lisDoss(doss);
            String retour="<h1> Les detections de "+doss+ " .</h1>\n\n\n";
            for(int i = 0; i < fichiers.size(); i++) {
                retour += new Simulation(duree, fichiers.get(i), true).detect(true);
            }
            System.out.println(retour);
            SerieSimul.export(retour,doss);
        } catch (FileFormatException e1) {
            e1.printStackTrace();
        }
    }

    private static void export (String html,String fichier){//Ill choppe l'html et il le met dans un fichier. On pourrais lui filer un path
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/drakefly/Desktop/DDDDDD/"+fichier+".html")));//Créé un fichier si inexistant
            FileOutputStream fos;
            fos = new FileOutputStream(new File(fichier+ ".html"));
            byte byteRetour[] = html.getBytes();
            fos.write(byteRetour);
            fos.close();//TODO verifier ou ca iras.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
