package Vue;
//TODO SINGLETON
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AffichageBD {//L'affichage des boites de dialogues
    public static void detect (String message,String fichier){
        JOptionPane jop1 = null;
        jop1.showMessageDialog(null, message, "Detection de "+fichier, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(String message){
        JOptionPane jop1 = null;
        jop1.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public static void information(String message){
        JOptionPane jop1 = null;
        jop1.showMessageDialog(null, message, "Attention", JOptionPane.WARNING_MESSAGE);
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
        jop1.showMessageDialog(null, "Chemin non valide aucune sauvegarde", "Erreur", JOptionPane.WARNING_MESSAGE);
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

    public class BoutonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null,
                    "Voulez-vous uttiliser une interface graphique?",
                    "Mode GUI",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if(option == JOptionPane.OK_OPTION){
                return true;//lancer ou changer une variable
                return false;
            }
        }
    }

}
