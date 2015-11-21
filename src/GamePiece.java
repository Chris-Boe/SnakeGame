
public abstract class GamePiece extends Object{
	private int size;
	private int xPos;
	private int yPos;
	private java.awt.Color color;
	
	/*player 2
	private int size2;
	private int xPos2;
	private int yPos2;
	private java.awt.Color color2;
	*/

	public GamePiece(int xPos, int yPos, int size, java.awt.Color color){
		this.xPos = xPos;
		this.yPos = yPos;
		this.size = size;
		this.color = color;
		
		/*player 2
		this.xPos2 = xPos2;
		this.yPos2 = yPos2;
		this.size2 = size2;
		this.color2 = color2;*/
	}
	
	public void draw(java.awt.Graphics g){
		g.setColor(color);
		g.fillOval(xPos, yPos, size, size);
	}
	public int getSize(){
		return size;
	}
	public int getXPos(){
		return xPos;
	}
	public int getYPos(){
		return yPos;
	}
	public void setXPos(int x){
		this.xPos = x;
	}
	public void setYPos(int y){
		this.yPos = y;
	}
	/*player 2
	public void draw2(java.awt.Graphics g){
		g.setColor(color2);
		g.fillOval(xPos2, yPos2, size2, size2);
	}
	public int getSize2(){
		return size2;
	}
	public int getXPos2(){
		return xPos2;
	}
	public int getYPos2(){
		return yPos2;
	}
	public void setXPos2(int x2){
		this.xPos = x2;
	}
	public void setYPos2(int y2){
		this.yPos = y2;
	}*/
	

}
