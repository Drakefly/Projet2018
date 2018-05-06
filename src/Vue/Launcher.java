package Vue;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Launcher du programme pour selectionner tous les parametre de args
 */
public class Launcher extends JDialog {
    private String fichierALancer;
    private JPanel panneauPrincipal;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton choisirButton;
    private JTextField nbMax;
    private JTextField origineX;
    private JTextField originY;
    private JTextField tailleX;
    private JTextField tailleY;
    private JComboBox<String> listeDeroulante;
    private JLabel fichierChoisi;
    private JLabel type;
    private JRadioButton radioButtonDossier;
    private JRadioButton radioButtonFichier;
    private JRadioButton radioButtonFenetre;
    private JRadioButton radioButtonConsole;

    private JPanel panneauOrdonnes;
    private String[] retour;

    /**
     * Constructeur de la fenetre de lancher
     */
    public Launcher() {
        setTitle("Launcher");
        setContentPane(panneauPrincipal);
        panneauOrdonnes.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setModal(true);

        ButtonGroup fileOrDir = new ButtonGroup();

        //BOUTON DOSSIER
        fileOrDir.add(radioButtonDossier);
        radioButtonDossier.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED) {
                fichierALancer = "";
                choisirButton.setEnabled(true);
                fichierChoisi.setText("Aucun dossier choisi");
                listeDeroulante.setSelectedIndex(0);
                listeDeroulante.setEnabled(false);
                buttonOK.setEnabled(true);
            }
        });
        //BOUTON FICHIER
        fileOrDir.add(radioButtonFichier);
        radioButtonFichier.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED) {
                choisirButton.setEnabled(true);
                fichierALancer = "";
                fichierChoisi.setText("Aucun fichier choisi");
                listeDeroulante.setEnabled(true);
                buttonOK.setEnabled(false);
            }
        });

        //LISTE DEROULANTE
        listeDeroulante.addItem("------------");
        listeDeroulante.addItem("Simulation");
        listeDeroulante.addItem("Detection");
        listeDeroulante.addItem("Limité");
        listeDeroulante.addItem("Sphérique");
        listeDeroulante.addActionListener(event -> {//Lambdas petit test, c'est une nouveauté de java.
            JComboBox<String> combo = (JComboBox<String>) event.getSource();//Merci IntelliJ de m'avoir ecris ca
            String selected = (String) combo.getSelectedItem();

            assert selected != null;
            if (selected.equals("Limité") || selected.equals("Sphérique")) {
                panneauOrdonnes.setVisible(true);
                radioButtonConsole.setSelected(true);
                radioButtonFenetre.setEnabled(false);
                radioButtonConsole.setEnabled(false);
                buttonOK.setEnabled(true);
                this.setSize(438, 298);
            } else {
                this.setSize(438, 224);
                if (!selected.equals("Detection")) {
                    radioButtonFenetre.setEnabled(true);
                    radioButtonConsole.setEnabled(true);
                } else {
                    radioButtonConsole.setSelected(true);
                    radioButtonFenetre.setEnabled(false);
                    radioButtonConsole.setEnabled(false);
                }
                panneauOrdonnes.setVisible(false);
                buttonOK.setEnabled(true);
                if (selected.equals("------------")) buttonOK.setEnabled(false);
            }
        });

        //GESTIOIN AFFICHAGE
        ButtonGroup affichage = new ButtonGroup();
        affichage.add(radioButtonFenetre);
        affichage.add(radioButtonConsole);

        //BOUTON OK ET ANNULER
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> onOK());
        choisirButton.addActionListener(e -> choisirButton());
        buttonCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        panneauPrincipal.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        panneauPrincipal.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    /**
     * Lorsque que l'uttilisiateur clique sur choisir, detecte si il faut lancer la selection de fichier ou de dossier
     */
    private void choisirButton() {
        boolean isDossSelected = radioButtonDossier.isSelected();

        if (isDossSelected) {
            fichierALancer = AffichageBD.chooseDir();
        } else {
            fichierALancer = AffichageBD.selectFichier();
        }
        if (fichierALancer.length() > 18)
            fichierChoisi.setText("..." + fichierALancer.substring(fichierALancer.length() - 18));
        else fichierChoisi.setText(fichierALancer);
    }

    /**
     * Retourne les args pour lancer le programme
     *
     * @return Tableau des argument pour le lancements du programme
     */
    public String[] getRetour() {
        return retour;
    }

    /**
     * Lorsque que ok est presse genere le retour est cache la fenetre
     */
    private void onOK() {
        if (buttonOK.isEnabled()) {
            retour = new String[8];
            boolean isDossSelected = radioButtonDossier.isSelected();
            boolean isGuiSelected = radioButtonFenetre.isSelected();

            if (isDossSelected) {
                retour[0] = "-w";
            } else {
                switch (listeDeroulante.getSelectedIndex()) {
                    case 0:
                        break;
                    case 1:
                        retour[0] = "-s";
                        break;
                    case 2:
                        retour[0] = "-c";
                        break;
                    case 3:
                        retour[0] = "-l";
                        break;
                    case 4:
                        retour[0] = "-mc";
                        break;
                }
            }
            retour[1] = nbMax.getText();
            retour[2] = fichierALancer;
            retour[3] = tailleY.getText();
            retour[4] = tailleX.getText();
            retour[5] = origineX.getText();
            retour[6] = originY.getText();

            if (isGuiSelected) {
                retour[7] = "Oui";
            } else {
                retour[7] = "Non";
            }
            dispose();
        } else {
            Toolkit.getDefaultToolkit().beep();

        }

    }

    /**
     * lorsque que c'est annulle cache la fenetre
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
