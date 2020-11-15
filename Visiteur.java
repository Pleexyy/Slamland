public class Visiteur {

    /* attributs privés */
    private String nom, prenom, dateNaissance, chaine;
    private int id;

    /* constructeur */
    public Visiteur(String nom, String prenom, String dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    /* constructeur */
    public Visiteur(int id, String nom, String prenom, String dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    /* constructeur */
    public Visiteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    /* getter */
    public String getNom() {
        return this.nom;
    }

    /* getter */
    public String getPrenom() {
        return this.prenom;
    }

    /* getter */
    public String getDateNaissance() {
        return this.dateNaissance;
    }

    /* getter */
    public int getId() {
        return this.id;
    }

    /* fonction qui renvoie une chaine de caractères au format JSON */
    public String toJSON() {
        chaine = "";
        chaine += "[{ \"prenom\":" + "\"" + this.prenom + "\"" + ", \"nom\":" + "\"" + this.nom + "\""
                + ", \"dateNaissance\": " + "\"" + this.dateNaissance + "\"" + "}]" + "\n";
        return chaine;
    }

    /* fonction qui renvoie une chaine de caractères au format CSV */
    public String toCSV() {
        chaine = "";
        chaine += this.prenom + "," + this.nom;
        return chaine;
    }

}
