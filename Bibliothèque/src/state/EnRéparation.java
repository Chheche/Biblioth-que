package state;

public class EnRéparation extends Etat {

    public EnRéparation(LivreAvecEtat livre) {
        super(livre);
    }

    @Override
    public void emprunté() {
        System.out.println("Impossible d’emprunter un livre en réparation.");
    }

    @Override
    public void réservé() {
        System.out.println("Impossible de réserver un livre en réparation.");
    }

    @Override
    public void enRéparation() {
        System.out.println("Le livre est en réparation.");
    }

    @Override
    public void doAction() {
        System.out.println("Le livre est en réparation.");
    }
}
