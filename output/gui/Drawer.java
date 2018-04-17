package output.gui;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.tan;
import static output.gui.U.AT;
import static output.gui.U.C;
import static output.gui.U.S;
import static output.gui.U.T;
import static output.gui.U.ac;
import static output.gui.U.as;
import static output.gui.U.defun;
import static output.gui.U.t;
import static output.gui.U.trm;
import static output.gui.V.add;
import static output.gui.V.mR;
import static output.gui.V.mXY;
import static output.gui.V.mult;
import static output.gui.V.multD;
import static output.gui.V.sub;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import output.TestLancer;
import virtualpresentation.VirtualPoint;

public class Drawer{
	protected static Color cl1 = new Color(240,248,248);
	protected static Color cl2 = new Color(255,240,240);
	protected static Color cl3 = new Color(252,250,250);
	protected static Color cl4 = new Color(240,240,240);
	
	protected static Color cla = new Color(250,250,255);
	protected static Color clb = new Color(250,200,200);

	protected int w, h;
	protected Graphics2D pt;
	protected static boolean threadOpen = false;
	protected static int scdImgSleep = 20;

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

	public void show(String[] args){
//		testImgReader();
//		testSimpleWaveMaker_Points();
//		testSimpleWaveMaker_Image();
//		testSimpleWaveMaker_toFile();
//		testSingleVibration();
//		testComplexVibration();
			
	}

	public void show(){
		show(null);
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

class Gra{
	int r;
	int d;
	float h;
	
	public Gra(	int r, int d, float h){
		this.r = r;
		this.d = d;
		this.h = h;
	}

	public Area getArea(int cx, int cy, int lx, int ly, V e){
		
		float c = new Float(e.z/(e.z-h));
		float c2 = new Float(h/(e.z-h));
		Float rf = r*c;
		int ri = rf.intValue();
		float demiRf = (r-1)*c/2;
//		float demiRf = (r)*c/2;
		float rm = d*c;
		float ox = cx + new Float(-e.x*c2);
		float oy = cy + new Float(-e.y*c2);
		
		float x0 = ox-rm*(lx+1);
		float wx = 2*rm*(lx+1);
		
		float y0 = oy-rm*(ly+1);
		float hy = 2*rm*(ly+1);
		
		Area area = new Area();
		for(int i=-lx; i<=lx; i++){
//			area.add(new Area(new Rectangle2D.Float(ox+i*rm-demiRf, y0, rf, hy)));
			area.add(new Area(new Rectangle2D.Float(new Float(ox+i*rm-demiRf+0.5).intValue(), y0, ri, hy)));
		}

		Area area2 = new Area();
		for(int j=-ly; j<=ly; j++){
//			area2.add(new Area(new Rectangle2D.Float(x0, oy+j*rm-demiRf, wx, rf)));
			area2.add(new Area(new Rectangle2D.Float(x0, new Float(oy+j*rm-demiRf+0.5).intValue(), wx, ri)));
		}
		
		area.intersect(area2);
		return area;
	}	
}

class Group{
	int r; // dir 1
	float z1; // dir 3

	int d; // dir 1
	int md; // dir 1

	int l;
	int n;
	int h;

	float zmd;
	int t;
	double a;
	float H;
	float hl;
	Gra g0;
	Gra gmd;
	Gra gl;
	Gra gn;
	
	public Image img;
	int objR;
	V obj;

	public Group(int r, float z1, int d, int md, int l, int n, int h){
		this.r = r;
		this.z1 = z1;
		this.d = d;
		this.md = md;
		this.l = l;
		this.n = n;
		this.h = h;

		zmd = z1*md/d;
		t = l*n+h;
		a = AT((d-1)/2/z1);

		g0 = new Gra(r, d*r, (l*n+h)*z1);
		gmd = new Gra(md*r, d*r, (l*n+h)*z1-zmd);
		gl = new Gra(r, l*r, (l*n-l+h)*z1);
		gn = new Gra(r, l*r, h*z1);
		
		H = (l*n+h)*z1;
		hl = l*z1;
	}
	
	public void initImage(int width, int height, int objDis, int oR, Color cO, Color cB) {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		Drawer dr = new Drawer(width, height, g);
		
		V objC = new V(0,0,objDis * H);

		objR = oR * r;
		obj = new V(0,0,H-objC.z);

		
		int cx = width/r/d; 
		int cy = height/r/d; 
		int cd = d/2;
//		List<Integer> ss = new ArrayList<Integer>();
		for(int i=-cx; i<=cx; i++){
			for(int j=-cy; j<=cy; j++){
				V ctij = new V(i*r*d, j*r*d, 0);
//				if(abs(i)>0 || abs(j)>0)continue;
//				System.out.println(objC.x+","+objC.y+","+objC.z+","+objR);
				for(int p=-cd; p<=cd; p++){
					for(int q=-cd; q<=cd; q++){
						V dirijpq = new V(p*t*r,q*t*r,H);
						V imijpq = add(ctij, dirijpq);
//						System.out.println(p+","+q+"  "+dirijpq.x+", "+dirijpq.y);
						
						V rej = sub(add(ctij, mult(dirijpq, objC.z/imijpq.z)), objC);
						if(abs(rej.x)<objR && abs(rej.y)<objR){
							g.setColor(cO);
							dr.flRect(imijpq.x, imijpq.y, r, r);
						}else{
							g.setColor(cB);
							dr.flRect(imijpq.x, imijpq.y, r, r);
						}
						
//						int sss = (i*d+p*t)*100000+(j*d+q*t);
////							ss.add(sss);
//							ss.add(new Double(imijpq.x).intValue()*10000000+new Double(imijpq.y).intValue());
					}
				}
			}
		}
//		System.out.println(ss.size());
//		Collections.sort(ss);
//		for(int i=0; i<ss.size()-1; i++){
////			if(ss.get(i)==ss.get(i+1)){
//				System.out.println(ss.get(i));
////			}
//		}
		
	}

	public Area getArea(int cx, int cy, int lx, int ly, V e){
		System.out.println("H="+H+",a="+a+",R="+r*d);
		System.out.println("E="+e.x+", "+e.z+", S="+obj.z+", a="+AT(e.x/e.z));
		System.out.println("S="+obj.z);
		System.out.println("e="+e.z/r/d+", s="+obj.z/r/d);

		Area a = g0.getArea(cx, cy, lx, ly, e);
//		a.intersect(gmd.getArea(cx, cy, lx, ly, e));
		
		int b = new Float(d*(e.z-gl.h)/l/(e.z-g0.h)).intValue();
		a.intersect(gl.getArea(cx, cy, lx*b, ly*b, e));
		
		b = new Float(d*(e.z-gn.h)/l/(e.z-g0.h)).intValue();
		a.intersect(gn.getArea(cx, cy, lx*b, ly*b, e));
		return a;
	}
}

class L{
	V o;
	V dir;
	
	L(V o, V dir){
		this.o = o;
		if(dir!=null){
			this.dir = dir.unit();
		}else{
			this.dir = null;
		}
	}
	
	public static int CROSS_NO = 0;
	public static int CROSS_BEFORE = 1;
	public static int CROSS_AFTER = 2;
	public int cross(LSeg s){
		if(s.c.equals(o)){
			return CROSS_NO;
		}
		
		double a = dir.x*(s.p1.y-o.y) - dir.y*(s.p1.x-o.x);
		double b = dir.x*(s.p2.y-o.y) - dir.y*(s.p2.x-o.x);
		if(a*b>=0){
			return CROSS_NO;
		}
		
		double R = (o.y-s.p1.y) * s.lx - (o.x-s.p1.x)*s.ly;
		return (s.ly*dir.x - s.lx*dir.y)*R>0 ? CROSS_AFTER : CROSS_BEFORE;
	}
	
	public boolean crossBefore(List<LSeg> pl){
		for(LSeg s : pl){
			if(CROSS_BEFORE == cross(s)){
				return true;
			}
		}
		return false;
	}

	public boolean crossAfter(List<LSeg> pl){
		for(LSeg s : pl){
			if(CROSS_AFTER == cross(s)){
				return true;
			}
		}
		return false;
	}

}

class LSeg{
	V p1; 
	V p2;
	V dir;	
	double len;
	double lx;
	double ly;
	
	V c;
	V dirN;
	
	LSeg(V p1, V p2){
		this.p1 = new V(p1);
		this.p2 = new V(p2);
		dir = sub(p2, p1).unit();

		len = sub(p2, p1).abs();
		lx = this.p2.x - this.p1.x;
		ly = this.p2.y - this.p1.y;
		
		calC();
		calNXY();
	}
	
	LSeg(V p1, V dir, double len){
		this.p1 = new V(p1);
		this.dir = dir.unit();
		this.len = len;
		this.p2 = add(p1, mult(this.dir, this.len));

		lx = this.p2.x - this.p1.x;
		ly = this.p2.y - this.p1.y;

		calC();
		calNXY();
	}
	
	V calC(){
		c = mult(add(p1, p2), 0.5);
		return c;
	}
	
	V calNXY(){
		dirN = new V(-dir.y, dir.x, dir.z);
		return dirN;
	}
}

class Ovl implements Surface{
//	x�/a� + y�/b� = 1
//  y=b(1-x�/a�)�
//  y'=-b�x / a�y
//  faxian xielv = a�y/b�x
	 
	V o;
	V df;
	double a;
	double b;
	double range;
	
	Ovl(V o, V df, double a, double b, double range){
		this.o = o;
		this.df = df.unit();
		this.a = a;
		this.b = b;
		this.range = range;
	}

	@Override
	public L out(L in, double n) {
		V dis = sub(in.o, o);
		V ratio = new V(1/a/a, 1/b/b, 1/a/a);
		
		double da = multD(in.dir, mult(in.dir, ratio));
		double db = multD(dis, mult(in.dir,ratio))*2;
		double dc = multD(dis, mult(dis,ratio)) - 1;
		
//		e�/a�  +  f�/b�
//		2((p-r)e/a�+(q-s)f/b�)
//		(p-r)�/a�  +  (q-s)�/b� -1
		
		double[] rt = defun(da, db, dc);
		if(rt == null){
			return null;
		}

		for(double l : rt){
			if(l>0){
				V pcr = add(mult(in.dir, l), in.o);
				V dfp = sub(pcr, o).unit();
				V xyz = sub(pcr, o);
			//  faxian xielv = a�y/b�x
				V fp = new V(xyz.x*b*b, xyz.y*a*a, 0);
				
				double ar = ac(multD(df, dfp));
				if(ar<=range){
					V dir = trm(fp, in.dir, n);
					return new L(pcr, dir);
				}
			}
		}
		
		return null;
	}

	@Override
	public void show(Drawer drawer) {
		drawer.drOval(o.x, o.y, a, b);
	}


}

class Params{
	double z, Z, h, r;
	double R;

	double A;
	double n = 1;
	
	V centre;
	V obj;
	

	public Params(double z, double Z, double h, double r, double n, double A){
		this.z = z;
		this.Z = Z;
		this.h = h;
		this.r = r;
		this.A = A;
		this.n = n;
		calR();
		centre = new V(z*T(A), 0, z);
		obj = new V((z+Z)*T(A), 0, z+Z);
	}
	
	public Params init(double z, double Z, double h, double r, double n, double A){
		this.z = z;
		this.Z = Z;
		this.h = h;
		this.r = r;
		this.A = A;
		this.n = n;
		calR();
		return this;
	}
	
	void calR(){
		R = h*t(as(1/n))+r;
	}
	
	public V tr(V v){
		double mXY = mXY(v);
		double xy = h * tan(as(mXY/mR(v)/n)) ;
		return new V(v.x * xy/mXY, v.y * xy/mXY, h);
	}

	public V atr(V v){
		double mXY = mXY(v);
		if(mXY>(R-r)){
			return null;
		}
		double xy = Z * tan(as(mXY/mR(v)*n)) ;
		return new V(v.x * xy/mXY, v.y * xy/mXY, Z);
	}

	public V border_0(double B){
		V p = new V(C(B)*r, S(B)*r, 0);
		V li = add(centre, p);
		V im = add(li, tr(li));
		V li2 = sub(im, centre);
		V obAb = atr(li2);		
		if(obAb == null){
			return null;
		}
		V ob = add(centre, obAb);

		return mult(ob, z/(z+Z));
	}
}

class PlDisc extends PlView{
	int resolution;
	int ua; 
	
	static int resoUnit = 1000;
	static double resoUnitD = new Double(resoUnit);

	public PlDisc(V o, V df, double step, int resolution){
		this.o = o;
		this.df = df.unit();
		this.step = step;
		this.resolution = resolution;
		this.ua = 180*resoUnit / resolution;
	}

	public double getDiscretA(double A){
		return (new Double( A*resoUnit + (A>0 ? 0.5 : -0.5)*ua).intValue())/ua*ua/resoUnitD;
	}
	
	public L out(L in, V c, double n) {
		double AXOut = getDiscretA(AT(in.dir.x/in.dir.z));
		double AYOut = getDiscretA(AT(in.dir.y/in.dir.z));
		
		L out = new L(c, new V(T(AXOut)*in.dir.z, T(AYOut)*in.dir.z, in.dir.z));
		return out;
	}
}

class PlDiscN extends PlView{
	int resolution;
	int ua; 
	
	static int resoUnit = 1000;
	static double resoUnitD = new Double(resoUnit);

	public PlDiscN(V o, V df, double step, int resolution){
		this.o = o;
		this.df = df.unit();
		this.step = step;
		this.resolution = resolution;
		this.ua = 180*resoUnit / resolution;
	}

	public double getDiscretA(double A){
		return (new Double( A*resoUnit + (A>0 ? 0.5 : -0.5)*ua).intValue())/ua*ua/resoUnitD;
	}
	
	public L out(L in, V c, double n) {
		double AXOut = getDiscretA(AT(in.dir.x/in.dir.z));
		double AYOut = getDiscretA(AT(in.dir.y/in.dir.z));
		
		L out = new L(c, new V(T(AXOut)*in.dir.z, T(AYOut)*in.dir.z, in.dir.z));
		return out;
	}
}

class PlEmpty extends PlView{
	public PlEmpty(V o, V df, double step){
		this.o = o;
		this.df = df.unit();
		this.step = step;
	}

	public L out(L in, V c, double n) {
		L out = new L(c, new V((in.dir.x/in.dir.z)*in.dir.z, (in.dir.y/in.dir.z)*in.dir.z, in.dir.z));
		return out;
	}
}

class PlGenerator{
	int segNum;
	double segLen;
	double aStep;
	double limitX;
	double limitY;
	double limitA;
	
	Double[] crtAs;	
	LSeg lsBegin;

	public PlGenerator(int segNum, double segLen, double aStep, double limitX, double limitY, double limitA){
		this.segNum = segNum;
		this.segLen = segLen;
		this.aStep = aStep;
		this.limitX = limitX;
		this.limitY = limitY;
		this.limitA = limitA;
		
		crtAs = new Double[segNum];
		lsBegin = new LSeg(new V(-segLen/2, 0, 0), new V(1, 0, 0), segLen);
	}
	
	public List<LSeg> getNextPl(){
		List<LSeg> pl = new ArrayList<LSeg>();
		pl.add(lsBegin);
		
		int iLast = 0;
		for(; iLast<segNum; iLast++){
			if(crtAs[iLast]==null){
				break;
			}else{
				pl.add(new LSeg(getLast(pl).p2, getDir(crtAs[iLast]), segLen));
			}
		}
		iLast = max(iLast -1, 0);
		
		int tour = 0;
		while(tour++<segNum*3){
			double preA = iLast==0? 0 : crtAs[iLast-1];
			
			// new
			if(crtAs[iLast] == null){
				crtAs[iLast] = max(-limitA, preA-60);
//				crtAs[iLast] = -limitA;
				pl.add(new LSeg(getLast(pl).p2, getDir(crtAs[iLast]), segLen));
			}else{

				double a = crtAs[iLast]+aStep;
				if(a>limitA || abs(preA-a)>60){
					// <--
					crtAs[iLast] = null;
					removeLast(pl);
					if(iLast == 0){
						return null;
					}
					iLast--;
					continue;
				}else{
					setLast(pl, new LSeg(getLast(pl).p1, getDir(a), segLen));
					crtAs[iLast] = a;
				}
			}

			if(isEndable(pl)){
				return pl;
			}else{
				iLast++;
			}
		}
	
		System.out.println("error");
		return null;
	}

	public List<LSeg> getNextSymPl(){
		List<LSeg> pl = getNextPl();
		if(pl == null){
			return null;
		}
		int l = pl.size();
		V sym = new V(-1, 1, 1);
		for(int i=1; i<l; i++){
			LSeg si = pl.get(i);
			pl.add(new LSeg(mult(si.p1, sym), mult(si.p2, sym)));
		}
		return pl;
	}

	
	public V getDir(double a){
		return new V(C(a), S(a), 0).unit();
	}
	
	public boolean isEndable(List<LSeg> poly){
		if(poly.size()>segNum){
			return true;
		}else{
			LSeg last = getLast(poly);
			return poly.size()>= segNum || abs(last.p2.x)>limitX  || abs(last.p2.y)>limitY ;
		}
	}
	
	public LSeg getLast(List<LSeg> poly){
		return poly.get(poly.size()-1);
	}

	public void removeLast(List<LSeg> poly){
		poly.remove(poly.size()-1);
	}

	public void setLast(List<LSeg> poly, LSeg lSeg){
		poly.set(poly.size()-1, lSeg);
	}
}

// z=0;
abstract class PlView implements Surface{
	V o;
	V df;
	double step;

	@Override
	public L out(L in, double n) {
		return null;
	}

	@Override
	public void show(Drawer drawer) {
	}

	abstract public L out(L in, V c, double n);
}

class RDM extends Random{
	public static Random R = new Random();
	public static int nextI(int i, int j){
		if(i > j){
			i = i+j;
			j = i-j;
			i = i-j;
		}
		return R.nextInt(j-i) +i;
	}
	
	public static double nextIC(int c, int r){
		return R.nextInt(2*r)-r+c;
	}

	public static double nextD(double i, double j){
		return nextDC((i+j)/2, (i-j)/2);
	}

	public static double nextDC(double c, double r){
		return R.nextDouble()*2*r-r+c;
	}

}

class Sph implements Surface{
	V o;
	V df;
	double r;
	double range;
	
	Sph(V o, V df, double r, double a){
		this.o = o;
		this.df = df.unit();
		this.r = r;
		this.range = a;
	}

	@Override
	public L out(L in, double n) {
		V dis = sub(in.o, o);
		double a = multD(in.dir, in.dir);
		double b = multD(in.dir, dis)*2;
		double c = multD(dis, dis) - pow(r, 2);
		
		double[] rt = defun(a, b, c);
		if(rt == null){
			return null;
		}

		for(double l : rt){
			if(l>0){
				V pcr = add(mult(in.dir, l), in.o);
				V dfl = sub(pcr, o).unit();
				double ar = ac(multD(dfl, df));
				if(ar<=range){
					V dir = trm(dfl, in.dir, n);
					return new L(pcr, dir);
				}
			}
		}
		
		return null;
	}

	@Override
	public void show(Drawer drawer) {
		drawer.drCircle(o.x, o.y, r);
	}
}

interface Surface{
	public L out(L in, double n);
	public void show(Drawer drawer);
}

class U{
	public static double toa(double arc){
		return PI*arc/180d;
	}

	public static double toA(double arc){
		return arc*180d/PI;
	}

	public static double c(double arc){
		return cos(arc);
	}
	
	public static double s(double arc){
		return sin(arc);
	}
	
	public static double t(double arc){
		return tan(arc);
	}
	
	public static double ac(double v){
		return acos(v);
	}
	
	public static double as(double v){
		return asin(v);
	}
	
	public static double at(double v){
		return atan(v);
	}

	
	public static double C(double arc){
		return c(toa(arc));
	}
	
	public static double S(double arc){
		return s(toa(arc));
	}
	
	public static double T(double arc){
		return t(toa(arc));
	}
	
	public static double AC(double v){
		return toA(acos(v));
	}
	
	public static double AS(double v){
		return toA(asin(v));
	}
	
	public static double AT(double v){
		return toA(atan(v));
	}
	
	public static V trm(V f, V in, double n){
		V uf = f.unit();
		V uIn = in.unit();
		double c = multD(uf, uIn);
		double a = ac(c);
		double s2 = s(a)/n;
		if(s2 >1){
			return null;
		}
		
		double c2 = abs(c(as(s2)))*(c>0 ? 1 : -1);
		V x = is0(s2)? new V(0,0,0) : sub(uIn, mult(uf, c)).unit();

		return add(mult(uf, c2), mult(x, s2)).unit();
	}
	
	public static boolean is0(double d){
		return abs(d)<0.000001;
	}
	
	public static double[] defun(double a, double b, double c){
		double d = b*b-4*a*c;
		if(d<0){
			return null;
		}else if(is0(d)){
			return new double[]{-b/2/a};
		}else{
			double rd = pow(d, 0.5);
			double r1 = (rd-b)/2/a;
			double r2 = (-rd-b)/2/a;
			return new double[]{min(r1, r2), max(r1, r2)};
		}
	}
}

class V{
	double x;
	double y;
	double z;
	public V(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public V unit(){
		double r = mR(this);
		return new V(x/r, y/r, z/r);
	}
	
	public V(V v){
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public boolean equals(Object o){
		if(o instanceof V){
			V v = (V)o;
			return v.x == x && v.y == y && v.z == z;
		}
		
		return false;
	}
	
	public String toString(){
		return "("+x+","+y+","+z+")";
	}
	
	public static V N(V v){
		return new V(-v.x, -v.y, -v.z);
	}

	public static V add(V v1, V ... vs){
		V res = new V(v1);
		
		for(V v:vs){
			res.x += v.x;
			res.y += v.y;
			res.z += v.z;
		}
		
		return res;
	}

	public static V sub(V v1, V v2){
		return add(v1, N(v2));
	}
	
	public static V mult(V v, double d){
		return new V(v.x * d, v.y * d, v.z * d);
	}

	public static V mult(V v1, double d1, double d2, double d3){
		return new V(v1.x * d1, v1.y * d2, v1.z * d3);
	}

	public static V mult(V v1, V v2){
		return mult(v1, v2.x, v2.y, v2.z);
	}
	
	public static V multV(V v1, V v2){
		return new V(v1.y*v2.z-v2.y*v1.z, v1.z*v2.x-v2.z*v1.x, v1.x*v2.y-v2.x*v1.y);
	}

	public static V multVU(V v1, V v2){
		return multV(v1, v2).unit();
	}
	
	public static double multD(V v1, V v2){
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}

	public static double multDU(V v1, V v2){
		return multD(v1.unit(), v2.unit());
	}

	public static double mR(V v){
		return pow(v.x*v.x + v.y*v.y + v.z*v.z, 0.5);
	}

	public static double mXY(V v){
		return pow(v.x*v.x + v.y*v.y, 0.5);
	}

	public static double moXZ(V v){
		return pow(v.x*v.x + v.z*v.z, 0.5);
	}

	public static double moYZ(V v){
		return pow(v.y*v.y + v.z*v.z, 0.5);
	}
	
	public static double a(V v1, V v2){
		return ac(multDU(v1, v2));
	}

	public double abs() {
		return pow(multD(this, this), 0.5);
	}
}