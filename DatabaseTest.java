import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class DatabaseTest {
	
	/* classe de test permettant de v�rifier la m�thode getLesAttractions() */
	@Test
	void testGetLesAttractions() {
		ArrayList <Attraction> lesAttractions = new ArrayList<Attraction>();
		int nombreAttractions = Database.getNbAttractions();
		lesAttractions = Database.getLesAttractions();
		assertEquals("Erreur", nombreAttractions, lesAttractions.size());
		
		String listeAttractions = Database.afficherLesAttractions();
		System.out.println(listeAttractions);
		System.out.println("nombre d'attractions : " + nombreAttractions);
	}
	
	/* classe de test qui v�rifie que la m�thode toXML() ne retourne pas une cha�ne
	vide mais bien le r�sultat attendu. */
	@Test
	void testXML() {
		ArrayList <Attraction> lesAttractions = new ArrayList<Attraction>();
		lesAttractions = Database.getLesAttractions();
		String chaine = " ";
		for (int i = 0; i < lesAttractions.size(); i++) {
			Attraction attraction = new Attraction(lesAttractions.get(i).getNom(), lesAttractions.get(i).getCapaciteMax(), lesAttractions.get(i).getDuree(), lesAttractions.get(i).getPrix());
			chaine += attraction.toXML();
		}
		Assert.assertFalse("La m�thode toXML retourne une chaine vide", chaine.isEmpty());
	}
	
	/* classe de test qui v�rifie que la m�thode getDureeTotaleAttractions()
		retourne bien la dur�e attendue dans tous les cas. */
	@Test
	void testGetDureeTotaleAttractions() {
		int duree = 0;
		ArrayList <Attraction> lesAttractions = new ArrayList<Attraction>();
		lesAttractions = Database.getLesAttractions();
		
		int dureeTotale = Database.getDureeAttractions();
		
		for (int i = 0; i < lesAttractions.size(); i++) {
			Attraction attraction = new Attraction(lesAttractions.get(i).getNom(), lesAttractions.get(i).getCapaciteMax(), lesAttractions.get(i).getDuree(), lesAttractions.get(i).getPrix());
			duree += attraction.getDuree();
		}
		assertEquals("Dur�e totale incorrecte", duree, dureeTotale);
	}
	
	/* classe de test afin de tester la m�thode d'ajout */
	@Test
	void testAjoutVisiteur() {
		int idTest = 19;
		int id = 0;
		
		ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
		lesVisiteurs = Database.getLesVisiteurs();
				
		Visiteur leVisiteur = new Visiteur(idTest);
		lesVisiteurs.add(leVisiteur);
		
		for (int i = 0; i < lesVisiteurs.size(); i++) {
			if (lesVisiteurs.get(i).getId() == 19) {
				id = lesVisiteurs.get(i).getId();
			}
		}
		
		Assert.assertEquals("Erreur d'ajout d'un visiteur", id, idTest);
	}
	
	/* classe de test afin de tester la m�thode de recherche */
	@Test
	void testRechercheVisiteur() {
		int idTest = 7;
		ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
		lesVisiteurs = Database.getLesVisiteurs();
		
		boolean recherche = Database.rechercheVisiteur(idTest);
		
		Assert.assertEquals("erreur de recherche", recherche, true);	
	}
	
	/* classe de test afin de tester la m�thode de suppression */
	@Test
	void testSuppressionVisiteur() {
		int idTest = 19;
		int id = 0;
		
		ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
		lesVisiteurs = Database.getLesVisiteurs();
				
		Visiteur leVisiteur = new Visiteur(idTest);
		
		int suppr = Database.supprimerVisiteur(idTest);
		
		for (int i = 0; i < lesVisiteurs.size(); i++) {
			if (lesVisiteurs.get(i).getId() == idTest) {
				id = lesVisiteurs.get(i).getId();
			}
		}

		Assert.assertEquals("Erreur de suppression d'un visiteur", suppr, id);
	}
	
	
}
