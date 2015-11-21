public class Superfood extends GamePiece {
	public Superfood(int xPos, int yPos, int size, java.awt.Color color){
		super(xPos, yPos, size, color);
		
	}
		public void move(){
			setXPos((int)(Math.random()*24)*getSize());
			setYPos((int)(Math.random()*24)*getSize());
		}
}
