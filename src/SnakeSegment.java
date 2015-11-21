import java.awt.Color;


public class SnakeSegment extends GamePiece{

	public SnakeSegment(int xPos, int yPos, int size, Color color) {
		super(xPos, yPos, size, color);

	}
	public void move(int xPos, int yPos){
		this.setXPos(xPos);
		this.setYPos(yPos);
	}

}
