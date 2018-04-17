package transformplans.displayplans;

import java.util.ArrayList;
import java.util.Random;

import virtualpresentation.AtomicWave;
import virtualpresentation.VirtualDimension;
import virtualpresentation.VirtualImage;
import virtualpresentation.VirtualPoint;

public class TimeRandomDisplayPlan extends DisplaySeriesFeedPlan {
	private VirtualDimension dimension;
	private Integer atomLength;
//	private Integer distance;
	private int loop;

	private int pointCount; 
	private int loopLength;
	private Integer[][] startTimes;
	
	public TimeRandomDisplayPlan(VirtualDimension dimension, int loop) {
		super();
		this.dimension = dimension;
		this.loop = loop;
		
		init();
	}

	private void init() {
		startTimes = new Integer[dimension.getOutWidth()][dimension.getOutHeight()];
		pointCount = dimension.getOutWidth() * dimension.getOutHeight();
		Random rdm = new Random();
		for(int i=0; i<pointCount; i++){
			int pn = rdm.nextInt(pointCount-i);
			setNotEmptyPointTime(pn, i);
		}		
	}

	private void setNotEmptyPointTime(int pn, int i) {
		int count = 0;
		for(int x = 0; x<dimension.getOutWidth(); x++){
			for(int y = 0; y<dimension.getOutHeight(); y++){
				if(startTimes[x][y] != null){
					continue;
				}
				
				if(count==pn){
					startTimes[x][y] = i;
					return;
				}
				count++;
			}
		}
	}

	private int getDistance(){
		return getDistance(atomLength);
	}
	
	private int getDistance(Integer atom){
		if(atom==null){
			return 0;
		}
		
		return atom/5;
	}
	
	@Override
	public void feedDisplaySeries(VirtualPoint pXY) {
		if(pXY == null){
			return;
		}
		
		if(atomLength == null){
			atomLength = pXY.getOriginalVib().getDurationInFrame();
			loopLength = (atomLength + getDistance()) * pointCount;
		}

		if(pXY.getDisplaySeries() == null){
			pXY.setDisplaySeries(new ArrayList<AtomicWave>());
		}
		
		for(int l=0; l<loop; l++){
			AtomicWave wave = new AtomicWave(startTimes[pXY.getI()][pXY.getJ()]*(atomLength + getDistance()) + l * loopLength , atomLength, 1);
			pXY.getDisplaySeries().add(wave);
		}
	}

	@Override
	public int getLengthInFrame(VirtualImage image) {
		int atom = image.getTransformPlan().getVibPlan().getVibreLegnthInFrame();
		VirtualDimension dimensionImg = image.getDimension();
		return loop * (dimensionImg.getOutHeight() * dimensionImg.getOutWidth()) * (atom + getDistance(atom));
	}


	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		TimeRandomDisplayPlan plan = new TimeRandomDisplayPlan(new VirtualDimension(100, 100), 1);
		System.out.println(System.currentTimeMillis() - t);
		
		for(int x = 0; x<plan.dimension.getOutWidth(); x++){
			for(int y = 0; y<plan.dimension.getOutWidth(); y++){
				System.out.print(plan.startTimes[x][y]+"\t");
			}
			System.out.println();
		}
		
	}
	
}

