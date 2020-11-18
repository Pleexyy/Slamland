import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenu extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JMenuBar barre;
    private JMenu menu, gestionVisiteurs;
    private JMenuItem attraction, visiteurs, commerces, magasins, ca, modificationVisiteurs, rechercherVisiteur;

    public VueMenu(JFrame frame) {
        barre = new JMenuBar();
        menu = new JMenu("Affichage des informations");
        gestionVisiteurs = new JMenu("Gestion des visiteurs");

        /* cr�ation de chaque option de menu */
        ca = new JMenuItem("Afficher le chiffre d'affaires d'un parc d'attractions");
        attraction = new JMenuItem("Afficher les attractions d'un parc");
        visiteurs = new JMenuItem("Afficher les visiteurs d'une attraction");
        commerces = new JMenuItem("Afficher les consommateurs");
        magasins = new JMenuItem("Afficher les articles d'un magasin");

        modificationVisiteurs = new JMenuItem("Ajouter / Supprimer un visiteur");
        rechercherVisiteur = new JMenuItem("Rechercher un visiteur");

        /* ajout des actions listeners pour chaque items de notre menu */
        attraction.addActionListener(new VueAttractions(frame));
        visiteurs.addActionListener(new VueVisiteurs(frame));
        commerces.addActionListener(new VueCommerce(frame));
        magasins.addActionListener(new VueMagasin(frame));
        ca.addActionListener(new VueCa(frame));
        
        modificationVisiteurs.addActionListener(new VueGestion(frame));
        rechercherVisiteur.addActionListener(new VueRecherche(frame));
        
        /* ajoute les items cr��s � notre menu */
        menu.add(ca);
        menu.add(attraction);
        menu.add(visiteurs);
        menu.add(commerces);
        menu.add(magasins);

        /* ajoute les items cr��s à notre second menu */
        gestionVisiteurs.add(modificationVisiteurs);
        gestionVisiteurs.add(rechercherVisiteur);

        /* ajoute les menus � notre barre */
        barre.add(menu);
        barre.add(gestionVisiteurs);

        this.add(barre);

        frame.getContentPane().add(this);
        /* ajoute le menu � notre fenetre */
        frame.setJMenuBar(barre);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}