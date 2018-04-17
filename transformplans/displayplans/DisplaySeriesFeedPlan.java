package transformplans.displayplans;

import virtualpresentation.VirtualImage;
import virtualpresentation.VirtualPoint;

public abstract class DisplaySeriesFeedPlan {
	public abstract int getLengthInFrame(VirtualImage image);
	public abstract void feedDisplaySeries(VirtualPoint pXY);

	public int getLengthWithMargeInFrame(VirtualImage image) {
		return getLengthInFrame(image) + image.getTransformPlan().getUnifiedOutputPlan().getMargeLength();
	}

}
