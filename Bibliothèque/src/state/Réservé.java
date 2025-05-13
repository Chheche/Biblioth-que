package state;

public class Réservé extends Etat {

    public Réservé(LivreAvecEtat livre) {
        super(livre);
    }

    @Override
    public void emprunté() {
        System.out.println("Le livre réservé est emprunté.");
        livre.setEtat(new Emprunté(livre));
    }

    @Override
    public void réservé() {
        System.out.println("Le livre est déjà réservé.");
    }

    @Override
    public void enRéparation() {
        System.out.println("Le livre réservé est en réparation.");
        livre.setEtat(new EnRéparation(livre));
    }

    @Override
    public void doAction() {
        System.out.println("Le livre est réservé.");
    }
}
