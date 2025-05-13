package utilisateur;

public class Admin extends Utilisateur {

    public Admin(String id, String nom, String email) {
        super(id, nom, email);
    }

    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Admin --");
        System.out.println("1. Ajouter livre\n2. Supprimer livre\n3. Gérer adhérents\n4. Voir rapports\n5. Se déconnecter");
    }
}
