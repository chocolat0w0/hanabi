package hanabi;

import java.util.ArrayList;

import processing.core.PApplet;

public class ExplodeHanabi {
	private static final int EXPLODABLE_STEP = 30;

	private PApplet parent;
	private float centerX;
	private float centerY;
	private int colorR;
	private int colorG;
	private int colorB;
	private int sparkSize;
	private float dx;
	private float dy;
	private float distance;
	private int step;
	private ArrayList<Spark> sparks = new ArrayList<Spark>();
	
	public ExplodeHanabi(HANABi hanabi, PApplet parent) {
		this.parent = parent;
		this.centerX = hanabi.x;
		this.centerY = hanabi.y;
		this.colorR = hanabi.r;
		this.colorG = hanabi.g;
		this.colorB = hanabi.b;
		this.sparkSize = hanabi.sparkSize;
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
			sparks.add(new Spark(centerX + dx, centerY + dy, step, sparkSize));
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
			parent.fill(colorR, colorG, colorB);
			parent.ellipse(spark.x, spark.y, spark.sparkSize, spark.sparkSize);
		}
	}
	
}
