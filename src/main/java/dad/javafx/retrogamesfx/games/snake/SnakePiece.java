package dad.javafx.retrogamesfx.games.snake;

public class SnakePiece {

	int x;
	int y;

	public SnakePiece(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public SnakePiece() {
		this(-1, -1);
	}


	public boolean collide(Food food) {
		return food.getX() == x && food.getY() == y;
	}

	public boolean collide(SnakePiece piece) {
		return piece.x == x && piece.y == y;
	}

	@Override
	public String toString() {
		return "SnakePiece [x=" + x + ", y=" + y + "]";
	}

}
