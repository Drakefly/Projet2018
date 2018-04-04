package Vue;

import javax.swing.*;

public class AffichageBD {//L'affichage des boites de dialogues
    public void BDdetect (String message,String fichier){
        JOptionPane jop1 = null;
        jop1.showMessageDialog(null, message, "Detection de "+fichier, JOptionPane.INFORMATION_MESSAGE);
    }

    public String BDEnregistrerSous(){
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


}
