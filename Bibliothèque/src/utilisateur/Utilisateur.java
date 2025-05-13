package utilisateur;

public abstract class Utilisateur {
    protected String id;
    protected String nom;
    protected String email;

    public Utilisateur(String id, String nom, String email) {
        this.id = id;
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
    
    public String getId() {
    	return id;
    }
}
