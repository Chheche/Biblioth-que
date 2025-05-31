package bibliotheque;

import java.time.LocalDate;

import utilisateur.*;

public class Reserve {
	    private final int id;
	    private Livre livre;
	    private Utilisateur adherent;
	    private LocalDate dateDeReservation;
	    private static int cpt = 0;

	    /**
	     * Constructeur Reserve
	     * Constructeur lors de l'ajout par un adhérent
	     * @param livre
	     * @param adherent
	     */
	    public Reserve(Livre livre, Utilisateur adherent, LocalDate dateDeReservation) {
	    	this.id = ++cpt;
	        this.livre = livre;
	        this.adherent = adherent;
	        this.dateDeReservation = dateDeReservation;
	    }
	    
	    /**
	     * Constructeur Reserve
	     * Pour le chargement du fichier txt
	     * @param id
	     * @param livre
	     * @param adherent
	     */
	    public Reserve(int id, Livre livre, Utilisateur adherent, LocalDate dateDeReservation) {
	    	this.id = id;
	        this.livre = livre;
	        this.adherent = adherent;
	        this.dateDeReservation = dateDeReservation;
	        
	        if (id > cpt) {
	            cpt = id;
	        }
	    }

	    public int getId() {
	    	return id;
	    }

	    public Livre getLivre() {
	        return livre;
	    }

	    public Utilisateur getAdherent() {
	        return adherent;
	    }
	    
	    public LocalDate getDateDeReservation() { 
	    	return dateDeReservation;
		}

	    @Override
	    public String toString() {
	        return "Reservation n°" + id + " | Livre: " + livre.getTitre() + " | Emprunteur: " + adherent.getNom();
	    }
	    
	    public String affichage() {
	        return "Livre: " + livre.getTitre();
	    }

}
