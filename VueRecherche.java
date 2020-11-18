import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueRecherche extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userText;
	private JButton searchButton;
	private JLabel userLabel, welcome;
	private JFrame frame;

	public VueRecherche(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);

		/* texte informatif */
		welcome = new JLabel("Veuillez saisir l'id du visiteur à rechercher");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(90, 40, 380, 100);
		
		userLabel = new JLabel("Id visiteur");
		userLabel.setBounds(115, 150, 80, 25);

		/* champ de saisi de l'id */
		userText = new JTextField(20);
		userText.setBounds(175, 150, 160, 25);

		/* bouton de recherche */
		searchButton = new JButton("Rechercher");
		searchButton.setBounds(175, 230, 150, 25);
		searchButton.setBackground(new Color(59, 89, 182));
		searchButton.setForeground(Color.WHITE);
		searchButton.setFocusPainted(false);
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		searchButton.addActionListener(this);

		/* ajoute les éléments à notre panel */
		this.add(welcome);
		this.add(userLabel);
		this.add(userText);
		this.add(searchButton);

		setVisible(true);

	}

	/* fonction qui affiche une boite de dialogue pour confirmer la recherche */
	public void found() {
		JOptionPane.showMessageDialog(this, "Visiteur trouvé !");
		revalidate();
	}

	/*
	 * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
	 * la recherche
	 */
	public void notFound() {
		JOptionPane.showMessageDialog(this, "L'id ne correspond à aucun visiteur.",
				"Erreur", JOptionPane.WARNING_MESSAGE);
		revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * appelle notre fonction de connexion à la base de données et affiche notre
		 * menu si la connexion a bien été effectuée
		 */
        this.frame.setContentPane(this);
        this.frame.revalidate();
        
        /* récupére l'id et le passe en paramètre à notre fonction */
		if (e.getSource() == searchButton) {
			int id = Integer.parseInt(userText.getText());
			if (Database.rechercheVisiteur(id)) {
				found();
			} else {
				notFound();
			}
		}
	}
}
