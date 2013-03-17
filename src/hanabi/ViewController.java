package hanabi;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;

public class ViewController {
	
	private static final int EXPLODABLE_STEP = 30;

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
				parent.ellipse(h.x, h.y, 30, 30);
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
		parent.fill(255, 0, 0);
		parent.ellipse(hanabi.x, hanabi.y, 30, 30);
		parent.rect(hanabi.x, hanabi.y, 30, 30);
	}
}
