package pang;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable{

	static final int Game_width=1000;
	static final int Game_height=600;
	static final Dimension Screen_size=new Dimension(1000,600);
	static final int Ball_Dia=20;
	static final int paddle_width=25;
	static final int paddle_height=100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	paddle pad1;
	paddle pad2;
	ball Ball;
	score sc;
	GamePanel(){
		newPaddle();
		newBall();
		sc=new score(Game_width,Game_height); 
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(Screen_size);
		gameThread=new Thread(this);
		gameThread.start();
		
	}
	public void newBall() {
		random =new Random();
		int X=Game_width/2-Ball_Dia/2;
		int Y=Game_height/2-Ball_Dia/2;
			
		Ball=new ball(X,Y,Ball_Dia/2,Ball_Dia);
		
	}
	public void newPaddle() {
		pad1=new paddle(0,((Game_height/2)-(paddle_height/2)),paddle_width,paddle_height,1);
		pad2=new paddle(Game_width-paddle_width,(Game_height)/2-(paddle_height/2),paddle_width,paddle_height,2);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics =image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
		
	}
	public void draw(Graphics g) {
		pad1.draw(g);
		pad2.draw(g);
		Ball.draw(g);
		sc.draw(g);
	}
	public void move() {
		pad1.move();
		pad2.move();
		Ball.move();
		
	}
	public void checkCollision() {
		if(Ball.y<=0)
			Ball.setYDirection(-Ball.YVelocity);
		if(Ball.y>=Game_height-Ball_Dia) {
			Ball.setYDirection(-Ball.YVelocity);
		}
		if(Ball.intersects(pad2)) {
			Ball.xVelocity=Math.abs(Ball.xVelocity);
		}
		if(Ball.intersects(pad1)) {
			Ball.xVelocity=Math.abs(Ball.xVelocity);
			Ball.xVelocity++;
			if(Ball.YVelocity>0)
				Ball.YVelocity++;
			else
				Ball.YVelocity--;
			Ball.setXDirection(Ball.xVelocity);
			Ball.setYDirection(Ball.YVelocity);
		}
		if(Ball.intersects(pad2)) {
			Ball.xVelocity=Math.abs(Ball.xVelocity);
			Ball.xVelocity++;
			if(Ball.YVelocity>0)
				Ball.YVelocity++;
			else
				Ball.YVelocity--;
			Ball.setXDirection(-Ball.xVelocity);
			Ball.setYDirection(-Ball.YVelocity);
		}
			
		if(pad1.y<=0)
		pad1.y=0;
		if(pad1.y>=(Game_height-paddle_height))
			pad1.y=Game_height-paddle_height;
		if(pad2.y<=0)
			pad2.y=0;
			if(pad2.y>=(Game_height-paddle_height))
				pad2.y=Game_height-paddle_height;
			if(Ball.x<=0) {
				sc.player2++;
				newPaddle();
				newBall();
				System.out.println(sc.player2);
			}
			if(Ball.x>=Game_width-Ball_Dia) {
				sc.player1++;
				newPaddle();
				newBall();
				System.out.println(sc.player1);
			}
	}
	public void run() {
		long lasttime=System.nanoTime();
		double amountOfsecs=60.0;
		double ns=1000000000/amountOfsecs;
		double delta=0;
		while(true) {
			long now=System.nanoTime();
			delta+=(now-lasttime)/ns;
			lasttime=now;
			if(delta>=1) {
				move();
				checkCollision();
				repaint();
				delta--;
				System.out.println();
			}
		}
	}
	
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			pad1.keyPressed(e);
			pad2.keyPressed(e);
			
		}
		public void keyReleased(KeyEvent e) {
			pad1.KeyReleased(e);
			pad2.KeyReleased(e);
		}
		
	}
}
