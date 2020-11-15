import java.util.ArrayList;

public class Attraction {

    /* attributs privés */
    private String nom, chaine;
    private int capaciteMax, duree;
    private ArrayList<Visiteur> lesVisiteurs;

    /* constructeur */
    public Attraction(String nom, int capaciteMax, int duree) {
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.duree = duree;
    }

    /* constructeur */
    public Attraction(String nom, int capaciteMax, int duree, float prix, ArrayList<Visiteur> lesVisiteurs) {
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.duree = duree;
        this.lesVisiteurs = lesVisiteurs;
    }

    /* constructeur */
    public Attraction(String nom, ArrayList<Visiteur> lesVisiteurs) {
        this.nom = nom;
        this.lesVisiteurs = lesVisiteurs;
    }

    /* constructeur */
    public Attraction(String nom) {
        this.nom = nom;
    }

    /* getter */
    public String getNom() {
        return this.nom;
    }

    /* récupère les visiteurs d'une attraction et l'affiche au format JSON */
    public String getLesVisiteurs() {
        String chaine = "\n";
        chaine += "{\"visiteurs\":" + '\n';
        for (int i = 0; i < this.lesVisiteurs.size(); i++) {
            chaine += this.lesVisiteurs.get(i).toJSON();
        }
        chaine += "}";
        return chaine;
    }

    /* fonction qui renvoie les informations d'une attraction au format XML */
    public String toXML() {
        chaine = "";
        chaine += "     <attraction>" + "\n";
        chaine += "          <nom>" + this.nom + "</nom>" + "\n";
        chaine += "          <capaciteMax>" + this.capaciteMax + "</capaciteMax>" + "\n";
        chaine += "          <duree>" + this.duree + "</duree>" + "\n";
        chaine += "     </attraction>" + "\n";
        return chaine;
    }

}
