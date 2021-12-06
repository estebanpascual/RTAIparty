package com.almasb.fxglgames.RTAIparty;

import static com.almasb.fxgl.dsl.FXGL.getSceneService;
import static com.almasb.fxgl.dsl.FXGL.runOnce;

import java.util.ArrayList;

import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

/**
 * @author GROUPE5
 *
 * Classe permettant de gérer le lancement des mini-jeux et la gestion des différents joueur ainsi que la victoire et la défaite
 */
public class PartyManager {
	
	//Liste contenant tout les joueurs actuel de la partie
	private ArrayList<Player> players;
	
	//joueur actuellement en jeu
	private int currentPlayer;
	
	//nombre de tour actuel
	private int currentLap;
	
	//index du mini-jeux actuel
	private int currentGame;
	
	//scène actuel
	private GameSubScene GameScene;

	//MACRO pour les différents mini-jeux
	public static final int DODGEGAME = 1;
	public static final int RYTHMGAME = 2;
	public static final int MEMORYGAME= 3;
	
	
	/**
	 * @param players
	 * Constructeur du PartyManager
	 */
	PartyManager(ArrayList<Player> players){
		this.players = players;
		this.currentGame = DODGEGAME;
		this.currentLap = 1;
		this.currentPlayer = 0;
	}
	
	
	/**
	 * @param p
	 */
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	
	/**
	 * Fonction de lancement de la première scène
	 */
	public void startGame() {
		this.GameScene = new DodgeSubScene(players.get(this.currentPlayer), this.currentLap, this);
		runOnce(() -> {
			getSceneService().pushSubScene(this.GameScene);

    	 }, Duration.seconds(0.0));
	}
	
	/**
	 * @param result
	 * Fonction de récupération de fin de mini-jeux
	 */
	public void nextPlayer(boolean result) {
		
		//si le joueur à gagner son mini-jeux
		if(result) {
			System.out.println("Le joueur à gagné");
			this.players.get(this.currentPlayer).onWinGame();
		}else {
			//si le joueur à perdu son mini-jeux
			System.out.println("Le joueur à perdu");
			this.players.get(this.currentPlayer).onLoseGame();
		}
		
		//Suppression des éléments de la scène actuellement en cours
		this.GameScene.getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
		
		System.out.println("next Player");
		
		//changement de joueur
		this.currentPlayer++;
		
		int response = checkPlayerTurn();
		
		//si tout les joueurs ont perdu ou si il n'en reste qu'un en vie ET que le mini-jeux est fini
		if(response == 0) {
			//On quitte le traitement
			return;
		}
		
		//Recherche du joueur prochain en passant les joueurs ayant déjà perd
		while(this.players.get(this.currentPlayer).isLosing() == true) {
			System.out.println(this.players.get(this.currentPlayer).getName() + " passe son tour car il a perdu");
			
			this.currentPlayer++;
			response = checkPlayerTurn();
			//si tout les joueurs ont perdu ou si il n'en reste qu'un en vie ET que le mini-jeux est fini
			if(response == 0) {
				//On quitte le traitement
				return;
			}
		}
		
		//lancement de la fonction pour changer de scène
		nextGame();
	}
	
	

	/**
	 * @return
	 * Fonction permettant de vérifier si les joueurs sont en vie
	 * Fonction permettant de passer au mini-jeu suivant
	 */
	public int checkPlayerTurn() {
		
		//Si tout les joueurs ont joué
		if(this.players.size()-1 < this.currentPlayer){
			
			int count = 0;
			int nbPlayerLive = 0;
			
			//remise à 0 du joueur actuel
			this.currentPlayer = 0;
			
			//boucle permettant de calculer le nombre de joueur en vie
			while (this.players.size() > count) {
				if(!this.players.get(count).isLosing()) {
					nbPlayerLive++;
				}
				 count++;
			}
			
			//si il y a moins de 2 joueurs en vie
			if(nbPlayerLive < 2) {
				
				//execution du changement de scène de fin de jeu
				System.out.println("FIN DE LA PARTIE");
				runOnce(() -> {
					getSceneService().pushSubScene(new EndgameSubScene(players, this));

		    	 }, Duration.seconds(0.0));
				
				return 0;
			}
			
			//switch permettant de determiner quel scène devra être éxecuté
			switch (currentGame) {
			case DODGEGAME:
				this.currentGame = RYTHMGAME;
				break;
			
			case RYTHMGAME:
				this.currentGame = MEMORYGAME;
				break;
				
			case MEMORYGAME:
				//si on fini le jeu de memory, on indique l'ajout du second tour
				this.currentLap++;
				this.currentGame = DODGEGAME;
				break;	
				
			default:
				break;
			}
			
			return 1;
		}
		
		return 1;
	}
	
	
	/**
	 * Fonction permettant de mettre en avant la scène actuellement sélectionné
	 */
	public void nextGame() {
		
		switch (this.currentGame) {
			case DODGEGAME:
				this.GameScene = new DodgeSubScene(players.get(this.currentPlayer), this.currentLap, this);
				runOnce(() -> {
					getSceneService().pushSubScene(this.GameScene);

		    	 }, Duration.seconds(0.0));
				break;
				
			case RYTHMGAME:
				this.GameScene = new RythmSubScene(players.get(this.currentPlayer), this.currentLap, this);
				runOnce(() -> {
					getSceneService().pushSubScene(this.GameScene);

		    	 }, Duration.seconds(0.0));
				break;
			
			case MEMORYGAME:
				this.GameScene = new MemorySubScene(players.get(this.currentPlayer), this.currentLap, this);
				runOnce(() -> {
					getSceneService().pushSubScene(this.GameScene);

		    	 }, Duration.seconds(0.0));
				break;

			default:
				break;
		}
		
	}
	
	@Override
	public String toString() {
		String statPlayers = "";

		for(int i = 0; i > players.size(); i++) {
			statPlayers += i + ") "+ players.get(i).getName() + "\n";
		}
		
		return statPlayers;
	}

}
