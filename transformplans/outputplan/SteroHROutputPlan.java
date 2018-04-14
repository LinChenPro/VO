package transformplans.outputplan;

import hrir.HRIRDoubleArrays;
import virtualpresentation.VirtualDimension;
import virtualpresentation.VirtualPoint;

public class SteroHROutputPlan extends UnifiedOutputPlan{	
	int impulseSize = 200;

	@Override
	public void calculUnifiedOutput(VirtualPoint pXY) {
		int durationInFrame = pXY.getOriginalVib().getDurationInFrame() ;
		double[][] output = pXY.getUnifiedSteroOutput();
		if(output == null){
			pXY.setUnifiedSteroOutput(new double[durationInFrame + impulseSize - 1][2]);
			output = pXY.getUnifiedSteroOutput();
		}
		
		// convolution begin
		double[][] impulse = getimpulse(pXY);
//		double tL = 0;
//		double tR = 0;
//		for(int i=0; i<impulse.length; i++){
//			tL += impulse[i][0];
//			tR += impulse[i][1];
//		}
		
		double[] vibValues = new double[durationInFrame];
		for(int i=0; i<durationInFrame; i++){
			vibValues[i] = pXY.getOriginalVib().readSByFrame(i);
		}
		for(int i=0; i<output.length; i++){
			double impIL = 0;
			double impIR = 0;
			for(int j=Math.max(i-durationInFrame+1, 0); j<impulseSize; j++){
				int a = i-j;
				if(a<0){
					break;
				}
//				double vibS = pXY.getOriginalVib().readSByFrame(a);
				double vibS = vibValues[a];
				impIL += vibS * impulse[j][0];
				impIR += vibS * impulse[j][1];
			}
//			output[i][0] = impIL/tL;
//			output[i][1] = impIR/tR;
			output[i][0] = impIL/impulse.length;
			output[i][1] = impIR/impulse.length;
		}
		// convolution end
		
	}

	@Override
	public int getMargeLength() {
		return impulseSize;
	}
	
	public double[][] getimpulse(VirtualPoint pXY){
		return HRIRDoubleArrays.getImpulse(pXY);
	}
	
//	for test, no need to keep
	public static void main(String[] args) {
		int impulseSize = 10;
		int durationInFrame = 3;
		double[] origineValues = new double[durationInFrame];
		double[][] impulse = new double[impulseSize][2];

		System.out.println("\n\nimpulse : ");
		for(int i=0; i<impulse.length; i++){
			impulse[i][0] = 1;
			impulse[i][1] = 2;
			System.out.print(impulse[i][0]+"\t");
		}
		System.out.println();
		for(int i=0; i<impulse.length; i++){
			System.out.print(impulse[i][1]+"\t");
		}
		System.out.println("\n\noriginal values: ");
		for(int i=0; i<origineValues.length; i++){
			origineValues[i] = 1;
			System.out.print(origineValues[i]+"\t");
		}

		double[][] output = new double[durationInFrame + impulseSize - 1][2];
		
		
		double tL = 0;
		double tR = 0;
		for(int i=0; i<impulse.length; i++){
			tL += impulse[i][0];
			tR += impulse[i][1];
		}
		
		for(int i=0; i<output.length; i++){
			double impIL = 0;
			double impIR = 0;
			for(int j=Math.max(i-durationInFrame+1, 0); j<impulseSize; j++){
				int a = i-j;
				if(a<0){
					break;
				}
				double vibS = origineValues[a];
				impIL += vibS * impulse[j][0];
				impIR += vibS * impulse[j][1];
			}
//			output[i][0] = impIL/tL;
//			output[i][1] = impIR/tR;
			output[i][0] = impIL;
			output[i][1] = impIR;
//			output[i][0] = impIL/impulseSize;
//			output[i][1] = impIR/impulseSize;
		}

		
		System.out.println("\n\noutput values: ");
		for(int i=0; i<output.length; i++){
			System.out.print(output[i][0]+"\t");
		}
		System.out.println();
		for(int i=0; i<output.length; i++){
			System.out.print(output[i][1]+"\t");
		}
		
	}

}
