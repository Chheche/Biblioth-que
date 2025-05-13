package state;

public class Emprunté extends Etat {

    public Emprunté(LivreAvecEtat livre) {
        super(livre);
    }

    @Override
    public void emprunté() {
        System.out.println("Le livre est déjà emprunté.");
    }

    @Override
    public void réservé() {
        System.out.println("Le livre est réservé.");
        livre.setEtat(new Réservé(livre));
    }

    @Override
    public void enRéparation() {
        System.out.println("Le livre emprunté est en réparation.");
        livre.setEtat(new EnRéparation(livre));
    }

    @Override
    public void doAction() {
        System.out.println("Le livre est actuellement emprunté.");
    }
}
