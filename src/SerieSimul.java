import java.io.*;
import java.util.LinkedList;

public class SerieSimul {
    public static void simulations(int duree,String doss) {//Propablement divisions en deux methodes. Parce que la c'est deg
        LinkedList <String> fichiers = new LinkedList<>();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/drakefly/Desktop/DDDDDD/"+doss+".html")));
            try {
                fichiers = Lecture.lisDoss(doss);
                String retour="";
                for(int i = 0; i < fichiers.size(); i++){
                    System.out.println("\n \n");
                    System.out.println(fichiers.get(i));
                        retour += new Simulation(duree,fichiers.get(i),true).detect() ;
                        try {//C'est la bonne archi. Va faloir verifier et rendre toussa au format html. TODO mettre au format html
                            FileOutputStream fos;
                            fos = new FileOutputStream(new File(doss+ ".html"));
                            byte byteRetour[] = retour.getBytes();
                            fos.write(byteRetour);
                            fos.close();//TODO verifier ou ca iras.
                        } catch (Exception e) {
                            System.out.println("Erreur de sauvegarde de l'historique de partie");
                        }
                    }
            } catch (FileFormatException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
