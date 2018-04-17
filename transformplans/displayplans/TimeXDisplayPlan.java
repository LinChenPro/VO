package transformplans.displayplans;

import java.util.ArrayList;

import virtualpresentation.AtomicWave;
import virtualpresentation.VirtualDimension;
import virtualpresentation.VirtualImage;
import virtualpresentation.VirtualPoint;

public class TimeXDisplayPlan extends DisplaySeriesFeedPlan {
	@Override
	public void feedDisplaySeries(VirtualPoint pXY) {
		if(pXY == null){
			return;
		}
		
		if(pXY.getDisplaySeries() == null){
			pXY.setDisplaySeries(new ArrayList<AtomicWave>());
		}
		
		VirtualDimension dimension = pXY.getImage().getDimension();
		
		int atomLength = pXY.getOriginalVib().getDurationInFrame();
		int xFrmDis = atomLength;
		int yFrmDis = (atomLength + xFrmDis)/dimension.getOutHeight();
		int loop = 3;
		
		int loopLength = (atomLength + xFrmDis) * (dimension.getOutWidth() +1) - yFrmDis; 

		int xLT = dimension.toLT0PosX(pXY.getX());
		int yLT = dimension.toLT0PosY(pXY.getY());
		
		for(int l=0; l<loop; l++){
			int t0 = loopLength * l + xLT * (atomLength + xFrmDis) + yLT * yFrmDis;
			AtomicWave wave = new AtomicWave(t0, atomLength, 1);
			pXY.getDisplaySeries().add(wave);
		}
	}

	@Override
	public int getLengthInFrame(VirtualImage image) {
		VirtualDimension dimension = image.getDimension();
		
		int atomLength = image.getTransformPlan().getVibPlan().getVibreLegnthInFrame();
		int xFrmDis = atomLength;
		int yFrmDis = (atomLength + xFrmDis)/dimension.getOutHeight();
		int loopLength = (atomLength + xFrmDis) * (dimension.getOutWidth() +1) - yFrmDis;

		return loopLength * 3;
	}
	
	

}

