import utilisateur.*;
import java.util.Scanner;

import bibliotheque.*;
import factory.*;
import service.*;
import state.*;

public class Main {
    public static void main(String[] args) {
    	Bibliotheque biblio = new Bibliotheque();
    	biblio.chargerLivresDepuisFichier();
    	
        Utilisateur admin = UtilisateurFactory.creerUtilisateur("admin", "Alice", "alice@bib.com");
        Utilisateur adherent = UtilisateurFactory.creerUtilisateur("adherent", "Bob", "bob@bib.com");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Utilisateur utilisateur = connexion(scanner);
            boolean active = true;
            
            while (active) {
                utilisateur.afficherMenu();
                System.out.println("Entrez votre choix (ex : 5 pour se déconnecter) :");

                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                    	((Admin) utilisateur).ajouterLivre(scanner, biblio); //Ajouter un livre
                        break;
                    case 2:
                    	((Admin) utilisateur).supprimerLivre(scanner, biblio); //Supprimer un livre
                        break;
                    case 3:
                    	((Admin) utilisateur).gererAdherent(scanner, biblio); //Gérer les adhérents
                        break;
                    case 4:
                    	((Admin) utilisateur).voirBiblio(scanner, biblio); //Voir les livres dans la bibliothèque
                        break;
                    case 5:
                        System.out.println("Déconnexion");
                        active = false;
                        break;
                    default:
                        System.out.println("Choix invalide.");
                        break;
                }
            }
        }
    }

    public static Utilisateur connexion(Scanner scanner) {
        AuthentificationService auth = new AuthentificationService();
        Utilisateur utilisateur = null;

        while (utilisateur == null) {
            utilisateur = auth.seConnecter(scanner);
        }

        return utilisateur;
    }	
}
