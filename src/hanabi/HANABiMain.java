package hanabi;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class HANABiMain extends PApplet {
	private static final int BACKGROUND_GRAY = 80;
	private static final int INTERVAL = 40;
	private static final int WINDOW_SIZE = 500;
	private static final int FRAME_RATE = 30;
	ViewController viewController = null;
	ArrayList<HANABi> hanabi = new ArrayList<HANABi>();
	private int time = 0;

	public void setup() {
		viewController = new ViewController(this);
		size(WINDOW_SIZE, WINDOW_SIZE);
		frameRate(FRAME_RATE);
		background(BACKGROUND_GRAY);
	}

	public void draw() {
		
		fill(BACKGROUND_GRAY, 30);
		rect(0, 0, width, height);
		
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
			_explodeHanabi();
		}
	}

	private void _createHanabi() {
		time++;
		if (time % INTERVAL == 0) {

			Random rand = new Random();

			int x = rand.nextInt(500);
			int speed = rand.nextInt(3) + 1;
			hanabi.add(new HANABi(x, height, speed));
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
	
	private void _explodeHanabi() {
		
		if (!_isExplodable()) {
			return;
		}
		
		for (int i = hanabi.size() - 1; i >= 0; i--) {
			if (hanabi.get(i).catched) {
				viewController.explode(hanabi.get(i));
				hanabi.remove(i);
			}
		}
	}
	
	private boolean _isExplodable() {
		
		int count = 0;
		for (HANABi h : hanabi) {
			if (h.catched) {
				count++;
			}
		}
		
		if (count > 2) {
			return true;
		}
		return false;
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { hanabi.HANABiMain.class.getName() });
	}
}
