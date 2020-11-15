import java.sql.*;
import java.util.ArrayList;

public class Database {
	private static Connection connexion;
	private static PreparedStatement preparedStatement, preparedStatement2;
	private static ResultSet result, resultParc, resultAttractions, resultVisiteurs, resultCommerces,
			resultConsommateurs, resultMagasins, resultArticles, resultCompte;
	private static int resultInsert, resultDelete;

	/* fonction de connexion à la base de données */
	public static void connexionBdd() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connexion = DriverManager
					.getConnection("jdbc:mariadb://localhost:3306/slamland?user=root&password=couleuvre");
			connexion.createStatement();
		}

		catch (SQLException e) {
			System.err.println("Erreur de connexion à la base de donnéees " + e);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver non chargé " + e);
		}
	}

	/* fonction de déconnexion de notre base de données */
	public static void deconnexionBdd() {
		try {
			connexion.close();
		} catch (SQLException e) {
			System.err.println("Erreur lors de la déconnexion " + e);
		}
	}

	/* fonction de connexion au formulaire d'authentification */
	public static boolean connexion(String login, String mdp) {
		boolean rep = false;
		try {
			connexionBdd();
			String rsInfo = "select count(login) as nb from utilisateur where login = ? and mdp = ?;";
			preparedStatement = connexion.prepareStatement(rsInfo);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, mdp);
			result = preparedStatement.executeQuery();
			if (result.next() && result.getInt("nb") > 0) {
				rep = true;
			}
			result.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rep;
	}

	/* fonction qui récupère la liste des parcs */
	public static ArrayList<Parc> getLesParcs() {
		ArrayList<Parc> lesParcs = new ArrayList<Parc>();
		try {
			connexionBdd();
			String rsParc = "select id as idparc1, nom, ville from parcattractions;";
			preparedStatement = connexion.prepareStatement(rsParc);
			resultParc = preparedStatement.executeQuery();
			while (resultParc.next()) {
				ArrayList<Attraction> lesAttractions = new ArrayList<Attraction>();
				int id = resultParc.getInt("idparc1");
				String nom = resultParc.getString("nom");
				String ville = resultParc.getString("ville");

				String rsAttractions = "select nom, capaciteMax, duree from attractions where idparc  = ?;";
				preparedStatement2 = connexion.prepareStatement(rsAttractions);
				preparedStatement2.setInt(1, id);
				result = preparedStatement2.executeQuery();

				while (result.next()) {
					String nomAttraction = result.getString("nom");
					int capaciteMax = result.getInt("capaciteMax");
					int duree = result.getInt("duree");

					lesAttractions.add(new Attraction(nomAttraction, capaciteMax, duree));
				}
				lesParcs.add(new Parc(id, nom, ville, lesAttractions));
			}
			resultParc.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesParcs;
	}

	/* fonctions qui récupère la liste des attractions */
	public static ArrayList<Attraction> getLesAttractions() {
		ArrayList<Attraction> lesAttractions = new ArrayList<Attraction>();
		try {
			connexionBdd();
			String rsAttractions = "select id as idattraction, nom from attractions;";
			preparedStatement = connexion.prepareStatement(rsAttractions);
			resultAttractions = preparedStatement.executeQuery();

			while (resultAttractions.next()) {
				ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
				int idattraction = resultAttractions.getInt("idattraction");
				String nom = resultAttractions.getString("nom");

				String rsVisiteurs = "select prenom, nom, dateNaissance from participer where idattraction = ?;";
				preparedStatement2 = connexion.prepareStatement(rsVisiteurs);
				preparedStatement2.setInt(1, idattraction);
				resultVisiteurs = preparedStatement2.executeQuery();

				while (resultVisiteurs.next()) {
					String prenom = resultVisiteurs.getString("prenom");
					String nomVisiteur = resultVisiteurs.getString("nom");
					String dateNaissance = resultVisiteurs.getString("dateNaissance");

					lesVisiteurs.add(new Visiteur(prenom, nomVisiteur, dateNaissance));
				}
				lesAttractions.add(new Attraction(nom, lesVisiteurs));
			}
			resultAttractions.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesAttractions;
	}

	/* fonction qui récupère la liste des commerces */
	public static ArrayList<Commerce> getLesCommerces() {
		ArrayList<Commerce> lesCommerces = new ArrayList<Commerce>();
		try {
			connexionBdd();
			String rsCommerces = "select id as idcommerce, nom from commerce;";
			preparedStatement = connexion.prepareStatement(rsCommerces);
			resultCommerces = preparedStatement.executeQuery();

			while (resultCommerces.next()) {
				ArrayList<Visiteur> lesConsommateurs = new ArrayList<Visiteur>();
				int idcommerce = resultCommerces.getInt("idcommerce");
				String nomcommerce = resultCommerces.getString("nom");

				String rsConsommateurs = "select idachat, nomconsommateur, prenomconsommateur from acheter where idcommerce = ?;";
				preparedStatement2 = connexion.prepareStatement(rsConsommateurs);
				preparedStatement2.setInt(1, idcommerce);
				resultConsommateurs = preparedStatement2.executeQuery();

				while (resultConsommateurs.next()) {
					String nomconsommateur = resultConsommateurs.getString("nomconsommateur");
					String prenomconsommateur = resultConsommateurs.getString("prenomconsommateur");

					lesConsommateurs.add(new Visiteur(prenomconsommateur, nomconsommateur));
				}
				lesCommerces.add(new Commerce(nomcommerce, lesConsommateurs));
			}
			resultCommerces.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesCommerces;
	}

	/* fonction qui récupère la liste des magasins */
	public static ArrayList<Magasin> getLesMagasins() {
		ArrayList<Magasin> lesMagasins = new ArrayList<Magasin>();
		try {
			connexionBdd();
			String rsMagasins = "select idmagasin, nom from magasin;";
			preparedStatement = connexion.prepareStatement(rsMagasins);
			resultMagasins = preparedStatement.executeQuery();

			while (resultMagasins.next()) {
				ArrayList<Article> lesArticles = new ArrayList<Article>();
				int idmagasin = resultMagasins.getInt("idmagasin");
				String nommagasin = resultMagasins.getString("nom");

				String rsArticles = "select idarticle, idmagasin, libelle, prix from posseder where idmagasin = ?;";
				preparedStatement2 = connexion.prepareStatement(rsArticles);
				preparedStatement2.setInt(1, idmagasin);
				resultArticles = preparedStatement2.executeQuery();

				while (resultArticles.next()) {
					String libellearticle = resultArticles.getString("libelle");
					float prixarticle = resultArticles.getFloat("prix");

					lesArticles.add(new Article(libellearticle, prixarticle));
				}
				lesMagasins.add(new Magasin(nommagasin, lesArticles));
			}
			resultMagasins.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesMagasins;
	}

	/* fonction qui récupère le chiffre d'affaires du parc selectionné */
	public static int getLesCA(String nomParc) {
		int total = 0;
		int nbParticipations, prixbillet;
		try {
			connexionBdd();
			String rsParc = "select id as idparc, prixbillet from parcattractions where nom = '" + nomParc + "';";
			preparedStatement = connexion.prepareStatement(rsParc);
			resultParc = preparedStatement.executeQuery();

			while (resultParc.next()) {
				int id = resultParc.getInt("idparc");
				prixbillet = resultParc.getInt("prixbillet");

				String rsParticipations = "select count(idparticipation) as nbParticipations from participer where idparc = ?;";
				preparedStatement2 = connexion.prepareStatement(rsParticipations);
				preparedStatement2.setInt(1, id);
				resultCompte = preparedStatement2.executeQuery();

				while (resultCompte.next()) {
					nbParticipations = resultCompte.getInt("nbParticipations");
					/*
					 * on récupère le chiffre d'affaires de l'attraction en multipliant le prix par
					 * le nombre de participants de l'attraction
					 */
					total = prixbillet * nbParticipations;
				}
			}
			resultParc.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	/* fonction qui ajoute un visiteur avec les données passées en paramètre */
	public static int ajouterVisiteur(int valId, String valNom, String valPrenom, String valDateNaissance) {
		try {
			connexionBdd();
			String rsInsert = "insert into visiteur (id, nom, prenom, dateNaissance) VALUES (?, ?, ?, ?);";
			preparedStatement = connexion.prepareStatement(rsInsert);

			preparedStatement.setInt(1, valId);
			preparedStatement.setString(2, valNom);
			preparedStatement.setString(3, valPrenom);
			preparedStatement.setString(4, valDateNaissance);

			resultInsert = preparedStatement.executeUpdate();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultInsert;
	}

	/* récupère les visiteurs sous la forme d'une ArrayList de type Visiteur */
	public static ArrayList<Visiteur> getLesVisiteurs() {
		ArrayList<Visiteur> visiteurs1 = new ArrayList<Visiteur>();
		try {
			connexionBdd();
			String rsSelect = "select id, nom, prenom, dateNaissance from visiteur;";
			preparedStatement = connexion.prepareStatement(rsSelect);

			resultVisiteurs = preparedStatement.executeQuery();

			while (resultVisiteurs.next()) {
				int id = resultVisiteurs.getInt("id");
				String nom = resultVisiteurs.getString("nom");
				String prenom = resultVisiteurs.getString("prenom");
				String dateNaissance = resultVisiteurs.getString("dateNaissance");
				Visiteur visiteur = new Visiteur(id, nom, prenom, dateNaissance);
				visiteurs1.add(visiteur);
			}
			resultVisiteurs.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visiteurs1;
	}

	/*
	 * supprime le visiteur de la base de données dont l'id est passé en paramètre
	 */
	public static int supprimerVisiteur(int id) {
		try {
			connexionBdd();
			String rsDelete = "delete from visiteur where id = ?";
			preparedStatement = connexion.prepareStatement(rsDelete);

			preparedStatement.setInt(1, id);

			resultDelete = preparedStatement.executeUpdate();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultDelete;
	}

}