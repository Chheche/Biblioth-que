package state;

// NotificationAvecEtat.java
	public class LivreAvecEtat {
	    private Etat etat;
	
	    public LivreAvecEtat() {
	        etat = new Disponible(this);
	    }
	
	    public void emprunté() {
	    	etat.emprunté();
	    }
	
	    public void réservé() {
	    	etat.réservé(); 
	    }
	    
	    public void enRéparation() {
	    	etat.enRéparation(); 
	    }
	    
	    public void doAction() {
	    	etat.doAction();
	    }
	
	    public void setEtat(Etat e) {
	    	this.etat = e;
	    }
	    
	    public Etat getEtat() {
	    	return etat;
	    }
	}
