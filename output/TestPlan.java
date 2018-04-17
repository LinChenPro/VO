package output;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;

import mediaformat.ImageReader;
import originalvibs.HarmonicSeries;
import transformplans.VOTransformPlan;
import transformplans.displayplans.DisplaySeriesFeedPlan;
import transformplans.displayplans.TimeXDisplayPlan2;
import transformplans.outputplan.SteroHROutputPlan;
import transformplans.outputplan.UnifiedOutputPlan;
import transformplans.vibplans.FreqYVibPlan;
import transformplans.vibplans.OriginalVibDefinePlan;
import virtualpresentation.VirtualImage;

public class TestPlan {
	public VirtualImage image;
	
	public VirtualImage getVirtualImage(){
		return image;
	}

	// first simple test
	public void initImage(){
		String imgPath = "/home/chen/searchprojets/resource/testCircle.png";
//		String imgPath = "/home/chen/searchprojets/resource/testRegtrangle.png";
//		String imgPath = "/home/chen/searchprojets/resource/testEmptyRegtrangle.png";
		File file= new File(imgPath);
		BufferedImage bi = null;
		try{
			bi = ImageIO.read(file);
		}catch (Exception e) {
			return;
		}

		ImageReader reader = new ImageReader(19, 17, 256,  bi);
		int sampleRate = 44100;
		AudioFormat outputFormat = new AudioFormat(sampleRate, 16, 2, true, true);

		HarmonicSeries vibType = new HarmonicSeries(600, 4, 0.02, sampleRate);
		OriginalVibDefinePlan vibPlan = new FreqYVibPlan<HarmonicSeries>(600, 200, vibType); 
		
		UnifiedOutputPlan outputPlan = new SteroHROutputPlan();
		DisplaySeriesFeedPlan displayPlan = new TimeXDisplayPlan2();
		
		VOTransformPlan transformPlan = new VOTransformPlan(vibPlan, outputPlan, displayPlan);
		
		VirtualImage vimg = new VirtualImage(transformPlan, reader, outputFormat);
		
		vimg.initPoints();
		vimg.readImage();
		
		this.image = vimg;
	}
	
	public static void main(String[] args) {
		TestPlan plan = new TestPlan();
		plan.initImage();
//		TestLancer.lanceInGUI(plan);
		
		TestLancer.saveAsWav(plan);
		
	}
	
}
