package transformplans;

import virtualpresentation.VirtualImage;

public abstract class VOTransformPlan {

	public abstract void definePointMatrix(VirtualImage virtualImage);

	public abstract void calculUnifiedOutput(VirtualImage virtualImage);

	public abstract void feedDisplayMatrix(VirtualImage virtualImage);

}
