import javax.swing.*;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VueAttractions extends JPanel implements ActionListener {
    private JTable table;
    private ArrayList<Parc> listeParcs;
    private JFrame frame;
    private JComboBox<String> parcs = new JComboBox<>();
    private JLabel welcome, generate;
    private TextArea textArea;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VueAttractions(JFrame frame) {
        this.frame = frame;
        listeParcs = Database.getLesParcs();
        welcome = new JLabel();
        welcome.setText("Veuillez sélectionner un parc d'attractions");
        welcome.setBounds(50, 50, 50, 50);
        /* ajoute chaque élément de notre liste de parcs dans une JComboBox */
        for (int i = 0; i < listeParcs.size(); i++) {
            parcs.addItem(listeParcs.get(i).getNom() + " " + listeParcs.get(i).getVille());
        }
        /* texte informatif */
        generate = new JLabel();
        generate.setText("Un texte au format XML sera affiché dans la console");
        generate.setBounds(150, 150, 100, 100);

        textArea = new TextArea();
        textArea.setBounds(10, 30, 300, 300);

        /* ajoute les éléments à notre panel */
        this.add(welcome);
        this.add(generate);
        this.add(parcs);
        this.add(textArea);
        this.add(new JScrollPane(table));
    }

    /* fonction qui nous permet de remplir notre zone de texte */
    public void setText(String listeXML) {
        textArea.setText(listeXML);
        textArea.setEditable(false);
    }

    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();

        parcs.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = parcs.getSelectedIndex();

                Parc parc = listeParcs.get(index);
                setText(parc.getLesAttractions());
            }
        });

    }
}