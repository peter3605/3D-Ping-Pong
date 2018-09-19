import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


public class HardAIPaddle extends HumanPaddle
{

	private int length=35;
	private int width=10;
	private int xPos=600, yPos=2,xVel,yVel=0;
	private int score=0;
	private boolean moveleft=false,moveright=false,right,left;
	HardAIPaddle(int n,String na) {
		super(n, na);
		xPos = 600;
		yPos = 50;
	}
	public void incrementscore()
	{
		score++;
	}
	public int getScore()
	{
		return score;
	}
	public boolean calcIntersect(Ball1 b1)
	{
		if (right)
		{
			if (b1.getY()<=yPos)
			{
				if (b1.getX()<xPos&&b1.getX()>=xPos+100)					
					return true;
			}
		}
		if (left)
		{
			if (b1.getY()>=yPos)
			{
				if (b1.getX()<xPos&&b1.getX()>=xPos+100)
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
	public void move(Ball1 b)
	{
		if(xPos<=0){
			xPos = 0;
		}
		if(xPos>=1000-length){
			xPos =1000-length;
		}
		if((xPos+(width*0.5)<b.getX())){
			xPos+=4;
		}else if((xPos+(width*0.5)>b.getX())){
			xPos-=4;
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
