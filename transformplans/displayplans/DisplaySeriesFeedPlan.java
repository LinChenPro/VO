package transformplans.displayplans;

import virtualpresentation.VirtualImage;
import virtualpresentation.VirtualPoint;

public abstract class DisplaySeriesFeedPlan {
	public abstract int getLengthInFrame(VirtualImage image);
	public abstract int getLengthWithMargeInFrame(VirtualImage image);
	public abstract void feedDisplaySeries(VirtualPoint pXY);
}
