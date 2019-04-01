package graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {
	
	public static BufferedImage player;
	
	// effects
	
	public static BufferedImage speed;
	
	// explosion
	
	public static BufferedImage[] exp = new BufferedImage[9];
	
	// lasers
	
	public static BufferedImage blueLaser, greenLaser, redLaser;
	
	// Meteors
	
	public static BufferedImage[] bigs = new BufferedImage[4];
	public static BufferedImage[] meds = new BufferedImage[2];
	public static BufferedImage[] smalls = new BufferedImage[2];
	public static BufferedImage[] tinies = new BufferedImage[2];
	
	// ufo
	
	public static BufferedImage ufo;
	
	// numbers
	
	public static BufferedImage[] numbers = new BufferedImage[11];
	
	public static BufferedImage life;
	
	// fonts
	
	public static Font fontBig;
	public static Font fontMed;
	
	public static Clip backgroundMusic, explosion, playerLoose, playerShoot, ufoShoot;
	
	public static void init()
	{
		player = Loader.ImageLoader("/ships/player.png");
		
		speed = Loader.ImageLoader("/effects/fire08.png");
		
		blueLaser = Loader.ImageLoader("/lasers/laserBlue01.png");
		
		greenLaser = Loader.ImageLoader("/lasers/laserGreen11.png");
		
		redLaser = Loader.ImageLoader("/lasers/laserRed01.png");
		
		ufo = Loader.ImageLoader("/ships/ufo.png");
		
		life = Loader.ImageLoader("/others/life.png");
		
		fontBig = Loader.loadFont("/fonts/futureFont.ttf", 42);
		
		fontMed = Loader.loadFont("/fonts/futureFont.ttf", 20);
		
		for(int i = 0; i < bigs.length; i++)
			bigs[i] = Loader.ImageLoader("/meteors/big"+(i+1)+".png");
		
		for(int i = 0; i < meds.length; i++)
			meds[i] = Loader.ImageLoader("/meteors/med"+(i+1)+".png");
		
		for(int i = 0; i < smalls.length; i++)
			smalls[i] = Loader.ImageLoader("/meteors/small"+(i+1)+".png");
		
		for(int i = 0; i < tinies.length; i++)
			tinies[i] = Loader.ImageLoader("/meteors/tiny"+(i+1)+".png");
		
		for(int i = 0; i < exp.length; i++)
			exp[i] = Loader.ImageLoader("/explosion/"+i+".png");
		
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = Loader.ImageLoader("/numbers/"+i+".png");
		
		backgroundMusic = Loader.loadSound("/sounds/backgroundMusic.wav");
		explosion = Loader.loadSound("/sounds/explosion.wav");
		playerLoose = Loader.loadSound("/sounds/playerLoose.wav");
		playerShoot = Loader.loadSound("/sounds/playerShoot.wav");
		ufoShoot = Loader.loadSound("/sounds/ufoShoot.wav");
		
		
	}

}
