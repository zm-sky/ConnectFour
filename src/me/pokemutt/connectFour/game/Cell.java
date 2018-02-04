package me.pokemutt.connectFour.game;

public class Cell {
	
	/**
	 * Indica la fila a la que pertenece esta celda.
	 */
	private int row;
	
	/**
	 * Indica la columna a la que pertenece esta celda.
	 */
	private int col;
	
	/**
	 * Una celda del tablero puede contener una ficha. Esta puede
	 * ser nula si es que esta vacia.
	 */
	private Chip chip;
	
	/**
	 * Constructor por default.
	 */
	public Cell(){
		
	}
	
	/**
	 * Crea una nueva celda con los parametros de row y col.
	 * 
	 * @param row Indica la fila a la que pertenece esta celda.
	 * @param col Indica la columna a la que pertenece esta celda.
	 */
	public Cell(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Agrega una ficha a esta celda. Si ya hay una ficha dentro de
	 * esta celda, entonces no se agregara nada.
	 * 
	 * @return Si se pudo agregar la ficha.
	 */
	public boolean addChip(Chip chip){
		if(chip != null){
			this.chip = chip;
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Quita la ficha de la celda.
	 */
	public void clear(){
		chip = null;
	}
	
	/**
	 * Regresa la ficha. 
	 * 
	 * @return La ficha actualmente dentro de la celda. Puede ser nula.
	 */
	public Chip getChip(){
		return chip;
	}
	
	/**
	 * Indica si hay algo dentro de esta celda.
	 * 
	 * @return Si hay algo dentro de la celda.
	 */
	public boolean isEmpty(){
		return chip == null;
	}
	
	/**
	 * Indica la fila a la que pertenece esta celda.
	 * 
	 * @return La fila a la que pertenece esta celda.
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * Indica la columna a la que pertenece esta celda.
	 * 
	 * @return La columna a la que pertenece esta celda.
	 */
	public int getCol(){
		return col;
	}
}
