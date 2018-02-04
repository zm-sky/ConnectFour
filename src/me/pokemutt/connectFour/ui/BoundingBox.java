package me.pokemutt.connectFour.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import me.zmsky.core.GameBox;

public class BoundingBox extends GUI{

	private float transparency;
	
	public BoundingBox(int StateID,String DistinctiveID,Color color, float transparency,int x, int y, int width, int height){
		this.x = x - width/2;
		this.y = y - height/2;
		this.width = width;
		this.height = height;
		
		backgroundColor = color;
		this.transparency = transparency;
		this.distinctiveID = DistinctiveID;
		this.stateID = StateID;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(backgroundColor); g.setComposite(GameBox.getDefaultComposite().derive(transparency));
		g.fillRoundRect(x, y, width, height, 7, 7);
		g.setComposite(GameBox.getDefaultComposite()); g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(x, y, width, height, 7, 7);
	}

}
