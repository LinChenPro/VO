package virtualpresentation;

import java.util.List;

import javax.sound.sampled.AudioFormat;

import originalvibs.ComplexVibration;
import transformplans.VOTransformPlan;
import visionaudio.ImageReader;

public class VirtualImage {
	VirtualDimension dimension;
	VOTransformPlan transformPlan;
	ImageReader reader;	
	AudioFormat outputFormat;
	
	VirtualPoint[][] pointMatrix;
	
	public VirtualImage(VirtualDimension dimension, VOTransformPlan transformPlan, ImageReader reader, AudioFormat outputFormat) {
		super();
		this.dimension = dimension;
		this.transformPlan = transformPlan;
		this.reader = reader;
		this.outputFormat = outputFormat;
	}
	
	public VirtualDimension getDimension() {
		return dimension;
	}
	public void setDimension(VirtualDimension dimension) {
		this.dimension = dimension;
	}
	public VOTransformPlan getTransformPlan() {
		return transformPlan;
	}
	public void setTransformPlan(VOTransformPlan transformPlan) {
		this.transformPlan = transformPlan;
	}
	public ImageReader getReader() {
		return reader;
	}
	public void setReader(ImageReader reader) {
		this.reader = reader;
	}
	public AudioFormat getOutputFormat() {
		return outputFormat;
	}
	public void setOutputFormat(AudioFormat outputFormat) {
		this.outputFormat = outputFormat;
	}

	
	public <T> T getMatrixValue(T[][] matrix, int px, int py){
		return matrix[dimension.toLT0PosX(px)][dimension.toLT0PosY(py)];
	}
		
	public <T> void setMatrixValue(T[][] matrix, int px, int py, T value){
		matrix[dimension.toLT0PosX(px)][dimension.toLT0PosY(py)] = value;
	}
		

	public void readImage(){
		if(pointMatrix==null){
			initPoints();
		}

		for(int i=0; i<dimension.getOutWidth(); i++){
			for(int j=0; j<dimension.getOutHeight(); j++){
				VirtualPoint pXY = pointMatrix[i][j];
				reader.readToPoint(pXY);
			}
		}		
	}
	
	public void initPoints(){
		pointMatrix = new VirtualPoint[dimension.getOutWidth()][dimension.getOutHeight()];
		
		int i, j;
		for(int x=-dimension.getRx(); x<=dimension.getRx(); x++){
			i = dimension.toLT0PosX(x);
			for(int y=-dimension.getRy(); y<=dimension.getRy(); y++){
				j = dimension.toLT0PosY(y);
				VirtualPoint pXY = new VirtualPoint(this, x, y);
				pointMatrix[i][j] = pXY;
				transformPlan.defineOriginalVib(pXY);
				transformPlan.calculUnifiedOutput(pXY);
				transformPlan.feedDisplaySeries(pXY);
			}
		}
	}
	

	
	public double[][] readFrame(int beginFrame, int size){
		return null;
	}
	
}
