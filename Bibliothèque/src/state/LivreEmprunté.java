package state;

import bibliotheque.Livre;

public class LivreEmprunté implements EtatLivre {

    @Override
    public void emprunté(Livre livre) throws Exception {
    	try {
            System.out.println("Le livre est déjà emprunté.");
            return;
    	}catch(Exception e) {
    		System.out.println("impossible" + e.getMessage());
    	}
    }

    @Override
    public void réservé(Livre livre) {
        System.out.println("Le livre est réservé. Il sera disponlibe après son retour.");
        livre.setEtat(new LivreRéservé());
    }

    @Override
    public void enRéparation(Livre livre) {
        System.out.println("Impossible de mettre le livre en réparation ! Il est emprunté.");
        livre.setEtat(new LivreEnRéparation());
    }

	@Override
	public void disponible(Livre livre) {
		System.out.println("Le livre emprunté n'est pas disponible");
	}
	
	@Override
	public String toString() {
	    return "Emprunté";
	}

}
