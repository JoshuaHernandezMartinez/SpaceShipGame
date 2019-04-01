package gameObjects;

public class Chronometer {
	private long delta, lastTime;
	private long time;
	private boolean running;
	
	public Chronometer(){
		delta = 0;
		lastTime = System.currentTimeMillis();
		running = false;
	}
	
	public void run(long time){
		running = true;
		this.time = time;
	}
	
	public void update(){	
		if(running)
			delta += System.currentTimeMillis() - lastTime;
		if(delta >= time){
			running = false;
			delta = 0;
		}
		
		lastTime = System.currentTimeMillis();
	}
	
	public boolean isRunning(){
		return running;
	}
	
}
