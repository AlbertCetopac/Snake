import java.util.LinkedList;

/**
 *	Gestion du serpent
 */
public class Serpent implements Constantes {

	private LinkedList<Case> list;
	private Direction direction;
	private Direction demande;
	private boolean estMort;
	private Case posQueueAnterieur;

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
		} else if(seMangePas()) {
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
		Case nextCase;
		switch (this.direction) {
			case VERS_LE_HAUT:
				nextCase = new Case(tete.getX(), tete.getY() - 1);
				if(nextCase.estValide()) {
					return nextCase;
				} else {
					return new Case(nextCase.getX(), NBRE_LIGNES - 1);
				}
			case VERS_LA_DROITE:
				nextCase = new Case(tete.getX() + 1, tete.getY());
				if(nextCase.estValide()) {
					return nextCase;
				} else {
					return new Case(0, nextCase.getY());
				}
			case VERS_LE_BAS:
				nextCase = new Case(tete.getX(), tete.getY() + 1);
				if(nextCase.estValide()) {
					return nextCase;
				} else {
					return new Case(nextCase.getX(), 0);
				}
			case VERS_LA_GAUCHE:
				nextCase = new Case(tete.getX() - 1, tete.getY());
				if(nextCase.estValide()) {
					return nextCase;
				} else {
					return new Case(NBRE_COLONNES - 1, nextCase.getY());
				}
		}
		return null;
	}

	/**
	 *	Retourne vrai si le serpent ne se mange pas
	 */
	private boolean seMangePas() {
		Case nextCase = getNextCase();
		return !this.list.contains(nextCase);
	}

	/**
	 *	Effectue l'action d'avancer le serpent
	 */
	private void avance() {
		posQueueAnterieur = this.list.getLast();
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
		avance();
		this.list.addLast(posQueueAnterieur);
	}

	private boolean peutManger(Nourriture nourriture) {
		return nourriture.equals(getNextCase());
	}
}