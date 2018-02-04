package me.pokemutt.connectFour.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import me.pokemutt.connectFour.game.Cell;
import me.zmsky.resources.ImageCenter;

public class CellUI extends GUI{
	
	/**
	 * La celda que contiene informacion relevante al juego.
	 */
	private Cell celda;
	
	/**
	 * Crea una nueva interfaz para mostrar informacion de la celda.
	 * 
	 * @param celda La celda que sera dibujada en pantalla.
	 */
	public CellUI(Cell celda){
		this.celda = celda;
	}
	
	@Override
	public void onMouseClick(int button, int x, int y) {
		
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void draw(Graphics2D g){
		BufferedImage cellImage = ImageCenter.getImage("Cell.png");
		
		g.drawImage(cellImage, x, y, null);
		
		/**
		 * Si la celda no esta vacia, tenemos que dibujar la ficha dentro de ella.
		 */
		if(!celda.isEmpty()){
			BufferedImage chipImage = celda.getChip().getType().chipImage;
			
			g.drawImage(chipImage, x + chipImage.getWidth()/4 + 1, y + chipImage.getHeight()/4 +4, null);
		}
	}
	
	/**
	 * Regresa informacion de la celda.
	 */
	public Cell getCell(){
		return celda;
	}
}
