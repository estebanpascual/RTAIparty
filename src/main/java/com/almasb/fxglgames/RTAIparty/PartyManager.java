package com.almasb.fxglgames.RTAIparty;

import static com.almasb.fxgl.dsl.FXGL.getSceneService;
import static com.almasb.fxgl.dsl.FXGL.runOnce;

import java.util.ArrayList;

import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

public class PartyManager {
	private ArrayList<Player> players;
	private int currentPlayer;
	private int currentLap;
	private int currentGame;
	private GameSubScene GameScene;

	
	public static final int DODGEGAME = 1;
	public static final int RYTHMGAME = 2;
	public static final int MEMORYGAME= 3;
	
	PartyManager(ArrayList<Player> players){
		this.players = players;
		this.currentGame = DODGEGAME;
		this.currentLap = 1;
		this.currentPlayer = 0;
		
		//getGameWorld().addEntityFactory(new RTAIpartyFactory());
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void startGame() {
		this.GameScene = new DodgeSubScene(players.get(this.currentPlayer), this.currentLap, this);
		runOnce(() -> {
			getSceneService().pushSubScene(this.GameScene);

    	 }, Duration.seconds(0.0));
	}
	
	public void nextPlayer(boolean result) {
		
		if(result) {
			System.out.println("Le joueur à gagné");
			this.players.get(this.currentPlayer).onWinGame();
		}else {
			System.out.println("Le joueur à perdu");
			this.players.get(this.currentPlayer).onLoseGame();
		}
			
		this.GameScene.getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
		
		System.out.println("next Player");
		
		this.currentPlayer++;
		int response = checkPlayerTurn();
		if(response == 0) {
			return;
		}
		
		while(this.players.get(this.currentPlayer).isLosing() == true) {
			System.out.println(this.players.get(this.currentPlayer).getName() + " passe son tour car il a perdu");
			
			this.currentPlayer++;
			response = checkPlayerTurn();
			if(response == 0) {
				return;
			}
		}
		
		
		nextGame();
	}
	
	
	public int checkPlayerTurn() {
		
		
		if(this.players.size()-1 < this.currentPlayer){
			
			int count = 0;
			int nbPlayerLive = 0;
			
			this.currentPlayer = 0;
			
			while (this.players.size() > count) {
				if(!this.players.get(count).isLosing()) {
					nbPlayerLive++;
				}
				 count++;
			}
			
			if(nbPlayerLive < 2) {
				int countPlayer = 0;
				while (this.players.size() > countPlayer) {
					System.out.println("Nom : " + this.players.get(countPlayer).getName() + " | Score: " + this.players.get(countPlayer).getWinCount());
					
					countPlayer++;
				}
				
				System.out.println("FIN DE LA PARTIE");
				runOnce(() -> {
					getSceneService().pushSubScene(new EndgameSubScene(players, this));

		    	 }, Duration.seconds(0.0));
				return 0;
				//System.exit(3);
			}
			
			this.currentPlayer = 0;
			
	
			switch (currentGame) {
			case DODGEGAME:
				this.currentGame = RYTHMGAME;
				break;
			
			case RYTHMGAME:
				this.currentGame = MEMORYGAME;
				break;
				
			case MEMORYGAME:
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
