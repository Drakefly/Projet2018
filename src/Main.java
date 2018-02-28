;public class Main {
    public static void main(String[] args) {//TODO JAVADOC
        String fichierlif = ""; //C'est le nom du fichier Lif qui sera la base de la simu
        int dureeMax; //C'est la durée max de la  simulation
       try {
           if (args.length != 0) {
               switch (args[0]) {
                   case "-name":
                       System.out.println("SENAT Clement\nDOUCHET Loic\nHERVE Camille\n");//TODO ECRIVEZ VOS NOMS LA (TODO C'est pratique pour attirer l'attention sur quelque chose qu'il faut faire)
                       System.exit(0);
                       break;
                   case "-h":
                       System.out.println(usage());
                       System.exit(0);
                       break;
                   case "-s":
                       dureeMax = Integer.parseInt(args[1]);//Les arguments sont des Strings  donc parseint pour recuperer la valeur
                       fichierlif = args[2];
                       Simulation s = new Simulation(dureeMax, fichierlif);
                       s.tourne();
                       break;
                   case "-c":
                       dureeMax = Integer.parseInt(args[1]);
                       fichierlif = args[2];
                       Simulation sc = new Simulation(dureeMax, fichierlif);
                       System.out.println(sc.detect(false));
                       break;
                   case "-w"://créé un fichier html
                       dureeMax = Integer.parseInt(args[1]);
                       fichierlif = args[2];
                       SerieSimul.simulations(dureeMax, fichierlif);
                       break;

                   case "-mc"://Monde circulaire TODO nan ne pas faire, demander au prof si c'est spérique ou carré.
                       break;
                   case "-l"://mondes limités
                       dureeMax = Integer.parseInt(args[1]);
                       int hauteur = Integer.parseInt(args[2]);
                       int largeur = Integer.parseInt(args[3]);
                       int originex = Integer.parseInt(args[4]);
                       int originey = Integer.parseInt(args[5]);
                       fichierlif = args[6];
                       Simulation simulation = new Simulation(dureeMax,fichierlif);
                       simulation.simulationlimité(hauteur,largeur,originex,originey);
                       break;
                   default:
                       break;
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    /**
     *
     * @return les options du programme
     */
    private static String usage() {//Listes des options du programme
        return  "java -jar JeuDeLaVie.jar -name affiche nos noms et prénoms\n" +
                "java -jar JeuDeLaVie.jar -h affiche cette aide\n" +
                "java -jar JeuDeLaVie.jar -s d fichier.lif exécute une simulation du jeu\n" +
                "d’une durée d affichant les configurations du jeu avec le numéro de génération.\n" +
                "java -jar JeuDeLaVie.jar -c max fichier.lif calcule le type d’évolution du\n" +
                "jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée\n" +
                "maximale de simulation pour déduire les résultats du calcul.\n" +
                "java -jar JeuDeLaVie.jar -w max dossier calcule le type d’évolution de tous les\n" +
                "jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier\n" +
                "html."+
                "java -jar JeuDeLaVie.jar -mc max l fichier.lif créé une simulation de durée max sur un monde circulaire de largeur l\n" +
                "java -jar JeuDeLaVie.jar -l max h l fichier.lif créé une simulation de durée max sur un monde de dimmenssions hauteur largeur ";
    }
}