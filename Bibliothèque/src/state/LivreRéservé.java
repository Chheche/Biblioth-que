package state;

import bibliotheque.*;

public class LivreRéservé implements EtatLivre {

    @Override
    public void emprunté(Livre livre) {
        System.out.println("Le livre réservé est emprunté.");
        livre.setEtat(new LivreEmprunté());
    }

    @Override
    public void réservé(Livre livre) {
        System.out.println("Le livre est déjà réservé.");
    }

    @Override
    public void enRéparation(Livre livre) {
        System.out.println("Le livre réservé est en réparation.");
        livre.setEtat(new LivreEnRéparation());
    }

	@Override
	public void disponible(Livre livre) {
		System.out.println("Le livre n'est pas disponible");
	}
	
	@Override
	public String toString() {
	    return "Réservé";
	}
}
