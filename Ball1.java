import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Ball1 
{
	private double xVel=3,yVel=20,xPos=400,yPos=150;
	private int radius=40;
	private HumanPaddle p1,p2;
	private boolean topintersect,bottomintersect;
	private Rectangle ball;
		Ball1(){
			ball = new Rectangle((int)xPos,(int)yPos,10,10);
		}
		public int generatexVel()
		{
			return (int)(Math.random()*2)+1;
		}
		public int generateyVel()
		{
			return (int)(Math.random()*1)+1;
		}
		public double getX()
		{
			return xPos;
		}
		public double getY()
		{
			return yPos;
		}
		public int getRadius()
		{
			return radius;
		}
		public double getxVel()
		{
			return xVel;
		}
		public double getyVel()
		{
			return yVel;
		}
		public void paint(Graphics g)
		{
			g.setColor(Color.YELLOW);
			g.fillOval((int)xPos,(int)yPos,10,10);
		}
		public boolean calcIntersect(HumanPaddle p1,HumanPaddle p2)
		{
			Rectangle rec1 = new Rectangle(p1.getPosx()-10, p1.getPosy(), p1.getLength()+20, p1.getWidth());
			Rectangle rec2 = new Rectangle(p2.getPosx(), p2.getPosy()+50, p2.getLength(), p2.getWidth());
			if(rec1.intersects(ball)){
				topintersect=true;
				bottomintersect=false;
				return true;
			}
			if(rec2.intersects(ball)){
				topintersect=false;
				bottomintersect=true;
				return true;
			}
			return false;
			
		}
		public void setxVel(double n)
		{
			xVel=n;
		}
		public void incrementVel()
		{
			xVel++;
		}
		public boolean getbotintersect()
		{
			return bottomintersect;
		}
		public boolean gettopintersect()
		{
			return topintersect;
		}
		public void setyVel(double n)
		{
			yVel=n;
		}
		public void setxPos(double x)
		{
			xPos=x;
		}
		public void setyPos(double y)
		{
			yPos=y;
		}
		public void move()
		{
			xPos+=xVel;
			yPos+=yVel;
			ball = new Rectangle((int)xPos,(int)yPos,20,20);
		}

}