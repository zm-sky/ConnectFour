package me.pokemutt.connectFour;

import me.pokemutt.connectFour.gamestates.GameStates;
import me.pokemutt.connectFour.gamestates.Gameplay;
import me.pokemutt.connectFour.gamestates.MainMenu;
import me.zmsky.core.GameBox;
import me.zmsky.resources.ImageCenter;

/**
 * 
 * @author Raul Karim Sabag Ballesteros
 *
 */
public class ConnectFour {
	
	public static void main(String[] args){
		//Setup, ponemos el path donde estaran nuestros recursos.
		ImageCenter.setImageCenterDefaultPath("me/pokemutt/connectFour/resources/");
		
		//Iniciando CoreEngine y dandole los parametros que ocupa para iniciar.
		GameBox.InitGame("Connect Four - Alpha v.1.030218", 1000, 700);
		
		//Registrando nuestros estados de juego.
		GameBox.getStateHandler().registerState(new MainMenu());
		GameBox.getStateHandler().registerState(new Gameplay());
		GameBox.getStateHandler().enterState(GameStates.MAINMENU);
		
		//Finalmente iniciamos el juego.
		GameBox.StartGame();
	}
}
