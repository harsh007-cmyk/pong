package pang;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class ball extends Rectangle{
	Random random;
	int xVelocity;
	int YVelocity;
	
	 ball(int x,int y,int width,int height) {
		 super(x,y,width,height);
		// TODO Auto-generated constructor stub
		 random=new Random();
		 int randomXdir=random.nextInt(2);
		 if(randomXdir==0)
			 randomXdir--;
		 setXDirection(randomXdir);
		 
		 int randomYdir=random.nextInt(2);
		 if(randomYdir==0)
			 randomYdir--;
		 setYDirection(randomYdir);
		 
	}
	public void setXDirection(int randomXDireciton) {
		xVelocity=randomXDireciton;
		
	}
	public void setYDirection(int randomYDirection) {
		YVelocity=randomYDirection;
	}
	public void move() {
		x+=xVelocity;
		y+=YVelocity;
	}
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x, y, height, width);
	}
}
