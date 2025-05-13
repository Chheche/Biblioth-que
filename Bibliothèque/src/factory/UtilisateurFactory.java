package factory;

import utilisateur.*;

public class UtilisateurFactory {
    public static Utilisateur creerUtilisateur(String type, String id, String nom, String email) {
    	return switch (type.toLowerCase()) {
    		case "admin" -> new Admin(id, nom, email);
            case "adherent" -> new Adherent(id, nom, email);
            default -> throw new IllegalArgumentException("Type d'utilisateur invalide : " + type);
        };
    }
}
