package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;
import ui.Action;

public class PowerUp extends MovingObject {

	private long duration;
	private Action action;
	private Sound powerUpPick;
	private BufferedImage typeTexture;
	
	public PowerUp(Vector2D position, BufferedImage texture, Action action, GameState gameState) {
		super(position, new Vector2D(), 0, Assets.orb, gameState);

		this.action = action;
		this.typeTexture = texture;
		duration = 0;
		powerUpPick = new Sound(Assets.powerUp);

	}
	
	void executeAction() {
		action.doAction();
		powerUpPick.play();
	}

	@Override
	public void update(float dt) {
		angle += 0.1;
		duration += dt;
		
		if(duration > Constants.POWER_UP_DURATION) {
			this.Destroy();
		}
		
		collidesWith();

	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		at = AffineTransform.getTranslateInstance(
				position.getX() + Assets.orb.getWidth()/2 - typeTexture.getWidth()/2,
				position.getY() + Assets.orb.getHeight()/2 - typeTexture.getHeight()/2);

		at.rotate(angle,
				typeTexture.getWidth()/2,
				typeTexture.getHeight()/2);
		
		g.drawImage(Assets.orb, (int)position.getX(), (int)position.getY(), null);
		
		
		
		g2d.drawImage(typeTexture, at, null);
	}

}
