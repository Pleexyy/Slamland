import java.util.ArrayList;

public class Magasin {
    /* attributs privés */
    private String nom;
    private ArrayList<Article> lesArticles;

    /* constructeur */
    public Magasin(ArrayList<Article> lesArticles) {
        this.lesArticles = lesArticles;
    }

    /* constructeur */
    public Magasin(String nom, ArrayList<Article> lesArticles) {
        this.nom = nom;
        this.lesArticles = lesArticles;
    }

    /* getter */
    public String getNom() {
        return this.nom;
    }

    /* fonction qui récupère les articles et l'affiche au format JSON */
    public String getLesArticles() {
        String chaine = "\n";
        chaine += "{\"articles\":" + '\n';
        for (int i = 0; i < this.lesArticles.size(); i++) {
            chaine += this.lesArticles.get(i).toJSON();
        }
        chaine += "}";
        return chaine;
    }

}
