package bibliotheque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilisateur.*;

public class Bibliotheque {
    private List<Livre> livres = new ArrayList<>();
    private List<Utilisateur> adherents = new ArrayList();

    /***
     * Méthode ajouterLivre
     * Permet d'ajouter un livre dans la list puis de sauvegarder le fichier txt
     * @param livre
     */
    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        sauvegarderLivre(livre);
        System.out.println("Livre ajouté !");
    }
    
    /***
     * Méthode supprimerLivre
     * Permet de supprimer un livre de notre list
     * @param id
     */
	public void supprimerLivre(int id) {
	    Livre livreASupprimer = null;

	    for (Livre livre : livres) {
	        if (livre.getId() == id) {
	            livreASupprimer = livre;
	            break;
	        }
	    }

	    if (livreASupprimer != null) {
	        livres.remove(livreASupprimer);
	        reecrire();
	        System.out.println("Livre supprimé !");
	    } else {
	        System.out.println("Aucun livre avec cet ID.");
	    }
	}

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

    /***
     * Méthode afficherLivres
     * Permet de parcourir toute la liste est d'afficher les informations du livre
     */
	public void afficherLivres() {
        if (livres.isEmpty()) {
            System.out.println("Aucun livre dans la bibliothèque.");
        } else {
            for (Livre l : livres) {
                System.out.println(l);
            }
        }
    }
	
	/***
	 * Methode sauvegarderLivre
	 Permet de sauvegarder les instances dans un fichier txt
	 * @param livre
	 */
	public void sauvegarderLivre(Livre livre) {
	    try (FileWriter writer = new FileWriter("livres.txt", true)) { // true = permet d'ajouter à la fin
	    	writer.write(livre.getId() + ";" + livre.getTitre() + ";" + livre.getAuteur() + ";" + livre.getCategorie() + "\n");
	    } catch (IOException e) {
	        System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
	    }
	}
	
	/***
	 * Méthode reecrire
	 * Permet de réecrire par dessus tout le fichier txt (évite le problème des id)
	 */
	public void reecrire() {
	    try (FileWriter writer = new FileWriter("livres.txt", false)) { // false = écrase tout
	        for (Livre livre : livres) {
	            writer.write(livre.getId() + ";" + livre.getTitre() + ";" + livre.getAuteur() + ";" + livre.getCategorie() + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/***
	 * Methode chargerLivreDepuisFichier
	 * Permet de récupérer les données d'un fichier txt, de recréer l'objet et le réajouter dans la liste
	 */
	public void chargerLivresDepuisFichier() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("livres.txt"))) {
	        String ligne;
	        while ((ligne = reader.readLine()) != null) {
	            String[] parties = ligne.split(";");
	            if (parties.length == 4) {
	                int id = Integer.parseInt(parties[0]);
	                String titre = parties[1];
	                String auteur = parties[2];
	                String categorie = parties[3];
	                
	                Livre livre = new Livre(id, titre, auteur, categorie);
	                livres.add(livre);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Aucun fichier trouvé");
	    }
	}
}
