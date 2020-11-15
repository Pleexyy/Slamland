import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Window() {
		/* settings de base de notre fenetre */
		this.setResizable(false);
		this.setSize(500, 525);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Slamland");

		// permet de centrer la frame au milieu de l'écran
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		/* la première vue appellée est celle de la connexion */
		VueConnexion pageConnexion = new VueConnexion(this);
		this.getContentPane().add(pageConnexion);

		this.setVisible(true);
	}
}
