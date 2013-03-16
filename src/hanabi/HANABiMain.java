package hanabi;

import java.util.ArrayList;

import processing.core.PApplet;


public class HANABiMain extends PApplet {
	
	ViewController viewController = null;
	ArrayList<HANABi> hanabi = new ArrayList<HANABi>();

	public void setup() {
		viewController = new ViewController(this);
		size(500, 500);
		frameRate(30);
		background(80);
		
		hanabi.add(new HANABi(100, height));
		hanabi.add(new HANABi(200, height));
		hanabi.add(new HANABi(300, height));
	}

	public void draw() {
		background(80);
		viewController.drawHanabi(hanabi);
		for (HANABi h : hanabi) {
			h.move();
		}
	}
	
	public void mousePressed() {
		for (HANABi h : hanabi) {
			if((h.x - 10) < mouseX && mouseX < (h.x + 10) 
					&& (h.y - 10) < mouseY && mouseY < (h.y + 10)) {
				h.catched = true;
				viewController.catchHanabi(h);
			}
		}
	}
	
	public void keyPressed() {
		ArrayList<Integer> num = new ArrayList<Integer>();
		
		if(key == 'a') {
			background(255, 0, 0);
			for (HANABi h : hanabi) {
				if(h.catched) {
					num.add(hanabi.indexOf(h));
//					hanabi.remove(hanabi.indexOf(h));
				}
			}
			for(Integer i : num) {
				hanabi.remove(hanabi.get(i));
				for(Integer j : num) {
					j = 0;
				}
			}
		}
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { hanabi.HANABiMain.class.getName() });
	}
}
