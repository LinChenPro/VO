package visionaudio;

public class WaveFormat {
	static final int BIG_ENDIAN = 0;
	static final int LITTLE_ENDIAN = 0;
	
	int simpleRate;
	int frameSize;
	int endian;
	
	public WaveFormat(int simpleRate, int frameSize, int endian) {
		super();
		this.simpleRate = simpleRate;
		this.frameSize = frameSize;
		this.endian = endian;
	}
	
	public int getSimpleRate() {
		return simpleRate;
	}
	public void setSimpleRate(int simpleRate) {
		this.simpleRate = simpleRate;
	}
	public int getFrameSize() {
		return frameSize;
	}
	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}
	public int getEndian() {
		return endian;
	}
	public void setEndian(int endian) {
		this.endian = endian;
	}
	
	
}
