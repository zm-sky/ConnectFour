package me.pokemutt.connectFour.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import me.pokemutt.connectFour.game.Board;
import me.pokemutt.connectFour.ui.BoardUI;
import me.pokemutt.connectFour.ui.UIManager;
import me.zmsky.core.GameBox;
import me.zmsky.core.states.GameState;

/**
 * 
 * @author Raul Karim Sabag Ballesteros
 *
 */
public class Gameplay extends GameState{

	private BoardUI board;
	private UIManager manager;
	
	public Gameplay(){
		board = new BoardUI(new Board(7,6), GameBox.getWindowWidth()/2, GameBox.getWindowHeight()/2);
		
		manager = new UIManager();
		manager.setState(0);
		manager.addUIElement(board);
	}
	
	@Override
	public void Render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GameBox.getWindowWidth(), GameBox.getWindowHeight());
		
		manager.renderUI(g);
	}
	
	@Override
	public void RenderGui(Graphics2D g) {
		super.RenderGui(g);
	}

	@Override
	public void Update(double delta) {
		super.Update(delta);
		manager.update();
	}

	@Override
	public void onMouseMove(int button, int x, int y, boolean Drag) {
		manager.onMouseMove(button, x, y, Drag);
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		manager.onMouseClick(button, x, y);
	}
	
	@Override
	public Enum<?> getStateID() {
		return GameStates.GAMEPLAY;
	}
}
