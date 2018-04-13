package transformplans;

import transformplans.displayplans.DisplaySeriesFeedPlan;
import transformplans.outputplan.UnifiedOutputPlan;
import transformplans.vibplans.OriginalVibDefinePlan;
import virtualpresentation.VirtualPoint;

public class VOTransformPlan {
	OriginalVibDefinePlan vibPlan;
	UnifiedOutputPlan unifiedOutputPlan;
	DisplaySeriesFeedPlan displayPlan;
	
	
	
	public VOTransformPlan(OriginalVibDefinePlan vibPlan, UnifiedOutputPlan unifiedOutputPlan, DisplaySeriesFeedPlan displayPlan) {
		super();
		this.vibPlan = vibPlan;
		this.unifiedOutputPlan = unifiedOutputPlan;
		this.displayPlan = displayPlan;
	}

	public DisplaySeriesFeedPlan getDisplayPlan() {
		return displayPlan;
	}
	public void setDisplayPlan(DisplaySeriesFeedPlan displayPlan) {
		this.displayPlan = displayPlan;
	}
	public OriginalVibDefinePlan getVibPlan() {
		return vibPlan;
	}
	public void setVibPlan(OriginalVibDefinePlan vibPlan) {
		this.vibPlan = vibPlan;
	}
	public UnifiedOutputPlan getUnifiedOutputPlan() {
		return unifiedOutputPlan;
	}
	public void setUnifiedOutputPlan(UnifiedOutputPlan unifiedOutputPlan) {
		this.unifiedOutputPlan = unifiedOutputPlan;
	}

	public void defineOriginalVib(VirtualPoint pXY){
		vibPlan.defineOriginalVib(pXY);
	}
	
	public void calculUnifiedOutput(VirtualPoint pXY){
		unifiedOutputPlan.calculUnifiedOutput(pXY);
	}
	
	public void feedDisplaySeries(VirtualPoint pXY){
		displayPlan.feedDisplaySeries(pXY);
	}

}
