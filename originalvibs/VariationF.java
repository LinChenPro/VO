package originalvibs;

public abstract class VariationF {
	double v1;
	double v2;
	double duration;

	public abstract double f(double t);
	public abstract double fIntegral(double t);
	
	public VariationF(){}
	public void init(double v1, double v2, double duration){
		this.v1 = v1;
		this.v2 = v2;
		this.duration = duration;
	}
	public VariationF(double v1, double v2, double duration){
		this();
		init(v1, v2, duration);
	}
	
	public static class LinearVF extends VariationF{
		double v;
		public void init(double v1, double v2, double duration) {
			super.init(v1, v2, duration);
			v = (v2-v1)/duration;
		}

		@Override
		public double f(double t) {
			return v1 + t*v;
		}

		@Override
		public double fIntegral(double t) {
			return v1*t + t*t*v/2; 
		}
		
	}
}
