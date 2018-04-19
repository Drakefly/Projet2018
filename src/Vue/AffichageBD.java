package Vue;
//TODO SINGLETON pour optimisation

import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AffichageBD extends Component {//L'affichage des boites de dialogues

    /**
     * Lance une boite de dialogue pour les detections
     * @param message message de detection
     * @param fichier fichier sur lequel ca a ete aplique
     */
    public static void detect(String message, String fichier) {
        JOptionPane.showMessageDialog(null, message, "Detection de " + fichier, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Ouvre une boite de dialogue d'erreur
     * @param message message d'erreur
     */
    public static void error(String message) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Ouvre une boite de dialogue de warning
     * @param message message de warning
     */
    public static void Attention(String message) {
        JOptionPane.showMessageDialog(null, message, "Attention", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Ouvre une boie de dialogue d'information
     * @param message message d'information
     */
    public static void information(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Ouvre une boite de dialogues pour choisir le dossier de sauvevegarde
     * @return      dossier de sauvegarde
     */
    public static String chooseDirSave() {
        JFileChooser chooser = new JFileChooser();

        //Affichage et récupération de la réponse de l'utilisateur
        int reponse = chooser.showDialog(chooser, "Enregistrer sous");

        // Si l'utilisateur clique sur OK
        if (reponse == JFileChooser.APPROVE_OPTION) {

            // Récupération du chemin du fichier
            return chooser.getSelectedFile().toString();
        }
        JOptionPane.showMessageDialog(null, "Chemin non valide, nous ne sauvegarderons pas.", "Erreur", JOptionPane.WARNING_MESSAGE);
        return "";
    }

    /**
     * Ouvre une boite de dialogue pour selectionner un fichier
     * @return le path du fichier séléctionne
     */
    public static String selectFichier() {
        // création de la boîte de dialogue
        JFileChooser dialogue = new JFileChooser();
        dialogue.setCurrentDirectory(new File("." + File.separator));
        dialogue.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialogue.addChoosableFileFilter(new FileNameExtensionFilter("Fichiers jeu de la vie", "lif","LIF"));
        dialogue.setAcceptAllFileFilterUsed(false);

        // affichage
        dialogue.showOpenDialog(null);

        File selectedFile = dialogue.getSelectedFile();
        System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
        return String.valueOf(dialogue.getSelectedFile());

    }

    /**
     * Ouvre une boite de dialogue pour entrer le paths d'un dossier
     * @return  le path rentre par l'utilisateur
     */
    public static String chooseDir() {
        DirChoose d = new DirChoose();
        d.pack();
        d.setVisible(true);
        return String.valueOf(d.doss.getText());
    }

    /**
     * Permet la selection d'un fichier mais uniquement un fichier .LIf ou .lif
     */
    private void showOpenFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Fichiers lif", "lif","LIF"));
        fileChooser.setAcceptAllFileFilterUsed(true);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    /**
     * Class car uniquement cette boite de dialogue necessite un action listener
     */
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
            if(active) System.out.println("Appuyez sur F1 pour voir les controles claviers\n" +
                    "F2 pour acceller F3 pour rallentir\n" +
                    "Les fleches directionnelles pour se deplacer\n" +
                    "Les boutons + et moins - pour zoomer ou dezoomer\n" +
                    "Si vous n'avez pas de pavé nummérique vous pouvez uttiliser F5 et F6 pour rallentir ou acceller");

        }
    }
}


