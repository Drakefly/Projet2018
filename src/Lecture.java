import java.io.*;
import java.io.FileNotFoundException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Lecture est la classe permettant de lire les fichiers lif & les dossiers contenant des lifs
 */
public class Lecture {
    LinkedList<Integer> survie = new LinkedList();//je suis con ca devrais aller dans liste ca.
    LinkedList<Integer> naissance = new LinkedList();

    /**
     *
     * @return la liste survie
     */
    public LinkedList<Integer> getSurvie() {
        return survie;
    }

    /**
     *
     * @return la liste naissance
     */
    public  LinkedList<Integer> getNaissance() {
        return naissance;
    }

    /**
     * Lit les doss et fait une liste chaine de tout les fichiers .lif a l'interieur
     * @param doss le doss à lire
     * @return  la liste des fichiers
     * @throws FileFormatException si un fichier n'est pas au format lif
     */
    public static LinkedList<String> lisDoss(String doss) throws FileFormatException {//TODO catcher l'erreur
    LinkedList<String> fichiers= new LinkedList<>();//Cherchez pas a comprendre ca marche. Je penses je pourrais meme pas vous le rexpliquer
        DirectoryStream<Path> h ;
        try {
            h=Files.newDirectoryStream(Paths.get(doss), path -> path.toString().endsWith(".lif"));//La fleche nouveauté java 8 ;)
            for (Path path : h) {//Le foreach bien trop peu uttilisé </3
                fichiers.add(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fichiers;
    }

    /**
     * Trouve le fichier correspondant au chemin donné par le String s
     * Le booléen b indique si modifier le chemin est nécessaire.
     * @param s le chemin du fichier donné
     * @param b si le chemin est complet ou non
     * @return File le fichier trouvé
     */
    private File synchroFichiker(String s, Boolean b){//Bah sa y abesoin sinon ca marche pas. Genre faudrait demander au gens de mettre le chemin complet dufichier.
            if(b){
                return  new File(s);
            }else{
                return new File("fichier_pour_test/"+s);
            }
    }

    /**
     * Lit le fichier lif donné en paramètre pour en faire une carte
     * @param fichier le String contenant le chemin du fichier
     * @param pathcomplet le booléen qui indique si le chemin donné par fichier est complet
     * @return Un objet Liste correspondant à la carte
     * @throws FileFormatException si le fichier n'est pas du format .lif
     * @throws FileNotFoundException si le fichier n'est pas trouvé
     */
        public Liste lis(String fichier) throws FileFormatException, FileNotFoundException {
        boolean reglesDefinies = false;
        int i, j;
        i=j=0;
        Liste retour = new Liste();
        StringTokenizer str = new StringTokenizer(fichier, ".");
        String extension,ligne;
        extension= " ";
        while (str.hasMoreElements()) {//On regarde le dernier élement du nom du fichier
            extension = str.nextToken();
        }
        if (!(extension.equals("lif")||extension.equals("LIF")))
            throw new FileFormatException();//Si l'extension n'est pas lif on retourne une exception.
        try {
            File fichierNiveau= new File(fichier);
            BufferedReader br = new BufferedReader(new FileReader(fichierNiveau));
            retour.setNom(fichier);
            while ((ligne = br.readLine()) != null) {
                if (ligne.startsWith("#R")) {
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
                    System.out.println(ligne.substring(3));
                }

                if (ligne.startsWith("#P")) {//On choisi les valeurs i&j
                    String[] tokens = ligne.split(" ");
                    i= Integer.parseInt(tokens[1]);
                    j= Integer.parseInt(tokens[2]);
                }

                if (!ligne.contains("#")&&(ligne.contains("*")||ligne.contains("."))) {//J'espere que ligne contains n'est pas lourd
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
