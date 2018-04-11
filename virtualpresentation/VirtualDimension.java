package virtualpresentation;

public class VirtualDimension {
	private int outWidth;
	private int outHeight;

	private int rx;
	private int ry;
	
	public VirtualDimension(int width, int height){
		setOutWidth(width);
		setOutHeight(height);
	}
	
	public VirtualDimension getInstanceByR(int rx, int ry){
		return new VirtualDimension(rx*2-1, ry*2-1);
	}
	
	public int getOutWidth() {
		return outWidth;
	}
	public void setOutWidth(int outWidth) {
		this.outWidth = outWidth;
		rx = (outWidth+1)/2;
	}
	public int getOutHeight() {
		return outHeight;
	}
	public void setOutHeight(int outHeight) {
		this.outHeight = outHeight;
		ry = (outHeight+1)/2;
	}
	public void setoutSize(int w, int h){
		setOutWidth(w);
		setOutHeight(h);
	}
	
	public int getRx() {
		return rx;
	}

	public int getRy() {
		return ry;
	}

	public static int toC0Pos(int v, int r){
		return v+1-r;
	}
	
	public static int toLT0Pos(int v, int r){
		return v-1+r;
	}

	public int toLT0PosX(int px) {
		return toLT0Pos(px, rx);
	}

	public int toLT0PosY(int py) {
		return toLT0Pos(py, ry);
	}
		

}
