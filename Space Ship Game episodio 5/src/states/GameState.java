package states;

import java.awt.Graphics;

import gameObjects.Player;
import graphics.Assets;
import math.Vector2D;

public class GameState {
	
	private Player player;
	
	public GameState()
	{
		player = new Player(new Vector2D(100, 500), Assets.player);
	}
	
	public void update()
	{
		
	}
	
	public void draw(Graphics g)
	{
		player.draw(g);
	}
}
