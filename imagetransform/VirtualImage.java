package imagetransform;

import java.util.List;

import originalvibs.ComplexVibration;
import visionaudio.ImageReader;

public class VirtualImage {
	int width;
	int height;
	int rX;
	int rY;
	
	ImageReader reader;
	
	// set in a class for output audio format : 
	int simpleRate;
	
	VirtualPoint[][] points;
	ComplexVibration[][] originalVibs;
	double[][][][] unifiedSteroOutputData; 
	List<DisplayWave>[][] displayWaveList;
	
	// CodeStrategie strategie;
	
	public void initOriginalVibs(){
		// use strategie to write originalVibs
	}
	
	public void calculUnifiedOutput(){
		// use strategie to write unifiedSteroOutputData
	}
	
	public void readImage(){
		// write points
	}
	
	public void displayPointsToWaveList(){
		// use strategie to write displayWaveList
	}
	
	public double[][] readFrame(int beginFrame, int size){
		return null;
	}

	
}
