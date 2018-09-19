public class Point3D {
	private double xCoord;
	private double yCoord;
	private double zCoord;
	
	Point3D(double x, double y, double z){
		xCoord = x;
		yCoord = y;
		zCoord = z;
	}
	public double getX(){
		return xCoord;
	}
	public double getY(){
		return yCoord;
	}
	public double getZ(){
		return zCoord;
	}
}