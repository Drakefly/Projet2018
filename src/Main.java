public class Main {
    public static void main(String[] args) {
        String fichierlif = ""; //C'est le nom du fichier Lif qui sera la base de la simu
        int dureeMax; //C'est la durée max de la  simulation
        if (args.length != 0) {//TODO Try Catch ou cas ou l'user mettrais une valeurs qui non valable
            switch (args[0]) {
                case "-name":
                    System.out.println("SENAT Clement\n");//TODO ECRIVEZ VOS NOMS LA (TODO C'est pratique pour attirer l'attention sur quelque chose qu'il faut faire)
                    System.exit(0);
                    break;

                case "-h":
                    System.out.println(usage());
                    System.exit(0);
                    break;
                case "-s":
                    dureeMax = Integer.parseInt(args[1]);//Les arguments sont des Strings  donc parseint pour recuperer la valeur
                    fichierlif = args[2];
                    //TODO lance le prog addapte
                    break;
                case "-c":
                    dureeMax = Integer.parseInt(args[1]);
                    fichierlif = args[2];
                    //TODO lance le prog addapte
                    break;
                case "-w":
                    dureeMax = Integer.parseInt(args[1]);
                    fichierlif = args[2];
                    //TODO lance le prog addapte
                    break;
                default:
                    break;
            }
        }
    }

    private static String usage() {//Listes des options du programme
        return "•java -jar JeuDeLaVie.jar -name affiche nos noms et prénoms\n" +
                "• java -jar JeuDeLaVie.jar -h affiche cette aide\n" +
                "• java -jar JeuDeLaVie.jar -s d fichier.lif exécute une simulation du jeu\n" +
                "d’une durée d affichant les configurations du jeu avec le numéro de génération.\n" +
                "• java -jar JeuDeLaVie.jar -c max fichier.lif calcule le type d’évolution du\n" +
                "jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée\n" +
                "maximale de simulation pour déduire les résultats du calcul.\n" +
                "• java -jar JeuDeLaVie.jar -w max dossier calcule le type d’évolution de tous les\n" +
                "jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier\n" +
                "html.";
    }
}
