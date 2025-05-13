package state;

public abstract class Etat {
    protected LivreAvecEtat livre;

    public Etat(LivreAvecEtat livre) {
        this.livre = livre;
    }

    public abstract void emprunté();
    public abstract void réservé();
    public abstract void enRéparation();
    public abstract void doAction();
}
