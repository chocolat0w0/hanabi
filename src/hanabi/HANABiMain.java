package hanabi;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class HANABiMain extends PApplet {

	ViewController viewController = null;
	ArrayList<HANABi> hanabi = new ArrayList<HANABi>();
	private int time = 0;
	private static final int interval = 40;

	public void setup() {
		viewController = new ViewController(this);
		size(500, 500);
		frameRate(30);
		background(80);
	}

	public void draw() {
		
		background(80);
		
		_createHanabi();

		_killHanabi();

		viewController.drawHanabi(hanabi);
		
		_moveAllHanabi();
	}

	public void mousePressed() {
		for (HANABi h : hanabi) {
			if ((h.x - 10) < mouseX && mouseX < (h.x + 10)
					&& (h.y - 10) < mouseY && mouseY < (h.y + 10)) {
				h.catched = true;
				viewController.catchHanabi(h);
			}
		}
	}

	public void keyPressed() {

		if (key == ' ') {
			for (int i = hanabi.size() - 1; i >= 0; i--) {
				if (hanabi.get(i).catched) {
					viewController.explode(hanabi.get(i));
					hanabi.remove(i);
				}
			}
		}
	}

	private void _createHanabi() {
		time++;
		if (time % interval == 0) {

			Random rand = new Random();

			int num = rand.nextInt(500);
			hanabi.add(new HANABi(num, height));
		}
	}
	
	private void _killHanabi() {
		
		for (int i = hanabi.size() - 1; i >= 0; i--) {
			if (!hanabi.get(i).isAlive()) {
				hanabi.remove(i);
			}
		}
	}
	
	private void _moveAllHanabi() {
		for (HANABi h : hanabi) {
			h.move();
		}
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { hanabi.HANABiMain.class.getName() });
	}
}
