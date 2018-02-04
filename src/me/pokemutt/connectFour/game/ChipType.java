package me.pokemutt.connectFour.game;

import java.awt.image.BufferedImage;

import me.zmsky.resources.ImageCenter;

public enum ChipType {
	RED(""),
	BLUE("");
	
	BufferedImage chipImage;
	
	ChipType(String imagePath){
		chipImage = ImageCenter.getImage(imagePath);
	}
}
