package me.pokemutt.connectFour.gamestates;

public enum GameStates {
	MAINMENU("MainMenu"),
	GAMEPLAY("Gameplay");
	
	public String id;
	
	GameStates(String id){
		this.id = id;
	}
}
