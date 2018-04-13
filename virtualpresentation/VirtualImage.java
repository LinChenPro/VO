package virtualpresentation;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;

import originalvibs.HarmonicSeries;
import transformplans.VOTransformPlan;
import transformplans.displayplans.DisplaySeriesFeedPlan;
import transformplans.displayplans.TimeXDisplayPlan;
import transformplans.outputplan.SteroHROutputPlan;
import transformplans.outputplan.UnifiedOutputPlan;
import transformplans.vibplans.FreqYVibPlan;
import transformplans.vibplans.OriginalVibDefinePlan;
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
		reader.setDimension(dimension);
	}
	
	public VirtualImage(VOTransformPlan transformPlan, ImageReader reader, AudioFormat outputFormat) {
		super();
		this.dimension = reader.getDimension();
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
		for(int x=1-dimension.getRx(); x<dimension.getRx(); x++){
			i = dimension.toLT0PosX(x);
			for(int y=1-dimension.getRy(); y<dimension.getRy(); y++){
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
		if(beginFrame+size > this.transformPlan.getDisplayPlan().getLengthWithMargeInFrame(this)){
			return null;
		}
		
		int realSize = Math.min(size, this.transformPlan.getDisplayPlan().getLengthWithMargeInFrame(this) - beginFrame);
		
		double[][] output = new double[realSize][2];
		
		for(int i=0; i<realSize; i++){
			int frmIndex = beginFrame+i;
			double vLeft = 0;
			double vRight = 0;
			long validPointCount = 0;
			
			for(int x=0; x<dimension.getOutWidth(); x++){
				for(int y=0; y<dimension.getOutHeight(); y++){
					double[] sXY = readFrame(pointMatrix[x][y], frmIndex);
					if(sXY != null){
						validPointCount++;
						vLeft += sXY[0];
						vRight += sXY[1];
					}
				}
			}
			
			if(validPointCount>0){
				vLeft /= validPointCount;
				vRight /= validPointCount;
			}
			
			output[i][0] = vLeft;
			output[i][1] = vRight;
		}
		
		return output;
	}
	
	public double[] readFrame(VirtualPoint virtualPoint, int frmIndex) {
		if(virtualPoint==null || (virtualPoint.getColor()==null && virtualPoint.getGray()==null)){
			return null;
		}
		
		double[] value = new double[2];
		int marge = transformPlan.getUnifiedOutputPlan().getMargeLength();
		for(AtomicWave wave : virtualPoint.getDisplaySeries()){
			int lastFrame = wave.endFrame+marge-1;
			if(wave.beginFrame<=frmIndex && lastFrame >= frmIndex){
				double[] output = virtualPoint.getUnifiedSteroOutput()[frmIndex-wave.beginFrame];
				value[0] += wave.getAmp() * output[0];
				value[1] += wave.getAmp() * output[1];
			}
		}

		value[0] *= virtualPoint.getGray();
		value[1] *= virtualPoint.getGray();

		return value;
	}

	public static void main(String[] args) {
		String imgPath = "C:/Users/lchen/tests/Penguins.jpg";
		File file= new File(imgPath);
		BufferedImage bi = null;
		try{
			bi = ImageIO.read(file);
		}catch (Exception e) {return;}

		ImageReader reader = new ImageReader(11, 11, 16,  bi);
		int sampleRate = 44100;
		AudioFormat outputFormat = new AudioFormat(sampleRate, 16, 2, true, true);

		HarmonicSeries vibType = new HarmonicSeries(800, 5, 0.01, sampleRate);
		OriginalVibDefinePlan vibPlan = new FreqYVibPlan<HarmonicSeries>(800, 200, vibType); 
		
		UnifiedOutputPlan outputPlan = new SteroHROutputPlan();
		DisplaySeriesFeedPlan displayPlan = new TimeXDisplayPlan();
		
		VOTransformPlan transformPlan = new VOTransformPlan(vibPlan, outputPlan, displayPlan);
		
		VirtualImage vimg = new VirtualImage(transformPlan, reader, outputFormat);
		
		vimg.initPoints();
		vimg.readImage();
		double[][] datas =  vimg.readFrame(0, 100);
		System.out.println(datas.length);
		for(double[] data : datas){
			System.out.println(data[0]);
			System.out.println(data[1]);
			System.out.println();
		}
	}
	
}
