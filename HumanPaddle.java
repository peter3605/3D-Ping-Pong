import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class HumanPaddle{
	private int length=42;
	private int width=50;
	private int xPos=400, yPos=2,xVel,yVel=0;
	private int score=0;
	private boolean moveleft=false,moveright=false,right,left;
	String name;
	HumanPaddle(int n,String na)
	{
		name=na;
		if (n==1)
		{
			xPos =400;
			yPos=50;
			right=true;
		}
		else
		{
			xPos=600;
			yPos=370;
			left=true;
		}
	}

	
	public void incrementscore()
	{
		score++;
	}
	public int getScore()
	{
		return score;
	}
	public String getName(){
		return name;
	}
	public boolean calcIntersect(Ball1 b1)
	{
		if (right)
		{
			if (b1.getY()==yPos+10)
			{
				if (b1.getX()<xPos-30&&b1.getX()>=xPos+30)					
					return true;
			}
		}
		if (left)
		{ 
			if (b1.getY()==yPos-10)
			{
				if (b1.getX()<xPos-30&&b1.getX()>=xPos+30)
					return true;
			}
		}
		return false;
	}
	public int getWidth()
	{
		return width;
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(xPos+16,yPos+40,10,40);
		g.setColor(Color.GRAY);
		g.fillOval(xPos,yPos+10,42,50);
	}
	public int getPosx()
	{
		return xPos;
	}
	public int getPosy()
	{
		return yPos;
	}
	public int getLength()
	{
		return length;
	}
	public void moveLeft(boolean b)
	{
		moveleft =b;
	}
	public void moveRight(boolean b)
	{
		moveright=b;
	}
	public void move(Ball1 b)
	{
		if(xPos<=0){
			xPos = 0;
		}
		if(xPos>=1000-length){
			xPos =1000-length;
		}
		if (moveright)
		{
			xVel+=4;
		}
		else if(moveleft)
		{
			xVel-=4;
		}
		else if(!moveright&&!moveleft)
		{xVel*=0.9;
		}
		if (xVel>=7)
		{
			xVel=7;
		}
		else if(xVel<=-7)
		{
			xVel=-7;
		}
		xPos+=xVel;
	}
	public void setxPos(int x)
	{
		xPos=x;
	}
	public void setyPos(int y)
	{
		yPos=y;
	}
}

