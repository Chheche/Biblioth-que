package state;

import bibliotheque.*;

public interface EtatLivre {
	
    void disponible(Livre livre);
    void emprunté(Livre livre) throws Exception;
    void réservé(Livre livre);
    void enRéparation(Livre livre);
    void doAction(Livre livre);
}
