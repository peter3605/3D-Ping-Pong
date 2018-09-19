public class Table3D {
	private Point3D[] verticies = new Point3D[8];
	Table3D(Point3D[] points){
		verticies = points;
	}
	public Point3D[] getVerticies(){
		return verticies;
	}
}