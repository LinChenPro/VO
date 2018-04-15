package mediaformat.outils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat.Type;

import mediaformat.outils.format.Encoder;

public class WaveFileWriter {
	AudioFormat format;
	File file;
	FileOutputStream stream;
	
	public WaveFileWriter(AudioFormat format, File file) {
		this.format = format;
		this.file = file;
	}

	public void open() {
		byte[] byteDatas = new byte[0];
		ByteArrayInputStream bais = new ByteArrayInputStream(byteDatas);
		AudioInputStream outputAIS = new AudioInputStream(bais, format, 0);

		try {
			int nWrittenBytes = AudioSystem.write(outputAIS, Type.WAVE, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			stream = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public void write(byte[] datas) {
		try {
			stream.write(datas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void close() {
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long trunkSize = file.length()-8;
		long trunk2Size = trunkSize - 36;
		
		try {
			RandomAccessFile rfile = new RandomAccessFile(file, "rw");
			byte[] bytes = new byte[4];
			
			Encoder.ENCODER_LITTLE_ENDIAN.setBytes((int)trunkSize, bytes, 0, 4);
			rfile.seek(4);
			rfile.write(bytes);
			
			Encoder.ENCODER_LITTLE_ENDIAN.setBytes((int)trunk2Size, bytes, 0, 4);
			rfile.seek(40);
			rfile.write(bytes);
			
			rfile.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
		File file = new File("resources/testOutput.wav");

		WaveFileWriter writer = new WaveFileWriter(format, file);
		writer.open();
		
		writer.write(new byte[]{(byte)0xAA, (byte)0xBB});
		
		writer.close();
	}

}
