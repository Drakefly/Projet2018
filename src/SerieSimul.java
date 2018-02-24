import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class SerieSimul {
    public static void simulations(int duree,String doss) {//Propablement divisions en deux methodes. Parce que la c'est deg
        LinkedList <String> fichiers ;
        try {
            fichiers = Lecture.lisDoss(doss);
            String retour=
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <meta charset=\"utf-8\" />\n" +
                    "        <title>Resultat</title>\n <style>\n" +
                            "body\n" +
                            "{\n" +
                            "    background-color: black;\n" +
                            "    color: white; /* Le texte de la page sera blanc */\n" +
                            "}" +
                            "        </style>" +
                    "    </head>\n" +
                    "\n" +
                    "    <body>\n" +
                    "       <h1> Les detections de "+doss+ " .</h1>\n";
            for(int i = 0; i < fichiers.size(); i++) {
                retour += new Simulation(duree, fichiers.get(i), true).detect(true);
            }
            retour +=
                    "    \n" +
                    "    </body>\n" +
                    "</html>";
            SerieSimul.export(retour,doss);
        } catch (FileFormatException e1) {
            e1.printStackTrace();
        }
    }

    private static void export (String html,String fichier){//Ill choppe l'html et il le met dans un fichier. On pourrais lui filer un path
        try {
            FileWriter writer = new FileWriter(new File("/Users/drakefly/Desktop/DDDDDD/"+fichier+".html"),true);//Ici choisisez ou vous voulez que le fichier sois créé
            writer.write(html);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
