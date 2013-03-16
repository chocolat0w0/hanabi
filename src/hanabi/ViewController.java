package hanabi;

import processing.core.PApplet;

public class ViewController {

	PApplet parent;
	
	public ViewController(PApplet parent) {
		this.parent = parent;
	}
	
	public void explode(HANABi hanabi) {
	}
	
	public void drawHanabi(HANABi[] hanabi) {
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
	}

	public void catchHanabi(HANABi hanabi) {
		parent.fill(0);
		parent.ellipse(hanabi.x, hanabi.y, 30, 30);
	}
}
