import javax.swing.*;
import java.util.ArrayList;

public class Parc extends JPanel {
    /**
     *
     */
    /* attributs privés */
    private static final long serialVersionUID = 1L;
    private String nom, ville;
    private int id;
    private ArrayList<Attraction> listeAttractions;

    /* constructeur */
    public Parc(int id, String nom, String ville, ArrayList<Attraction> listeAttractions) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.listeAttractions = listeAttractions;
    }

    /* getter */
    public String getNom() {
        return this.nom;
    }
    /* getter */

    public String getVille() {
        return this.ville;
    }
    /* getter */

    public int getId() {
        return this.id;
    }

    /* fonction qui récupère la liste des attractions et l'affiche au format XML */
    public String getLesAttractions() {
        String chaine = "";
        chaine += "\n" + "<?xml version='1.0' encoding='UTF-8'?>";
        chaine += "\n" + "<lesAttractions>" + "\n";
        for (int i = 0; i < listeAttractions.size(); i++) {
            chaine += listeAttractions.get(i).toXML();
        }
        chaine += "</lesAttractions>";
        return chaine;
    }

    public String calculerCA(String nomParc) {
        String chaine = "";
        chaine += "chiffre d'affaires du parc : " + Database.getLesCA(nomParc) + " €";
        return chaine;
    }

}
