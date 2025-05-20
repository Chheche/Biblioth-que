package utilisateur;

import java.util.Scanner;

import bibliotheque.*;
import factory.UtilisateurFactory;

public class Admin extends Utilisateur {

	/***
	 * Construteur Admin
	 * @param nom
	 * @param email
	 */
    public Admin(String nom, String email) {
        super(nom, email);
    }

    /***
     * Méthode afficherMenu
     * Permet l'affichage du menu
     */
    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Admin --");
        System.out.println("1. Ajouter livre\n2. Supprimer livre\n3. Gérer adhérents\n4. Voir rapports\n5. Se déconnecter");
    }
   
    /***
     * Méthode ajouterLivre
     * Permet lorsque l'admin sélectionne 1 dans son menu d'acceder au menu d'ajout de livre
     * @param scanner
     * @param biblio
     */
    public void ajouterLivre(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Ajouter un livre --");
        System.out.println("Tapez '1' à tout moment pour annuler\n");
        
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        if (titre.equals("1")) return;
        
        System.out.print("Auteur : ");
        String auteur = scanner.nextLine();
        if (auteur.equals("1")) return;
        
        System.out.print("Categorie : ");
        String categorie = scanner.nextLine();
        if (categorie.equals("1")) return;
        
        Livre livre = new Livre(titre, auteur, categorie);
        biblio.ajouterLivre(livre);
    }
    
    /***
     * Méthode supprimerLivre
     * Permet lorsque l'admin sélectionne 2 dans son menu d'acceder au menu pour supprimer un livre
     * @param scanner
     * @param biblio
     */
    public void supprimerLivre(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Supprimer un livre --");
        System.out.println("Tapez '1' à tout moment pour annuler\n");
        
        System.out.print("Entrez l’ID du livre à supprimer :\n");
        int id = scanner.nextInt();
        if (id == 1) return;
        
        biblio.supprimerLivre(id);
    }

    /***
     * Méthode voirBiblio
     * Permet de voir l'ensemble des livres ajouté à la bibliothèque
     * @param scanner
     * @param biblio
     */
    public void voirBiblio(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Bibliothèque --");
        
        biblio.afficherLivres();
        
        System.out.println("Tapez '1' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("1")) return;
    }
    
    /***
     * Methode gererAdherent
     * Permet de passer au menu des adhérents
     * @param scanner
     * @param biblio
     */
    public void gererAdherent(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Gérer les adhérents --");
    	
        System.out.println("Entrez votre choix :");

        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
            	//Ajouter un adhérent
                break;
            case 2:
            	//Supprimer un adhérent
                break;
            case 3:
            	//Voir la liste des adhérents
                break;
            default:
                System.out.println("Choix invalide.");
                break;
        }
    }
    
    /***
     * Méthode ajouterAdherent
     * Permet d'ajouter un adhérent
     * @param scanner
     * @param biblio
     */
    public void ajouterAdherent(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Ajouter un adhérent --");
        System.out.println("Tapez '1' à tout moment pour annuler\n");
        
        System.out.print("Nom de l'adherent : ");
        String nom = scanner.nextLine();
        if (nom.equals("1")) return;
        
        System.out.print("Email de l'adherent : ");
        String mail = scanner.nextLine();
        if (mail.equals("1")) return;
        
        System.out.print("Categorie : ");
        String categorie = scanner.nextLine();
        if (categorie.equals("1")) return;
        
        Utilisateur adherent = UtilisateurFactory.creerUtilisateur("adherent", nom, mail);
        //biblio.ajouterAdherent(adherent);
    }
}
