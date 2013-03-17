package hanabi;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class HANABiMain extends PApplet {
	private static final int BACKGROUND_GRAY = 80;
	private static final int INTERVAL = 40;
	private static final int WINDOW_SIZE = 500;
	private static final int FRAME_RATE = 30;
	ViewController viewController = null;
	ArrayList<HANABi> hanabis = new ArrayList<HANABi>();
	private int time = 0;
	private PImage image = loadImage("yakei.jpeg");

	public void setup() {
		viewController = new ViewController(this);
		size(WINDOW_SIZE, WINDOW_SIZE);
		frameRate(FRAME_RATE);
		background(BACKGROUND_GRAY);
	}

	public void draw() {
		image(image, 0, 0, 500, 500);

		fill(BACKGROUND_GRAY, 30);
		rect(0, 0, width, height);

		_createHanabi();

		_killHanabi();

		viewController.drawHanabi(hanabis);

		_moveAllHanabi();
	}

	public void mousePressed() {
		for (HANABi h : hanabis) {
			if ((h.x - 10) < mouseX && mouseX < (h.x + 10)
					&& (h.y - 10) < mouseY && mouseY < (h.y + 10)) {
				h.catched = true;
				viewController.drawCatchHanabi(h);
			}
		}
	}

	public void keyPressed() {

		if (key == ' ') {
			_explodeHanabi();
		}
		
		if (key == 'a') {
			for (int i = hanabis.size() - 1; i >= 0; i--) {
				if (hanabis.get(i).catched) {
					viewController.explode(hanabis.get(i));
					hanabis.remove(i);
				}
			}
		}
	}

	private void _createHanabi() {
		time++;
		if (time % INTERVAL == 0) {

			Random rand = new Random();

			int x = rand.nextInt(500);
			int speed = rand.nextInt(3) + 1;
			int r = rand.nextInt(180) + 75;
			int g = rand.nextInt(180) + 75;
			int b = rand.nextInt(180) + 75;
			hanabis.add(new HANABi(x, height, r, g, b, speed));
		}
	}

	private void _killHanabi() {

		for (int i = hanabis.size() - 1; i >= 0; i--) {
			if (!hanabis.get(i).isAlive()) {
				hanabis.remove(i);
			}
		}
	}

	private void _moveAllHanabi() {
		for (HANABi h : hanabis) {
			h.move();
		}
	}

	private void _explodeHanabi() {

		if (!_isExplodable()) {
			return;
		}

		for (int i = hanabis.size() - 1; i >= 0; i--) {
			if (hanabis.get(i).catched) {
				viewController.explode(hanabis.get(i));
				hanabis.remove(i);
			}
		}
	}

	private boolean _isExplodable() {

		int count = 0;
		for (HANABi h : hanabis) {
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
