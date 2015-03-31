import java.util.LinkedList;

/**
 *	Gestion du serpent
 */
public class Serpent implements Constantes {

	private LinkedList<Case> list;
	private Direction direction;
	private Direction demande;
	private boolean estMort;

	/**
	 *	Creer un serpent de TAILLE_SERPENT case en position
	 */
	public Serpent(Case position) {
		this.list = new LinkedList<Case>();
		for(int i = 0; i < TAILLE_SERPENT; i++) {
			this.list.add(new Case(position.getX()+i, position.getY()));
		}
		this.direction = Direction.VERS_LA_GAUCHE;
		this.estMort = false;
	}

	/**
	 *	Effectue un tour d'action pour le serpent
	 */
	public void calcul(Nourriture nourriture) {
		tourner();
		if(peutManger(nourriture)) {
			mange();
			nourriture.nouvelleNourriture();
		} else if(peutAvancer()) {
			avance();
		} else {
			this.estMort = true;
		}
	}

	/**
	 *	Retourne si le serpent est mort ou pas
	 */
	public boolean estMort() {
		return this.estMort;
	}

	/**
	 *	Affichage du serpent
	 */
	public void affichage() {
		// dessin du serpent
	}

	/**
	 *	
	 */
	public void setDemandeClavier(Direction demande) {
		this.demande = demande;
	}

	/**
	 *	Retourne les coordonnees de la case suivante
	 */
	private Case getNextCase() {
		Case tete = this.list.getFirst();
		switch (this.direction) {
			case VERS_LE_HAUT:
				return new Case(tete.getX(), tete.getY() - 1);
			case VERS_LA_DROITE:
				return new Case(tete.getX() + 1, tete.getY());
			case VERS_LE_BAS:
				return new Case(tete.getX(), tete.getY() + 1);
			case VERS_LA_GAUCHE:
				return new Case(tete.getX() - 1, tete.getY());
		}
		return null;
	}

	/**
	 *	Retourne vrai si le serpent peut avancer
	 */
	private boolean peutAvancer() {
		Case nextCase = getNextCase();
		return nextCase.estValide() && !this.list.contains(nextCase);
	}

	/**
	 *	Effectue l'action d'avancer le serpent
	 */
	private void avance() {
		this.list.addFirst(getNextCase());
		this.list.removeLast();
	}

	private void tourner() {
		if(this.demande != null) {
			if(this.direction == Direction.VERS_LE_HAUT || this.direction == Direction.VERS_LE_BAS) {
				if(this.demande == Direction.VERS_LA_DROITE) {
					this.direction = Direction.VERS_LA_DROITE;
				} else if(this.demande == Direction.VERS_LA_GAUCHE) {
					this.direction = Direction.VERS_LA_GAUCHE;
				}
			} else {
				if(this.demande == Direction.VERS_LE_HAUT) {
					this.direction = Direction.VERS_LE_HAUT;
				} else if(this.demande == Direction.VERS_LE_BAS) {
					this.direction = Direction.VERS_LE_BAS;
				}
			}
		}
		this.demande = null;
	}

	private void mange() {
		this.list.addFirst(getNextCase());
	}

	private boolean peutManger(Nourriture nourriture) {
		return nourriture.equals(getNextCase());
	}
}