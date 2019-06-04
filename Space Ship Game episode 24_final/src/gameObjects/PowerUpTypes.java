package gameObjects;

import java.awt.image.BufferedImage;

import graphics.Assets;


public enum PowerUpTypes {
	SHIELD("SHIELD", Assets.shield),
	LIFE("+1 LIFE", Assets.life),
	SCORE_X2("SCORE x2", Assets.doubleScore),
	FASTER_FIRE("FAST FIRE", Assets.fastFire),
	SCORE_STACK("+1000 SCORE", Assets.star),
	DOUBLE_GUN("DOUBLE GUN", Assets.doubleGun);
	
	public String text;
	public BufferedImage texture;
	
	private PowerUpTypes(String text, BufferedImage texture){
		this.text = text;
		this.texture = texture;
	}
}
