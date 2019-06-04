package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vector2D;
import states.GameState;

public class Meteor extends MovingObject{

	private Size size;	
	
	public Meteor(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState, Size size) {
		super(position, velocity, maxVel, texture, gameState);
		this.size = size;
		this.velocity = velocity.scale(maxVel);
		
	}

	@Override
	public void update(float dt) {
		
		Vector2D playerPos = new Vector2D(gameState.getPlayer().getCenter());
		
		int distanceToPlayer = (int) playerPos.subtract(getCenter()).getMagnitude();
		
		if(distanceToPlayer < Constants.SHIELD_DISTANCE / 2 + width / 2) {
			
			if(gameState.getPlayer().isShieldOn()) {
				Vector2D fleeForce = fleeForce();
				velocity = velocity.add(fleeForce);
			}
			

		}
		
		if(velocity.getMagnitude() >= this.maxVel) {
			Vector2D reversedVelocity = new Vector2D(-velocity.getX(), -velocity.getY());
			velocity = velocity.add(reversedVelocity.normalize().scale(0.01f));
		}
		
		velocity = velocity.limit(Constants.METEOR_MAX_VEL);
		
		position = position.add(velocity);
		
		if(position.getX() > Constants.WIDTH)
			position.setX(-width);
		if(position.getY() > Constants.HEIGHT)
			position.setY(-height);
		
		if(position.getX() < -width)
			position.setX(Constants.WIDTH);
		if(position.getY() < -height)
			position.setY(Constants.HEIGHT);
		
		angle += Constants.DELTAANGLE/2;
		
	}
	
	private Vector2D fleeForce() {
		Vector2D desiredVelocity = gameState.getPlayer().getCenter().subtract(getCenter());
		desiredVelocity = (desiredVelocity.normalize()).scale(Constants.METEOR_MAX_VEL);
		Vector2D v = new Vector2D(velocity);
		return v.subtract(desiredVelocity);
	}
	
	@Override
	public void Destroy(){
		gameState.divideMeteor(this);
		gameState.playExplosion(position);
		gameState.addScore(Constants.METEOR_SCORE, position);
		super.Destroy();
	}
	
	
	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		
		at.rotate(angle, width/2, height/2);
		
		g2d.drawImage(texture, at, null);
		
	}

	public Size getSize(){
		return size;
	}
}
