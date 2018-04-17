package transformplans.vibplans;

import originalvibs.ComplexVibration;
import originalvibs.HarmonicSeries;
import virtualpresentation.VirtualPoint;

public class FreqYVibPlan<T extends ComplexVibration> extends OriginalVibDefinePlan {
	double fCenter;
	double fRange;
	T vibType;
	
	
	
	public FreqYVibPlan(double fCenter, double fRange, T vibType) {
		super();
		this.fCenter = fCenter;
		this.fRange = fRange;
		this.vibType = vibType;
	}

	
	
	@Override
	public void defineOriginalVib(VirtualPoint pXY) {
		ComplexVibration vib = vibType.getInstance();
		vib.initVibList();
		if(vibType instanceof HarmonicSeries){
			double f = fCenter - fRange * pXY.getY() / pXY.getImage().getDimension().getRy();
			((HarmonicSeries)vib).setF0(f);
			vib.initVibList();
			pXY.setOriginalVib(vib);
		}
	}



	@Override
	public int getVibreLegnthInFrame() {
		return vibType.getDurationInFrame();
	}
	
	

}
