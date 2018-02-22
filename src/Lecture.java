import java.io.*;
import java.io.FileNotFoundException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Lecture {
    LinkedList<Integer> survie = new LinkedList();
    LinkedList<Integer> naissance = new LinkedList();

    public LinkedList<Integer> getSurvie() {
        return survie;
    }

    public  LinkedList<Integer> getNaissance() {
        return naissance;
    }

    public static LinkedList<String> lisDoss(String doss) throws FileFormatException {
    LinkedList<String> fichiers= new LinkedList<>();
        DirectoryStream<Path> h ;
        try {
            h=Files.newDirectoryStream(Paths.get(doss), path -> path.toString().endsWith(".lif"));
            for (Path path : h) {
                fichiers.add(path.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fichiers.toString());
        return fichiers;
    }

    public static void main(String[] args) {
        try {
            LinkedList<String> strings = lisDoss("fichier_pour_test");
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }

    private File fichierNiveau(String s,Boolean b){
            if(b){
                return  new File(s);
            }else{
                return new File("fichier_pour_test/"+s);
            }
    }

        public Liste lis(String fichier,boolean pathcomplet) throws FileFormatException, FileNotFoundException {//Ca pourait etre une classe
        boolean reglesDefinies = false;
        int i, j;
        i=j=0;
        Liste retour = new Liste();
        StringTokenizer str = new StringTokenizer(fichier, ".");
        String extension = "";
        while (str.hasMoreElements()) {//On regarde le dernier élement du nom du fichier
            extension = str.nextToken();
        }
        if (!extension.equals("lif")) {
            throw new FileFormatException();//Si l'extension n'est pas lif on retourne une exception.
        }
        String ligne = "";

        try {
            File fichierNiveau=fichierNiveau(fichier,pathcomplet);
            BufferedReader br = new BufferedReader(new FileReader(fichierNiveau));
            while ((ligne = br.readLine()) != null) {

                if (ligne.startsWith("#R")) {//TODO WARNING Regles Perso NE FONCTIONNES PAS IL FAUT DEBUGGUER CA !!
                    reglesDefinies =true;
                    boolean vie = true;
                    char a;
                    for (int x = 3; x < ligne.length(); x++) {
                        a = ligne.charAt(x);
                        if (vie) this.survie.add((int) a);
                        if (a == '/') vie = false;
                        if (!vie) this.naissance.add((int) a);
                    }
                }

                if (ligne.startsWith("#D")) {//On affiche la ligne de commentaires
                    System.out.println(ligne);
                }

                if (ligne.startsWith("#P")) {//On choisi les valeurs i&j
                    String[] tokens = ligne.split(" ");
                    i= Integer.parseInt(tokens[1]);
                    j= Integer.parseInt(tokens[2]);
                }

                if (ligne.contains("*")) {//Leture peu efficace probablement moyen de faire mieux.
                    for (int z = 0; z < ligne.length(); z++) {//Pour chaques caracteres on chek
                        if (ligne.charAt(z) == '*'){
                            retour.ajouter(new Cellule(i + z, j));
                        }
                    }
                    j++;
                }

            }
            if(!reglesDefinies){
                this.survie.add(2);
                this.survie.add(3);
                this.naissance.add(3);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return retour;
    }
}
