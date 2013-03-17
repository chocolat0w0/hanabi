package hanabi;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;

public class ViewController {
	
	private static final int EXPLODABLE_STEP = 30;

	PApplet parent;
	private ArrayList<ExplodeHanabi> explodeHanabis = new ArrayList<ExplodeHanabi>();
	Iterator<ExplodeHanabi> iter = explodeHanabis.iterator();
	
	public ViewController(PApplet parent) {
		this.parent = parent;
	}
	
	public void explode(HANABi hanabi) {
		explodeHanabis.add(new ExplodeHanabi(hanabi.x, hanabi.y));
	}
	
	public void drawHanabi(ArrayList<HANABi> hanabi) {
		_drawShotHanabis(hanabi);
		_drawExplodeHanabis();
	}

	private void _drawShotHanabis(ArrayList<HANABi> hanabi) {
		for (HANABi h : hanabi) {
			parent.noStroke();
			if(h.catched == true) {
				parent.fill(100);
			}
			else {
//				parent.fill(200);
				parent.fill(h.r, h.g, h.b);
			}
			parent.ellipse(h.x, h.y, 30, 30);
		}
	}

	private void _drawExplodeHanabis() {
		iter = explodeHanabis.iterator();
		ExplodeHanabi explodeHanabi;
		while (iter.hasNext()) {
			explodeHanabi = iter.next();
			boolean explodable = explodeHanabi.update();
			if (explodable) {
				explodeHanabi.drawSparks();
			}
			else {
				iter.remove();
			}
		}
	}

	public void drawCatchHanabi(HANABi hanabi) {
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
		
		public boolean update() {
			distance += 10;
			step++;
			if (EXPLODABLE_STEP < step) {
				return false;
			}
			for (int i = 0; i < step * 4; i++) {
				dx = (float) (distance * Math.sin(360 / (step * 4 * 2) * i));
				dy = (float) (distance * Math.cos(360 / (step * 4 * 2) * i));
				sparks.add(new Spark(centerX + dx, centerY + dy, step));
			}
			for (int i = sparks.size() - 1; i >= 0; i--) {
				if (sparks.get(i).step == step - 10) {
					sparks.remove(i);
				}
			}
			return true;
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
