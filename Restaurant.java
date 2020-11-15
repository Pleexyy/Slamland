public class Restaurant {
    /* attributs privés */
    private String nbTables;
    private int capacite;

    /* constructeur */
    public Restaurant(String nbTables, int capacite) {
        this.nbTables = nbTables;
        this.capacite = capacite;
    }

    /* getter */
    public String getNbTables() {
        return this.nbTables;
    }

    /* getter */
    public int getCapacite() {
        return this.capacite;
    }

}
