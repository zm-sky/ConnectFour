package me.pokemutt.connectFour.particles;

import java.awt.image.BufferedImage;

import me.zmsky.resources.ImageCenter;

public enum ParticleType {
	REDWAVE("WaveRed.png"),
	BLUEWAVE("WaveBlue.png"),
	REDCHIP("RedChip.png"),
	BLUECHIP("BlueChip.png");
	
	public BufferedImage particleImage;
	
	ParticleType(String path){
		particleImage = ImageCenter.getImage(path);
	}
}
