package visionaudio;

import java.awt.Color;
import java.awt.image.BufferedImage;

import virtualpresentation.VirtualDimension;
import virtualpresentation.VirtualPoint;

public class ImageReader {
	private int resolution;
	private BufferedImage sourceImage;
	private VirtualDimension dimension;
	
	private Float stepX;
	private Float stepY;
	private Float stepColor;
	
	boolean needInit = true;
	
	Double[][] grayValueCaches = null;
	Double[][][] colorValueCaches = null;
	
	public ImageReader(int outWidth, int outHeight, int resolution, BufferedImage sourceImage) {
		this.resolution = resolution;
		this.sourceImage = sourceImage;
		setDimension(new VirtualDimension(outWidth, outHeight));
		needInit = true;
	}
	
	public VirtualDimension getDimension() {
		return dimension;
	}

	public void setDimension(VirtualDimension dimension) {
		this.dimension = dimension;
		needInit = true;
	}

	public BufferedImage getSourceImage() {
		return sourceImage;
	}
	public void setSourceImage(BufferedImage sourceImage) {
		this.sourceImage = sourceImage;
		needInit = true;
	}
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
		needInit = true;
	}
	
	private void initParams() {
		if(needInit){
			stepX = sourceImage.getWidth()/(float)dimension.getOutWidth();
			stepY = sourceImage.getHeight()/(float)dimension.getOutHeight();		
			stepColor = 255/(float)resolution;
			
			grayValueCaches = new Double[dimension.getOutWidth()][dimension.getOutHeight()];
			colorValueCaches = new Double[dimension.getOutWidth()][dimension.getOutHeight()][3];
			
			needInit = false;
		}
	}

	private int iToStepI(int i, float step){
		return new Float(i*step).intValue();
	}
	
	private double vToResulutionV(int v){
		initParams();
		return new Float(v/stepColor).intValue()/(double)resolution;
	}
	
	private double[] vToResulutionV(int[] v){
		double[] vr = new double[v.length];
		for(int i=0; i<v.length; i++){
			vr[i] = vToResulutionV(v[i]);
		}
		return vr;
	}
	
	public static double toGray(double[] colorValue){
		double t = 0;
		for(double i : colorValue){
			t += i;
		}
		return t/colorValue.length;
	}
	
	public static int toGray(int[] colorValue){
		int t = 0;
		for(int i : colorValue){
			t += i;
		}
		return t/colorValue.length;
	}
	
	public double readGray(int px, int py){
		if(needInit){
			initParams();
		}
		
		int x = dimension.toLT0PosX(px);
		int y = dimension.toLT0PosY(py);

		if(grayValueCaches[x][y]==null){
			grayValueCaches[x][y] = vToResulutionV(toGray(readColorOrigine(px, py)));
		}
		return grayValueCaches[x][y];
	}
	
	public double[] readColor(int px, int py){
		if(needInit){
			initParams();
		}

		int x = dimension.toLT0PosX(px);
		int y = dimension.toLT0PosY(py);

		if(colorValueCaches[x][y][0]==null){
			double[] color = vToResulutionV(readColorOrigine(px, py));
			colorValueCaches[x][y][0] = color[0];
			colorValueCaches[x][y][1] = color[1];
			colorValueCaches[x][y][2] = color[2];
		}
		
		Double[] color = colorValueCaches[x][y];
		return new double[]{color[0], color[1], color[2]};
	}
	
	public int readGrayOrigine(int px, int py){
		return toGray(readColorOrigine(px, py));
	}
	
	public int[] readColorOrigine(int px, int py){
		initParams();
		
		int x = dimension.toLT0PosX(px);
		int y = dimension.toLT0PosY(py);

		int count = 0;
		int[] values = new int[3];
		for(int i=iToStepI(x, stepX); i<iToStepI(x+1, stepX); i++){
			for(int j=iToStepI(y, stepY); j<iToStepI(y+1, stepY); j++){
				Color color = new Color(sourceImage.getRGB(i, j));
				values[0] += color.getRed();
				values[1] += color.getGreen();
				values[2] += color.getBlue();				
				count++;
			}
		}
		
		for(int i=0; i<3; i++){
			values[i] /= count;
		}

		return values;
	}

	public void readToPoint(VirtualPoint pXY) {
		int x = pXY.getX();
		int y = pXY.getY();
		pXY.setColor(readColor(x, y));
		pXY.setGray(readGray(x, y));
		
	}

	
}
