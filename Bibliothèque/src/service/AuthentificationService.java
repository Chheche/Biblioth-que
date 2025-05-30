package service;

import utilisateur.*;
import factory.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AuthentificationService {
	private static AuthentificationService instance;
    private Map<String, Utilisateur> utilisateurs = new HashMap<>();

    /***
     * Constructeur AuthentificationService
     */
    private AuthentificationService() {
        Utilisateur admin = UtilisateurFactory.creerUtilisateur("admin", "Rafael", "rafael@bib.com", "admin123");
        Utilisateur adherent = UtilisateurFactory.creerUtilisateur("adherent", "Bob", "bob@bib.com", "bob123");
        
        utilisateurs.put(admin.getEmail(), admin);
        utilisateurs.put(adherent.getEmail(), adherent);
        
        chargerUtilisateurs();
    }
    
    public static AuthentificationService getInstance() {
    	if(instance == null) {
    		instance = new AuthentificationService();
    	}
    	return instance;
    }
    
    /***
     * Méthode ajouterUtilisateur
     * Permet d'ajouter les nouveaux utilisateurs dans la map
     * @param utilisateur
     */
    public void ajouterUtilisateur(Utilisateur utilisateur) {
    	utilisateurs.put(utilisateur.getEmail(), utilisateur);
    }
    
    /**
     * Méthode supprimerUtilisateur
     * Permet de supprimer complétement l'utilisateur de l'authentification et non seulement de la bibliotheque.
     * @param id
     */
    public void supprimerUtilisateur(int id) {
        String email = null;

        for (Map.Entry<String, Utilisateur> entry : utilisateurs.entrySet()) {
            if (entry.getValue().getId() == id) {
                email = entry.getKey();
                break;
            }
        }

        if (email != null) {
            utilisateurs.remove(email);
            System.out.println("Utilisateur supprimé du système d'authentification.");
        } else {
            System.out.println("Aucun utilisateur avec cet ID trouvé dans le système d'authentification.");
        }
    }
    
    /***
     * Méthode chargerUtilisateurs
     * Permet de récupérer les utilisateurs du fichier txt et de les réajouter dans la map pour que la connexion fonctionne
     */
    public void chargerUtilisateurs() {
        // Charge les adhérents depuis le fichier et les ajoute à `utilisateurs`
        try (BufferedReader reader = new BufferedReader(new FileReader("adherents.txt"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parties = ligne.split(";");
                if (parties.length == 4) {
                    int id = Integer.parseInt(parties[0]);
                    String nom = parties[1];
                    String email = parties[2];
                    String motDePasse = parties[3];

                    Utilisateur adherent = new Adherent(id, nom, email, motDePasse);
                    utilisateurs.put(adherent.getEmail(), adherent);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur de chargement des adhérents : " + e.getMessage());
        }
    }

    /***
     * Méthode seConnecter
     * Permet de vérifier si l'utilisateur est existant dans la map
     * @param scanner
     * @return
     */
    public Utilisateur seConnecter(Scanner scanner) {
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        
        Utilisateur utilisateur = utilisateurs.get(email);

        if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {
            return utilisateur;
        } else {
            System.out.println("Identifiants invalides.");
            return null;
        }
    }
    
    public Map<String, Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
}
