package virtualpresentation;

public class AtomicWave {
	int beginFrame;
	int endFrame;
	int length;
	double amp;
	public AtomicWave(int beginFrame, int length, double amp) {
		super();
		this.beginFrame = beginFrame;
		this.length = length;
		this.amp = amp;
		
		this.endFrame = beginFrame + length-1;
	}
	
	public double getAmp() {
		return amp;
	}
	public void setAmp(double amp) {
		this.amp = amp;
	}
	
	
}
