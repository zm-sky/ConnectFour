package me.pokemutt.connectFour.game;

public class Chip {
	/**
	 * Representa el tipo de ficha que es. Puede ser Azul o Roja.
	 */
	private ChipType type;
	
	/**
	 * Crea una nueva ficha de determinado tipo, azul o roja.
	 * @param type El tipo de ficha desesado.
	 */
	public Chip(ChipType type){
		this.type = type;
	}
	
	/**
	 * Regresa el tipo de ficha a la cual pertenece esta instancia.
	 * @return El tipo de ficha.
	 */
	public ChipType getType(){
		return type;
	}
}
