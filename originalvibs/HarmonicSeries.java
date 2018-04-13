package originalvibs;

public class HarmonicSeries extends ComplexVibration {
	double f0;
	int serisLength;
	
	public HarmonicSeries(double f0, int serisLength, double duration, Integer readingSimpleRate) {
		super(duration, readingSimpleRate);
		this.f0 = f0;
		this.serisLength = serisLength;
	}

	public HarmonicSeries(double f0, int serisLength, double duration) {
		super(duration);
		this.f0 = f0;
		this.serisLength = serisLength;
	}
	
	public void setF0(double f0){
		this.f0 = f0;
	}

	@Override
	public void initVibList() {
		for(int i=1; i<=serisLength; i++){
			SHMVibration vibI = new SHMVibration();
			vibI.setPhaseBegin(2d/i);
			vibI.setDuration(duration);
//			double a = Math.pow(i, -0.1);
			double a = Math.pow(0.5, 5d/i);
			vibI.setAmp(a, a);
//			vibI.setAmp(a, a/10);
			vibI.setFreq(f0*i, f0*i);
			vibI.setReadingSimpleRate(readingSimpleRate);
			vibI.initAmpFreq(new VariationF.LinearVF(), new VariationF.LinearVF());
			
			vibList.add(vibI);
		}

	}

	@Override
	public ComplexVibration getInstance() {
		return new HarmonicSeries(f0, serisLength, duration, readingSimpleRate);
	}
	
	

}
