package Vue;

import javax.swing.*;
import java.awt.event.*;

public class DirChoose extends JDialog {
    private JPanel panePrincipal;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField dossier;

    /**
     *
     */
    public DirChoose() {
        setContentPane(panePrincipal);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
        panePrincipal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Retourne le nom du dossier
     * @return le nom du dossier
     */
    String getNomDoss() {
        return String.valueOf(dossier.getText());
    }

    /**
     * Methode appele quanc ok est clique
     */
    private void onOK() {
        // add your code here
        dossier.getText();
        dispose();
    }

    /**
     * que faire quand on annulle
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
