package transformplans;

import virtualpresentation.VirtualImage;
import virtualpresentation.VirtualPoint;

public abstract class VOTransformPlan {
	public abstract void defineOriginalVib(VirtualPoint pXY);
	public abstract void calculUnifiedOutput(VirtualPoint pXY);
	public abstract void feedDisplaySeries(VirtualPoint pXY);

}
