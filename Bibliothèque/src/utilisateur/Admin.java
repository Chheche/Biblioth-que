package utilisateur;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import bibliotheque.*;
import factory.*;
import service.*;
import state.*;

public class Admin extends Utilisateur {

	/**
	 * Construteur Admin
	 * @param nom
	 * @param email
	 */
    public Admin(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
    }

    /**
     * Méthode afficherMenu
     * Permet l'affichage du menu
     */
    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Admin --");
        System.out.println("1. Ajouter livre\n2. Supprimer livre\n3. Gérer adhérents\n4. Voir bibliothèque\n5. Voir livres empruntés\n6. Gérer réparation\n7. Se déconnecter");
    }
    
    /**
     * Méthode afficherMenuAdherent
     * Permet d'afficher le menu pour gérer les adhérents
     */
    public void afficherMenuAdherent() {
        System.out.println("\n-- Gérer les adhérents --");
        System.out.println("1. Ajouter adhérent\n2. Supprimer adhérent\n3. Voir liste adhérent\n4. Retour menu");
    }
   
    /**
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
    
    /**
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

    /**
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
    
    /**
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
            	supprimerAdherent(scanner, biblio, auth);
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
    
    /**
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
    
    /**
     * Méthode supprimerAdherent
     * Permet de supprimer un adherent
     * @param scanner
     * @param biblio
     */
    public void supprimerAdherent(Scanner scanner, Bibliotheque biblio, AuthentificationService auth) {
    	System.out.println("\n-- Supprimer un adherent --");
        System.out.println("Tapez '1' à tout moment pour annuler\n");
        
        System.out.print("Entrez l’ID de l'adherent à supprimer :\n");
        int id = scanner.nextInt();
        if (id == 1) return;
        
        biblio.supprimerAdherent(id);
        auth.supprimerUtilisateur(id);
    }
    
    /**
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
    
    /**
     * Méthode afficherLivresEmpruntes
     * Permet de voir la liste des livres empruntés ainsi que les adhérents qui les possèdent
     * @param biblio
     */
    public void afficherLivresEmpruntes(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Livres empruntés --");
    	
    	biblio.afficherLivresEmpruntes();

        System.out.println("Tapez '1' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("1")) return;
    }

    
    /**
     * Méthode voirLivreEnReparation
     * Permet de voir la liste des livres en réparation
     */
    public void gererReparations(Scanner scanner, Bibliotheque biblio) {

        while (true) {
            System.out.println("\n--- Menu Réparation ---");
            System.out.println("Tapez '0' pour revenir au menu");
            System.out.println("1. Mettre un livre en réparation");
            System.out.println("2. Remettre un livre dans la bibliothèque");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                	System.out.println("Tapez '0' pour revenir au menu");
                    System.out.print("Entrez le titre du livre à réparer : ");
                    String titre = scanner.nextLine();
                    if (titre.equals("0")) return;
                    
                    Livre trouve = null;

                    for (Livre livre : biblio.getLivres()) {
                        if (livre.getTitre().toLowerCase().equals(titre.toLowerCase())) {
                            trouve = livre;
                            break;
                        }
                    }
                    
                    if (trouve == null) {
                        System.out.println("Livre non trouvé.");
                        return;
                    }
                    
                    if (trouve.estEmprunte()) {
                        System.out.println("Erreur : Ce livre est déjà emprunté !");
                        return;
                    }else if (trouve.estEnReparation()) {
                        System.out.println("Erreur : Ce livre est déjà en réparation !");
                        return;
                    }
                    
                    try {
                        trouve.enRéparation();
                        biblio.ajouterReparation(trouve);
                    }catch(Exception e) {
                    	System.out.println("Erreur : " + e.getMessage());
                    	return;
                    }
                    break;

                case "2":
                    System.out.println("\n--- Livres en réparation ---");
                    List<Livre> livresEnReparation = biblio.getLivresEnReparation();

                    if (livresEnReparation.isEmpty()) {
                        System.out.println("Aucun livre en réparation.");
                    } else {
                        for (Livre l : livresEnReparation) {
                            System.out.println("- " + l.getTitre());
                        }

                        System.out.println("Tapez '0' pour revenir au menu");
                        System.out.print("Entrez le titre du livre à remettre en disponibilité : ");
                        String titreRemis = scanner.nextLine();
                        if (titreRemis.equals("0")) return;
                        
                        Livre livreRemis = null;

                        for (Livre livre : biblio.getLivres()) {
                            if (livre.getTitre().toLowerCase().equals(titreRemis.toLowerCase())) {
                            	livreRemis = livre;
                                break;
                            }
                        }

                        if (livreRemis != null && livreRemis.getEtat() instanceof LivreEnRéparation) {
                            livreRemis.disponible();
                            biblio.supprimerReparation(livreRemis);
                            System.out.println("Le livre \"" + livreRemis.getTitre() + "\" est maintenant disponible.");
                            return;
                        } else {
                            System.out.println("Livre introuvable ou non en réparation.");
                        }
                    }
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
    
    /**
     * Méthode voirLivreReserve
     * Permet de voir la liste des livres en réservation
     */
    public void voirLivreReserve() {
    	
    }
}
