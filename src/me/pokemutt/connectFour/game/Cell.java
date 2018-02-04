package me.pokemutt.connectFour.game;

public class Cell {
	/**
	 * Una celda del tablero puede contener una ficha. Esta puede
	 * ser nula si es que esta vacia.
	 */
	private Chip chip;
	
	/**
	 * Constructor por default.
	 */
	public Cell(){}
	
	/**
	 * Agrega una ficha a esta celda. Si ya hay una ficha dentro de
	 * esta celda, entonces no se agregara nada.
	 * 
	 * @return Si se pudo agregar la ficha.
	 */
	public boolean addChip(Chip chip){
		this.chip = chip;
		
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
}
