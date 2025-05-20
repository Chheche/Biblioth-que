package utilisateur;

import java.util.Scanner;

import bibliotheque.*;
import factory.*;
import service.*;

public class Admin extends Utilisateur {

	/***
	 * Construteur Admin
	 * @param nom
	 * @param email
	 */
    public Admin(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
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
     * Méthode afficherMenuAdherent
     * Permet d'afficher le menu pour gérer les adhérents
     */
    public void afficherMenuAdherent() {
        System.out.println("\n-- Gérer les adhérents --");
        System.out.println("1. Ajouter adhérent\n2. Supprimer adhérent\n3. Voir liste adhérent\n4. Retour menu");
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
    public void gererAdherent(Scanner scanner, Bibliotheque biblio, AuthentificationService auth) {
    	afficherMenuAdherent();
        System.out.println("Entrez votre choix :");

        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
            	ajouterAdherent(scanner, biblio, auth);
                break;
            case 2:
            	supprimerAdherent(scanner, biblio);
                break;
            case 3:
            	voirAdherent(scanner, biblio);
                break;
            case 4:
            	return;
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
    public void ajouterAdherent(Scanner scanner, Bibliotheque biblio, AuthentificationService auth) {
    	System.out.println("\n-- Ajouter un adhérent --");
        System.out.println("Tapez '1' à tout moment pour annuler\n");
        
        System.out.print("Nom de l'adherent : ");
        String nom = scanner.nextLine();
        if (nom.equals("1")) return;
        
        System.out.print("Email de l'adherent : ");
        String mail = scanner.nextLine();
        if (mail.equals("1")) return;
        
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();
        if (mdp.equals("1")) return;
        
        Utilisateur adherent = UtilisateurFactory.creerUtilisateur("adherent", nom, mail, mdp);
        biblio.ajouterAdherent(adherent);
        auth.ajouterUtilisateur(adherent);
    }
    
    /***
     * Méthode supprimerAdherent
     * Permet de supprimer un adherent
     * @param scanner
     * @param biblio
     */
    public void supprimerAdherent(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Supprimer un adherent --");
        System.out.println("Tapez '1' à tout moment pour annuler\n");
        
        System.out.print("Entrez l’ID de l'adherent à supprimer :\n");
        int id = scanner.nextInt();
        if (id == 1) return;
        
        //biblio.supprimerAdherent(id);
    }
    
    /***
     * Méthode voirAdherent
     * Permet de voir l'ensemble des adhérents ajouté à la bibliothèque
     * @param scanner
     * @param biblio
     */
    public void voirAdherent(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Bibliothèque --");
        
        biblio.afficherAdherent();
        
        System.out.println("Tapez '1' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("1")) return;
    }
    
    /***
     * Méthode voirLivreEmprunte
     * Permet de voir la liste des livres empruntés ainsi que les adhérents qui les possèdent
     */
    public void voirLivreEmprunte() {
    	
    }
    
    /***
     * Méthode voirLivreEnReparation
     * Permet de voir la liste des livres en réparation
     */
    public void voirLivreEnReparation() {
    	
    }
    
    /***
     * Méthode voirLivreReserve
     * Permet de voir la liste des livres en réservation
     */
    public void voirLivreReserve() {
    	
    }
}
