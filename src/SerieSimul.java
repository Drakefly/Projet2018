import java.util.LinkedList;

public class SerieSimul {
    public static void simulations(int duree,String doss) {//Propablement divisions en deux methodes. Parce que la c'est deg
        LinkedList <String> fichiers = new LinkedList<>();
        try {
            fichiers = Lecture.lisDoss(doss);
            String retour="";
            for(int i = 0; i < fichiers.size(); i++) {
                retour += new Simulation(duree, fichiers.get(i), true).detect(true);
            }
            System.out.println(retour);
        } catch (FileFormatException e1) {
            e1.printStackTrace();
        }
    }

    /*private void export (String html){//Ill choppe l'html et il le met dans un fichier.

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/drakefly/Desktop/DDDDDD/"+doss+".html")));
            try {
                    System.out.println("\n \n");
                    System.out.println(fichiers.get(i));
                    System.out.println(retour);
                    try {//C'est la bonne archi. Va faloir verifier et rendre toussa au format html.
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
    }*/

}
