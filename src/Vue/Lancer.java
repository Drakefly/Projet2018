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
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public Lancer() {
        setTitle("Launcher");
        setContentPane(contentPane);
        this.setLocationRelativeTo(null);
        setModal(true);
        combo.addItem("------------");
        combo.addItem("Simulation");
        combo.addItem("Detection");
        combo.addItem("Limité");
        combo.addItem("Sphérique");

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
             //Alors il faudra lancer le dossChooser todo 
        } else {
            fileToLaunch=AffichageBD.selectFichier();
        }
        filechoosed.setText("..."+fileToLaunch.substring(fileToLaunch.length()-18));
        combo.setEnabled(true);
        buttonOK.setEnabled(true);
    }

    private void onOK() {
        boolean isDossSelected = dossierRadioButton.isSelected();
        if (isDossSelected) {
            //Alors il faudra lancer le dossChooser todo 
        } else {

        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
