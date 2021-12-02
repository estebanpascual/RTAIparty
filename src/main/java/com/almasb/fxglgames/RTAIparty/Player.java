package com.almasb.fxglgames.RTAIparty;

public class Player implements Comparable<Player> {
	private String name;
	private int winCount;
	private boolean isLosing;
	private int typePlayer;
	
	public static final int GARCON_BLOND	= 1;
	public static final int GARCON_BRUN 	= 2;
	public static final int FILLE_BLONDE	= 3;
	public static final int FILLE_BRUNE		= 4;

	public Player(String name, int typePlayer){
		this.name = name;
		this.typePlayer = typePlayer;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getTypePlayer() {
		return this.typePlayer;
	}
	
	public int getWinCount() {
		return this.winCount;
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
	
	 @Override
	 public int compareTo(Player player) {
		 return (player.getWinCount() - this.getWinCount());
	 }
	
}
