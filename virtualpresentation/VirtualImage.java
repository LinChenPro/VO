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
	ComplexVibration[][] originalVibMatrix;
	double[][][][] unifiedSteroOutputMatrix; 
	List<AtomicWave>[][] displayMatrix;
	
	// CodeStrategie strategie;
	
	public void initOriginalVibs(){
		transformPlan.definePointMatrix(this);
	}
	
	public void calculUnifiedOutput(){
		transformPlan.calculUnifiedOutput(this);
	}
	
	public void readImage(){
		// write points
	}
	
	public void feedDisplayMatrix(){
		transformPlan.feedDisplayMatrix(this);
	}
	
	public double[][] readFrame(int beginFrame, int size){
		return null;
	}

	
}
