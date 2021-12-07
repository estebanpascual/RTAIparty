package com.almasb.fxglgames.RTAIparty;

/**
 * @author zorm1
 *	Classe permettant de stocker les données des joueurs
 */
public class Player implements Comparable<Player> {
	private String name;
	private int winCount;
	private boolean isLosing;
	private int typePlayer;
	
	
	/**
	 * Macro correspondant au sprite du garçon blond
	 */
	public static final int GARCON_BLOND	= 1;
	
	/**
	 * Macro correspondant au sprite du garçon brun
	 */
	public static final int GARCON_BRUN 	= 2;
	
	/**
	 * Macro correspondant au sprite de la fille blonde
	 */
	public static final int FILLE_BLONDE	= 3;
	
	/**
	 * Macro correspondant au sprite de la fille brune
	 */
	public static final int FILLE_BRUNE		= 4;

	/**
	 * @param name Nom du joueur
	 * @param typePlayer Type de sprite a affecter sur le joueur
	 * Constructeur de classe
	 */
	public Player(String name, int typePlayer){
		this.name = name;
		this.typePlayer = typePlayer;
	}

	/**
	 * 
	 * @param name  Nom du joueur
	 * @param typePlayer Type de sprite a affecter sur le joueur
	 * @param winCount Nombre de victoire
	 * @param isLosing Si le joueur a perdu
	 * 
	 * Surcharge du constructeur
	 */
	public Player(String name, int typePlayer, int winCount, boolean isLosing){
		this.name = name;
		this.typePlayer = typePlayer;
		this.winCount = winCount;
		this.isLosing = isLosing;
	}
	
	/**
	 * @return Retourne le nom du joueur
	 * Méthode permettant de récupérer le nom du joueur
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return Retourne le type de sprite
	 * Méthode permettant de récupérer le type de sprite du joueur
	 */
	public int getTypePlayer() {
		return this.typePlayer;
	}
	
	/**
	 * @return Retourne les points de victoire
	 * Méthode qui retourne le nombre de mini-jeux gagné
	 */
	public int getWinCount() {
		return this.winCount;
	}
	
	
	/**
	 * Incrémente un point de victoire
	 */
	public void onWinGame() {
		this.winCount++;
	}
	
	/**
	 * Le joueur est considéré comme perdant
	 */
	public void onLoseGame() {
		this.isLosing = true;
	}
	
	/**
	 * @return
	 * Méthode qui retourne si le joueur a perdu
	 */
	public boolean isLosing() {
		return this.isLosing;
	}
	
	 /**
	 * Méthode permettant de trier grâce à la collection les joueurs entre eux
	 */
	@Override
	 public int compareTo(Player player) {
		 return (player.getWinCount() - this.getWinCount());
	 }
	
}
