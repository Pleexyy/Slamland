import javax.swing.*;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VueVisiteurs extends JPanel implements ActionListener {
    private ArrayList<Attraction> listeAttractions;
    private JFrame frame;
    private JComboBox<String> attractions = new JComboBox<>();
    private JLabel welcome, generate, generate2;
    private TextArea textArea;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VueVisiteurs(JFrame frame) {
        this.frame = frame;
        listeAttractions = Database.getLesAttractions();
        welcome = new JLabel();
        welcome.setText("Veuillez sélectionner une attraction");
        welcome.setBounds(50, 50, 50, 50);

        /* ajoute chaque élément de notre liste d'attractions dans une JComboBox */
        for (int i = 0; i < listeAttractions.size(); i++) {
            attractions.addItem(listeAttractions.get(i).getNom());
        }

        textArea = new TextArea();
        textArea.setBounds(10, 30, 300, 300);

        /* texte informatif */
        generate = new JLabel();
        generate.setText("Un texte au format JSON sera affiché dans la console.");
        generate.setBounds(150, 150, 100, 100);

        /* texte informatif */
        generate2 = new JLabel();
        generate2.setText("Il contiendra la liste des visiteurs ayant participé à l'attraction choisie");
        generate2.setBounds(175, 150, 100, 100);

        /* ajoute les éléments à notre JPanel */
        this.add(welcome);
        this.add(generate);
        this.add(generate2);
        this.add(attractions);
        this.add(textArea);
    }

    /* fonction qui nous permet de remplir notre zone de texte */
    public void setText(String visiteurs) {
        textArea.setText(visiteurs);
        textArea.setEditable(false);
    }

    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();

        attractions.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = attractions.getSelectedIndex();

                Attraction attraction = listeAttractions.get(index);
                textArea.setText(attraction.getLesVisiteurs());
            }
        });

    }
}