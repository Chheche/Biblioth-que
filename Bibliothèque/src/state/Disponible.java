package state;

// EnAttente.java
public class Disponible extends Etat {
	
    public Disponible(LivreAvecEtat livre) {
        super(livre);
    }

    @Override
    public void emprunté() {
        System.out.println("Le livre est emprunté.");
        livre.setEtat(new Emprunté(livre));
    }

    @Override
    public void réservé() {
        System.out.println("Le livre est réservé.");
        livre.setEtat(new Réservé(livre));
    }
    
    @Override
    public void enRéparation() {
        System.out.println("Le livre est en réparation.");
        livre.setEtat(new EnRéparation(livre));
    }

    @Override
    public void doAction() {
        System.out.println("Le livre est disponible.");
    }
}
