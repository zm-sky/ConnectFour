package me.pokemutt.connectFour.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import me.pokemutt.connectFour.game.Board;
import me.zmsky.core.GameBox;

/**
 * 
 * @author Raul Karim Sabag Ballesteros
 *
 */
public class BoardUI extends GUI{

	/**
	 * Indica el tamaño usual de las celdas.
	 */
	private static final int cellSize = 85;
	
	/**
	 * El tablero que vamos a estar dibujando, y que contiene toda la informacion
	 * relevante a ello.
	 */
	private Board board;
	
	/**
	 * Las celdas de esta UI para mostrar informacion de manera grafica.
	 */
	private CellUI[][] celdas;
	
	/**
	 * Crea una nueva UI para dibujar un objeto de tipo tablero.
	 * 
	 * @param board El tablero que contiene informacion de juego.
	 * @param x La posicion X del tablero en pantalla.
	 * @param y La posicion Y del tablero en pantalla.
	 */
	public BoardUI(Board board, int x, int y){
		this.x = x;
		this.y = y;
		this.board = board;
		
		celdas = new CellUI[board.getCells().length][board.getCells()[0].length];
		crearUICeldas();
	}
	
	/**
	 * Crea las celdas visibles.
	 */
	private void crearUICeldas(){
		//Primero, vamos a calcular el centro del tablero en X y Y.
		height = celdas.length * cellSize;
		width = celdas[0].length * cellSize;
		
		//Estas son las posiciones iniciales para empezar a
		//acomodar los elementos del tablero.
		int initX = x - width/2;
		int initY = y - height/2;
		
		//Estos son para recordar la posicion de cada celda temporalmente.
		int cellX = initX;
		int cellY = initY;
		
		for(int row = 0; row < celdas.length; row++){
			for(int col = 0; col < celdas[row].length; col++){
				CellUI ui = new CellUI();
				ui.x = cellX;
				ui.y = cellY;
				ui.width = cellSize;
				ui.height = cellSize;
				
				celdas[row][col] = ui;
				
				cellX+= cellSize;
			}
			
			cellY+= cellSize;
			cellX = initX;
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		/**Esta variable nos servira para determinar si tenemos que dibujar 
		 * un cuadro de seleccion alrededor de una columna. Ocupamos hacer esto 
		 * ya que si dibujaramos el cuadro de seleccion al momento de estar dibujando
		 * cada una de las celdas, las celdas seran dibujadas encima del cuadro.
		 * 
		 * Poreso primero dibujamos todas las celdas, y luego vemos si tenemos que
		 * dibujar el cuadro de seleccion.
		 * **/
		boolean highLightedRow = false;
		/**
		 * La columna que esta siendo seleccionada.
		 */
		int column = 0;
		
		/**
		 * Dibujamos todas las celdas..
		 */
		for(int row = 0; row < celdas.length; row++){
			for(int col = 0; col < celdas[row].length; col++){
				CellUI celda = celdas[row][col];	
				celda.draw(g);
				
				//Aqui vemos si una celda esta siendo seleccionada.
				//En caso de que si, tenemos que hacer que todas las celdas en esta columna
				//se muestren como que estan siendo seleccionadas.
				if(celda.isHighlighted){
					highLightedRow = true;
					column = col;
				}
			}
		}
		
		/**
		 * El usuario si esta seleccionando una columna, vamos a dibujar
		 * el cuadro de seleccion sobre todas las celdas en la columna.
		 */
		if(highLightedRow){
			CellUI celdaSuperior = celdas[0][column];
			g.setComposite(GameBox.getDefaultComposite().derive(0.3f));
			g.setColor(Color.WHITE);
			g.fillRect(celdaSuperior.x, celdaSuperior.y, cellSize, cellSize * celdas.length);
			g.setComposite(GameBox.getDefaultComposite());
		}
	}
	
	@Override
	public void onMouseMove(int button, int x, int y, boolean Drag) {
		for(CellUI[] row : celdas){
			for(CellUI celda : row){
				celda.onMouseMove(button, x, y, Drag);
			}
		}
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		
	}

	@Override
	public void update() {
		
	}
	
}
