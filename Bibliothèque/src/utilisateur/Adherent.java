package utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bibliotheque.*;

public class Adherent extends Utilisateur {
    private List<Livre> livresEmpruntes;

    /***
     * Constructeur Adherent
     * @param nom
     * @param email
     */
    public Adherent(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        this.livresEmpruntes = new ArrayList<>();
    }
    
    public Adherent(int id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
        this.livresEmpruntes = new ArrayList<>();
    }

    /***
     * Méthode afficherMenu
     * Permet l'affichage du menu
     */
    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Adhérent --");
        System.out.println("1. Voir les livres\n2. Emprunter\n3. Retourner\n4. Réserver\n5. Se déconnecter");
    }

    public List<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    /***
     * Méthode ajouterLivre
     * Permet d'emprunter un livre en l'ajouter dans la list de livre emprunté de l'adhérent
     * @param livre
     */
    public void ajouterLivre(Livre livre) {
        livresEmpruntes.add(livre);
    }

	@Override
	public String toString() {
		return " Adherent [" + getId() + "]: " + nom + " - " + email;
	}
    
    /***
     * Méthode voirBiblio
     * Permet de voir l'ensemble des livres ajouté à la bibliothèque
     * @param scanner
     * @param biblio
     */
    public void voirLivre(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Bibliothèque --");
        
        biblio.afficherLivres();
        
        System.out.println("Tapez '1' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("1")) return;
    }
    
    /***
     * Méthode emprunterLivre
     * Permet d'emprunter un livre via son id
     * @param scanner
     * @param biblio
     */
    public void emprunterLivre(Scanner scanner, Bibliotheque biblio) {
    	
    }
    
    /***
     * Méthode retournerLivre
     * Permet à l'adhérent de retourner un livre deja emorunté
     * @param scanner
     * @param biblio
     */
    public void retournerLivre(Scanner scanner, Bibliotheque biblio) {
    	
    }
    
    /***
     * Méthode reserver
     * Permet de réserver un livre qui est deja emprunté par un adhérent ou en réparation
     * @param scanner
     * @param biblio
     */
    public void reserver(Scanner scanner, Bibliotheque biblio) {
    	
    }
    
    /***
     * Méthode information
     * Permet de voir les différentes information (amende de retard, notification via observer
     * @param scanner
     * @param biblio
     */
    public void information(Scanner scanner, Bibliotheque biblio) {
    	
    }
    
}
