package originalvibs;

public class SHMVibration extends SingleVibration {
	/*
	 * vib function : 
	 * 	s(t) = a(t)*sin(p)
	 *  p(t) = p0 + Integral of w(t)
	 *  w(t) = 2*PI*f(t)
	 *  
	 */

	double ampBegin;
	double ampEnd;
	double freqBegin; 
	double freqEnd;
	double phaseBegin;
	
	VariationF ampF;
	VariationF freqF;
	
	public double getAmpBegin() {
		return ampBegin;
	}

	public void setAmpBegin(double ampBegin) {
		this.ampBegin = ampBegin;
	}

	public double getAmpEnd() {
		return ampEnd;
	}

	public void setAmpEnd(double ampEnd) {
		this.ampEnd = ampEnd;
	}

	public void setAmp(double ampBegin, double ampEnd) {
		this.ampBegin = ampBegin;
		this.ampEnd = ampEnd;
	}

	public double getFreqBegin() {
		return freqBegin;
	}

	public void setFreqBegin(double freqBegin) {
		this.freqBegin = freqBegin;
	}

	public double getFreqEnd() {
		return freqEnd;
	}

	public void setFreqEnd(double freqEnd) {
		this.freqEnd = freqEnd;
	}

	public void setFreq(double freqBegin, double freqEnd) {
		this.freqBegin = freqBegin;
		this.freqEnd = freqEnd;
	}

	public double getPhaseBegin() {
		return phaseBegin;
	}

	public void setPhaseBegin(double phaseBegin) {
		this.phaseBegin = phaseBegin;
	}

	public void initAmpFreq(VariationF ampF, VariationF freqF){
		try{
			ampF = ampF.getClass().newInstance();
			freqF = freqF.getClass().newInstance();
		}catch(Exception e){
			throw new RuntimeException("Error when init SHMVibration");
		}
		
		ampF.init(ampBegin, ampEnd, duration);
		freqF.init(freqBegin, freqEnd, duration);
		this.ampF = ampF;
		this.freqF = freqF;
	}
	
	@Override
	public double s(double t) {
		return ampF.f(t) * Math.sin(2*Math.PI*(phaseBegin + freqF.fIntegral(t)));
	}

}
