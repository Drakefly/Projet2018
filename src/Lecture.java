import java.io.*;
import java.io.FileNotFoundException;
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

    public Liste lis(String fichier) throws FileFormatException, FileNotFoundException {//Ca pourait etre une classe
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
        File fichierNiveau = new File("fichier_pour_test/"+fichier);
        String ligne = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fichierNiveau));
            while ((ligne = br.readLine()) != null) {

                if (ligne.contains("#R")) {//TODO WARNING Regles Perso NE FONCTIONNES PAS IL FAUT DEBUGGUER CA !!
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

                if (ligne.contains("#D")) {//On affiche la ligne de commentaires
                    System.out.println(ligne);
                }

                if (ligne.contains("#P")) {//On choisi les valeurs i&j
                    StringTokenizer strtoken = new StringTokenizer(ligne);
                    strtoken.nextToken();
                    i= Integer.parseInt(strtoken.nextToken());
                    j= Integer.parseInt(strtoken.nextToken());

                }

                if (ligne.contains(".") || ligne.contains("*")) {//Leture peu efficace probablement moyen de faire mieux.
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
