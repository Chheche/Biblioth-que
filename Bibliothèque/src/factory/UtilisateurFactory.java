package factory;

import utilisateur.*;

public class UtilisateurFactory {
    public static Utilisateur creerUtilisateur(String type, String nom, String email, String motDePasse) {
    	return switch (type.toLowerCase()) {
    		case "admin" -> new Admin(nom, email, motDePasse);
            case "adherent" -> new Adherent(nom, email, motDePasse);
            default -> throw new IllegalArgumentException("Type d'utilisateur invalide : " + type);
        };
    }
}
