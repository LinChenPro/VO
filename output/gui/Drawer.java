package output.gui;

import static java.lang.Math.min;
import static output.gui.V.add;
import static output.gui.V.mult;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;

import mediaformat.ImageReader;
import mediaformat.SimpleWaveMaker;
import mediaformat.WaveFormat;
import mediaformat.WaveMaker;
import mediaformat.outils.WaveFileWriter;
import mediaformat.outils.format.DataTransformer;
import originalvibs.HarmonicSeries;
import originalvibs.SHMVibration;
import originalvibs.VariationF;
import virtualpresentation.VirtualPoint;

class Drawer{
	static Color cl1 = new Color(240,248,248);
	static Color cl2 = new Color(255,240,240);
	static Color cl3 = new Color(252,250,250);
	static Color cl4 = new Color(240,240,240);
	
	static Color cla = new Color(250,250,255);
	static Color clb = new Color(250,200,200);

	static int w, h;
	Graphics2D pt;
	static boolean threadOpen = false;
	static int scdImgSleep = 20;

	public int getX(double x){
		return new Double(x+w/2).intValue();
	}
	
	public int getY(double y){
		return new Double(y+h/2).intValue();
	}
	
	public int getI(double i){
		return new Double(i).intValue();
	}
	
	public Drawer(int w, int h, Graphics g){
		this.w = w;
		this.h = h;
		this.pt = (Graphics2D)g;
		g.setColor(cl1);

	}
	
	public void drPoint(double x, double y){
		int px = getX(x);
		int py = getY(y);
		pt.drawLine(px, py, px, py);
	}

	public void drCircle(double ox, double oy, double r){
		int oX = getX(ox);
		int oY = getY(oy);
		int R = getI(r);
		pt.drawOval(oX-R, oY-R, R*2, R*2);
	}
	
	public void drOval(double ox, double oy, double a, double b){
		int oX = getX(ox);
		int oY = getY(oy);
		int A = getI(a);
		int B = getI(b);
		pt.drawOval(oX-A, oY-B, A*2, B*2);
	}
	
	public void drRect(double ox, double oy, double width, double height){
		int oX = getX(ox);
		int oY = getY(oy);
		int wI = getI(width);
		int hI = getI(height);
		pt.drawRect(oX-wI/2, oY-hI/2, wI, hI);
	}
	
	public void fiRect(double ox, double oy, double width, double height){
		int oX = getX(ox);
		int oY = getY(oy);
		int wI = getI(width);
		int hI = getI(height);
		pt.fillRect(oX-wI/2, oY-hI/2, wI, hI);
	}
	
	public void flRect(double ox, double oy, double width, double height){
		int oX = getX(ox);
		int oY = getY(oy);
		int wI = getI(width);
		int hI = getI(height);
		pt.fillRect(oX-wI/2, oY-hI/2, wI, hI);
	}
	
	public void drLine(double x1, double y1, double x2, double y2){
		pt.drawLine(getX(x1), getY(y1), getX(x2), getY(y2));
	}

	public void drLXY(L l, double length){
		V v = add(l.o, mult(l.dir, length));
		drLineXY(l.o, v);
	}
	
	public void drLXZ(L l, double length){
		V v = add(l.o, mult(l.dir, length));
		drLineXZ(l.o, v);
	}
	
	public void drLYZ(L l, double length){
		V v = add(l.o, mult(l.dir, length));
		drLineYZ(l.o, v);
	}
	
	public void drSegXY(LSeg sg) {
		drLine(sg.p1.x, sg.p1.y, sg.p2.x, sg.p2.y);
//		drLXY(new L(sg.c, sg.dirN), 20);
	}

	public void drPL(List<LSeg> pl) {
		for(LSeg sg : pl){
			drSegXY(sg);
		}
		
	}

	public void drLineXY(V v1, V v2){
		drLine(v1.x, v1.y, v2.x, v2.y);
	}

	public void drLineXZ(V v1, V v2){
		drLine(v1.x, v1.z, v2.x, v2.z);
	}
	
	public void drLineYZ(V v1, V v2){
		drLine(v1.y, v1.z, v2.y, v2.z);
	}
	
	public static int getDiscV(int v, int d){
		int l = 256/d;
//		return min(v/l, d-1)*l+l/2;
		return v*l;
	}

	public static int getDiscV(double v){
		return new Double(255 * v).intValue();
	}

	public void show(){
		
//		testImgReader();

		testSimpleWaveMaker_Points();
//		testSimpleWaveMaker_Image();
//		testSimpleWaveMaker_toFile();
		
//		testSingleVibration();
//		testComplexVibration();
	}

	static Date crtTime = new Date();
	static String imgPath = "C:/Users/lchen/tests/Penguins.jpg";


	public void testImgReader(){
		File file= new File(imgPath);
		BufferedImage bi;
		try{
			bi = ImageIO.read(file);
		}catch (Exception e) {return;}
		
		int deep = 32;
		int rx = 25;
		int ry = 25;
		
		ImageReader reader = new ImageReader(rx*2-1, ry*2-1, deep, bi);
		
		for(int i = -rx+1; i<rx; i++){
			for(int j = -ry+1; j<ry; j++){
				double cv[] = reader.readColor(i, j);
				Color c = new Color(getDiscV(cv[0]), getDiscV(cv[1]), getDiscV(cv[2]));

//				double gray = reader.readGray(i, j);
//				Color c = new Color(getDiscV(gray), getDiscV(gray), getDiscV(gray));
				
//				int cv[] = reader.readColorOrigine(i, j);
//				Color c = new Color(cv[0], cv[1], cv[2]);

				pt.setColor(c);
				drPoint(i, j);
			}
		}
	}

	public void testSimpleWaveMaker_Points(){
		int deep = 16;
		int rx = 5;
		int ry = 5;		
		
		ImageReader reader = new ImageReader(rx*2-1, ry*2-1, deep, null);
		WaveFormat wfm = new WaveFormat(44100, 2, WaveFormat.LITTLE_ENDIAN);
		
		WaveMaker svm = new SimpleWaveMaker(wfm, reader, 255);
		VirtualPoint[] testPoints = {
				new VirtualPoint(0, 0, 1),

				new VirtualPoint(4, 0, 1),
				new VirtualPoint(-4, 0, 1),
				new VirtualPoint(0, -4, 1),
				new VirtualPoint(0, 4, 1),
				
				new VirtualPoint(4, 4, 8d/deep),
				new VirtualPoint(-4, 4, 8d/deep),
				new VirtualPoint(-4, -4, 8d/deep),
				new VirtualPoint(4, -4, 8d/deep),
		};

		int yl = h/4;
		int yr = 3*h/4;

		pt.setColor(Color.lightGray);
		Integer pl = null;
		Integer pr = null;
		int step = wfm.getSimpleRate()/w;
		int max = h*20;
		for(int i=0; i<wfm.getSimpleRate()/step; i++){
			double[] vi = svm.getWaveValue(testPoints, i*step, WaveMaker.MODE_GRAY);
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


	public void testSingleVibration(){

		int yc = h/2;

		pt.setColor(Color.black);
		Integer pCentre = null;
		int step = 1;
		int max = h/2-10;
		
		SHMVibration vib = new SHMVibration();
		vib.setPhaseBegin(0);
		vib.setDuration(1);
		vib.setAmp(1, 1);
		vib.setFreq(50, 0);
		vib.setReadingSimpleRate(w);
		vib.initAmpFreq(new VariationF.LinearVF(), new VariationF.LinearVF());
		
		for(int i=0; i<=w; i++){
			double vi = vib.readSByFrame(i);
			if(pCentre!=null){
				pt.drawLine(i-1, pCentre+yc, i, new Double(max*vi).intValue()+yc);
			}
			
			pCentre = new Double(max*vi).intValue();
		}
		
		
		
		
	}

	
	public void testComplexVibration(){

		int yc = h/2;

		pt.setColor(Color.lightGray);
		Integer pCentre = null;
		int step = 1;
		int max = h/2-10;
		
		HarmonicSeries harmonicSeries = new HarmonicSeries(10, 6, 1, w);
		harmonicSeries.initVibList();
		
		for(int i=0; i<=w; i++){
			double vi = harmonicSeries.readSByFrame(i);
			if(pCentre!=null){
				pt.drawLine(i-1, yc-pCentre, i, yc-new Double(max*vi).intValue());
			}
			
			pCentre = new Double(max*vi).intValue();
		}
	}


	
	
	public void testSimpleWaveMaker_Image(){
		int deep = 16;
		int rx = 5;
		int ry = 5;		
		
		File file= new File(imgPath);
		BufferedImage bi;
		try{
			bi = ImageIO.read(file);
		}catch (Exception e) {return;}

		ImageReader reader = new ImageReader(rx*2-1, ry*2-1, deep, bi);
		WaveFormat wfm = new WaveFormat(44100, 2, WaveFormat.LITTLE_ENDIAN);
		
		WaveMaker svm = new SimpleWaveMaker(wfm, reader, 255);

		int yl = h/4;
		int yr = 3*h/4;

		pt.setColor(new Color(222,222,222));
		Integer pl = null;
		Integer pr = null;
		int step = wfm.getSimpleRate()/w;
		int max = h*200;
		for(int i=0; i<wfm.getSimpleRate()/step; i++){
			double[] vi = svm.getWaveValue(i*step, WaveMaker.MODE_GRAY);
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

	public void testSimpleWaveMaker_toFile(){
		int deep = 16;
		int rx = 5;
		int ry = 5;		
		
		ImageReader reader = new ImageReader(rx*2-1, ry*2-1, deep, null);
		WaveFormat wfm = new WaveFormat(44100, 16, WaveFormat.LITTLE_ENDIAN);
		
		WaveMaker svm = new SimpleWaveMaker(wfm, reader, 255);
		VirtualPoint[] testPoints = {
				new VirtualPoint(0, 0, 1),

				new VirtualPoint(4, 0, 1),
				new VirtualPoint(-4, 0, 1),
//				new VirtualPoint(0, -4, 1),
//				new VirtualPoint(0, 4, 1),
//				
//				new VirtualPoint(4, 4, 8d/deep),
//				new VirtualPoint(-4, 4, 8d/deep),
//				new VirtualPoint(-4, -4, 8d/deep),
//				new VirtualPoint(4, -4, 8d/deep),
		};

		
		AudioFormat format = new AudioFormat(wfm.getSimpleRate(), wfm.getFrameSize(), 2, true, true);
		File file = new File("/home/chen/searchprojets/vo/testOutput.wav");
//		file.deleteOnExit();
		WaveFileWriter writer = new WaveFileWriter(format, file);
		DataTransformer transformer = new DataTransformer(format, format);
		writer.open();
		int volume = 30;
		
		int step = 100;
		double[][] valueStep = null;
		int dataLegnth = wfm.getSimpleRate()*2;
		
		
		for(int i=0, stepI=0; i<dataLegnth; i++, stepI=(stepI+1)%step){
			if(i%step == 0){
				valueStep = new double[min(step, dataLegnth-i)][2];
			}
			double[] dataI = svm.getWaveValue(testPoints, i, WaveMaker.MODE_GRAY);
			valueStep[stepI][0] = dataI[0]*volume;
			valueStep[stepI][1] = dataI[1]*volume;
			
			if(stepI+1>=valueStep.length){
				byte[] datas = transformer.getOutputBytes(valueStep);
				writer.write(datas);				
			}
			
		}
		
		writer.close();
		System.out.println("finished");
	}

	
}