public class Article {
    /* attributs privés */
    private String libelle, chaine;
    private float prix;

    /* constructeur */
    public Article(String libelle, float prix) {
        this.libelle = libelle;
        this.prix = prix;
    }

    /* fonction qui renvoie les informations d'un article au format JSON */
    public String toJSON() {
        chaine = "";
        chaine += "[{\"libelle\":" + "\"" + this.libelle + "\"" + ", \"prix\":" + "\"" + this.prix + "\"" + "}]" + "\n";
        return chaine;
    }

}
