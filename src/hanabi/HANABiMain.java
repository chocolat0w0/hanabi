package hanabi;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class HANABiMain extends PApplet {
	private static final int BACKGROUND_GRAY = 80;
	private static final int INTERVAL = 20;
	private static final int WINDOW_H = 500;
	private static final int WINDOW_W = 625;
	private static final int FRAME_RATE = 30;

	private int time = 0;
	private int score = 0;
	private int totalScore = 0;
	private ViewController viewController = null;
	private ArrayList<HANABi> hanabis = new ArrayList<HANABi>();
	private PImage yakei = loadImage("yakei.jpeg");

	public void setup() {
		viewController = new ViewController(this);
		size(WINDOW_W, WINDOW_H);
		frameRate(FRAME_RATE);
		background(BACKGROUND_GRAY);
	}

	public void draw() {
		tint(255, 50);
		image(yakei, 0, 0, WINDOW_W, WINDOW_H);
		fill(BACKGROUND_GRAY, 30);
		rect(0, 0, width, height);

		_createHanabi();

		_killDeadHanabi();

		viewController.drawHanabi(hanabis);

		_moveAllHanabi();
		
		_showScore();
		
	}
	
	private void _showScore() {
		fill(255);
		textSize(16);
		text("total : " + totalScore, 10, 20);
		text("explode : " + score, 10, 40);
	}

	public void mousePressed() {
		for (HANABi h : hanabis) {
			if ((h.x - h.radius) < mouseX && mouseX < (h.x + h.radius)
					&& (h.y - h.radius) < mouseY && mouseY < (h.y + h.radius)) {
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
		if (time % INTERVAL != 0) {
			return;
		}

		Random rand = new Random();

		int x = rand.nextInt(500);
		int radius = rand.nextInt(4) + 6;
		int speed = rand.nextInt(3) + 1;
		int sparkSize = rand.nextInt(8) + 5;

		int r = rand.nextInt(2);
		int g = rand.nextInt(2);
		int b = rand.nextInt(2);
		
		while (r == 0 && g == 0 && b == 0) {
			r = rand.nextInt(2);
			g = rand.nextInt(2);
			b = rand.nextInt(2);
		}
		
		r = r * 255 + 100;
		g = g * 255 + 100;
		b = b * 255 + 100;
				
		hanabis.add(new HANABi(x, height, radius * 2, r, g, b, sparkSize, speed));
	}

	private void _killDeadHanabi() {

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

		score = 0;
		for (int i = hanabis.size() - 1; i >= 0; i--) {
			if (hanabis.get(i).catched) {
				_calcPoint(hanabis.get(i));

				viewController.explode(hanabis.get(i));
				hanabis.remove(i);
			}
		}
		totalScore = totalScore + score;
	}
	
	private void _calcPoint(HANABi hanabi) {
		score = score + hanabi.y * hanabi.radius * hanabi.speed / 100;
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
