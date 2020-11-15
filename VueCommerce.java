import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.TextArea;

public class VueCommerce extends JPanel implements ActionListener {
    private JTable table;
    private ArrayList<Commerce> listeCommerces;
    private JFrame frame;
    private JComboBox<String> commerces = new JComboBox<>();
    private JLabel welcome, generate, generate2;
    private TextArea textArea;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VueCommerce(JFrame frame) {
        this.frame = frame;
        listeCommerces = Database.getLesCommerces();
        welcome = new JLabel();
        welcome.setText("Veuillez sélectionner un commerce");
        welcome.setBounds(50, 50, 50, 50);

        /* ajoute chaque élément de notre liste de commerces dans une JComboBox */
        for (int i = 0; i < listeCommerces.size(); i++) {
            commerces.addItem(listeCommerces.get(i).getNom());
        }
        /* texte informatif */
        generate = new JLabel();
        generate.setText("Un texte au format CSV sera affiché dans la console.");
        generate.setBounds(150, 150, 100, 100);

        /* texte informatif */
        generate2 = new JLabel();
        generate2.setText("Il contiendra la liste des consommateurs (personnes ayant acheté)");
        generate2.setBounds(175, 150, 100, 100);

        textArea = new TextArea();
        textArea.setBounds(10, 30, 300, 300);

        /* ajoute les éléments à notre panel */
        this.add(welcome);
        this.add(generate);
        this.add(generate2);
        this.add(commerces);
        this.add(textArea);

        this.add(new JScrollPane(table));

    }

    /* fonction qui nous permet de remplir notre zone de texte */
    public void setText(String listeConsommateurs) {
        textArea.setText(listeConsommateurs);
        textArea.setEditable(false);
    }

    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();

        commerces.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = commerces.getSelectedIndex();

                Commerce commerce = listeCommerces.get(index);
                /* ajout du texte à notre zone de texte */
                setText(commerce.getLesConsommateurs());
            }
        });

    }
}