package state;

import bibliotheque.*;

// EnAttente.java
public class LivreDisponible implements EtatLivre {

    @Override
    public void emprunté(Livre livre) {
        System.out.println("Le livre est emprunté.");
        livre.setEtat(new LivreEmprunté());
    }

    @Override
    public void réservé(Livre livre) {
        System.out.println("Le livre est réservé.");
        livre.setEtat(new LivreRéservé());
    }
    
    @Override
    public void enRéparation(Livre livre) {
        System.out.println("Le livre est en réparation.");
        livre.setEtat(new LivreEnRéparation());
    }

    @Override
    public void doAction(Livre livre) {
        System.out.println("Le livre est disponible.");
    }

	@Override
	public void disponible(Livre livre) {
		System.out.println("Le livre est disponible.");
	}
}
