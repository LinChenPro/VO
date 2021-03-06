package originalvibs;

public abstract class SingleVibration {
	Integer readingSimpleRate;
	double duration;
	
	public Integer getReadingSimpleRate() {
		return readingSimpleRate;
	}

	public void setReadingSimpleRate(Integer readingSimpleRate) {
		this.readingSimpleRate = readingSimpleRate;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public abstract double s(double t);

	public double readSByFrame(long frameIndex){
		if(readingSimpleRate==null){
			throw new RuntimeException("Reading Simple Rate Not defined");
		}
		return readSByFrame(readingSimpleRate, frameIndex);
	}
	
	public double readSByFrame(long simpleRate, long frameIndex) {
		return readS(frameIndex/(double)simpleRate);
	}

	public double readS(double t) {
		if(duration<t){
			return 0;
		}
		
		return s(t);
	}
}
