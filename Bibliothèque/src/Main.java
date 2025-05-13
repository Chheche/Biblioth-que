import utilisateur.*;
import java.util.Scanner;
import factory.*;
import service.*;
import state.*;

public class Main {
    public static void main(String[] args) {
        Utilisateur admin = UtilisateurFactory.creerUtilisateur("admin", "1", "Alice", "alice@bib.com");
        Utilisateur adherent = UtilisateurFactory.creerUtilisateur("adherent", "2", "Bob", "bob@bib.com");

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
                        System.out.println("Ajouter livre");
                        break;
                    case 2:
                        System.out.println("Supprimer livre");
                        break;
                    case 3:
                        System.out.println("Gérer adhérents");
                        break;
                    case 4:
                        System.out.println("Voir rapports");
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
