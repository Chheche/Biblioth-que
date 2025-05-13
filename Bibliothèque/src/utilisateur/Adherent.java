package utilisateur;

import java.util.ArrayList;
import java.util.List;

public class Adherent extends Utilisateur {
    private List<Livre> livresEmpruntes;

    public Adherent(String id, String nom, String email) {
        super(id, nom, email);
        this.livresEmpruntes = new ArrayList<>();
    }

    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Adhérent --");
        System.out.println("1. Rechercher un livre\n2. Emprunter\n3. Retourner\n4. Réserver");
    }

    public List<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public void ajouterLivre(Livre livre) {
        livresEmpruntes.add(livre);
    }
}
