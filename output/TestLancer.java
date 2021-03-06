package output;

import static java.lang.Math.min;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.sound.sampled.AudioFormat;

import mediaformat.outils.WaveFileWriter;
import mediaformat.outils.format.DataTransformer;
import output.gui.Drawer;
import output.gui.ShowWindow;
import virtualpresentation.VirtualImage;

public class TestLancer extends Drawer{
	public TestLancer(int w, int h, Graphics g) {
		super(w, h, g);
	}

	public static TestPlan currentTestPlan;

	public static void saveAsWav(TestPlan testPlan){
		double dataDuration = testPlan.getVirtualImage().getDataDuration();
		saveAsWav(testPlan, dataDuration);
	}

	public static void saveAsWav(TestPlan testPlan, double duration){
		currentTestPlan = testPlan;
		VirtualImage vImg = currentTestPlan.getVirtualImage();
		if(vImg==null){
			return;
		}

		AudioFormat format = vImg.getOutputFormat();
		File file = new File("/home/chen/searchprojets/vo/testOutput3_2.wav");
//		file.deleteOnExit();
	
		WaveFileWriter writer = new WaveFileWriter(format, file);
		DataTransformer transformer = new DataTransformer(format, format);
		writer.open();
		int volume = 20;
		
		int step = 441;
		int dataLength = new Double(currentTestPlan.getVirtualImage().getOutputFormat().getSampleRate() * duration).intValue();
		
		long t = System.currentTimeMillis();
		for(int i=0; i<dataLength; i+=step){
			int stepLength = min(step, dataLength-i);
			
			double[][] doubleData = vImg.readFrame(i, stepLength, volume);
			byte[] datas = transformer.getOutputBytes(doubleData);
			writer.write(datas);
			System.out.println(i);
		}
		
		writer.close();
		System.out.println("finished");
		System.out.println("time : " +(System.currentTimeMillis()-t));
	}
	
	
	public static void lanceInGUI(TestPlan testPlan){
		lanceInGUI(testPlan, 0);
	}
	
	public static void lanceInGUI(TestPlan testPlan, int beginFrame){
		currentTestPlan = testPlan;
		ShowWindow.main(new String[]{Integer.toString(beginFrame)});
	}
	
	
	public void show(String[] args){
		VirtualImage vImg = currentTestPlan.getVirtualImage();
		if(vImg==null){
			return;
		}

		int beginFrame = 0;
		if(args!=null && args.length>0){
			beginFrame = Integer.parseInt(args[0]);
		}
		
		int yl = h/4;
		int yr = 3*h/4;

		pt.setColor(new Color(222,222,222));
		Integer pl = null;
		Integer pr = null;

		int step = 1;//vImg.getSimpleRate()/w;
		int max = 256 * 256;
		for(int i=0; i<w; i++){
			double[] vi = vImg.readFrame((i+beginFrame)*step, 1)[0];
			if(pl!=null){
				pt.drawLine(i-1, pl+yl, i, new Double(max*vi[0]).intValue()+yl);
			}
			if(pr!=null){
				pt.drawLine(i-1, pr+yr, i, new Double(max*vi[1]).intValue()+yr);
			}
			
			pl = new Double(max*vi[0]).intValue();
			pr = new Double(max*vi[1]).intValue();
		}

	}

}
