package me.pokemutt.connectFour.game;

/**
 * 
 * @author Raul Karim Sabag Ballesteros
 *
 */
public class Board {
	
	//Representa el numero de celdas horizontales.
	private int hcells;
	//Representa el numero de celdas verticales.
	private int vcells;
	//Representa el tablero en un arreglo multidimensional.
	private Cell[][] tablero;
	
	/**
	 * Crea una instancia vacia de un tablero. Este guarda datos,
	 * ofrece metodos para la manipulacion de los mismos entre otras utilidades.
	 * Para visualizar este tablero, se puede utilizar la clase BoardUI.
	 * 
	 * @param hcells La cantidad de celdas horizontales.
	 * @param vcells La cantidad de celdas verticales.
	 */
	public Board(int hcells, int vcells){
		this.hcells = hcells;
		this.vcells = vcells;
		
		tablero = new Cell[vcells][hcells];
		
		//Inicializamos el arreglo.
		for(int i = 0; i < tablero.length; i++){
			for(int j = 0; j < tablero[i].length; j++){
				tablero[i][j] = new Cell();
			}
		}
	}
	
	/**
	 * Agrega una nueva ficha en determinada columna. Solamente se ocupa la columna, ya que
	 * la ficha debe aparecer en el espacio mas lejano que este vacio. Si no hay mas espacio
	 * en la columna para una ficha, entonces no se realiza ninguna operacion.
	 * 
	 * @param row La columna en donde agregaremos la nueva ficha.
	 * @param chip La ficha que vamos a agregar.
	 * 
	 * @return Si se pudo agregar la ficha.
	 */
	public boolean addChip(int row, Chip chip){
		for(int i = 0; i < tablero[row].length; i++){
			//Si el espacio esta vacio, vamos a ocupar este espacio para la ficha nueva.
			if(tablero[row][i] == null){
				tablero[row][i].addChip(chip);
				
				return true;
			}
		}
		
		//La ficha no se pudo agregar, porque ya no hay espacios disponibles en la columna.
		return false;
	}
	
	/**
	 * Determina si hay un ganador en el tablero. Para ello, uno de los jugadores debe de conectar
	 * cuatro fichas de manera vertical, diagonal o horizontal del mismo color.
	 * 
	 * @return Regresa el tipo de ficha del ganador. Si no hay ganador, regresa null.
	 */
	public Chip checkWin(){
	    final int HEIGHT = tablero.length;
	    final int WIDTH = tablero[0].length;
	    
	    for (int r = 0; r < HEIGHT; r++) { // Itera filas, de arriba hacia abajo.
	        for (int c = 0; c < WIDTH; c++) { // Itera columnas, de izquierda a derecha.
	            Cell cell = tablero[r][c];
	            
	            //Si el espacio no contiene una ficha, no hay porque checarla.
	            if (cell.isEmpty())
	                continue; 

	            Chip player = cell.getChip();
	            
	            //Aqui checamos si hay 4 hacia la derecha.
	            //Primero vemos si hay 3 espacios disponibles..
	            if (c + 3 < WIDTH &&
	                player.getType() == tablero[r][c+1].getChip().getType() && 
	                player.getType() == tablero[r][c+2].getChip().getType() &&
	                player.getType() == tablero[r][c+3].getChip().getType())
	                return player;
	            
	            //Si no hubo ganador, checamos hacia abajo para ver
	            //si hay 3 espacios disponibles.
	            if (r + 3 < HEIGHT) {
	                if (player.getType() == tablero[r+1][c].getChip().getType() && 
	                	player.getType() == tablero[r+2][c].getChip().getType() &&
	                	player.getType() == tablero[r+3][c].getChip().getType())
	                    return player;
	                if (c + 3 < WIDTH &&
	                	player.getType() == tablero[r+1][c+1].getChip().getType() &&
	                	player.getType() == tablero[r+2][c+2].getChip().getType() &&
	                	player.getType() == tablero[r+3][c+3].getChip().getType())
	                    return player;
	                if (c - 3 >= 0 &&
	                	player.getType() == tablero[r+1][c-1].getChip().getType() &&
	                	player.getType() == tablero[r+2][c-2].getChip().getType() &&
	                	player.getType() == tablero[r+3][c-3].getChip().getType())
	                    return player;
	            }
	        }
	    }
		
		return null;
	}
	
	/**
	 * Regresa todas las celdas que actualmente estan en el tablero.
	 * @return
	 */
	public Cell[][] getCells(){
		return tablero;
	}
}
