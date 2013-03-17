package hanabi;

import java.util.Random;

import processing.core.PApplet;

public class Sprite {
	
	private float centerX;
	private float centerY;
	private PApplet parent;
	
	public Sprite(float x, float y, PApplet parent) {
		this.centerX = x;
		this.centerY = y;
		this.parent = parent;
	}
	
	public void drawRandom() {
		Random rand = new Random();
		float randX = rand.nextInt(40) - 10;
		float randY = rand.nextInt(40) - 10;
		parent.noStroke();
		parent.fill(255, 70);
		parent.ellipse(centerX+randX, centerY+randY, 10, 10);
		parent.fill(255, 80);
		parent.ellipse(centerX+randX, centerY+randY, 3, 3);
	}

}
