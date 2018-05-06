package Controleur;

import Vue.AffichageBD;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public interface VerificationUser {
    /**
     * Verifie si les arguments sont valide
     *
     * @param args Arguments a verifier
     * @return false si les arguments sont bons
     */
    public static boolean argsInvalide(String[] args) {
        int taille = args.length;
        if (taille > 0) {
            String args0 = args[0];
            if (args0 != null) {
                if (args0.equals("-name") || args0.equals("-h")) return false;
                if (args0.equals("-s") || args0.equals("-c") || args0.equals("-w") || args0.equals("-mc") || args0.equals("-l")) {
                    if (taille > 2) {
                        String args1 = args[1];
                        String args2 = args[2];
                        if (estUnEntierPositif(args1)) {
                            if (args0.equals("-w")) {
                                return !estUnDossierValide(args2);
                            }
                            if (args0.equals("-s") || args0.equals("-c")) return !estUnFichierValide(args2);
                            if (estUnFichierValide(args2)) {
                                if (taille > 7) {
                                    String args3 = args[3];
                                    String args4 = args[4];
                                    String args5 = args[5];
                                    String args6 = args[6];
                                    return !estUnEntier(args3) || !estUnEntier(args4) || !estUnEntier(args5) || !estUnEntier(args6);
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Les arguments sont invalides, nous vous lancons un launcher.");
        }
        System.out.println("Aucun arguments, nous lancons par defaut notre launcher.");
        return true;
    }

    /**
     * Verifie si un nom de fichier est valide
     *
     * @param args2 nom de fichier a verifier
     * @return true si le fichier est valide
     */
    private static boolean estUnFichierValide(String args2) {
        StringTokenizer str = new StringTokenizer(args2, ".");
        String extension;
        extension = " ";
        while (str.hasMoreElements()) {//On regarde le dernier element du nom du fichier
            extension = str.nextToken();
        }
        if (!(extension.equals("lif") || extension.equals("LIF"))) {
            AffichageBD.error("Désolé ce n'est pas un fichier.lif");
            try {
                throw new FileFormatException();
            } catch (FileFormatException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    /**
     * Verifie si un dossier est valide
     *
     * @param args2 nom de dossier a verier
     * @return true si le dossier est valide
     */
    private static boolean estUnDossierValide(String args2) {
        try {
            DirectoryStream<Path> h = Files.newDirectoryStream(Paths.get(args2));
        } catch (IOException e) {
            e.printStackTrace();
            AffichageBD.error("Désolé le nom de dossier est incorect");
            return false;
        }
        return true;
    }

    /**
     * Verifie siune chaine est un entier positif
     *
     * @param chaine a verifier
     * @return true si le fichier est valide
     */
    private static boolean estUnEntierPositif(String chaine) {
        try {
            if (Integer.parseInt(chaine) > 0) return true;
        } catch (NumberFormatException e) {
            AffichageBD.error("Désolé " + chaine + " n'est pas un entier positif");
            return false;
        }
        return false;
    }

    /**
     * Verifie si une chaine est un entier
     *
     * @param chaine chaine a verifier
     * @return true si c'est un entier
     */
    private static boolean estUnEntier(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e) {
            AffichageBD.error("Désolé " + chaine + " n'est pas un entier");
            return false;
        }

        return true;
    }
}
