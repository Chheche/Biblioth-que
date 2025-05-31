package utilisateur;

import bibliotheque.Emprunt;
import observer.*;

public abstract class Utilisateur implements Observer {
	protected final int id;
    protected String nom;
    protected String email;
    protected String motDePasse;
    private static int cpt = 0;

    /***
     * Constructeur Uilisateur
     * Permet la création d'un utilisateur
     * @param nom
     * @param email
     * @param motDePasse
     */
    public Utilisateur(String nom, String email, String motDePasse) {
        this.id = ++cpt;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }
    
    /***
     * Constructeur Utilisateur spécial
     * Permet la création d'un utilisateur avec son id lors du chargement du fichier
     * @param id
     * @param nom
     * @param email
     * @param motDePasse
     */
    public Utilisateur(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;

        if (id >= cpt) {
            cpt = id + 1;
        }
    }

    public abstract void afficherMenu();

    public String getNom() {
    	return nom;
    }
    
    public String getEmail() {
    	return email;
    }

	public int getId() {
		return id;
	}
	
    public String getMotDePasse() {
        return motDePasse;
    }
}
