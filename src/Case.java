/**
 *	Gestion des cases du terrain
 */
public class Case implements Constantes {

	private int x;
	private int y;

	/**
	 *	Creer une case avec les coordonnees x et y
	 */
	public Case(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 *	Modifie la coordonnee en X
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 *	Retourne la coordonnee en X
	 */
	public int getX() {
		return this.x;
	}

	/**
	 *	Modifie la coordonnee en Y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 *	Retourne la coordonee en Y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 *	Verifie si une case est dans le plateau
	 */
	public boolean estValide() {
		return this.x >= 0 && this.x < NBRE_COLONNES && this.y >= 0 && this.y < NBRE_LIGNES;
	}

	/**
	 *	Compare deux objets et retourne vrai si ils sont identiques
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Case) {
			Case box = (Case)obj;
			return this.x == box.x && this.y == box.y;
		}
		return false;
	}
}