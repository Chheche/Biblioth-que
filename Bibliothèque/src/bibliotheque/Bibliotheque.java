package bibliotheque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import state.*;
import utilisateur.*;

public class Bibliotheque {
    private List<Livre> livres = new ArrayList<>();
    private List<Utilisateur> adherents = new ArrayList();
    private List<Emprunt> livresEmpruntes = new ArrayList<>();
    private List<Livre> livresEnReparation = new ArrayList();
    private Map<Livre, Utilisateur> livresReserve = new HashMap<>();

    /************************ Ajout *******************************/
    
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
     * @param emprunt
     */
    public void ajouterEmprunt(Emprunt emprunt) {
        livresEmpruntes.add(emprunt);
        sauvegarderEmprunt(emprunt);
    }
    
    public void ajouterReparation(Livre livre) {
    	livresEnReparation.add(livre);
    }
    
    /************************ Suppression *******************************/
    
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
	        adherents.remove(adherentASupprimer);
	        reecrireLivre();
	        System.out.println("Adherent supprimé !");
	    } else {
	        System.out.println("Aucun adherent avec cet ID.");
	    }
	}
	
	/**
	 * Méthode supprimerEmprunt
	 * Permet de supprimer un emprunt de notre list
	 * @param id
	 */
	public void supprimerEmprunt(int id) {
	    Emprunt empruntASupprimer = null;

	    for (Emprunt emprunt : livresEmpruntes) {
	        if (emprunt.getId() == id) {
	        	empruntASupprimer = emprunt;
	            break;
	        }
	    }

	    if (empruntASupprimer != null) {
	    	livresEmpruntes.remove(empruntASupprimer);
	        reecrireEmprunts();
	    } else {
	        System.out.println("Aucun emprunt avec cet ID.");
	    }
	}
	
	/**
	 * Méthode supprimerReparation
	 * Supprime le livre de la liste de livre en réparation
	 * @param livre
	 */
	public void supprimerReparation(Livre livre) {
	    livresEnReparation.remove(livre);
	}
	
	/************************ Affichage *******************************/

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
     * Méthode afficherLivresEmpruntes
     * Permet de parcourir toute la liste est d'afficher les informations du livre
     */
	public void afficherLivresEmpruntes() {
        if (livresEmpruntes.isEmpty()) {
            System.out.println("Aucun livre emprunté.");
        } else {
            for (Emprunt l : livresEmpruntes) {
                System.out.println(l);
            }
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
	
	/************************ Sauvegarde *******************************/
	
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
	 * Méthode sauvegarderEmprunts
	 * Permet de sauvegarder dans un fichier txt les associations entre adhérent et livre emprunté
	 * @param emprunt
	 */
	public void sauvegarderEmprunt(Emprunt emprunt) {
	    try (FileWriter writer = new FileWriter("emprunts.txt", true)) { // true = append
	        writer.write(
	            emprunt.getId() + ";" +
	            emprunt.getLivre().getId() + ";" +
	            emprunt.getAdherent().getId() + ";" +
	            emprunt.getDateDebut() + ";" +
	            emprunt.getDateFin() + "\n"
	        );
	    } catch (IOException e) {
	        System.out.println("Erreur lors de la sauvegarde de l'emprunt : " + e.getMessage());
	    }
	}
	
	/************************ Réecriture *******************************/
	
	/**
	 * Méthode reecrireLivre
	 * Permet de réecrire par dessus tout le fichier txt (évite le problème des id). Utile lorsque l'on supprime un livre.
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
	 * Méthode reecrireEmprunts
	 * Permet de réecrire par dessus tout le fichier txt
	 */
	public void reecrireEmprunts() {
	    try (FileWriter writer = new FileWriter("emprunts.txt", false)) {
	        for (Emprunt emprunt : livresEmpruntes) {
	            writer.write(emprunt.getId() + ";" +
	                         emprunt.getLivre().getId() + ";" +
	                         emprunt.getAdherent().getId() + ";" +
	                         emprunt.getDateDebut() + ";" +
	                         emprunt.getDateFin() + "\n");
	        }
	    } catch (IOException e) {
	        System.out.println("Erreur lors de la réécriture des emprunts : " + e.getMessage());
	    }
	}
	
	/************************ Chargement *******************************/
	
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
	 * Méthode chargerEmpruntsDepuisFichier
	 * Permet de charger les livres empruntés du fichier txt
	 */
	public void chargerEmpruntsDepuisFichier() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("emprunts.txt"))) {
	        String ligne;
	        while ((ligne = reader.readLine()) != null) {
	            String[] parties = ligne.split(";");
	            if (parties.length != 5) continue;

	            int id = Integer.parseInt(parties[0]);
	            int idLivre = Integer.parseInt(parties[1]);
	            int idAdherent = Integer.parseInt(parties[2]);
	            LocalDate dateDebut = LocalDate.parse(parties[3]);
	            LocalDate dateFin = LocalDate.parse(parties[4]);

	            Livre livre = getLivreParId(idLivre);
	            Utilisateur adherent = getAdherentParId(idAdherent);

	            if (livre != null && adherent != null) {
	                Emprunt emprunt = new Emprunt(id, livre, adherent, dateDebut, dateFin);
	                livresEmpruntes.add(emprunt);
	                livre.setEtat(new LivreEmprunté());
	            } else {
	                System.out.println("Erreur de correspondance d'ID pour l'emprunt " + id);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Erreur lors du chargement des emprunts : " + e.getMessage());
	    } catch (Exception e) {
			// TODO Auto-generated catch block
	    	System.out.println("Erreur état emprunt : " + e.getMessage());
		}
	}
	
	/************************ Getters *******************************/

    public List<Livre> getLivres() {
        return livres;
    }
    
    public Livre getLivreParId(int id) {
        for (Livre livre : livres) {
            if (livre.getId() == id) {
                return livre;
            }
        }
        return null;
    }
    
    public Utilisateur getAdherentParId(int id) {
        for (Utilisateur adherent : adherents) {
            if (adherent.getId() == id) {
                return adherent;
            }
        }
        return null;
    }

	public List<Utilisateur> getAdherents() {
		return adherents;
	}

	public List<Emprunt> getLivresEmpruntes() {
		return livresEmpruntes;
	}

	public List<Livre> getLivresEnReparation() {
		return livresEnReparation;
	}

	public Map<Livre, Utilisateur> getLivresReserve() {
		return livresReserve;
	}

}
