package utilisateur;

import java.util.Scanner;

import bibliotheque.*;

public class Admin extends Utilisateur {

    public Admin(String id, String nom, String email) {
        super(id, nom, email);
    }

    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Admin --");
        System.out.println("1. Ajouter livre\n2. Supprimer livre\n3. Gérer adhérents\n4. Voir rapports\n5. Se déconnecter");
    }
    
    /*
     * Méthode ajouterLivre
     * Permet lorsque l'admin sélectionne 1 dans son menu d'acceder au menu d'ajout de livre
     * 
     * */
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
    
    
    /*
     * Méthode supprimerLivre
     * Permet lorsque l'admin sélectionne 2 dans son menu d'acceder au menu pour supprimer un livre
     * 
     * */
    public void supprimerLivre(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Supprimer un livre --");
        System.out.println("Tapez '1' à tout moment pour annuler\n");
        
        System.out.print("Entrez l’ID du livre à supprimer :\n");
        int id = scanner.nextInt();
        if (id == 1) return;
        
        biblio.supprimerLivre(id);
    }
    
    
    /*
     * Méthode rapport
     * Permet de voir l'ensemble des livres ajouté à la bibliothèque
     * 
     * */
    public void voirBiblio(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Bibliothèque --");
        
        biblio.afficherLivres();
        
        System.out.println("Tapez '1' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("1")) return;
    }
}
