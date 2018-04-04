import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class SerieSimul {
    /**
     *  Fais les simulations et les detections de youy les fichoiers contenus dans le parametre doss puis mets les resultats au format html
     * @param duree durée maximale des tests
     * @param doss dossier contenant les fichiers a traiter
     */
    public static void simulations(int duree,String doss) {//Propablement divisions en deux methodes. Parce que la c'est deg
        LinkedList <String> fichiers ;
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
                    "    color: white;" +
                            " font-family: Verdana;\n" +
                    "}\n" +
                    "h1\n" +
                    "{\n" +
                    "    text-align: center;\n" +
                    "}\n" +
                    "        </style>" +
                    "    </head>\n" +
                    "\n" +
                    "    <body>\n" +
                    "       <h1> Les detections de "+doss+ " .</h1>\n";
            for(int i = 0; i < fichiers.size(); i++) {
                retour += new Simulation(duree, fichiers.get(i)).detect(true);
            }
            retour +=
                    "    \n" +
                    "    </body>\n" +
                    "</html>";
            SerieSimul.export(retour,doss);
    }

    /**
     * Créée un fichier .html contenant le contenu du parametre nommé html
     * @param html LE string qui sera mis dans le fichiers
     * @param fichier nom du fichier créée
     */
    private static void export (String html,String fichier){//Ill choppe l'html et il le met dans un fichier. On pourrais lui filer un path
        try{

            JFileChooser chooser = new JFileChooser();

            //Affichage et récupération de la réponse de l'utilisateur
            int reponse = chooser.showDialog(chooser,"Enregistrer sous");

            // Si l'utilisateur clique sur OK
            if  (reponse == JFileChooser.APPROVE_OPTION){

                // Récupération du chemin du fichier
                String  fichier2= chooser.getSelectedFile().toString();
                //Ecriture du fichier
                try {
                    try {
                        File f = new File(fichier2 + ".html");
                        f.delete();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    FileWriter writer = new FileWriter(new File(fichier2  + ".html"), true);//Ici choisisez ou vous voulez que le fichier sois créé
                    writer.write(html);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch(HeadlessException he){
            he.printStackTrace();
        }
    }
}
