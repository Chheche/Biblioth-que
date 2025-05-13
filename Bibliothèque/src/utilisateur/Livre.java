package utilisateur;

public class Livre {
    private String id;
    private String titre;
    private String auteur;
    private String categorie;

    public Livre(String id, String titre, String auteur, String categorie) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
    }

    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getCategorie() { return categorie; }

    @Override
    public String toString() {
        return "[" + id + "] " + titre + " - " + auteur + " (" + categorie + ")";
    }
}
