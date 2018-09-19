import java.awt.Point;
import java.util.ArrayList;

public class Ball extends Calc {
	public static void method(int angle, int speed){
		 Calc ball = new Calc();
		 ball.setAngle(angle);
		 ball.setVel(speed);
		 ball.solveForT();
		 ball.Add();
	}
	


}