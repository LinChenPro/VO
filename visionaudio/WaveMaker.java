package visionaudio;

import imagetransform.VirtualPoint;

public abstract class WaveMaker {
	WaveFormat waveFormat;
	ImageReader imageReader;
	int maxVolume;
	
	
	
	public WaveMaker(WaveFormat waveFormat, ImageReader imageReader, int maxVolume) {
		this.waveFormat = waveFormat;
		this.imageReader = imageReader;
		this.maxVolume = maxVolume;
	}

	public static final int MODE_COLOR = 0;
	public static final int MODE_GRAY = 1;
	
	public abstract int getStreamDuration();
	public abstract int getStreamSize();
	public abstract double[] getPixelColorWaveValue(int x, int y, double[] color, double t);
	public abstract double[] getPixelGrayWaveValue(int x, int y, double gray, double t);
	
	public double[] getPixelColorWaveValue(int x, int y, double[] color, int frameIndex){
		return getPixelColorWaveValue(x, y, color, frameIndex/(double)waveFormat.simpleRate);
	}
	
	public double[] getPixelGrayWaveValue(int x, int y, double gray, int frameIndex){
		return getPixelGrayWaveValue(x, y, gray, frameIndex/(double)waveFormat.simpleRate);
	}

	
	public double[] getPixelColorWaveValue(int x, int y, int frameIndex){
		return getPixelColorWaveValue(x, y, imageReader.readColor(x, y), frameIndex);
	}
	
	public double[] getPixelGrayWaveValue(int x, int y, int frameIndex){
		return getPixelGrayWaveValue(x, y, imageReader.readGray(x, y), frameIndex);
	}
	
	public double[] getWaveValue(int frameIndex, int mode){
		double[] wave = new double[2];
		for(int x=-imageReader.getRx()+1; x<imageReader.getRx(); x++){
			for(int y=-imageReader.getRy()+1; y<imageReader.getRy(); y++){
				double[] waveXY = mode==MODE_COLOR ? getPixelColorWaveValue(x, y, frameIndex) : getPixelGrayWaveValue(x, y, frameIndex); 
				wave[0] += waveXY[0]; 
				wave[1] += waveXY[1]; 
			}
		}
		wave[0] /= imageReader.getOutWidth() * imageReader.getOutHeight(); 
		wave[1] /= imageReader.getOutWidth() * imageReader.getOutHeight(); 
		
		return wave;
	}

	public double[] getWaveValue(VirtualPoint[] inputPoints, int frameIndex, int mode){
		double[] wave = new double[2];
		for(VirtualPoint inputPoint : inputPoints){
			double[] waveXY = mode==
					MODE_COLOR 
					? getPixelColorWaveValue(inputPoint.getX(), inputPoint.getY(), inputPoint.getColor(), frameIndex)
					: getPixelGrayWaveValue(inputPoint.getX(), inputPoint.getY(), inputPoint.getGray(), frameIndex); 
			wave[0] += waveXY[0]; 
			wave[1] += waveXY[1]; 
		}
		wave[0] /= inputPoints.length; 
		wave[1] /= inputPoints.length; 
		
		return wave;
	}

	public double[] getColorWaveValue(int frameIndex){
		return getWaveValue(frameIndex, MODE_COLOR);
	}

	public double[] getGrayWaveValue(int frameIndex){
		return getWaveValue(frameIndex, MODE_GRAY);
	}
}
