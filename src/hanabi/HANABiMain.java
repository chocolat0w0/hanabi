package hanabi;

import processing.core.PApplet;


public class HANABiMain extends PApplet {
	
	ViewController viewController = null;
	HANABi[] hanabi = new HANABi[1];

	public void setup() {
		viewController = new ViewController(this);
		size(500, 500);
		frameRate(30);
		background(80);
		hanabi[0] = new HANABi(100, height);
	}

	public void draw() {
		background(80);
		viewController.drawHanabi(hanabi);
		hanabi[0].move();
	}
	
	public void mousePressed() {
		if((hanabi[0].x - 10) < mouseX && mouseX < (hanabi[0].x + 10) 
				&& (hanabi[0].y - 10) < mouseY && mouseY < (hanabi[0].y + 10)) {
			hanabi[0].catched = true;
			viewController.catchHanabi(hanabi[0]);
		}
	}
	public static void main(String _args[]) {
		PApplet.main(new String[] { hanabi.HANABiMain.class.getName() });
	}
}
