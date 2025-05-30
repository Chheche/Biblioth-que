package bibliotheque;

import java.time.LocalDate;
import utilisateur.*;

public class Emprunt {
    private final int id;
    private Livre livre;
    private Utilisateur adherent;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private static int cpt = 0;

    /**
     * Constructeur Emprunt
     * Constructeur lors de l'ajout par un adhérent
     * @param livre
     * @param adherent
     * @param dateDebut
     * @param dateFin
     */
    public Emprunt(Livre livre, Utilisateur adherent, LocalDate dateDebut, LocalDate dateFin) {
    	this.id = ++cpt;
        this.livre = livre;
        this.adherent = adherent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    /**
     * Constructeur Emprunt
     * Pour le chargement du fichier txt
     * @param id
     * @param livre
     * @param adherent
     * @param dateDebut
     * @param dateFin
     */
    public Emprunt(int id, Livre livre, Utilisateur adherent, LocalDate dateDebut, LocalDate dateFin) {
    	this.id = id;
        this.livre = livre;
        this.adherent = adherent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        
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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Méthode estEnRetard
     * Permet de savoir si un livre est en retard
     * @return
     */
    public boolean estEnRetard() {
        return LocalDate.now().isAfter(dateFin);
    }

    /**
     * Méthode joursDeRetard
     * Permet d'obtenir le nombre de jour ou l'emprunt est en retard
     * @return
     */
    public long joursDeRetard() {
        if (estEnRetard()) {
            long jours = java.time.temporal.ChronoUnit.DAYS.between(dateFin, java.time.LocalDate.now());
            return jours;
        } else {
            return 0;
        }
    }

    /**
     * Méthode calculerAmende
     * Permet de caluler le prix de l'amende selon le nombre de jour de retard
     * @return
     */
    public double calculerAmende() {
        double taux = 1;
        return joursDeRetard() * taux;
    }
    
    @Override
    public String toString() {
        return "Emprunt n°" + id + " | Livre: " + livre.getTitre() + " | Emprunteur: " + adherent.getNom() +
               " | Du " + dateDebut + " au " + dateFin + (estEnRetard() ? " | EN RETARD !" : "");
    }
    
    public String affichage() {
        return "Livre: " + livre.getTitre() +
                " | Du " + dateDebut + " au " + dateFin + (estEnRetard() ? " | EN RETARD !" : "");
    }
}
