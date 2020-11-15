import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class VueGestion extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private JButton btnSupprimer;
    private DefaultTableModel tableModel;
    private JTextField nom, prenom, dateNaissance, id;
    private JLabel lblnom, lblprenom, lbldate, lblid;
    private JButton ajouterButton;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VueGestion(JFrame frame) {
        this.frame = frame;

        /*
         * création de notre bouton pour supprimer un visiteur de notre base de données
         */
        /* label id */

        remplirPanel();
        frame.setVisible(true);
    }

    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesVisiteurs().size()][4];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id visiteur");
        tableModel.addColumn("nom visiteur");
        tableModel.addColumn("pr�nom visiteur");
        tableModel.addColumn("date de naissance");

        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesVisiteurs().size(); i++) {
            donnees[i][0] = Database.getLesVisiteurs().get(i).getId();
            donnees[i][1] = Database.getLesVisiteurs().get(i).getNom();
            donnees[i][2] = Database.getLesVisiteurs().get(i).getPrenom();
            donnees[i][3] = Database.getLesVisiteurs().get(i).getDateNaissance();
            tableModel.addRow(donnees[i]);
        }

        // this.add(btnSupprimer);
        btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBounds(190, 270, 150, 25);
        btnSupprimer.setBounds(175, 270, 150, 25);
        btnSupprimer.setBackground(new Color(59, 89, 182));
        btnSupprimer.setForeground(Color.WHITE);
        btnSupprimer.setFocusPainted(false);
        btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSupprimer.addActionListener(this);

        lblid = new JLabel("Id");
        lblid.setBounds(110, 100, 80, 25);

        /* champ de texte id */
        id = new JTextField(10);
        id.setBounds(175, 100, 160, 25);

        /* label prénom */
        lblprenom = new JLabel("Pr�nom");
        lblprenom.setBounds(110, 140, 80, 25);

        /* champ de texte prénom */
        prenom = new JTextField(10);
        prenom.setBounds(175, 140, 160, 25);

        /* label nom */
        lblnom = new JLabel("Nom");
        lblnom.setBounds(125, 180, 80, 25);

        /* champ de texte nom */
        nom = new JTextField(20);
        nom.setBounds(175, 180, 160, 25);

        /* label date de naissance */
        lbldate = new JLabel("Date de naissance");
        lbldate.setBounds(40, 220, 150, 25);

        /* champ de texte date de naissance */
        dateNaissance = new JTextField(10);
        dateNaissance.setBounds(175, 220, 160, 25);

        ajouterButton = new JButton("Ajouter");
        ajouterButton.setBounds(175, 270, 150, 25);
        ajouterButton.setBackground(new Color(59, 89, 182));
        ajouterButton.setForeground(Color.WHITE);
        ajouterButton.setFocusPainted(false);
        ajouterButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        ajouterButton.addActionListener(this);

        /* ajoute les éléments à notre panel */

        this.add(lblid);
        this.add(id);
        this.add(lblprenom);
        this.add(prenom);
        this.add(lblnom);
        this.add(nom);
        this.add(lbldate);
        this.add(dateNaissance);

        this.add(ajouterButton);
        this.add(btnSupprimer);

        /* création du table + remplissage */
        table = new JTable(tableModel);

        /* ajout du tableau à un scrollpane */
        this.add(new JScrollPane(table));

    }

    /* ajout du bouton supprimer au panel */

    /*
     * fonction qui affiche une boite de dialogue pour confirmer la supression d'un
     * visiteur
     */
    public void removed() {
        JOptionPane.showMessageDialog(this, "Suppression réussie.");
        removeAll();
        remplirPanel();
        repaint();
        revalidate();
    }

    /*
     * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
     * la supression
     */
    public void notRemoved() {
        JOptionPane.showMessageDialog(this, "Erreur.", "Erreur lors de la suppression", JOptionPane.WARNING_MESSAGE);
        removeAll();
        remplirPanel();
        repaint();
        revalidate();
    }

    /*
     * fonction qui affiche une boite de dialogue pour confirmer l'ajout d'un
     * visiteur
     */
    public void added() {
        JOptionPane.showMessageDialog(this, "Ajout réussi.");
        removeAll();
        remplirPanel();
        repaint();
        revalidate();
    }

    /*
     * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
     * l'ajout
     */
    public void notAdded() {
        JOptionPane.showMessageDialog(this, "L'id saisi existe déjà.", "Erreur d'ajout'", JOptionPane.WARNING_MESSAGE);
        removeAll();
        remplirPanel();
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();

        if (e.getSource() == btnSupprimer) {
            /* on récupère la colonne et la ligne sur laquelle la sélection est faite */
            int row = table.getSelectedRow();
            int column = 0;
            /* on converti la valeur récuperée en entier */
            int id = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
            /*
             * appelle la boite de dialogue en fonction du résultat renvoyé par la fonction
             */
            if (Database.supprimerVisiteur(id) == 1) {
                removed();
            } else {
                notRemoved();
            }

        } else if (e.getSource() == ajouterButton) {
            int valId = Integer.parseInt(id.getText());
            String valPrenom = prenom.getText();
            String valNom = nom.getText();
            String valDateNaissance = dateNaissance.getText();

            /* vide les champs de texte une fois l'ajout effectué */
            id.setText("");
            prenom.setText("");
            nom.setText("");
            dateNaissance.setText("");

            Database.ajouterVisiteur(valId, valNom, valPrenom, valDateNaissance);
            /*
             * appelle la boite de dialogue en fonction du résultat renvoyé par la fonction
             */
            if (Database.ajouterVisiteur(valId, valNom, valPrenom, valDateNaissance) == 1) {
                added();
            } else {
                notAdded();
            }
        }

    }
}