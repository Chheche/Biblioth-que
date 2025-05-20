package bibliotheque;

public class Livre {
    private final int id;
    private String titre;
    private String auteur;
    private String categorie;
    private static int cpt = 0;

    /***
     * Contructeur Livre
     * Utilisé quand un admin crée un nouveau livre
     * @param titre
     * @param auteur
     * @param categorie
     */
    public Livre(String titre, String auteur, String categorie) {
        this.id = ++cpt;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
    }
    
    /***
     * Constructeur Livre
     * Utilisé lors du chargement depuis le fichier
     * @param id
     * @param titre
     * @param auteur
     * @param categorie
     */
    public Livre(int id, String titre, String auteur, String categorie) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;

        if (id > cpt) {
            cpt = id;
        }
    }

    public String getTitre() { 
    	return titre; 
    }
    
    public String getAuteur() {
    	return auteur;
    }
    
    public String getCategorie() {
    	return categorie;
    }

    @Override
    public String toString() {
        return " Livre [" + id + "]: " + titre + " - " + auteur + " (" + categorie + ")";
    }

	public int getId() {
		return id;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
}
