package hanabi;

public class HANABi {

	private int centerX;
	public int x;
	public int y;
	public int radius;
	public int r;
	public int g;
	public int b;
	public int sparkSize;
	public int speed;
	public boolean catched;
	private int step;


	public HANABi(int x, int y, int radius, int r, int g, int b, int sparkSize, int speed) {

		this.centerX = x;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.r = r;
		this.g = g;
		this.b = b;
		this.sparkSize = sparkSize;
		this.speed = speed;
		this.catched = false;
		this.step = 0;
	}

	public void move() {

		this.y = this.y - this.speed;
		this.x = centerX + (int)(2 * Math.sin(step / 2));
		
		step++;
		return;
	}

	public boolean isAlive() {

		if (this.y <= 50) {
			return false;
		}

		return true;
	}
}
