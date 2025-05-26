package bibliotheque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import state.*;
import utilisateur.*;

public class Bibliotheque {
    private List<Livre> livres = new ArrayList<>();
    private List<Utilisateur> adherents = new ArrayList();
    private Map<Utilisateur, List<Livre>> livresEmpruntes = new HashMap<>();
    private List<Livre> livresEnReparation = new ArrayList();
    private Map<Livre, Utilisateur> livresReserve = new HashMap<>();

    /**
     * Méthode ajouterLivre
     * Permet d'ajouter un livre dans la list puis de sauvegarder le fichier txt
     * @param livre
     */
    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        sauvegarderLivre(livre);
        System.out.println("Livre ajouté !");
    }
    
    /**
     * Méthode ajouterAdherent
     * Permet d'ajouter un adherent dans la list puis de sauvegarder le fichier txt
     * @param adherent
     */
    public void ajouterAdherent(Utilisateur adherent) {
        adherents.add(adherent);
        sauvegarderAdherent(adherent);
        System.out.println("Adherent ajouté !");
    }
    
    /**
     * Méthode ajouterAdherent
     * Permet d'ajouter un emprunt dans la map et de sauvegarder
     * @param adherent
     * @param livre
     */
    public void ajouterEmprunt(Utilisateur adherent, Livre livre) {
        if (!livresEmpruntes.containsKey(adherent)) {
            livresEmpruntes.put(adherent, new ArrayList<>());
        }
        livresEmpruntes.get(adherent).add(livre);

        sauvegarderEmprunts();
    }

    
    /**
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
	        reecrireLivre();
	        System.out.println("Livre supprimé !");
	    } else {
	        System.out.println("Aucun livre avec cet ID.");
	    }
	}
	
	/**
	 * Méthode supprimerAdherent
	 * Permet de supprimer un adherent de notre list
	 * @param id
	 */
	public void supprimerAdherent(int id) {
	    Utilisateur adherentASupprimer = null;

	    for (Utilisateur adherent : adherents) {
	        if (adherent.getId() == id) {
	        	adherentASupprimer = adherent;
	            break;
	        }
	    }

	    if (adherentASupprimer != null) {
	        livres.remove(adherentASupprimer);
	        reecrireLivre();
	        System.out.println("Adherent supprimé !");
	    } else {
	        System.out.println("Aucun adherent avec cet ID.");
	    }
	}

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

    /**
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
	
	/**
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
	
	/**
	 * Méthode reecrireLivre
	 * Permet de réecrire par dessus tout le fichier txt (évite le problème des id)
	 */
	public void reecrireLivre() {
	    try (FileWriter writer = new FileWriter("livres.txt", false)) { // false = écrase tout
	        for (Livre livre : livres) {
	            writer.write(livre.getId() + ";" + livre.getTitre() + ";" + livre.getAuteur() + ";" + livre.getCategorie() + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
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
	        System.out.println("Aucun fichier livre trouvé");
	    }
	}
	
    /**
     * Méthode afficherAdherent
     * Permet de parcourir toute la liste est d'afficher les informations des adhérents
     */
	public void afficherAdherent() {
        if (adherents.isEmpty()) {
            System.out.println("Aucun adhérent dans la bibliothèque.");
        } else {
            for (Utilisateur adherent : adherents) {
                System.out.println(adherent);
            }
        }
	}
	
	/**
	 * Methode sauvegarderAdherent
	 Permet de sauvegarder les instances dans un fichier txt
	 * @param adherent
	 */
	public void sauvegarderAdherent(Utilisateur adherent) {
	    try (FileWriter writer = new FileWriter("adherents.txt", true)) { // true = permet d'ajouter à la fin
	    	writer.write(adherent.getId() + ";" + adherent.getNom() + ";" + adherent.getEmail() + ";" + adherent.getMotDePasse() + "\n");
	    } catch (IOException e) {
	        System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
	    }
	}
	
	/**
	 * Méthode reecrireUtilisateur
	 * Permet de réecrire par dessus tout le fichier txt (évite le problème des id)
	 */
	public void reecrireUtilisateur() {
	    try (FileWriter writer = new FileWriter("adherents.txt", false)) { // false = écrase tout
	        for (Utilisateur adherent : adherents) {
	        	writer.write(adherent.getId() + ";" + adherent.getNom() + ";" + adherent.getEmail() + ";" + adherent.getMotDePasse() + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Methode chargerAdherentsDepuisFichier
	 * Permet de récupérer les données d'un fichier txt, de recréer l'objet et le réajouter dans la liste
	 */
	public void chargerAdherentsDepuisFichier() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("adherents.txt"))) {
	        String ligne;
	        while ((ligne = reader.readLine()) != null) {
	            String[] parties = ligne.split(";");
	            if (parties.length == 4) {
	                int id = Integer.parseInt(parties[0]);
	                String nom = parties[1];
	                String email = parties[2];
	                String motDePasse = parties[3];
	                
	                Utilisateur adherent = new Adherent(id, nom, email, motDePasse);
	                adherents.add(adherent);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Aucun fichier adherent trouvé");
	    }
	}
	
	/**
	 * Méthode sauvegarderEmprunts
	 * Permet de sauvegarder dans un fichier txt les associations entre adhérent et livre emprunté
	 */
	public void sauvegarderEmprunts() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("livresEmprunte.txt"))) {
	        for (Map.Entry<Utilisateur, List<Livre>> entry : livresEmpruntes.entrySet()) {
	            Utilisateur user = entry.getKey();
	            List<Livre> livres = entry.getValue();

	            StringBuilder ligne = new StringBuilder();
	            ligne.append(user.getNom()).append(";").append(user.getEmail()).append(";");

	            for (int i = 0; i < livres.size(); i++) {
	                ligne.append(livres.get(i).getId());
	                if (i < livres.size() - 1) {
	                    ligne.append(",");
	                }
	            }

	            writer.write(ligne.toString());
	            writer.newLine();
	        }

	    } catch (IOException e) {
	        System.out.println("Erreur lors de la sauvegarde des emprunts : " + e.getMessage());
	    }
	}
	
	/**
	 * Méthode chargerEmpruntsDepuisFichier
	 * Permet de charger les livres empruntés du fichier txt
	 * @param utilisateurs
	 */
	public void chargerEmpruntsDepuisFichier() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("livresEmprunte.txt"))) {
	        String ligne;
	        while ((ligne = reader.readLine()) != null) {
	            String[] parts = ligne.split(";");
	            if (parts.length == 3) {
	                String nomAdherent = parts[0];
	                String email = parts[1];
	                String[] idsLivres = parts[2].split(",");

	                Utilisateur utilisateur = getAdherentParNom(nomAdherent); // Ou par email pour être plus fiable

	                if (utilisateur != null) {
	                    for (String idStr : idsLivres) {
	                        try {
	                            int id = Integer.parseInt(idStr);
	                            Livre livre = getLivreById(id);
	                            if (livre != null) {
	                                ajouterEmprunt(utilisateur, livre);
	                                livre.emprunté(); // remet l'état à Emprunté au lancement
	                            }
	                        } catch (NumberFormatException e) {
	                            System.out.println("ID de livre invalide : " + idStr);
	                        }
	                    }
	                }
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("Erreur lors du chargement des emprunts : " + e.getMessage());
	    }
	}


	
	/**
	 * Méthode getLivreById
	 * Permet de retrouver les vrais objets Livre à partir de leur ID
	 * @param id
	 * @return
	 */
	public Livre getLivreById(int id) {
	    for (Livre livre : this.getLivres()) {
	        if (livre.getId() == id) {
	            return livre;
	        }
	    }
	    return null;
	}
	
	public Utilisateur getAdherentParNom(String nom) {
	    for (Utilisateur u : adherents) {
	        if (u.getNom().equalsIgnoreCase(nom)) return u;
	    }
	    return null;
	}

	public Livre getLivreParTitre(String titre) {
	    for (Livre l : livres) {
	        if (l.getTitre().equalsIgnoreCase(titre)) return l;
	    }
	    return null;
	}

	public List<Utilisateur> getAdherents() {
		return adherents;
	}
	
	public Map<Utilisateur, List<Livre>> getLivresEmpruntes() {
		return livresEmpruntes;
	}

	public List<Livre> getLivresEnReparation() {
		return livresEnReparation;
	}

	public Map<Livre, Utilisateur> getLivresReserve() {
		return livresReserve;
	}

}
