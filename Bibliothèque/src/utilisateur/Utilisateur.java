package utilisateur;

public abstract class Utilisateur {
	private final int id;
    protected String nom;
    protected String email;
    private static int cpt = 0;

    public Utilisateur(String nom, String email) {
        this.id = ++cpt;
        this.nom = nom;
        this.email = email;
    }

    public abstract void afficherMenu();

    public String getNom() {
    	return nom;
    }
    
    public String getEmail() {
    	return email;
    }
}
