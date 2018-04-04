package Vue;
//TODO SINGLETON
import javax.swing.*;
import java.io.File;

public class AffichageBD {//L'affichage des boites de dialogues
    public static void detect (String message,String fichier){
        JOptionPane jop1 = null;
        jop1.showMessageDialog(null, message, "Detection de "+fichier, JOptionPane.INFORMATION_MESSAGE);
    }

    public static String chooseDirSave(){
        JFileChooser chooser = new JFileChooser();

        //Affichage et récupération de la réponse de l'utilisateur
        int reponse = chooser.showDialog(chooser,"Enregistrer sous");

        // Si l'utilisateur clique sur OK
        if  (reponse == JFileChooser.APPROVE_OPTION) {

            // Récupération du chemin du fichier
            return  chooser.getSelectedFile().toString();
        }
        JOptionPane jop1 = null;
        jop1.showMessageDialog(null, "Chemin non valide, un chemin par defaut a été selectionné", "Erreur", JOptionPane.WARNING_MESSAGE);
    return "xxx";
    }

    public static String selectFichier(){
        // création de la boîte de dialogue
        JFileChooser dialogue = new JFileChooser();
        dialogue.setCurrentDirectory(new File("."+File.separator));
        // affichage
        dialogue.showOpenDialog(null);

        // récupération du fichier sélectionné
        System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
        return String.valueOf(dialogue.getSelectedFile());
    }

}
