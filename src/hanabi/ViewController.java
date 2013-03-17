package hanabi;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;

public class ViewController {
		
	private PApplet parent;
	private ArrayList<ExplodeHanabi> explodeHanabis = new ArrayList<ExplodeHanabi>();
	private Iterator<ExplodeHanabi> iter = explodeHanabis.iterator();
	
	public ViewController(PApplet parent) {
		this.parent = parent;
	}
	
	public void explode(HANABi hanabi) {
		explodeHanabis.add(new ExplodeHanabi(hanabi, parent));
	}
	
	public void drawHanabi(ArrayList<HANABi> hanabi) {
		_drawShotHanabis(hanabi);
		_drawExplodeHanabis();
	}

	private void _drawShotHanabis(ArrayList<HANABi> hanabi) {
		for (HANABi h : hanabi) {
			parent.noStroke();
			if(h.catched == true) {
				drawCatchHanabi(h);
			}
			else {
				parent.fill(h.r, h.g, h.b);
				parent.ellipse(h.x, h.y, h.radius * 2, h.radius * 2);
			}
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
		parent.fill(hanabi.r + 20, hanabi.g + 20, hanabi.b + 20);
		parent.ellipse(hanabi.x, hanabi.y, hanabi.radius * 2, hanabi.radius * 2);
		parent.noFill();
		parent.stroke(255, 0, 0);
		parent.rect(hanabi.x - hanabi.radius, hanabi.y - hanabi.radius, hanabi.radius * 2, hanabi.radius * 2);
	}
}
