package state;

import bibliotheque.Livre;

public class LivreEmprunté implements EtatLivre {

    @Override
    public void emprunté(Livre livre) throws Exception {
        System.out.println("Le livre est déjà emprunté.");
        throw new Exception("Le livre est déjà emprunté.");
    }

    @Override
    public void réservé(Livre livre) {
        System.out.println("Le livre est réservé.");
        livre.setEtat(new LivreRéservé());
    }

    @Override
    public void enRéparation(Livre livre) {
        System.out.println("Le livre emprunté est en réparation.");
        livre.setEtat(new LivreEnRéparation());
    }

    @Override
    public void doAction(Livre livre) {
        System.out.println("Le livre est actuellement emprunté.");
    }

	@Override
	public void disponible(Livre livre) {
		System.out.println("Le livre n'est pas disponible");
	}
	
	@Override
	public String toString() {
	    return "Emprunté";
	}

}
