package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Assets;
import input.KeyBoard;
import main.Window;
import math.Vector2D;

public class Player extends MovingObject{
	
	private Vector2D heading;	
	private Vector2D acceleration;
	private final double ACC = 0.2;
	private final double DELTAANGLE = 0.1;
	private boolean accelerating = false;
	
	public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture) {
		super(position, velocity, maxVel, texture);
		heading = new Vector2D(0, 1);
		acceleration = new Vector2D();
	}
	
	@Override
	public void update() 
	{
		if(KeyBoard.RIGHT)
			angle += DELTAANGLE;
		if(KeyBoard.LEFT)
			angle -= DELTAANGLE;
		
		if(KeyBoard.UP)
		{
			acceleration = heading.scale(ACC);
			accelerating = true;
		}else
		{
			if(velocity.getMagnitude() != 0)
				acceleration = (velocity.scale(-1).normalize()).scale(ACC/2);
			accelerating = false;
		}
		
		velocity = velocity.add(acceleration);
		
		velocity = velocity.limit(maxVel);
		
		heading = heading.setDirection(angle - Math.PI/2);
		
		position = position.add(velocity);
		
		if(position.getX() > Window.WIDTH)
			position.setX(0);
		if(position.getY() > Window.HEIGHT)
			position.setY(0);
		
		if(position.getX() < 0)
			position.setX(Window.WIDTH);
		if(position.getY() < 0)
			position.setY(Window.HEIGHT);
		
		
	}
	
	
	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX() + width/2 + 5,
				position.getY() + height/2 + 10);
		
		AffineTransform at2 = AffineTransform.getTranslateInstance(position.getX() + 5, position.getY() + height/2 + 10);
		
		at1.rotate(angle, -5, -10);
		at2.rotate(angle, width/2 -5, -10);
		
		if(accelerating)
		{
			g2d.drawImage(Assets.speed, at1, null);
			g2d.drawImage(Assets.speed, at2, null);
		}
		
		
		
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		at.rotate(angle, width/2, height/2);
		
		g2d.drawImage(Assets.player, at, null);
		
	}
}
