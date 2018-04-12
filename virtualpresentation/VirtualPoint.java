package virtualpresentation;

import java.util.List;

import originalvibs.ComplexVibration;
import visionaudio.ImageReader;

public class VirtualPoint {
	int x;
	int y;
	double[] color;
	double gray; 
	
	VirtualImage vImage;
	
	ComplexVibration originalVib;
	double[][] unifiedSteroOutput; 
	List<AtomicWave> displaySeries;
	
	public VirtualPoint(int x, int y, double gray) {
		this.x = x;
		this.y = y;
		this.gray = gray;
		this.color = new double[]{gray, gray, gray};
	}
	public VirtualPoint(int x, int y, double[] color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.gray = ImageReader.toGray(color);
	}
	public VirtualPoint(VirtualImage virtualImage, int x, int y) {
		this.vImage = virtualImage;
		this.x = x;
		this.y = y;
	}

	public VirtualImage getImage(){
		return vImage;
	}
	
	public double[] getColor() {
		return color;
	}
	public void setColor(double[] color) {
		this.color = color;
	}
	public double getGray() {
		return gray;
	}
	public void setGray(double gray) {
		this.gray = gray;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	

}
