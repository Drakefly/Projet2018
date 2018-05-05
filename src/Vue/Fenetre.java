package Vue;

import Modele.Cellule;
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
    final private Panneau panneau = new Panneau();
    final private JButton buttonDezoom = new JButton("Dezoom");
    final private JButton buttonZoom = new JButton("Zoom");
    private boolean close = false;
    private int vitesse=300;

    /**
     * La fenetre de l'interface graphique
     */
    public Fenetre() {//Todo faire fonctionner pour limité et sphérique.
        //Parametres de la fenetre
        this.setTitle("Jeu de la vie");
        final int TAILLE = 600;
        this.setSize(TAILLE , TAILLE + 25);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setFocusable(true);

        //Parametre du panneau
        panneau.setDimmension(TAILLE);
        panneau.setNombreCases(20);
        panneau.originx=(-10);
        panneau.originy=(-10);

        //KeyListener
        addKeyListener(this);

        //Boutons
        buttonDezoom.setBounds(TAILLE - 40, 40, 100, 30);//Il vaudrait mieux diviser la tailler totale en nombre de case demander
        buttonDezoom.addActionListener(this);
        panneau.add(buttonDezoom);

        buttonZoom.setBounds(TAILLE - 40, 75, 100, 30);
        buttonZoom.addActionListener(this);
        panneau.add(buttonZoom);

        this.setContentPane(panneau);
    }

    /**
     * Getter de vitesse
     * @return permet de renvoyer la vitesse
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     * Remet l'echelle par defaut pour lancer l'annimation
     */
    public void setDefaultEchelle() {
        panneau.setNombreCases(30);
        panneau.originx=(-10);
        panneau.originy=(-10);
    }

    /**
     * SETTER DE CLOSE
     * @return true si close est vraie
     */
    public boolean isClose() {
        return close;
    }

    /**
     * Met a jour l'interface graphique
     * @param liste La nouvelle liste permettant la maj
     * @param numeroSim numero de simulation permmetant la maj
     */
    public void go(Liste<Cellule> liste, int numeroSim) {
        panneau.setListe(liste);
        panneau.setNumeroSim(numeroSim);
        panneau.repaint();
    }

    /**
     * Controle les boutons
     * @param arg0 le bouton clique
     */
    public void actionPerformed(ActionEvent arg0) {
        //Lorsque l'on clique sur le bouton, on met a jour le JLabel
        if (arg0.getSource() == buttonDezoom) dezoom();//switch imposible ne fonctionne qu'avec des constantes
        if (arg0.getSource() == buttonZoom) zoom();
    }

    /**
     * Permet de dezoomer
     */
    private void dezoom() {
        buttonZoom.setEnabled(true);
        panneau.setNombreCases(panneau.getNombreCases() + 10);//Dezoom
        panneau.originx -= 5;
        panneau.originx -= 5;
        if (panneau.getNombreCases() == 160) AffichageBD.information("Attention trop dezoomer peut ralentir le programme");
        panneau.repaint();
    }

    /**
     * Permet de zoomer
     */
    private void zoom() {
        if (panneau.getNombreCases()>10) {
            panneau.setNombreCases(panneau.getNombreCases() - 10);
            panneau.originy += 5;
            panneau.originx += 5;
        }else{
            Toolkit.getDefaultToolkit().beep();
        }
        if(panneau.getNombreCases()==10) buttonZoom.setEnabled(false);
    }

    public void termine(boolean b){
        panneau.setTermine(b);
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
            vitesse =  (vitesse*2);
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
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                panneau.originx+=4;
                break;
            case KeyEvent.VK_UP:
                panneau.originy-=4;
                break;
            case KeyEvent.VK_DOWN:
                panneau.originy+=4;
                break;
            case KeyEvent.VK_LEFT:
                panneau.originx-=4;
                break;
            case KeyEvent.VK_F2:
                accellerer();
                break;
            case KeyEvent.VK_F3:
                ralentir();
                break;
            case KeyEvent.VK_ESCAPE:
            close=true;
                break;
            case KeyEvent.VK_F1:
                AffichageBD.information("Uttilisez les touches directionnelles pour vous deplacer, + ou - du pave numefique pour zommer ou dezzommer et f12 pour afficher" +
                        "les credits F2 pour accellerer F3 pour rallentir, Echap pour quitter ");
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
        panneau.repaint();
    }

    /**
     * detecte si une touche est relachee
     * @param e evenement de la touche
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }


}