package utilisateur;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bibliotheque.*;
import state.*;

public class Adherent extends Utilisateur {
	private List<String> notifications = new ArrayList<>();

    /**
     * Constructeur Adherent
     * @param nom
     * @param email
     */
    public Adherent(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
    }
    
    public Adherent(int id, String nom, String email, String motDePasse) {
        super(id, nom, email, motDePasse);
    }

    /**
     * Méthode afficherMenu
     * Permet l'affichage du menu
     */
    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Adhérent --");
        System.out.println("1. Voir les livres\n2. Emprunter\n3. Retourner\n4. Réserver\n5. Voir livre(s) emprunté(s)\n6. Voir livre(s) réservé(s)\n7. Se déconnecter");
        afficherNotifications();
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
        
        System.out.println("Tapez '0' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("0")) return;
    }
    
    /**
     * Méthode voirLivresEmpruntes
     * Permet de voir la liste des emprunts
     * @param scanner
     * @param biblio
     */
    public void voirLivresEmpruntes(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Emprunts --");
    	System.out.println("Attention ! 1€ par jour de retard");
        
    	List<Emprunt> emprunts = new ArrayList<>();
    	for (Emprunt e : biblio.getLivresEmpruntes()) {
    	    if (e.getAdherent().equals(this)) {
    	        emprunts.add(e);
    	    }
    	}
    	if (emprunts.isEmpty()) {
    	    System.out.println("Aucun livre emprunté.");
    	} else {
    	    for (Emprunt e : emprunts) {
    	        System.out.println(e.affichage());
    	    }
    	}

        
        System.out.println("Tapez '0' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("0")) return;
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
        
        if (trouve.estEmprunte()) {
            System.out.println("Erreur : Ce livre est déjà emprunté ! Vous pouvez le réserver dans la section \"Réserver\"");
            return;
        }else if (trouve.estEnReparation()) {
            System.out.println("Erreur : Ce livre est en réparation !");
            return;
        }
        
        for (Reserve res : biblio.getLivresReserve()) {
            if (res.getLivre().equals(trouve)) {
                if (!res.getAdherent().equals(this)) {
                    long jours = ChronoUnit.DAYS.between(res.getDateDeReservation(), LocalDate.now());
                    if (jours <= 3) {
                        System.out.println("Ce livre est réservé pendant encore " + (3 - jours) + " jour(s). Vous ne pouvez pas l’emprunter.");
                        return;
                    } else {
                        System.out.println("La réservation a expiré, le livre redevient disponible.");
                        biblio.supprimerReservation(res);
                        trouve.setEtat(new LivreDisponible());
                        break;
                    }
                } else {
                    biblio.supprimerReservation(res);
                    trouve.setEtat(new LivreDisponible());
                    break;
                }
            }
        }

        try {
            trouve.emprunté();
            LocalDate debut = LocalDate.now();
            LocalDate fin = debut.plusWeeks(2);
            Emprunt emprunt = new Emprunt(trouve, this, debut, fin);
            
            biblio.ajouterEmprunt(emprunt);
        }catch(Exception e) {
        	System.out.println("Erreur : " + e.getMessage());
        	return;
        }
    }
    
    /**
     * Méthode retournerLivre
     * Permet à l'adhérent de retourner un livre deja emorunté
     * @param scanner
     * @param biblio
     */
    public void retournerLivre(Scanner scanner, Bibliotheque biblio) {
        System.out.println("\n-- Retourner un livre --");
        System.out.println("Tapez '0' pour revenir au menu");
        System.out.print("Entrez le titre du livre à retourner : ");
        String choix = scanner.nextLine();
        if (choix.equals("0")) return;

        String titre = choix.toLowerCase();
        Emprunt trouve = null;

        for (Emprunt emprunt : biblio.getLivresEmpruntes()) {
            if (emprunt.getLivre().getTitre().toLowerCase().equals(titre)) {
                trouve = emprunt;
                break;
            }
        }

        if (trouve == null) {
            System.out.println("Livre non trouvé.");
            return;
        }

        if (trouve.estEnRetard()) {
            double amende = trouve.calculerAmende();
            System.out.println("Retour en retard ! Amende due : " + amende + "€");
        } else {
            System.out.println("Livre retourné à temps.");
        }
        
        Livre livre = trouve.getLivre();
        Utilisateur reservant = biblio.getReservation(livre);

        if (reservant != null) {
            livre.setEtat(new LivreRéservé());
            biblio.notifierAdherentPourLivre(reservant, livre);
        } else {
            livre.disponible();
        }

        biblio.supprimerEmprunt(trouve.getId());
    }
    
    /**
     * Méthode reserver
     * Permet de réserver un livre qui est deja emprunté par un adhérent ou en réparation
     * @param scanner
     * @param biblio
     */
    public void reserverLivre(Scanner scanner, Bibliotheque biblio) {
        System.out.println("\n-- Reserver un livre --");
        System.out.println("Tapez '0' pour revenir au menu");
        System.out.print("Entrez le titre du livre à réserver : ");
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
        
        if (trouve.estEnReparation()) {
            System.out.println("Erreur : Ce livre est en réparation !");
            return;
        }
        
        if (trouve.getEtat() instanceof LivreDisponible) {
            System.out.println("Le livre est disponible, inutile de le réserver !");
            return;
        }
        
        for (Reserve r : biblio.getLivresReserve()) {
            if (r.getLivre().equals(trouve) && r.getAdherent().equals(this)) {
                System.out.println("Vous avez déjà réservé ce livre.");
                return;
            }
        }

        try {
        	LocalDate debut = LocalDate.now();
            Reserve reserve = new Reserve(trouve, this, debut);
            biblio.ajouterReservation(reserve);
            System.out.println("Réservation effectuée avec succès.");
        }catch(Exception e) {
        	System.out.println("Erreur : " + e.getMessage());
        	return;
        }
    }
    
    /**
     * Méthode voirLivresReserves
     * Permet à l'adherent de voir sa liste de réservations
     * @param scanner
     * @param biblio
     */
    public void voirLivresReserves(Scanner scanner, Bibliotheque biblio) {
    	System.out.println("\n-- Réservations --");
        
    	List<Reserve> reservations = new ArrayList<>();
    	for (Reserve r : biblio.getLivresReserve()) {
    	    if (r.getAdherent().equals(this)) {
    	    	reservations.add(r);
    	    }
    	}
    	if (reservations.isEmpty()) {
    	    System.out.println("Aucun livre emprunté.");
    	} else {
    	    for (Reserve r : reservations) {
    	        System.out.println(r.affichage());
    	    }
    	}

        
        System.out.println("Tapez '0' pour revenir au menu");
        String titre = scanner.nextLine();
        if (titre.equals("0")) return;
    }
    
    /**
     * Méthode information
     * Permet de voir les différentes information (amende de retard, notification via observer
     * @param scanner
     * @param biblio
     */
    public void information(Scanner scanner, Bibliotheque biblio) {
    	
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Adherent other = (Adherent) obj;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.getId());
    }

	@Override
	public void notifier(String msg) {
		notifications.add(msg);
	}
	
    public void afficherNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("Aucune nouvelle notification.");
        } else {
            System.out.println("Vos notifications :");
            for (String msg : notifications) {
                System.out.println(" - " + msg);
            }
            notifications.clear();
        }
    }

}
