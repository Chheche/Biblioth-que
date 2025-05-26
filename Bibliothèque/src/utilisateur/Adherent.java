package utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bibliotheque.*;
import state.*;

public class Adherent extends Utilisateur {
    private List<Livre> livresEmpruntes;

    /**
     * Constructeur Adherent
     * @param nom
     * @param email
     */
    public Adherent(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        this.livresEmpruntes = new ArrayList<>();
    }
    
    public Adherent(int id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
        this.livresEmpruntes = new ArrayList<>();
    }

    /**
     * Méthode afficherMenu
     * Permet l'affichage du menu
     */
    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Adhérent --");
        System.out.println("1. Voir les livres\n2. Emprunter\n3. Retourner et voir livre(s) emprunté(s)\n4. Réserver\n5. Se déconnecter");
    }

    public List<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    /**
     * Méthode ajouterLivre
     * Permet d'emprunter un livre en l'ajouter dans la list de livre emprunté de l'adhérent
     * @param livre
     */
    public void ajouterLivre(Livre livre) {
        livresEmpruntes.add(livre);
    }

	@Override
	public String toString() {
		return " Adherent [" + getId() + "]: " + nom + " - " + email;
	}
    
    /**
     * Méthode voirBiblio
     * Permet de voir l'ensemble des livres ajouté à la bibliothèque
     * @param scanner
     * @param biblio
     */
    public void voirLivre(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Bibliothèque --");
        
        biblio.afficherLivres();
        
        System.out.println("Tapez '1' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("1")) return;
    }
    
    /**
     * Méthode emprunterLivre
     * Permet d'emprunter un livre via son id
     * @param scanner
     * @param biblio
     */
    public void emprunterLivre(Scanner scanner, Bibliotheque biblio) {
        System.out.println("\n-- Emprunter un livre --");
        System.out.println("Tapez '0' pour revenir au menu");
        System.out.print("Entrez le titre du livre à emprunter : ");
        String choix = scanner.nextLine();
        if (choix.equals("0")) return;

        String titre = choix.toLowerCase();

        Livre trouve = null;
        for (Livre livre : biblio.getLivres()) {
            if (livre.getTitre().toLowerCase().equals(titre)) {
                trouve = livre;
                break;
            }
        }

        if (trouve == null) {
            System.out.println("Livre non trouvé.");
            return;
        }

        trouve.emprunté();
        livresEmpruntes.add(trouve);

        System.out.println("Livre '" + trouve.getTitre() + "' emprunté avec succès.");
        biblio.ajouterEmprunt(this, trouve);
    }

    
    /**
     * Méthode retournerLivre
     * Permet à l'adhérent de retourner un livre deja emorunté
     * @param scanner
     * @param biblio
     */
    public void retournerLivre(Scanner scanner, Bibliotheque biblio) {
        List<Livre> empruntes = biblio.getLivresEmpruntes().get(this);

        if (empruntes == null || empruntes.isEmpty()) {
            System.out.println("Vous n'avez emprunté aucun livre.");
            return;
        }

        System.out.println("\n-- Vos livres empruntés --");
        for (int i = 0; i < empruntes.size(); i++) {
            System.out.println((i + 1) + ". " + empruntes.get(i).getTitre());
        }

        System.out.println("Tapez '0' pour revenir au menu");
        String retour = scanner.nextLine();
        if (retour.equals("0")) return;

        System.out.print("Entrez le numéro du livre à retourner : ");
        String input = scanner.nextLine();

        try {
            int choix = Integer.parseInt(input);
            if (choix < 1 || choix > empruntes.size()) {
                System.out.println("Choix invalide.");
                return;
            }

            Livre aRetourner = empruntes.get(choix - 1);
            aRetourner.disponible();

            empruntes.remove(aRetourner);
            if (empruntes.isEmpty()) {
                biblio.getLivresEmpruntes().remove(this);
            }

            System.out.println("Livre '" + aRetourner.getTitre() + "' retourné avec succès.");
            biblio.sauvegarderEmprunts();
        } catch (NumberFormatException e) {
            System.out.println("Entrée non valide.");
        }
    }


    
    /**
     * Méthode reserver
     * Permet de réserver un livre qui est deja emprunté par un adhérent ou en réparation
     * @param scanner
     * @param biblio
     */
    public void reserver(Scanner scanner, Bibliotheque biblio) {
    	
    }
    
    /**
     * Méthode information
     * Permet de voir les différentes information (amende de retard, notification via observer
     * @param scanner
     * @param biblio
     */
    public void information(Scanner scanner, Bibliotheque biblio) {
    	
    }
    
}
