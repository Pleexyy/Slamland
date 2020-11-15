import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.TextArea;

public class VueMagasin extends JPanel implements ActionListener {
    private ArrayList<Magasin> listeMagasins;
    private JFrame frame;
    private JComboBox<String> articles = new JComboBox<>();
    private JLabel welcome, generate, generate2;
    private TextArea textArea;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VueMagasin(JFrame frame) {
        this.frame = frame;
        listeMagasins = Database.getLesMagasins();
        welcome = new JLabel();
        welcome.setText("Veuillez sélectionner un magasin.");
        welcome.setBounds(50, 50, 50, 50);

        /* ajoute chaque élément de notre liste de magasins dans une JComboBox */
        for (int i = 0; i < listeMagasins.size(); i++) {
            articles.addItem(listeMagasins.get(i).getNom());
        }

        /* texte informatif */
        generate = new JLabel();
        generate.setText("Un tableau sera généré.");
        generate.setBounds(150, 150, 100, 100);

        /* texte informatif */
        generate2 = new JLabel();
        generate2.setText("Il contiendra la liste des articles présents dans le magasin choisi.");
        generate2.setBounds(175, 150, 100, 100);

        textArea = new TextArea();
        textArea.setBounds(10, 30, 300, 300);

        /* ajoute les éléments à notre panel */
        this.add(welcome);
        this.add(generate);
        this.add(generate2);
        this.add(articles);
        this.add(textArea);
    }

    /* fonction qui nous permet de remplir notre zone de texte */
    public void setText(String listeArticles) {
        textArea.setText(listeArticles);
        textArea.setEditable(false);
    }

    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();

        articles.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = articles.getSelectedIndex();

                Magasin magasin = listeMagasins.get(index);
                // System.out.println(magasin.getLesArticles());
                setText(magasin.getLesArticles());
            }
        });

    }
}