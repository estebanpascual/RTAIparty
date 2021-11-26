package com.almasb.fxglgames.RTAIparty;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getSceneService;
import static com.almasb.fxgl.dsl.FXGL.runOnce;

import java.util.ArrayList;

import com.almasb.fxgl.app.scene.GameSubScene;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.scene.SubScene;

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
	
	public void nextPlayer() {
		
		this.GameScene.getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
		
		System.out.println("next Player");
		this.currentPlayer++;
		if(this.players.size()-1 < this.currentPlayer){
			
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
		}
		
		nextGame();
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
