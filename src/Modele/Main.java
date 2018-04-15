package Modele;

import Modele.SerieSimul;
import Modele.Simulation;
import Vue.AffichageBD;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String fichierlif ; //C'est le nom du fichier Lif qui sera la base de la simu
        final int dureeMax; //C'est la durée max de la  simulation
        try {
            if (args.length != 0) {
                switch (args[0]) {
                    case "-name":
                        System.out.println("SENAT Clement\nDOUCHET Loic\nHERVE Camille\n");
                        System.exit(0);
                        break;
                    case "-h":
                        usage();
                        System.exit(0);
                        break;
                    case "-s":
                        dureeMax = parseInt(args[1]);//Les arguments sont des Strings  donc parseint pour recuperer la valeur
                        fichierlif = args[2];
                        Simulation s = new Simulation(dureeMax, fichierlif);
                        s.tourne();
                        break;
                    case "-c":
                        if(args.length ==1){
                            System.out.println("Veuillez choisir votre fichier\nPar default, la recherche se fera sur 500 essais");
                            Simulation simulation= new Simulation();
                            AffichageBD.detect(simulation.detect(false),simulation.fichier);
                        }else {
                            dureeMax = parseInt(args[1]);
                            fichierlif = args[2];
                            Simulation sc = new Simulation(dureeMax, fichierlif);
                            AffichageBD.detect(sc.detect(false),fichierlif);
                        }
                        break;
                    case "-w"://créé un fichier html
                        dureeMax = parseInt(args[1]);
                        fichierlif = args[2];
                        SerieSimul.simulations(dureeMax, fichierlif);
                        break;

                    case "-mc"://Monde circulaire todo debug & test
                        dureeMax = parseInt(args[1]);
                        int hauteur = parseInt(args[3]);
                        int largeur = parseInt(args[4]);
                        int originex = parseInt(args[5]);
                        int originey = parseInt(args[6]);
                        fichierlif = args[2];
                        Simulation simuspherique = new Simulation(dureeMax, fichierlif);
                        simuspherique.simuSpherique(hauteur, largeur, originex, originey);
                        break;
                    case "-l"://mondes limités
                        dureeMax = parseInt(args[1]);
                        int h = parseInt(args[3]);
                        int l = parseInt(args[4]);
                        int ox = parseInt(args[5]);
                        int oy = parseInt(args[6]);
                        fichierlif = args[2];
                        Simulation simulation = new Simulation(dureeMax, fichierlif);
                        simulation.simulation(h, l, ox, oy);
                        break;
                    default:
                        System.out.println("Nous lancerons par defaut une simulation de durée 500 sur un fichiers que vous pouvez choisir");
                        Simulation si = new Simulation();
                        si.tourne();
                        break;
                }
            }else{
                System.out.println("Nous lancerons par defaut une simulation de durée 500 sur un fichiers que vous pouvez choisir");
                Simulation si = new Simulation();
                si.tourne();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {//Fun a virer
            Runtime.getRuntime().exec("java -jar /Users/drakefly/IdeaProjects/Projet2018/out/artifacts/Projet2018_jar/Projet2018.jar");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * affiche les options du programme
     */
    private static void usage() {//Listes des options du programme
        System.out.println(
                "java -jar JeuDeLaVie.jar -name affiche nos noms et prénoms\n" +
                "java -jar JeuDeLaVie.jar -h affiche cette aide\n" +
                "java -jar JeuDeLaVie.jar -s d fichier.lif exécute une simulation du jeu\n" +
                "d’une durée d affichant les configurations du jeu avec le numéro de génération.\n" +
                "java -jar JeuDeLaVie.jar -c max fichier.lif calcule le type d’évolution du\n" +
                "jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée\n" +
                "maximale de simulation pour déduire les résultats du calcul.\n" +
                "java -jar JeuDeLaVie.jar -c  calcule le type d’évolution du\n" +
                "jeu avec ses caractéristiques  \n" +
                "pour un fichier que vous pourrez par la suite choisir \n" +
                "java -jar JeuDeLaVie.jar -w max dossier calcule le type d’évolution de tous les\n" +
                "jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier\n" +
                "html."+
                "java -jar JeuDeLaVie.jar -mc max l fichier.lif créé une simulation de durée max sur un monde circulaire de largeur l\n" +
                "java -jar JeuDeLaVie.jar -l max h l fichier.lif créé une simulation de durée max sur un monde de dimmenssions hauteur largeur \n "+
                "java -jar JeuDeLaVie.jar avec nimporte quelle arguments pour ouvrir une boite de dialogue avec la selection du fichier\n"
        );
    }
}