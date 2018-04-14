package hrir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import hrir.irdatas.*;
import virtualpresentation.VirtualDimension;
import virtualpresentation.VirtualPoint;

public class HRIRDoubleArrays {
	static public IRArrays[][] datas = new IRArrays[19][17];
	
	static VirtualDimension dimension = new VirtualDimension(19, 17);
	
	static{
		datas[0][0] = new IRArray_0_0();
		datas[0][1] = new IRArray_0_1();
		datas[0][2] = new IRArray_0_2();
		datas[0][3] = new IRArray_0_3();
		datas[0][4] = new IRArray_0_4();
		datas[0][5] = new IRArray_0_5();
		datas[0][6] = new IRArray_0_6();
		datas[0][7] = new IRArray_0_7();
		datas[0][8] = new IRArray_0_8();
		datas[0][9] = new IRArray_0_9();
		datas[0][10] = new IRArray_0_10();
		datas[0][11] = new IRArray_0_11();
		datas[0][12] = new IRArray_0_12();
		datas[0][13] = new IRArray_0_13();
		datas[0][14] = new IRArray_0_14();
		datas[0][15] = new IRArray_0_15();
		datas[0][16] = new IRArray_0_16();
		datas[1][0] = new IRArray_1_0();
		datas[1][1] = new IRArray_1_1();
		datas[1][2] = new IRArray_1_2();
		datas[1][3] = new IRArray_1_3();
		datas[1][4] = new IRArray_1_4();
		datas[1][5] = new IRArray_1_5();
		datas[1][6] = new IRArray_1_6();
		datas[1][7] = new IRArray_1_7();
		datas[1][8] = new IRArray_1_8();
		datas[1][9] = new IRArray_1_9();
		datas[1][10] = new IRArray_1_10();
		datas[1][11] = new IRArray_1_11();
		datas[1][12] = new IRArray_1_12();
		datas[1][13] = new IRArray_1_13();
		datas[1][14] = new IRArray_1_14();
		datas[1][15] = new IRArray_1_15();
		datas[1][16] = new IRArray_1_16();
		datas[2][0] = new IRArray_2_0();
		datas[2][1] = new IRArray_2_1();
		datas[2][2] = new IRArray_2_2();
		datas[2][3] = new IRArray_2_3();
		datas[2][4] = new IRArray_2_4();
		datas[2][5] = new IRArray_2_5();
		datas[2][6] = new IRArray_2_6();
		datas[2][7] = new IRArray_2_7();
		datas[2][8] = new IRArray_2_8();
		datas[2][9] = new IRArray_2_9();
		datas[2][10] = new IRArray_2_10();
		datas[2][11] = new IRArray_2_11();
		datas[2][12] = new IRArray_2_12();
		datas[2][13] = new IRArray_2_13();
		datas[2][14] = new IRArray_2_14();
		datas[2][15] = new IRArray_2_15();
		datas[2][16] = new IRArray_2_16();
		datas[3][0] = new IRArray_3_0();
		datas[3][1] = new IRArray_3_1();
		datas[3][2] = new IRArray_3_2();
		datas[3][3] = new IRArray_3_3();
		datas[3][4] = new IRArray_3_4();
		datas[3][5] = new IRArray_3_5();
		datas[3][6] = new IRArray_3_6();
		datas[3][7] = new IRArray_3_7();
		datas[3][8] = new IRArray_3_8();
		datas[3][9] = new IRArray_3_9();
		datas[3][10] = new IRArray_3_10();
		datas[3][11] = new IRArray_3_11();
		datas[3][12] = new IRArray_3_12();
		datas[3][13] = new IRArray_3_13();
		datas[3][14] = new IRArray_3_14();
		datas[3][15] = new IRArray_3_15();
		datas[3][16] = new IRArray_3_16();
		datas[4][0] = new IRArray_4_0();
		datas[4][1] = new IRArray_4_1();
		datas[4][2] = new IRArray_4_2();
		datas[4][3] = new IRArray_4_3();
		datas[4][4] = new IRArray_4_4();
		datas[4][5] = new IRArray_4_5();
		datas[4][6] = new IRArray_4_6();
		datas[4][7] = new IRArray_4_7();
		datas[4][8] = new IRArray_4_8();
		datas[4][9] = new IRArray_4_9();
		datas[4][10] = new IRArray_4_10();
		datas[4][11] = new IRArray_4_11();
		datas[4][12] = new IRArray_4_12();
		datas[4][13] = new IRArray_4_13();
		datas[4][14] = new IRArray_4_14();
		datas[4][15] = new IRArray_4_15();
		datas[4][16] = new IRArray_4_16();
		datas[5][0] = new IRArray_5_0();
		datas[5][1] = new IRArray_5_1();
		datas[5][2] = new IRArray_5_2();
		datas[5][3] = new IRArray_5_3();
		datas[5][4] = new IRArray_5_4();
		datas[5][5] = new IRArray_5_5();
		datas[5][6] = new IRArray_5_6();
		datas[5][7] = new IRArray_5_7();
		datas[5][8] = new IRArray_5_8();
		datas[5][9] = new IRArray_5_9();
		datas[5][10] = new IRArray_5_10();
		datas[5][11] = new IRArray_5_11();
		datas[5][12] = new IRArray_5_12();
		datas[5][13] = new IRArray_5_13();
		datas[5][14] = new IRArray_5_14();
		datas[5][15] = new IRArray_5_15();
		datas[5][16] = new IRArray_5_16();
		datas[6][0] = new IRArray_6_0();
		datas[6][1] = new IRArray_6_1();
		datas[6][2] = new IRArray_6_2();
		datas[6][3] = new IRArray_6_3();
		datas[6][4] = new IRArray_6_4();
		datas[6][5] = new IRArray_6_5();
		datas[6][6] = new IRArray_6_6();
		datas[6][7] = new IRArray_6_7();
		datas[6][8] = new IRArray_6_8();
		datas[6][9] = new IRArray_6_9();
		datas[6][10] = new IRArray_6_10();
		datas[6][11] = new IRArray_6_11();
		datas[6][12] = new IRArray_6_12();
		datas[6][13] = new IRArray_6_13();
		datas[6][14] = new IRArray_6_14();
		datas[6][15] = new IRArray_6_15();
		datas[6][16] = new IRArray_6_16();
		datas[7][0] = new IRArray_7_0();
		datas[7][1] = new IRArray_7_1();
		datas[7][2] = new IRArray_7_2();
		datas[7][3] = new IRArray_7_3();
		datas[7][4] = new IRArray_7_4();
		datas[7][5] = new IRArray_7_5();
		datas[7][6] = new IRArray_7_6();
		datas[7][7] = new IRArray_7_7();
		datas[7][8] = new IRArray_7_8();
		datas[7][9] = new IRArray_7_9();
		datas[7][10] = new IRArray_7_10();
		datas[7][11] = new IRArray_7_11();
		datas[7][12] = new IRArray_7_12();
		datas[7][13] = new IRArray_7_13();
		datas[7][14] = new IRArray_7_14();
		datas[7][15] = new IRArray_7_15();
		datas[7][16] = new IRArray_7_16();
		datas[8][0] = new IRArray_8_0();
		datas[8][1] = new IRArray_8_1();
		datas[8][2] = new IRArray_8_2();
		datas[8][3] = new IRArray_8_3();
		datas[8][4] = new IRArray_8_4();
		datas[8][5] = new IRArray_8_5();
		datas[8][6] = new IRArray_8_6();
		datas[8][7] = new IRArray_8_7();
		datas[8][8] = new IRArray_8_8();
		datas[8][9] = new IRArray_8_9();
		datas[8][10] = new IRArray_8_10();
		datas[8][11] = new IRArray_8_11();
		datas[8][12] = new IRArray_8_12();
		datas[8][13] = new IRArray_8_13();
		datas[8][14] = new IRArray_8_14();
		datas[8][15] = new IRArray_8_15();
		datas[8][16] = new IRArray_8_16();
		datas[9][0] = new IRArray_9_0();
		datas[9][1] = new IRArray_9_1();
		datas[9][2] = new IRArray_9_2();
		datas[9][3] = new IRArray_9_3();
		datas[9][4] = new IRArray_9_4();
		datas[9][5] = new IRArray_9_5();
		datas[9][6] = new IRArray_9_6();
		datas[9][7] = new IRArray_9_7();
		datas[9][8] = new IRArray_9_8();
		datas[9][9] = new IRArray_9_9();
		datas[9][10] = new IRArray_9_10();
		datas[9][11] = new IRArray_9_11();
		datas[9][12] = new IRArray_9_12();
		datas[9][13] = new IRArray_9_13();
		datas[9][14] = new IRArray_9_14();
		datas[9][15] = new IRArray_9_15();
		datas[9][16] = new IRArray_9_16();
		datas[10][0] = new IRArray_10_0();
		datas[10][1] = new IRArray_10_1();
		datas[10][2] = new IRArray_10_2();
		datas[10][3] = new IRArray_10_3();
		datas[10][4] = new IRArray_10_4();
		datas[10][5] = new IRArray_10_5();
		datas[10][6] = new IRArray_10_6();
		datas[10][7] = new IRArray_10_7();
		datas[10][8] = new IRArray_10_8();
		datas[10][9] = new IRArray_10_9();
		datas[10][10] = new IRArray_10_10();
		datas[10][11] = new IRArray_10_11();
		datas[10][12] = new IRArray_10_12();
		datas[10][13] = new IRArray_10_13();
		datas[10][14] = new IRArray_10_14();
		datas[10][15] = new IRArray_10_15();
		datas[10][16] = new IRArray_10_16();
		datas[11][0] = new IRArray_11_0();
		datas[11][1] = new IRArray_11_1();
		datas[11][2] = new IRArray_11_2();
		datas[11][3] = new IRArray_11_3();
		datas[11][4] = new IRArray_11_4();
		datas[11][5] = new IRArray_11_5();
		datas[11][6] = new IRArray_11_6();
		datas[11][7] = new IRArray_11_7();
		datas[11][8] = new IRArray_11_8();
		datas[11][9] = new IRArray_11_9();
		datas[11][10] = new IRArray_11_10();
		datas[11][11] = new IRArray_11_11();
		datas[11][12] = new IRArray_11_12();
		datas[11][13] = new IRArray_11_13();
		datas[11][14] = new IRArray_11_14();
		datas[11][15] = new IRArray_11_15();
		datas[11][16] = new IRArray_11_16();
		datas[12][0] = new IRArray_12_0();
		datas[12][1] = new IRArray_12_1();
		datas[12][2] = new IRArray_12_2();
		datas[12][3] = new IRArray_12_3();
		datas[12][4] = new IRArray_12_4();
		datas[12][5] = new IRArray_12_5();
		datas[12][6] = new IRArray_12_6();
		datas[12][7] = new IRArray_12_7();
		datas[12][8] = new IRArray_12_8();
		datas[12][9] = new IRArray_12_9();
		datas[12][10] = new IRArray_12_10();
		datas[12][11] = new IRArray_12_11();
		datas[12][12] = new IRArray_12_12();
		datas[12][13] = new IRArray_12_13();
		datas[12][14] = new IRArray_12_14();
		datas[12][15] = new IRArray_12_15();
		datas[12][16] = new IRArray_12_16();
		datas[13][0] = new IRArray_13_0();
		datas[13][1] = new IRArray_13_1();
		datas[13][2] = new IRArray_13_2();
		datas[13][3] = new IRArray_13_3();
		datas[13][4] = new IRArray_13_4();
		datas[13][5] = new IRArray_13_5();
		datas[13][6] = new IRArray_13_6();
		datas[13][7] = new IRArray_13_7();
		datas[13][8] = new IRArray_13_8();
		datas[13][9] = new IRArray_13_9();
		datas[13][10] = new IRArray_13_10();
		datas[13][11] = new IRArray_13_11();
		datas[13][12] = new IRArray_13_12();
		datas[13][13] = new IRArray_13_13();
		datas[13][14] = new IRArray_13_14();
		datas[13][15] = new IRArray_13_15();
		datas[13][16] = new IRArray_13_16();
		datas[14][0] = new IRArray_14_0();
		datas[14][1] = new IRArray_14_1();
		datas[14][2] = new IRArray_14_2();
		datas[14][3] = new IRArray_14_3();
		datas[14][4] = new IRArray_14_4();
		datas[14][5] = new IRArray_14_5();
		datas[14][6] = new IRArray_14_6();
		datas[14][7] = new IRArray_14_7();
		datas[14][8] = new IRArray_14_8();
		datas[14][9] = new IRArray_14_9();
		datas[14][10] = new IRArray_14_10();
		datas[14][11] = new IRArray_14_11();
		datas[14][12] = new IRArray_14_12();
		datas[14][13] = new IRArray_14_13();
		datas[14][14] = new IRArray_14_14();
		datas[14][15] = new IRArray_14_15();
		datas[14][16] = new IRArray_14_16();
		datas[15][0] = new IRArray_15_0();
		datas[15][1] = new IRArray_15_1();
		datas[15][2] = new IRArray_15_2();
		datas[15][3] = new IRArray_15_3();
		datas[15][4] = new IRArray_15_4();
		datas[15][5] = new IRArray_15_5();
		datas[15][6] = new IRArray_15_6();
		datas[15][7] = new IRArray_15_7();
		datas[15][8] = new IRArray_15_8();
		datas[15][9] = new IRArray_15_9();
		datas[15][10] = new IRArray_15_10();
		datas[15][11] = new IRArray_15_11();
		datas[15][12] = new IRArray_15_12();
		datas[15][13] = new IRArray_15_13();
		datas[15][14] = new IRArray_15_14();
		datas[15][15] = new IRArray_15_15();
		datas[15][16] = new IRArray_15_16();
		datas[16][0] = new IRArray_16_0();
		datas[16][1] = new IRArray_16_1();
		datas[16][2] = new IRArray_16_2();
		datas[16][3] = new IRArray_16_3();
		datas[16][4] = new IRArray_16_4();
		datas[16][5] = new IRArray_16_5();
		datas[16][6] = new IRArray_16_6();
		datas[16][7] = new IRArray_16_7();
		datas[16][8] = new IRArray_16_8();
		datas[16][9] = new IRArray_16_9();
		datas[16][10] = new IRArray_16_10();
		datas[16][11] = new IRArray_16_11();
		datas[16][12] = new IRArray_16_12();
		datas[16][13] = new IRArray_16_13();
		datas[16][14] = new IRArray_16_14();
		datas[16][15] = new IRArray_16_15();
		datas[16][16] = new IRArray_16_16();
		datas[17][0] = new IRArray_17_0();
		datas[17][1] = new IRArray_17_1();
		datas[17][2] = new IRArray_17_2();
		datas[17][3] = new IRArray_17_3();
		datas[17][4] = new IRArray_17_4();
		datas[17][5] = new IRArray_17_5();
		datas[17][6] = new IRArray_17_6();
		datas[17][7] = new IRArray_17_7();
		datas[17][8] = new IRArray_17_8();
		datas[17][9] = new IRArray_17_9();
		datas[17][10] = new IRArray_17_10();
		datas[17][11] = new IRArray_17_11();
		datas[17][12] = new IRArray_17_12();
		datas[17][13] = new IRArray_17_13();
		datas[17][14] = new IRArray_17_14();
		datas[17][15] = new IRArray_17_15();
		datas[17][16] = new IRArray_17_16();
		datas[18][0] = new IRArray_18_0();
		datas[18][1] = new IRArray_18_1();
		datas[18][2] = new IRArray_18_2();
		datas[18][3] = new IRArray_18_3();
		datas[18][4] = new IRArray_18_4();
		datas[18][5] = new IRArray_18_5();
		datas[18][6] = new IRArray_18_6();
		datas[18][7] = new IRArray_18_7();
		datas[18][8] = new IRArray_18_8();
		datas[18][9] = new IRArray_18_9();
		datas[18][10] = new IRArray_18_10();
		datas[18][11] = new IRArray_18_11();
		datas[18][12] = new IRArray_18_12();
		datas[18][13] = new IRArray_18_13();
		datas[18][14] = new IRArray_18_14();
		datas[18][15] = new IRArray_18_15();
		datas[18][16] = new IRArray_18_16();
	}
	
	public static class IRArrays{
		public double[][] irData;
	}
	
	public static double[][] getImpulse(int x, int y){
		return datas[dimension.toLT0PosX(x)][dimension.toLT0PosY(y)].irData;
	}
	
	public static double[][] getImpulse(VirtualPoint pXY) {
		VirtualDimension dimImage = pXY.getImage().getDimension();
		
		Double scaleX = dimension.getRx()/(double)dimImage.getRx();
		Double scaleY = dimension.getRy()/(double)dimImage.getRy();
		
		if(scaleX>1)scaleX = (double)(scaleX.intValue()); 
		if(scaleY>1)scaleY = (double)(scaleY.intValue());
		
		int x = new Double(pXY.getX() * scaleX).intValue();
		int y = new Double(pXY.getY() * scaleY).intValue();
	
		return getImpulse(x, y);
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		printInitArr();
	}

	public static void download()throws FileNotFoundException, IOException {
		int[] xs = {-45, -40, -35, -30, -25, -20, -15, -10, -5, 0, 5, 10, 15, 20, 25, 30, 35, 40, 45};
		String[] ys = {"45", "39.375", "33.75", "28.125", "22.5", "16.875", "11.25", "5.625", "0", "-5.625", "-11.25", "-16.875", "-22.5", "-28.125", "-33.75", "-39.375", "-45"};
		
		for(int i=0; i<xs.length; i++){
			for(int j=0; j<ys.length; j++){
				URL url = new URL("http://earlab.bu.edu/CIPIC/Subject_003_"+xs[i]+"_"+ys[j]+".txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				File f = new File("/home/chen/searchprojets/vo/HRIR_txts/texts/f_"+i+"_"+j+".txt");
				f.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				String l = null;
				while((l=reader.readLine()) != null ){
					writer.write(l+"\n");
				}
				writer.close();
			}
		}
				

	}

	public static void printArrays() throws FileNotFoundException, IOException {
		
		for(int i=0; i<19; i++){


			for(int j=0; j<17; j++){
				File f = new File("/home/chen/searchprojets/vo/HRIR_txts/texts/f_"+i+"_"+j+".txt");
				BufferedReader reader = new BufferedReader(new FileReader(f));
				String l = null;
				int n = 0;
				
				File arrayF = new File("/home/chen/searchprojets/vo/HRIR_txts/javas/IRArray_"+i+"_"+j+".java");
				arrayF.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(arrayF));

				writer.write("package hrir.irdatas;\n");
				writer.write("import hrir.HRIRDoubleArrays.IRArrays;\n");
				writer.write("public class IRArray_"+i+"_"+j+" extends IRArrays {\n");
				writer.write("\tpublic IRArray_"+i+"_"+j+"(){\n");

				writer.write("\t\tcolData = new double[][]{\n");
				while((l=reader.readLine()) != null ){
					n++;
					if(n<=5){
						continue;
					}
					
					boolean isLastLine = n==205;
					
					writer.write("\t\t\t{"+l.replace("\t", ",")+"}"+(isLastLine?"":",")+"\n");
					
				}
				writer.write("\t\t};\n");
				writer.write("\t}\n");
				writer.write("}\n");
				writer.close();

				reader.close();
			}
		}

	}

	
	public static void printInitArr(){
		for(int i=0; i<19; i++){
			for(int j=0; j<17; j++){
				System.out.println("datas["+i+"]["+j+"] = new IRArray_"+i+"_"+j+"();");
			}
		}
	}

}
