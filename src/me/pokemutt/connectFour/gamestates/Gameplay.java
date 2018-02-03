package me.pokemutt.connectFour.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import me.zmsky.core.states.GameState;

/**
 * 
 * @author Raul Karim Sabag Ballesteros
 *
 */
public class Gameplay extends GameState{

	@Override
	public void Render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1,1);
	}
	
	@Override
	public void RenderGui(Graphics2D g) {
		super.RenderGui(g);
	}

	@Override
	public void Update(double delta) {
		super.Update(delta);
	}

	@Override
	public Enum<?> getStateID() {
		return GameStates.GAMEPLAY;
	}
}
