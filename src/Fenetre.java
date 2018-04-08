import Vue.AffichageBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Fenetre extends JFrame implements ActionListener, KeyListener {
    private Panneau pan = new Panneau();
    private JButton dezoom = new JButton("Dezoom");
    private JButton zoom = new JButton("Zoom");

    /**
     * La fenetre de l'interface graphique
     */
    Fenetre() {
        //Parametres de la fenetre
        this.setTitle("Jeu de la vie");
        final int TAILLE = 400;
        this.setSize(TAILLE + 120, TAILLE + 25);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setFocusable(true);

        //Parametre du panneau
        pan.setDimm(TAILLE);
        pan.setNombre(20);
        pan.setOriginx(-10);
        pan.setOriginy(-10);

        //KeyListener
        addKeyListener(this);

        //Boutons
        dezoom.setBounds(TAILLE - 40, 40, 100, 30);//Il vaudrait mieux diviser la tailler totale en nombre de case demander
        dezoom.addActionListener(this);
        pan.add(dezoom);

        zoom.setBounds(TAILLE - 40, 75, 100, 30);
        zoom.addActionListener(this);
        pan.add(zoom);

        this.setContentPane(pan);
    }

    public static void main(String[] args) {//Juste pour les tests
        Fenetre f = new Fenetre();
        f.setVisible(true);
    }

    /* x largeur width */
    void go(Liste liste, int numeroSim) {
        pan.setL(liste);
        pan.setNumeroSim(numeroSim);
        pan.repaint();
    }

    public void actionPerformed(ActionEvent arg0) {//TODO grisé entre certains seuils
        //Lorsque l'on clique sur le bouton, on met à jour le JLabel
        if (arg0.getSource() == dezoom) dezoom();//switch imposible ne fonctionne qu'avec des constantes
        if (arg0.getSource() == zoom) zoom();
    }

    private void dezoom() {
        pan.setNombre(pan.getNombre() + 10);//Dezoom
        pan.setOriginy(pan.getOriginy() - 5);
        pan.setOriginx(pan.getOriginx() - 5);
        if (pan.getNombre() == 100) AffichageBD.information("Attention trop dezoomer peut etre gourmand en ressources");
        pan.repaint();
    }

    private void zoom() {
        pan.setNombre(pan.getNombre() - 10);
        pan.setOriginy(pan.getOriginy() + 5);
        pan.setOriginx(pan.getOriginx() + 5);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {//Todo accellerer ralentir pause
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                pan.setOriginx(pan.getOriginx() - 4);
                break;
            case KeyEvent.VK_UP:
                pan.setOriginy(pan.getOriginy() + 4);
                break;
            case KeyEvent.VK_DOWN:
                pan.setOriginy(pan.getOriginy() - 4);
                break;
            case KeyEvent.VK_LEFT:
                pan.setOriginx(pan.getOriginx() + 4);
                break;
            case KeyEvent.VK_ESCAPE:

                        break;
            case KeyEvent.VK_F1:
                AffichageBD.information("Uttilisez les touches directionnelles pour vous deplacer, + ou - du pavé numéfique pour zommer ou dezzommer et f12 pour afficher" +
                        "les credits ");
                break;
            case KeyEvent.VK_F12:
                AffichageBD.information("\"SENAT Clement\\nDOUCHET Loic\\nHERVE Camille\\n\"");
                break;
            case 107:
                zoom();//Zoom mais il faudrais verifier l'echelle
                break;
            case 109:
                dezoom();
                break;
        }
        pan.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}