package utilisateur;

import java.util.ArrayList;
import java.util.List;
import bibliotheque.*;

public class Adherent extends Utilisateur {
    private List<Livre> livresEmpruntes;

    /***
     * Constructeur Adherent
     * @param nom
     * @param email
     */
    public Adherent(String nom, String email) {
        super(nom, email);
        this.livresEmpruntes = new ArrayList<>();
    }

    /***
     * Méthode afficherMenu
     * Permet l'affichage du menu
     */
    @Override
    public void afficherMenu() {
        System.out.println("\n-- Menu Adhérent --");
        System.out.println("1. Rechercher un livre\n2. Emprunter\n3. Retourner\n4. Réserver");
    }

    public List<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    /***
     * Méthode ajouterLivre
     * Permet d'emprunter un livre en l'ajouter dans la list de livre emprunté de l'adhérent
     * @param livre
     */
    public void ajouterLivre(Livre livre) {
        livresEmpruntes.add(livre);
    }
}
