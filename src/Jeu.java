import java.awt.event.KeyEvent;

/**
 *	Differentes actions du jeu
 */
public class Jeu implements Constantes {
	
	private Serpent serpent;
	private boolean laPartieEstPerdu;
	private Nourriture nourriture;

	/**
	 *	Creer une partie
	 */
	public Jeu() {
		this.serpent = new Serpent(POS_DEPART_SERPENT);
		this.laPartieEstPerdu = false;
		this.nourriture = new Nourriture();
	}

	/**
	 *	Effectue un tour de jeu
	 */
	public void calcul() {
		if(!this.laPartieEstPerdu) {
			this.serpent.calcul(this.nourriture);
			if(this.serpent.estMort()) {
				this.laPartieEstPerdu = true;
			}
		}
	}

	/**
	 *	Detection des touches du clavier
	 */
	public void gestionDuClavier(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.serpent.setDemandeClavier(Direction.VERS_LA_DROITE);
		} else if(event.getKeyCode() == KeyEvent.VK_LEFT) {
			this.serpent.setDemandeClavier(Direction.VERS_LA_GAUCHE);
		} else if(event.getKeyCode() == KeyEvent.VK_UP) {
			this.serpent.setDemandeClavier(Direction.VERS_LE_HAUT);
		} else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			this.serpent.setDemandeClavier(Direction.VERS_LE_BAS);
		}
	}

	/**
	 *	Affichage du jeu
	 */
	public void affichage() {
		this.serpent.affichage();
		this.nourriture.affichage();
	}
}