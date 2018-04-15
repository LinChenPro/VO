package mediaformat;

import java.util.Random;

import virtualpresentation.VirtualDimension;

public class SimpleWaveMaker extends WaveMaker {

	public SimpleWaveMaker(WaveFormat waveFormat, ImageReader imageReader, int maxVolume) {
		super(waveFormat, imageReader, maxVolume);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getStreamDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStreamSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPixelColorWaveValue(int x, int y, double[] color, double t) {
		return getPixelGrayWaveValue(x, y, ImageReader.toGray(color), t);
	}

	static double f = 800;
	static int repeatTime = 70;
	static Random rdm = new Random(); 
	
	Double l;
	Double[][] startTimes;

	double getStartTime(int x, int y){
		VirtualDimension readerDimension = imageReader.getDimension();
		if(startTimes==null){
			startTimes = new Double[readerDimension.getOutWidth()][readerDimension.getOutHeight()];
		}
		x = readerDimension.toLT0PosX(x);
		y = readerDimension.toLT0PosY(y);
		if(startTimes[x][y]==null){
			startTimes[x][y] = rdm.nextDouble() * (2 - getOneSoundDuration());
		}
		return startTimes[x][y];
	}
	
	Double getL(){
		if(l==null){
			l = 1/f;
		}
		return l;
	}

	Double getOneSoundDuration(){
		return getL() * repeatTime;
	}
	
	@Override
	public double[] getPixelGrayWaveValue(int x, int y, double gray, double t) {
		double s = 5;
		
		double mX = s*x/(double)imageReader.getDimension().getRx();
		double mY = s*y/(double)imageReader.getDimension().getRy();
		
		double eX = 0.1;
		double v = 340;
		
		double distL = Math.pow(s*s+ mY*mY +(mX+eX)*(mX+eX), 0.5);
		double distR = Math.pow(s*s+ mY*mY +(mX-eX)*(mX-eX), 0.5);
		
		double dtL = distL/v;
		double dtR = distR/v;
		
		double tL = t - dtL - getStartTime(x, y);
		double tR = t - dtR - getStartTime(x, y);

		double aL = 1/(distL*distL);
		double aR = 1/(distR*distR);
		
		double vL = (tL>0 && tL< getOneSoundDuration())? (double)gray * aL * Math.sin(2*Math.PI*tL/getL()) : 0d;
		double vR = (tR>0 && tR< getOneSoundDuration())? (double)gray * aR * Math.sin(2*Math.PI*tR/getL()) : 0d;
		
		return new double[]{vL, vR};
	}

}
