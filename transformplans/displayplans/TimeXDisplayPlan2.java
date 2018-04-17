package transformplans.displayplans;

import java.util.ArrayList;

import originalvibs.HarmonicSeries;
import virtualpresentation.AtomicWave;
import virtualpresentation.VirtualDimension;
import virtualpresentation.VirtualImage;
import virtualpresentation.VirtualPoint;

public class TimeXDisplayPlan2 extends DisplaySeriesFeedPlan {
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
		int xFrmDis = 0;//atomLength/10;
		int yFrmDis = 0;//atomLength/10;
		int loop = 3;
		
		int yLength = atomLength + yFrmDis;
		int xLength = yLength * dimension.getOutHeight() + xFrmDis;
		int loopLength = xLength * dimension.getOutWidth(); 
		
		int xLT = dimension.toLT0PosX(pXY.getX());
		int yLT = dimension.toLT0PosY(pXY.getY());
		
		for(int l=0; l<loop; l++){
			int t0 = loopLength * l + xLT * xLength + yLT * yLength;
//			System.out.println(xLT+","+yLT+" : "+((HarmonicSeries)pXY.getOriginalVib()).getF0());
			AtomicWave wave = new AtomicWave(t0, atomLength, 1);
			pXY.getDisplaySeries().add(wave);
		}
	}

	@Override
	public int getLengthInFrame(VirtualImage image) {
		VirtualDimension dimension = image.getDimension();
		
		int atomLength = image.getTransformPlan().getVibPlan().getVibreLegnthInFrame();
		int xFrmDis = atomLength/5;
		int yFrmDis = atomLength/5;
		int loop = 3;
		
		int yLength = atomLength + yFrmDis;
		int xLength = yLength * dimension.getOutHeight() + xFrmDis;
		int loopLength = xLength * dimension.getOutWidth(); 

		return loopLength * loop;
	}


}

