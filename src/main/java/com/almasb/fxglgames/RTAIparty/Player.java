package com.almasb.fxglgames.RTAIparty;

import com.almasb.fxgl.texture.Texture;

public class Player {
	private String name;
	private int winCount;
	private boolean isLosing;
	private Texture spritePlayer;
	
	public Player(String name, Texture spritePlayer){
		this.name = name;
		this.spritePlayer = spritePlayer;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void onWinGame() {
		this.winCount++;
	}
	
	public void onLoseGame() {
		this.isLosing = true;
	}
	
	public boolean isLosing() {
		return this.isLosing;
	}
	
}
