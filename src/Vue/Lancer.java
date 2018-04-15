package Vue;



import javax.swing.*;
import java.awt.event.*;

public class Lancer extends JDialog {
    String fileToLaunch;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton choisirButton;
    private JTextField nbMax; //todo Verifier que c'est un nombre
    private JComboBox combo;
    private JLabel filechoosed;
    private JRadioButton dossierRadioButton;
    private JRadioButton fichierRadioButton;
    private JLabel Type;
    private JRadioButton fenetreRadioButton;
    private JRadioButton consoleRadioButton;
    private JTextField ox;
    private JTextField oy;
    private JTextField tx;
    private JTextField ty;
    private JPanel ordonnes;
    private String[] retour;

    public Lancer() {
        setTitle("Launcher");
        setContentPane(contentPane);
        ordonnes.setVisible(false);
        this.setLocationRelativeTo(null);
        setModal(true);
        //Combo
        combo.addItem("------------");
        combo.addItem("Simulation");
        combo.addItem("Detection");
        combo.addItem("Limité");
        combo.addItem("Sphérique");
        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                String selected = (String) combo.getSelectedItem();

                if (selected.equals("Limité")||selected.equals("Sphérique")) {
                    ordonnes.setVisible(true);
                    AffichageBD.information("Modifiez la taille de la fenetre");
                } else{
                    ordonnes.setVisible(false);
                    if(selected.equals("------------"))buttonOK.setEnabled(false);
                }
            }
        });

        ButtonGroup fileOrDir = new ButtonGroup();
        fileOrDir.add(dossierRadioButton);
        fileOrDir.add(fichierRadioButton);

        dossierRadioButton.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {
                int state = event.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    fileToLaunch="";
                    filechoosed.setText("Aucun dossier choisi");
                    combo.setSelectedIndex(0);
                    combo.setEnabled(false);
                    buttonOK.setEnabled(false);
                } else if (state == ItemEvent.DESELECTED) {


                }
            }
        });
        fichierRadioButton.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {
                int state = event.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    fileToLaunch="";
                    filechoosed.setText("Aucun fichier choisi");
                    combo.setEnabled(false);
                    buttonOK.setEnabled(false);

                } else if (state == ItemEvent.DESELECTED) {

                }
            }
        });

        ButtonGroup affichage  = new ButtonGroup();
        affichage.add(fenetreRadioButton);
        affichage.add(consoleRadioButton);


        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        choisirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choisirButton();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void main(String[] args) {
        Lancer dialog = new Lancer();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void choisirButton() {
        boolean isDossSelected = dossierRadioButton.isSelected();

        if (isDossSelected) {
            fileToLaunch=AffichageBD.chooseDir();
        } else {
            fileToLaunch=AffichageBD.selectFichier();
        }
        filechoosed.setText("..."+fileToLaunch.substring(fileToLaunch.length()-18));
        combo.setEnabled(true);
        buttonOK.setEnabled(true);
    }

    private void onOK() {
        retour= new String[8];

        boolean isDossSelected = dossierRadioButton.isSelected();
        boolean isGuiSelected = fenetreRadioButton.isSelected();

        if (isDossSelected) {
            retour[0]="-w";
        } else {
            switch (combo.getSelectedIndex()) {
                case 0:
                    System.out.println("probleme");
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
        Modele.Main.main(retour);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
