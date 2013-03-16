package hanabi;

import java.util.ArrayList;

import processing.core.PApplet;

public class ViewController {

	PApplet parent;
	private ArrayList<ExplodeHanabi> explodeHanabis = new ArrayList<ExplodeHanabi>();
	
	public ViewController(PApplet parent) {
		this.parent = parent;
	}
	
	public void explode(HANABi hanabi) {
		explodeHanabis.add(new ExplodeHanabi(hanabi.x, hanabi.y));
	}
	
	public void drawHanabi(ArrayList<HANABi> hanabi) {
		for (HANABi h : hanabi) {
			parent.noStroke();
			if(h.catched == true) {
				parent.fill(100);
			}
			else {
				parent.fill(200);
			}
			parent.ellipse(h.x, h.y, 30, 30);
		}
		for(ExplodeHanabi explodeHanabi : explodeHanabis) {
			explodeHanabi.update();
			explodeHanabi.drawSparks();
			
		}
	}

	public void catchHanabi(HANABi hanabi) {
		parent.fill(0);
		parent.ellipse(hanabi.x, hanabi.y, 30, 30);
	}
	
	class ExplodeHanabi {
		float centerX;
		float centerY;
		float dx;
		float dy;
		float distance;
		int step;
		ArrayList<Spark> sparks = new ArrayList<Spark>();
		
		ExplodeHanabi(float x, float y) {
			this.centerX = x;
			this.centerY = y;
			this.step = 0;
			this.distance = 0;
		}
		
		public void update() {
			distance += 10;
			step++;
			for (int i = 0; i < step * 4; i++) {
				dx = (float) (distance * Math.cos(90 - 360 / (step * 4 * 2) * i));
				dy = (float) (distance * Math.sin(90 - 360 / (step * 4 * 2) * i));
				sparks.add(new Spark(centerX + dx, centerY + dy, step));
			}
			
		}
		
		public void drawSparks() {
			for(Spark spark : sparks) {
				parent.ellipse(spark.x, spark.y, 10, 10);
			}
		}
	}
	
	class Spark {
		float x;
		float y;
		int step;
		
		Spark(float x, float y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
}
