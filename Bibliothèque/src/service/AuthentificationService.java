package service;

import utilisateur.*;
import factory.*;
import java.util.*;

public class AuthentificationService {
    private Map<String, String> comptes = new HashMap<>();
    private Map<String, Utilisateur> utilisateurs = new HashMap<>();

    /***
     * Constructeur AuthentificationService
     */
    public AuthentificationService() {
        Utilisateur admin = UtilisateurFactory.creerUtilisateur("admin", "Alice", "alice@bib.com");
        Utilisateur adherent = UtilisateurFactory.creerUtilisateur("adherent", "Bob", "bob@bib.com");
        utilisateurs.put("admin", admin);
        utilisateurs.put("bob", adherent);

        comptes.put("admin", "admin123");
        comptes.put("bob", "bob123");
    }

    /***
     * Méthode seConnecter
     * Permet de vérifier si l'utilisateur est existant dans la map
     * @param scanner
     * @return
     */
    public Utilisateur seConnecter(Scanner scanner) {
        System.out.print("Identifiant : ");
        String login = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        if (comptes.containsKey(login) && comptes.get(login).equals(motDePasse)) {
            return utilisateurs.get(login);
        } else {
            System.out.println("Identifiants invalides.");
            return null;
        }
    }
}
