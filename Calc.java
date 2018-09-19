
import java.awt.Point;
import java.util.ArrayList;



public class Calc{
	private double xPos;
	private double alpha, v;
	private double yVel, xVel;
	private double endT;
	private double deltaSec = 0;
	private double yPos;
	public void move(){
		xVel();
		yVel();
	   xPos = xVel;
	   yPos = yVel;
	}
	
	
	public void yVel(){
	   yVel=((v*Math.sin(alpha))*(deltaSec))-(16)*(Math.pow(deltaSec,2));
	}
	
	public void xVel(){
	   xVel=(v*Math.cos(alpha))*deltaSec;
	}
	
	public Point newPoint(){
		return new Point ((int)xPos, (int)yPos); //Point only takes int coordinates
	}	
	
	public void setAngle(double alpha){
	    this.alpha=Math.toRadians(alpha);
	}
	
	
	public void setVel(double v){
	   this.v=v;
	}
	public void solveForT(){
		double b = v*Math.sin(alpha);
		double a = -16;
		double c = 1;
		endT = (-b+Math.sqrt(Math.pow(b, 2)-(4*a*c)))/(2*a);
		if(endT<0)
			endT = (-b-Math.sqrt(Math.pow(b, 2)-(4*a*c)))/(2*a);

	}
	public static ArrayList<Point> shoot = new ArrayList<Point>();
	
	public void Add(){
		while(deltaSec<=endT*20){
	       move();
	       shoot.add(newPoint());
	       deltaSec+=.01;
	       if(yPos<-500){
	    	   break;
	       }
		}
	
	}
}

