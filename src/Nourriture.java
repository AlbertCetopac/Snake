import java.util.Random;

/**
 *	Gestion de la nourriture
 */
public class Nourriture extends Case {

	private final static Random random = new Random();

	/**
	 *	Creer une case de type nourriture
	 */
	public Nourriture() {
		super(getRandomX(), getRandomY());
	}

	/**
	 *	Genere une coordonnee X aleatoire
	 */
	public static int getRandomX() {
		return random.nextInt(NBRE_COLONNES);
	}

	/**
	 *	Genere une coordonnee Y aleatoire
	 */
	public static int getRandomY() {
		return random.nextInt(NBRE_LIGNES);
	}

	/**
	 *	Donne de nouvelle coordonnee à la nourriture
	 */
	public void nouvelleNourriture() {
		setX(getRandomX());
		setY(getRandomY());
	}

	/**
	 *	Affichage de la nourriture
	 */
	public void affichage() {}
}