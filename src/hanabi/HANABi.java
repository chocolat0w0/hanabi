package hanabi;

public class HANABi {

	public int x;
	public int y;
	public int count;
	public boolean catched;


	public HANABi(int x, int y) {

		this.x = x;
		this.y = y;
		this.count = 5;
		this.catched = false;
	}

	public void move() {

		this.y = this.y -1;
		
		return;
	}

	public boolean isAlive() {

		if (this.count <= 0) {
			return false;
		}

		return true;
	}
}
