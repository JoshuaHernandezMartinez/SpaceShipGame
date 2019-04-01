package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import graphics.Assets;
import input.KeyBoard;
import main.Window;
import math.Vector2D;
import states.GameState;

public class Player extends MovingObject{
	
	private Vector2D heading;	
	private Vector2D acceleration;
	private final double ACC = 0.2;
	private final double DELTAANGLE = 0.1;
	private boolean accelerating = false;
	private GameState gameState;	
	
	private long time, lastTime;
	
	public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
		super(position, velocity, maxVel, texture);
		this.gameState = gameState;
		heading = new Vector2D(0, 1);
		acceleration = new Vector2D();
		time = 0;
		lastTime = System.currentTimeMillis();
	}
	
	@Override
	public void update() 
	{
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(KeyBoard.SHOOT && time > 200)
		{
			gameState.getMovingObjects().add(0,new Laser(
					getCenter().add(heading.scale(width)),
					heading,
					10,
					angle,
					Assets.redLaser
					));
			time = 0;
		}
		
		
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
	
	public Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + height/2);
	}
	
}
