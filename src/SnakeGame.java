import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
//Power ups: Speed Boost, Speed Reset (after 4 boosts), Super food(+score), Poison(-score)
//Win screen if 30 food before time is up
//Timer counts down from 60 seconds?
//Chomp sound effect/music?
//background?
//Obstacles? (static location but increasing in number)
public class SnakeGame extends Applet implements KeyListener, Runnable {
	Dimension dim;
	Image img;
	Graphics g;
	int	dirX;
	int	dirY;
	long endTime;
	Food Food;
	SpeedReset SpeedReset;
	SpeedBoost Boost;
	Superfood Superfood;
	PoisonFood Poison;
	long framePeriod;
	SnakeSegment head;
	boolean	paused;
	int	pieces;
	int	score;
	SnakeSegment[]	segments;
	int	size;
	int	snakeSize;
	long startTime;
	java.lang.Thread thread;
	int[] xLocs;
	int[] yLocs;
	int lives = 3;
	int foodleft = 30;
	
	/*player 2
		int dirX2;
		int dirY2;
		SnakeSegment head2;
		int	pieces2;
		int	score2;
		SnakeSegment[]	segments2;
		int	size2;
		int	snakeSize2;
		int[] xLocs2;
		int[] yLocs2;
		int lives2 = 3;
		int foodleft2 = 30;
	 */
	
	public void paint(Graphics gfx){
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 1000);
		head.draw(g);
		Food.draw(g);
		if(framePeriod <= 130){
			SpeedReset.draw(g);
		}
		Boost.draw(g);
		try{
			for(int k = 1; k <= pieces; k++){
				segments[k].draw(g);
			}
		}catch (NullPointerException e){
			
		}
		gfx.drawImage(img,  0, 0, this);
		gfx.setColor(Color.cyan);
		gfx.drawString("Food left: "+foodleft, 25, 45);
		gfx.drawString("Score: "+ score, 25, 25);
		gfx.drawString("Lives: "+lives, 900, 25);
		if(paused){
			gfx.drawString("Press Enter to start!", 460, 25);
		}
		for(int i = 1; i < 50; i++){
			gfx.setColor(Color.DARK_GRAY);
			gfx.drawLine(0, i*size, dim.width, i*size);
			gfx.drawLine(i*size, 0, i*size, dim.height);
		}
		if(lives == 0){
			gfx.setColor(Color.red);
			gfx.drawString("Game Over.", 500, 750);
		}
		if(foodleft == 0){
			paused=true;
			gfx.setColor(Color.green);
			gfx.drawString("You've eaten all the food! You've won!", 430, 750);
		}
		
		/*player 2
		head2.draw(g);
		
		try{
			for(int x = 1; x <= pieces; x++){
				segments2[x].draw(g);
			}
		}catch (NullPointerException e){
			
		}
		
		gfx.setColor(Color.cyan);
		gfx.drawString("Food left: "+foodleft, 25, 45);
		gfx.drawString("Score: "+ score, 25, 25);
		gfx.drawString("Lives: "+lives, 900, 25);
		 
		 	if(lives2 == 0){
			gfx.setColor(Color.red);
			gfx.drawString("Game Over.", 500, 750);
		}
		 	
		 	if(foodleft2 == 0){
			paused=true;
			gfx.setColor(Color.green);
			gfx.drawString("You've eaten all the food! You've won!", 430, 750);
		}
		 */

}
	public void init(){
		this.resize(1000, 1000);
		size = 20;
		pieces = 0;
		score = 0;
		snakeSize = 1000;
		paused = true;
		endTime = 0;
		startTime = 0;
		dim = getSize();
		img = createImage(dim.width, dim.height);
		g = img.getGraphics();
		framePeriod = 150;
		thread = new Thread(this);
		thread.start();
		addKeyListener(this);
		head = new SnakeSegment(200, 200, size, Color.red);
		Food = new Food(0, 0, size, Color.green);
		Food.move();
		Boost = new SpeedBoost(0, 0, size, Color.white);
		Boost.move();
		Superfood = new Superfood(0, 0, size, Color.magenta);
		Superfood.move();
		Poison = new PoisonFood(0, 0, size, Color.magenta);
		Poison.move();
		SpeedReset = new SpeedReset(0, 0, size, Color.orange);
		SpeedReset.move();
		segments = new SnakeSegment[snakeSize];
		xLocs = new int[1000];
		yLocs = new int[1000];
		dirX = 0;
		dirY = 0;
		lives = 3;
		foodleft = 30;
		
		/*player 2
		dirX2 = 0;
		dirY2 = 0;
		head2= new SnakeSegment(800, 800, size, Color.blue);
		pieces2;
		score2;
		SnakeSegment[]	segments2;
		size2;
		snakeSize2;
		xLocs2 = new int[1000];
		yLocs2 = new int[1000];
		lives2 = 3;
		foodleft2 = 30;
		 */
	}


	public void run() {
		for(;;){
			if(lives == 0){
				try{
				Thread.sleep(3000);
			}catch (InterruptedException e){
				
			}
				System.exit(0);
			}
			if(foodleft == 0){
				try{
				Thread.sleep(3000);
			}catch (InterruptedException e){
				
			}
				System.exit(0);
			}
			startTime = System.currentTimeMillis();
			if(!paused){
				if((head.getXPos() < 1000 
						&& (head.getXPos() >= 0)
						&& (head.getYPos() < 1000) 
						&& (head.getYPos() >= 0))){
					if((head.getYPos() == SpeedReset.getYPos()) 
							&& (head.getXPos() == SpeedReset.getXPos())){
						framePeriod = 150;
						SpeedReset.move();
					}
					if((head.getYPos() == Superfood.getYPos()) 
							&& (head.getXPos() == Superfood.getXPos())){
						score += 3;
						Superfood.move();
					}
					if((head.getYPos() == Poison.getYPos()) 
							&& (head.getXPos() == Poison.getXPos())){
						score -= 5;
						Poison.move();
					}
					if((head.getYPos() == Boost.getYPos()) 
							&& (head.getXPos() == Boost.getXPos())){
						framePeriod -= 5;
						Boost.move();
					}
					if((head.getYPos() == Food.getYPos()) 
							&& (head.getXPos() == Food.getXPos())){
						Food.move();
						pieces++;
						foodleft--;
						try{
							segments[pieces] = new SnakeSegment(0, 0, size, Color.pink);
							score++;
						}catch(ArrayIndexOutOfBoundsException e){
							dead();
						}
					}
					xLocs[0] = head.getXPos();
					yLocs[0] = head.getYPos();
					head.move(head.getXPos() + size*dirX, head.getYPos() + size*dirY);
						for(int i = 1; i<=pieces; i++){
							xLocs[i] = segments[i].getXPos();
							yLocs[i] = segments[i].getYPos();
							segments[i].move(xLocs[i-1], yLocs[i-1]);
							if((xLocs[i] == head.getXPos()) 
									&& (yLocs[i] == head.getYPos())){
								dead();
							}
						}
				}else{
					dead();
				}try{
					endTime = System.currentTimeMillis();
					if(framePeriod - (endTime - startTime) > 0)
						Thread.sleep(framePeriod - (endTime - startTime));
				}
				catch(InterruptedException e){
					
				}
			}
			repaint();
		}
		
	}
	
	public void dead(){
		score -= 5;
		head.move(500, 500);
		paused = false;
		pieces = 0;
		dirX = 0;
		dirY = 0;
		lives--;
	}
	/*player 2
	 	public void dead2(){
		score2 -= 5;
		head2.move(500, 500);
		paused = false;
		pieces2 = 0;
		dirX2 = 0;
		dirY2 = 0;
		lives2--;
		}
		 */
	
	public void update(){
		paint(g);
	}
	public void keyPressed(java.awt.event.KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			paused = !paused; //enter is the pause button

		}else if (e.getKeyCode()==KeyEvent.VK_ENTER){
			paused = false;
		}else if (e.getKeyCode()==KeyEvent.VK_UP && !(Math.abs(dirY)>0 && pieces> 0)){
				dirY=-1;
				dirX=0;
		}else if (e.getKeyCode()==KeyEvent.VK_LEFT && !(Math.abs(dirX)>0 && pieces> 0)){
				dirY=0;
				dirX=-1;
		}else if (e.getKeyCode()==KeyEvent.VK_RIGHT && !(Math.abs(dirX)>0 && pieces> 0)){
				dirY=0;
				dirX=1;
		}else if (e.getKeyCode()==KeyEvent.VK_DOWN && !(Math.abs(dirY)>0 && pieces> 0)){
				dirY=1;
				dirX=0;
		}
		
		/*player 2
		 * 		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			paused = !paused; //enter is the pause button

		}else if (e.getKeyCode()==KeyEvent.VK_ENTER){
			paused = false;
		}else if (e.getKeyCode()==KeyEvent.VK_W && !(Math.abs(dirY)>0 && pieces> 0)){
				dirY2=-1;
				dirX2=0;
		}else if (e.getKeyCode()==KeyEvent.VK_A && !(Math.abs(dirX)>0 && pieces> 0)){
				dirY2=0;
				dirX2=-1;
		}else if (e.getKeyCode()==KeyEvent.VK_D && !(Math.abs(dirX)>0 && pieces> 0)){
				dirY2=0;
				dirX2=1;
		}else if (e.getKeyCode()==KeyEvent.VK_S && !(Math.abs(dirY)>0 && pieces> 0)){
				dirY2=1;
				dirX2=0;
		}
		 */
		}
	

	public void keyReleased(KeyEvent e) {

		
	}


	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}