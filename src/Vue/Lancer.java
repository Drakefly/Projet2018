package Vue;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Launcher du programme pour selectionner tous les parametre de args
 */
public class Lancer extends JDialog {
    private String fileToLaunch;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton choisirButton;
    private JTextField nbMax;
    private JTextField ox;
    private JTextField oy;
    private JTextField tx;
    private JTextField ty;
    private JComboBox<String> combo;
    private JLabel filechoosed;
    private JLabel Type;
    private JRadioButton dossierRadioButton;
    private JRadioButton fichierRadioButton;
    private JRadioButton fenetreRadioButton;
    private JRadioButton consoleRadioButton;

    private JPanel ordonnes;
    private String[] retour;

    /**
     * Constructeur de la fenetre
     */
    public Lancer() {
        setTitle("Launcher");
        setContentPane(contentPane);
        ordonnes.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setModal(true);
        //Combo
        combo.addItem("------------");
        combo.addItem("Simulation");
        combo.addItem("Detection");
        combo.addItem("Limité");
        combo.addItem("Sphérique");
        combo.addActionListener(event -> {//Lambdas petit test, c'est une nouveauté de java.
            JComboBox<String> combo = (JComboBox<String>) event.getSource();//Merci IntelliJ de m'avoir ecris ca
            String selected = (String) combo.getSelectedItem();

            assert selected != null;
            if (selected.equals("Limité")||selected.equals("Sphérique")) {
                ordonnes.setVisible(true);
                consoleRadioButton.setSelected(true);
                fenetreRadioButton.setEnabled(false);
                consoleRadioButton.setEnabled(false);
                buttonOK.setEnabled(true);
                this.setSize(438,298);
            } else{
                this.setSize(438,224);
                if(!selected.equals("Detection")){
                    fenetreRadioButton.setEnabled(true);
                    consoleRadioButton.setEnabled(true);
                }else{
                    consoleRadioButton.setSelected(true);
                    fenetreRadioButton.setEnabled(false);
                    consoleRadioButton.setEnabled(false);
                }
                ordonnes.setVisible(false);
                buttonOK.setEnabled(true);
                if(selected.equals("------------"))buttonOK.setEnabled(false);
            }
        });

        ButtonGroup fileOrDir = new ButtonGroup();
        fileOrDir.add(dossierRadioButton);
        fileOrDir.add(fichierRadioButton);

        dossierRadioButton.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED) {
                fileToLaunch="";
                filechoosed.setText("Aucun dossier choisi");
                combo.setSelectedIndex(0);
                combo.setEnabled(false);
                buttonOK.setEnabled(false);
            }
        });
        fichierRadioButton.addItemListener(event -> {
            int state = event.getStateChange();
            if (state == ItemEvent.SELECTED) {
                fileToLaunch="";
                filechoosed.setText("Aucun fichier choisi");
                combo.setEnabled(true);
                buttonOK.setEnabled(false);
            }
        });

        ButtonGroup affichage  = new ButtonGroup();
        affichage.add(fenetreRadioButton);
        affichage.add(consoleRadioButton);


        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> onOK());
        choisirButton.addActionListener(e -> choisirButton());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Lorsque que l'uttilisiateur clique sur choisir, detecte si il faut lancer la selection de fichier ou de dossier
     */
    private void choisirButton() {
        boolean isDossSelected = dossierRadioButton.isSelected();

        if (isDossSelected) {
            fileToLaunch=AffichageBD.chooseDir();
        } else {
            fileToLaunch=AffichageBD.selectFichier();
        }
        if(fileToLaunch.length()>18) filechoosed.setText("..."+fileToLaunch.substring(fileToLaunch.length()-18));
        else filechoosed.setText(fileToLaunch);
    }

    /**
     * Retourne les args pour lancer le programme
     * @return Tableau des argument pour le lancements du programme
     */
    public String[] getRetour() {
        return retour;
    }

    /**
     * Lorsque que ok est presse genere le retour est cache la fenetre
     */
    private void onOK() {
        retour= new String[8];

        boolean isDossSelected = dossierRadioButton.isSelected();
        boolean isGuiSelected = fenetreRadioButton.isSelected();

        if (isDossSelected) {
            retour[0]="-w";
        } else {
            switch (combo.getSelectedIndex()) {
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
        retour[1]=nbMax.getText();
        retour[2]=fileToLaunch;
        retour[3]=ty.getText();
        retour[4]=tx.getText();
        retour[5]=ox.getText();
        retour[6]=oy.getText();

        if (isGuiSelected){
            retour[7]="Oui";
        }else{
            retour[7]="Non";
        }
        dispose();
    }

    /**
     * lorsque que c'est annulle cache la fenetre
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
