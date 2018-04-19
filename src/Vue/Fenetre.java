package Vue;

import Modele.Liste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La fenetre de l'interface graphique
 */
public class Fenetre extends JFrame implements ActionListener, KeyListener {
    final private Panneau pan = new Panneau();
    final private JButton dezoom = new JButton("Dezoom");
    final private JButton zoom = new JButton("Zoom");
    public int vitesse=300;

    /**
     * La fenetre de l'interface graphique
     */
    public Fenetre() {
        //Parametres de la fenetre
        this.setTitle("Jeu de la vie");
        final int TAILLE = 600;
        this.setSize(TAILLE , TAILLE + 25);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setFocusable(true);

        //Parametre du panneau
        pan.setDimm(TAILLE);
        pan.setNombre(20);
        pan.originx=(-10);
        pan.originy=(-10);

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

    /* x largeur width */
    public void go(Liste liste, int numeroSim) {
        pan.setL(liste);
        pan.setNumeroSim(numeroSim);
        pan.repaint();
    }

    /**
     * Controle les boutons
     * @param arg0 le bouton cliqué
     */
    public void actionPerformed(ActionEvent arg0) {//TODO grisé entre certains seuils
        //Lorsque l'on clique sur le bouton, on met à jour le JLabel
        if (arg0.getSource() == dezoom) dezoom();//switch imposible ne fonctionne qu'avec des constantes
        if (arg0.getSource() == zoom) zoom();
    }

    /**
     * Permet de dezoomer
     */
    private void dezoom() {
        zoom.setEnabled(true);
        pan.setNombre(pan.getNombre() + 10);//Dezoom
        pan.originx -= 5;
        pan.originx -= 5;
        if (pan.getNombre() == 160) AffichageBD.information("Attention trop dezoomer peut ralentir le programme");
        pan.repaint();
    }

    /**
     * Permet de zoomer
     */
    private void zoom() {
        if (pan.getNombre()>10) {
            pan.setNombre(pan.getNombre() - 10);
            pan.originy += 5;
            pan.originx += 5;
        }else{
            Toolkit.getDefaultToolkit().beep();
        }
        if(pan.getNombre()==10)zoom.setEnabled(false);
    }

    /**
     * Accellere la vitesse d'annimation par 2
     */
    private void accellerer(){
            vitesse= (int) (vitesse*0.5);
    }

    /**
     * Ralenti la itesse d'annimation par 2
     */
    private void ralentir(){
            vitesse = (int) (vitesse*2);
    }

    /**
     * Regarde si une touche est tapee
     * @param e evenement de la touche
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * detecte si une touche est pressee
     * @param e evenement de la touche
     */
    @Override
    public void keyPressed(KeyEvent e) {//Todo accellerer ralentir
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                pan.originx+=4;
                break;
            case KeyEvent.VK_UP:
                pan.originy-=4;
                break;
            case KeyEvent.VK_DOWN:
                pan.originy+=4;
                break;
            case KeyEvent.VK_LEFT:
                pan.originx-=4;
                break;
            case KeyEvent.VK_F2:
                accellerer();
                break;
            case KeyEvent.VK_F3:
                ralentir();
                break;
            case KeyEvent.VK_F1:
                AffichageBD.information("Uttilisez les touches directionnelles pour vous deplacer, + ou - du pavé numéfique pour zommer ou dezzommer et f12 pour afficher" +
                        "les credits F2 pour accellerer F3 pour rallentir ");
                break;
            case KeyEvent.VK_F12:
                AffichageBD.information("Credits: SENAT Clement DOUCHET Loic nHERVE Camille ");
                break;
            case 107:
                zoom();
                break;
            case KeyEvent.VK_F5:
                zoom();
                break;
            case KeyEvent.VK_F6:
                dezoom();
                break;
            case 109:
                dezoom();
                break;
        }
        pan.repaint();
    }

    /**
     * detecte si une touche est relachéee
     * @param e evenement de la touche
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

}