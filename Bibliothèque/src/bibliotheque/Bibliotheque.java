package bibliotheque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres = new ArrayList<>();

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        sauvegarderLivre(livre);
        System.out.println("Livre ajouté !");
    }
    
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

	public void afficherLivres() {
        if (livres.isEmpty()) {
            System.out.println("Aucun livre dans la bibliothèque.");
        } else {
            for (Livre l : livres) {
                System.out.println(l);
            }
        }
    }
	
	public void sauvegarderLivre(Livre livre) {
	    try (FileWriter writer = new FileWriter("livres.txt", true)) { // true = permet d'ajouter à la fin
	    	writer.write(livre.getId() + ";" + livre.getTitre() + ";" + livre.getAuteur() + ";" + livre.getCategorie() + "\n");
	    } catch (IOException e) {
	        System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
	    }
	}
	
	public void reecrire() {
	    try (FileWriter writer = new FileWriter("livres.txt", false)) { // false = écrase tout
	        for (Livre livre : livres) {
	            writer.write(livre.getId() + ";" + livre.getTitre() + ";" + livre.getAuteur() + ";" + livre.getCategorie() + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
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
