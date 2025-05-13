import utilisateur.*;

import java.util.Scanner;

import factory.*;
import service.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Utilisateur admin = UtilisateurFactory.creerUtilisateur("admin", "1", "Alice", "alice@bib.com");
        Utilisateur adherent = UtilisateurFactory.creerUtilisateur("adherent", "2", "Bob", "bob@bib.com");

        admin.afficherMenu();
        adherent.afficherMenu();
        
        Scanner scanner = new Scanner(System.in);
        AuthentificationService auth = new AuthentificationService();

        Utilisateur utilisateur = null;
        while (utilisateur == null) {
            utilisateur = auth.seConnecter(scanner);
        }

        utilisateur.afficherMenu();
	}

}
