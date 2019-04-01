package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.Assets;
import math.Vector2D;
import states.GameState;

public class Ufo extends MovingObject{
	
	private ArrayList<Vector2D> path;
	
	private Vector2D currentNode;
	
	private int index;
	
	private boolean following;
	
	private Chronometer fireRate;
	
	public Ufo(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
			ArrayList<Vector2D> path, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		this.path = path;
		index = 0;
		following = true;
		fireRate = new Chronometer();
		fireRate.run(Constants.UFO_FIRE_RATE);
	}
	
	private Vector2D pathFollowing() {
		currentNode = path.get(index);
		
		double distanceToNode = currentNode.subtract(getCenter()).getMagnitude();
		
		if(distanceToNode < Constants.NODE_RADIUS) {
			index ++;
			if(index >= path.size()) {
				following = false;
			}
		}
		
		return seekForce(currentNode);
		
	}
	
	private Vector2D seekForce(Vector2D target) {
		Vector2D desiredVelocity = target.subtract(getCenter());
		desiredVelocity = desiredVelocity.normalize().scale(maxVel);
		return desiredVelocity.subtract(velocity);
	}
	
	@Override
	public void update() {
		
		Vector2D pathFollowing;
		
		if(following)
			pathFollowing = pathFollowing();
		else
			pathFollowing = new Vector2D();
		
		pathFollowing = pathFollowing.scale(1/Constants.UFO_MASS);
		
		velocity = velocity.add(pathFollowing);
		
		velocity = velocity.limit(maxVel);
		
		position = position.add(velocity);
		
		if(position.getX() > Constants.WIDTH || position.getY() > Constants.HEIGHT
				|| position.getX() < 0 || position.getY() < 0)
			Destroy();
		
		// shoot
		
		if(!fireRate.isRunning()) {
			
			Vector2D toPlayer = gameState.getPlayer().getCenter().subtract(getCenter());
			
			toPlayer = toPlayer.normalize();
			
			double currentAngle = toPlayer.getAngle();
			
			double newAngle = Math.random()*(Math.PI) - Math.PI/2 + currentAngle;
			
			toPlayer = toPlayer.setDirection(newAngle);
			
			Laser laser = new Laser(
					getCenter().add(toPlayer.scale(width)),
					toPlayer,
					Constants.LASER_VEL,
					newAngle + Math.PI/2,
					Assets.redLaser,
					gameState
					);
			
			gameState.getMovingObjects().add(0, laser);
			
			fireRate.run(Constants.UFO_FIRE_RATE);
			
		}
		
		angle += 0.05;
		
		collidesWith();
		fireRate.update();
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		at.rotate(angle, width/2, height/2);
		
		g2d.drawImage(texture, at, null);

	}
	
}
