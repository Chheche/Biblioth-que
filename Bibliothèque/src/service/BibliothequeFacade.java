package service;

import utilisateur.*;
import bibliotheque.*;
import java.util.Scanner;

public class BibliothequeFacade {
    private Bibliotheque biblio;
    private AuthentificationService auth;
    private Scanner scanner;
    
    /**
     * Constructeur BibliothequeFacade
     * Initialise et charger les éléments
     */
    public BibliothequeFacade() {
        biblio = new Bibliotheque();
        auth = AuthentificationService.getInstance();
        scanner = new Scanner(System.in);
        
        biblio.chargerAdherentsDepuisFichier();
        biblio.chargerLivresDepuisFichier();
        biblio.chargerEmpruntsDepuisFichier();
        biblio.chargerLivresEnReparation();
        biblio.chargerReservationsDepuisFichier();
    }
    
    /**
     * Méthode lancer
     * Lance l'application 
     */
    public void lancer() {
        while (true) {
            Utilisateur utilisateur = seConnecter();

            boolean actif = true;
            
            while (actif) {
                utilisateur.afficherMenu();
                System.out.print("Entrez votre choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine();

                if (utilisateur instanceof Admin admin) {
                    actif = gererAdmin(admin, choix);
                } else if (utilisateur instanceof Adherent adherent) {
                    actif = gererAdherent(adherent, choix);
                }
            }
        }
    }
    
    /**
     * Méthode seConnecter
     * Permet d'authenfier l'utilisateur qui se connecte
     * @return
     */
    private Utilisateur seConnecter() {
        Utilisateur utilisateur = null;
        while (utilisateur == null) {
            utilisateur = auth.seConnecter(scanner);
        }
        return utilisateur;
    }
    
    /**
     * Méthode gererAdmin
     * Gère la partie menu de l'admin
     * @param admin
     * @param choix
     * @return
     */
    private boolean gererAdmin(Admin admin, int choix) {
        switch (choix) {
	        case 1:
	        	admin.ajouterLivre(scanner, biblio); //Ajouter un livre
	            break;
	        case 2:
	        	admin.supprimerLivre(scanner, biblio); //Supprimer un livre
	            break;
	        case 3:
	        	admin.gererAdherent(scanner, biblio, auth); //Gérer les adhérents
	            break;
	        case 4:
	        	admin.voirBiblio(scanner, biblio); //Voir les livres dans la bibliothèque
	            break;
	        case 5:
	        	admin.afficherLivresEmpruntes(scanner, biblio); //Voir les livres empruntés dans la bibliothèque
	            break;
	        case 6:
	        	admin.afficherLivresReserves(scanner, biblio); //Voir les livres réservés dans la bibliothèque
	            break;
	        case 7:
	        	admin.gererReparations(scanner, biblio); //Mettre un livre en réparation ou le remettre dans la bibliothèque
	            break;
	        case 8:
	            System.out.println("Déconnexion");
	            return false;
	        default:
	            System.out.println("Choix invalide.");
	            break;
        }
        return true;
    }
    
    /**
     * Méthode gererAdherent
     * Gère la partie menu des adhérents
     * @param adherent
     * @param choix
     * @return
     */
    private boolean gererAdherent(Adherent adherent, int choix) {
        switch (choix) {
	        case 1:
	        	adherent.voirLivre(scanner, biblio); //Voir les livres dans la bibliothèque
	            break;
	        case 2:
	        	adherent.emprunterLivre(scanner, biblio); //Emprunter un livre
	            break;
	        case 3:
	        	adherent.retournerLivre(scanner, biblio); //Retourne un livre emprunté
	            break;
	        case 4:
	        	adherent.reserverLivre(scanner, biblio); // Reserve un livre et reçois une notif des qu'il est disponible
	            break;
	        case 5:
	        	adherent.voirLivresEmpruntes(scanner, biblio); //Voir la liste d'emprunts
	            break;
	        case 6:
	        	adherent.voirLivresReserves(scanner, biblio); //Voir la liste de réservations
	            break;
	        case 7:
	            System.out.println("Déconnexion");
	            return false;
	        default:
	            System.out.println("Choix invalide.");
	            break;
        }
        return true;
    }
    
}
