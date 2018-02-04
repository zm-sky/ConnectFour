package me.pokemutt.connectFour.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import me.pokemutt.connectFour.math.Vector2;
import me.pokemutt.connectFour.particles.ParticleEmitter;
import me.pokemutt.connectFour.particles.ParticleType;
import me.pokemutt.connectFour.ui.BoundingBox;
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
	 * Emisores de particulas, sirven de estetica para el menu.
	 */
	private ParticleEmitter blueWaveEmitter;
	private ParticleEmitter redWaveEmitter;
	
	/**
	 * Crea una instancia de este estado de juego. 
	 */
	public MainMenu(){
		manager = new UIManager();
		manager.setState(MAINMENU);
		
		crearEmisores();
		crearUI();
	}

	/**
	 * Crea toda la UI del main menu.
	 */
	private void crearUI(){
		//Valores temporales, para determinar desde donde poner los elementos de la UI en pantalla.
		int x = GameBox.getWindowWidth()/2;
		int y = GameBox.getWindowHeight()/2;
		
		manager.addUIElement(new BoundingBox(MAINMENU, "BoundingBox", Color.BLACK, 0.7f, x, y+10, 300, 300));
		manager.addUIElement(new Button(MAINMENU, "PlayButton", "Join Server", x, y - 70, 150, 50));
		manager.addUIElement(new Button(MAINMENU, "OnlineButton", "Host Server", x, manager.getUIElement("PlayButton").getY() + 78, 150, 50));
		manager.addUIElement(new Button(MAINMENU, "OptionsButton", "Options", x, manager.getUIElement("OnlineButton").getY() + 78, 150, 50));
		manager.addUIElement(new Button(MAINMENU, "ExitButton", "Quit", x, manager.getUIElement("OptionsButton").getY() + 78, 150, 50));
		manager.addUIListener(this);
	}
	
	/**
	 * Crea los emisores de particulas.
	 */
	private void crearEmisores(){
		blueWaveEmitter = new ParticleEmitter(ParticleType.BLUEWAVE);
		blueWaveEmitter.posicion = new Vector2(-200, -400);
		blueWaveEmitter.direccion = new Vector2(0,1);
		blueWaveEmitter.velocidad = new Vector2(1, 2);
		blueWaveEmitter.tiempoVida = 14;
		blueWaveEmitter.preEmit(1);
		
		redWaveEmitter = new ParticleEmitter(ParticleType.REDWAVE);
		redWaveEmitter.posicion = new Vector2(GameBox.getWindowWidth()-140, -400);
		redWaveEmitter.direccion = new Vector2(0,1);
		redWaveEmitter.velocidad = new Vector2(1, 2);
		redWaveEmitter.tiempoVida = 14;
		redWaveEmitter.preEmit(1f);
	}
	
	/**
	 * Dibuja la interfaz grafica encima de todos los demas elementos del juego.
	 */
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
		
		blueWaveEmitter.draw(g);
		redWaveEmitter.draw(g);
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
		
		blueWaveEmitter.update(delta);
		redWaveEmitter.update(delta);
	}

	@Override
	public Enum<?> getStateID() {
		return GameStates.MAINMENU;
	}
	
	
}
