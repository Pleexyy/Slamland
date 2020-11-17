import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class DatabaseTest {

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
	
	
	@Test
	void testXML() {
		ArrayList <Attraction> lesAttractions = new ArrayList<Attraction>();
		lesAttractions = Database.getLesAttractions();
		String chaine = " ";
		for (int i = 0; i < lesAttractions.size(); i++) {
			Attraction attraction = new Attraction(lesAttractions.get(i).getNom(), lesAttractions.get(i).getCapaciteMax(), lesAttractions.get(i).getDuree(), lesAttractions.get(i).getPrix());
			chaine = chaine + attraction.toXML();
		}
		Assert.assertFalse("La méthode XML retourne une chaine vide", chaine.isEmpty());
	}
	
}
