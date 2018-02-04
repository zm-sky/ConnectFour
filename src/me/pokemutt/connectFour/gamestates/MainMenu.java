package me.pokemutt.connectFour.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import me.pokemutt.connectFour.ConnectFour;
import me.pokemutt.connectFour.ui.Button;
import me.pokemutt.connectFour.ui.GUI;
import me.pokemutt.connectFour.ui.UIListener;
import me.pokemutt.connectFour.ui.UIManager;
import me.zmsky.core.GameBox;
import me.zmsky.core.states.GameState;
import me.zmsky.resources.ImageCenter;

/**
 * 
 * @author Raul Karim Sabag Ballesteros
 *
 */
public class MainMenu extends GameState implements UIListener{

	/**
	 * Estados del UIManager. 
	 */
	private static final int MAINMENU = 1, OPTIONS = 2;
	
	/**
	 * Un UIManager que nos permite usar de manera facil elementos de interfaz grafica.
	 */
	private UIManager manager;
	
	/**
	 * Crea una instancia de este estado de juego. 
	 */
	public MainMenu(){
		manager = new UIManager();
		manager.setState(MAINMENU);
		
		//Valores temporales, para determinar desde donde poner los elementos de la UI en pantalla.
		int x = GameBox.getWindowWidth()/2;
		int y = GameBox.getWindowHeight()/2;
		
		manager.addUIElement(new Button(MAINMENU, "PlayButton", "Play Game", x, y - 70, 120, 50));
		manager.addUIElement(new Button(MAINMENU, "OnlineButton", "Online", x, manager.getUIElement("PlayButton").getY() + 78, 120, 50));
		manager.addUIElement(new Button(MAINMENU, "OptionsButton", "Options", x, manager.getUIElement("OnlineButton").getY() + 78, 120, 50));
		manager.addUIElement(new Button(MAINMENU, "ExitButton", "Quit", x, manager.getUIElement("OptionsButton").getY() + 78, 120, 50));
		manager.addUIListener(this);
	}

	@Override
	public void RenderGui(Graphics2D g) {
		super.RenderGui(g);
		
		BufferedImage logo = ImageCenter.getImage("ConnectFourLogo.png");
		g.drawImage(logo, (GameBox.getWindowWidth()/2 - logo.getWidth()/2), (20 + logo.getHeight()/2), null);
		
		manager.renderUI(g);
	}

	/**
	 * Escucha cuando algun elemento de la UI haya sido activado.
	 * 
	 * @param uiElement El elemento UI que fue activado.
	 * @param uiID La ID del elemento que fue activado.
	 */
	@Override
	public void onUIAction(GUI uiElement, String uiID) {
		if(uiID.equalsIgnoreCase("PlayButton")){
			GameBox.getStateHandler().enterState(GameStates.GAMEPLAY);
		}
		else if(uiID.equalsIgnoreCase("ExitButton"))
			GameBox.StopGame();
	}
	
	/**
	 * Renders this Focus instance.
	 * 
	 * @param g Current graphics context
	 */
	public void Render(Graphics2D g) { 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GameBox.getWindowWidth(), GameBox.getWindowHeight());
	}
	
	@Override
	public void onMouseMove(int button, int x, int y, boolean Drag) {
		// TODO Auto-generated method stub
		manager.onMouseMove(button, x, y, Drag);
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		// TODO Auto-generated method stub
		manager.onMouseClick(button, x, y);
	}

	@Override
	public void Update(double delta) {
		super.Update(delta);
		manager.update();
	}

	@Override
	public Enum<?> getStateID() {
		return GameStates.MAINMENU;
	}
	
	
}
