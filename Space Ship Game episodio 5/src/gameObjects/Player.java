package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import math.Vector2D;

public class Player extends GameObject{

	public Player(Vector2D position, BufferedImage texture) {
		super(position, texture);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
	}
}
