package Vue;
//TODO SINGLETON

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AffichageBD {//L'affichage des boites de dialogues

    public static void detect(String message, String fichier) {
        JOptionPane.showMessageDialog(null, message, "Detection de " + fichier, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public static void Attention(String message) {
        JOptionPane.showMessageDialog(null, message, "Attention", JOptionPane.WARNING_MESSAGE);
    }

    public static void information(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.WARNING_MESSAGE);
    }


    public static String chooseDirSave() {
        JFileChooser chooser = new JFileChooser();

        //Affichage et récupération de la réponse de l'utilisateur
        int reponse = chooser.showDialog(chooser, "Enregistrer sous");

        // Si l'utilisateur clique sur OK
        if (reponse == JFileChooser.APPROVE_OPTION) {

            // Récupération du chemin du fichier
            return chooser.getSelectedFile().toString();
        }
        JOptionPane.showMessageDialog(null, "Cheminnon valide, nous ne sauvegarderons pas.", "Erreur", JOptionPane.WARNING_MESSAGE);
        return "";
    }

    public static String selectFichier() {
        // création de la boîte de dialogue
        JFileChooser dialogue = new JFileChooser();
        dialogue.setCurrentDirectory(new File("." + File.separator));
        // affichage
        dialogue.showOpenDialog(null);

        // récupération du fichier sélectionné
        System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
        return String.valueOf(dialogue.getSelectedFile());
    }

    public static class BoutonListener implements ActionListener {
        private boolean active;

        public boolean isActive() {
            return active;
        }

        public void actionPerformed(ActionEvent arg0) {
            int option = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous utiliser une interface graphique?",
                    "Mode GUI",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            this.active = option == JOptionPane.OK_OPTION;

        }
    }

}
