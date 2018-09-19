import java.awt.Point;


public class Table2D {
	
	/*
		front rectangle:
			1**************0
			****************
			****************
			****************
			3**************2
			
		back rectangle:
			7**************6
			****************
			****************
			****************
			5**************4
	 */
	private static final int focal =5;

	public Point2D[] projectPoints(Table3D table){
		Point3D[] verticies = table.getVerticies();
		Point2D[] points = new Point2D[8];
		for(int i=0;i<verticies.length;i++){
			double x = verticies[i].getX();
			double y = verticies[i].getY();
			double z = verticies[i].getZ();
			
			double newX = (x*focal)/(focal+z);
			double newY = (y*focal)/(focal+z);
			
			points[i] = new Point2D(newX, newY);
		}
		return points;
	}

}

