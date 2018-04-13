package originalvibs;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexVibration {
	List<SingleVibration> vibList;
	Integer readingSimpleRate;
	double duration;
	
	public abstract void initVibList();
	public abstract ComplexVibration getInstance();
	
	public ComplexVibration(double duration, Integer readingSimpleRate) {
		this.readingSimpleRate = readingSimpleRate;
		this.duration = duration;
		vibList = new ArrayList<SingleVibration>();
	}

	public ComplexVibration(double duration) {
		this.duration = duration;
		vibList = new ArrayList<SingleVibration>();
	}

	public Integer getReadingSimpleRate() {
		return readingSimpleRate;
	}

	public void setReadingSimpleRate(Integer readingSimpleRate) {
		this.readingSimpleRate = readingSimpleRate;
	}

	public double getDuration() {
		return duration;
	}

	public int getDurationInFrame() {
		return new Double(getReadingSimpleRate() * getDuration()).intValue();
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
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
		if(duration<t || vibList==null || vibList.isEmpty()){
			return 0;
		}
		
		double st = 0;
		for(SingleVibration vibI : vibList){
			st += vibI.readS(t);
		}

		return st/vibList.size();
	}

}
