import utilisateur.*;
import java.util.Scanner;

import bibliotheque.*;
import factory.*;
import service.*;
import state.*;

public class Main {
    public static void main(String[] args) {
    	Bibliotheque biblio = new Bibliotheque();
    	biblio.chargerAdherentsDepuisFichier();
    	biblio.chargerLivresDepuisFichier();
    	
    	AuthentificationService auth = new AuthentificationService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Utilisateur utilisateur = connexion(scanner, auth);
            boolean active = true;
            
            while (active) {
                utilisateur.afficherMenu();
                System.out.println("Entrez votre choix (ex : 5 pour se déconnecter) :");

                int choix = scanner.nextInt();
                scanner.nextLine();

                //Switch pour les commandes de l'admin
                if(utilisateur instanceof Admin) {
	                switch (choix) {
	                    case 1:
	                    	((Admin) utilisateur).ajouterLivre(scanner, biblio); //Ajouter un livre
	                        break;
	                    case 2:
	                    	((Admin) utilisateur).supprimerLivre(scanner, biblio); //Supprimer un livre
	                        break;
	                    case 3:
	                    	((Admin) utilisateur).gererAdherent(scanner, biblio, auth); //Gérer les adhérents
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
                }else if(utilisateur instanceof Adherent){
                
	                //Switch pour les commandes de l'adhérent
	                switch (choix) {
	                case 1:
	                	((Adherent) utilisateur).voirLivre(scanner, biblio); //Voir les livres dans la bibliothèque
	                    break;
	                case 2:
	                	((Adherent) utilisateur).emprunterLivre(scanner, biblio); //Emprunter un livre
	                    break;
	                case 3:
	                    break;
	                case 4:
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
    }

    public static Utilisateur connexion(Scanner scanner, AuthentificationService auth) {
        Utilisateur utilisateur = null;

        while (utilisateur == null) {
            utilisateur = auth.seConnecter(scanner);
        }

        return utilisateur;
    }	
}
