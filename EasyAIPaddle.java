import java.awt.Color;
import java.awt.Graphics;

public class EasyAIPaddle extends HumanPaddle {
	private int length=35;
	private int width=10;
	private int xPos=400, yPos=2,xVel,yVel=0;
	private int score=0;
	private boolean moveleft=false,moveright=false,right,left;
	EasyAIPaddle(int n,String na) {
		super(n, na);
		xPos = 400;
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
	private boolean moveRight = true;
	public void move(Ball1 b)
	{
		if(moveRight){
			if(xPos>=800){
				moveRight = false;
				xPos-=9;
			}else{
				xPos+=9;
			}
		}else{
			if(xPos<=200){
				moveRight = true;
				xPos+=9;
			}else{
				xPos-=9;
			}
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
