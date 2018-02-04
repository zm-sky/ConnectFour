package me.pokemutt.connectFour.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import me.zmsky.core.GameBox;
import me.zmsky.resources.ImageCenter;

public class CellUI extends GUI{
	
	@Override
	public void onMouseClick(int button, int x, int y) {
		
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void draw(Graphics2D g){
		BufferedImage cellImage = ImageCenter.getImage("Cell.png");
		
		g.drawImage(cellImage, x, y, width, height, null);
	}
}
