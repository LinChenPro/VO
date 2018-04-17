package output.gui;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JFrame;

import output.TestLancer;

public class ShowWindow {
//	static int w = 1900;
//	static int h = 300;

//	static int w = 1900;
//	static int h = 1000;
	static int w = 1200;
	static int h = 600;

	static Canvas cvs;
	public static void main(String[] args) {

//		System.out.println(AS(1/1.33));
//		System.out.println(AS(1/1.4));
//		System.out.println(AS(1/1.5));
//
//		System.out.println(AS(S(72)/1.5));
//		if(true)return;
		
		JFrame jf = new JFrame();
		jf.setSize(w+15, h+40);
		jf.setLayout(null);
		jf.setLocation(0, 0);
		
		cvs = new Canvas(){
			public void paint(Graphics g){
//				g.setColor(Color.white);
//				g.fillRect(0, 0, w, h);
				Drawer dr = new TestLancer(w, h, g);
				dr.show();
			}
		};
		cvs.setSize(w, h);
		cvs.setLocation(0, 0);
		jf.add(cvs);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread th = new Thread(){
			public void run(){
				for(int i=0; i<10000000; i++){
					long t = System.currentTimeMillis();
					cvs.repaint();
					try {
						t = System.currentTimeMillis() - t;
						sleep(Math.max(Drawer.scdImgSleep-t, 1));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		if(Drawer.threadOpen){
			th.start();
		}
		
	}


}

