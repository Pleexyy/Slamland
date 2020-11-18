import java.util.ArrayList;

public class Commerce {
    /* attributs priv�s */
    private String nom;
    private ArrayList<Visiteur> lesConsommateurs;

    /* constructeur */
    public Commerce(String nom, ArrayList<Visiteur> lesConsommateurs) {
        this.nom = nom;
        this.lesConsommateurs = lesConsommateurs;
    }

    /* getter */
    public String getNom() {
        return this.nom;
    }

    /*
     * r�cup�re la liste des personnes ayant r�alis� un ou plusieurs achats et
     * l'affiche au format CSV
     */
    public String getLesConsommateurs() {
        String chaine = "";
        chaine += "\n" + "pr�nom consommateur, nom consommateur" + "\n";
        for (int i = 0; i < lesConsommateurs.size(); i++) {
            chaine += lesConsommateurs.get(i).toCSV();
        }
        return chaine;
    }

}
