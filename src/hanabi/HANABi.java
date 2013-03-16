package hanabi;

public class HANABi {

	public int x;
	public int y;
	public int speed;
	public boolean catched;


	public HANABi(int x, int y, int speed) {

		this.x = x;
		this.y = y;
		this.speed = speed;
		this.catched = false;
	}

	public void move() {

		this.y = this.y - this.speed;
		
		return;
	}

	public boolean isAlive() {

		if (this.y <= 50) {
			return false;
		}

		return true;
	}
}
