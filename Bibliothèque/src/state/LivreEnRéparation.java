package state;

import bibliotheque.*;

public class LivreEnRéparation implements EtatLivre {

    @Override
    public void emprunté(Livre livre) {
        System.out.println("Impossible d’emprunter un livre en réparation.");
    }

    @Override
    public void réservé(Livre livre) {
        System.out.println("Impossible de réserver un livre en réparation.");
    }

    @Override
    public void enRéparation(Livre livre) {
        System.out.println("Le livre est deja en réparation.");
    }

	@Override
	public void disponible(Livre livre) {
		System.out.println("Le livre n'est pas disponible");
	}
	
	@Override
	public String toString() {
	    return "En réparation";
	}
}
