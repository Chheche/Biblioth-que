package factory;

import utilisateur.*;

public class UtilisateurFactory {
    public static Utilisateur creerUtilisateur(String type, String nom, String email) {
    	return switch (type.toLowerCase()) {
    		case "admin" -> new Admin(nom, email);
            case "adherent" -> new Adherent(nom, email);
            default -> throw new IllegalArgumentException("Type d'utilisateur invalide : " + type);
        };
    }
}
