package visionaudio.outils.format;

import javax.sound.sampled.AudioFormat;

public class DataTransformer {
	AudioFormat outputFormat;
	AudioFormat inputFormat;
	Encoder inputEncoder;
	Encoder outputEncoder;
	DataSeter inputSeter;
	DataSeter outputSeter;
	
	int inputSimpleByteSize;
	int outputSimpleByteSize;
	
	public DataTransformer(AudioFormat inputFormat, AudioFormat outputFormat) {
		setInputFormat(inputFormat);
		setOutputFormat(outputFormat);
		
	}

	public int getInputSimpleByteSize() {
		return inputSimpleByteSize;
	}

	public int getOutputSimpleByteSize() {
		return outputSimpleByteSize;
	}

	public AudioFormat getOutputFormat() {
		return outputFormat;
	}

	public AudioFormat getInputFormat() {
		return inputFormat;
	}



	public static Encoder getEncoder(AudioFormat audioFormat){
		if(audioFormat.isBigEndian()){
			return Encoder.ENCODER_BIG_ENDIAN;
		}else{
			return Encoder.ENCODER_LITTLE_ENDIAN;
		}
	}
	
	public void setInputFormat(AudioFormat inputFormat){
		this.inputFormat = inputFormat;
		if(this.inputFormat!=null){
			inputSimpleByteSize = this.inputFormat.getSampleSizeInBits()/8;
			inputEncoder = getEncoder(this.inputFormat);			
			inputSeter = DataSeter.getDataSeter(this.inputFormat, inputEncoder, inputSimpleByteSize);
		}
	}
	
	public void setOutputFormat(AudioFormat outputFormat){
		this.outputFormat = getWaveOutputFormat(outputFormat);
		if(this.outputFormat!=null){
			outputSimpleByteSize = this.outputFormat.getSampleSizeInBits()/8;
			outputEncoder = getEncoder(this.outputFormat);
			outputSeter = DataSeter.getDataSeter(this.outputFormat, outputEncoder, outputSimpleByteSize);
		}
	}
	
	public static AudioFormat getWaveOutputFormat(AudioFormat format) {
		if(format.getSampleSizeInBits()<=8){
			return new AudioFormat(format.getSampleRate(), format.getSampleSizeInBits(), format.getChannels(), false, false);
		}else{
			return new AudioFormat(format.getSampleRate(), format.getSampleSizeInBits(), format.getChannels(), true, false);
		}
		
	}

	public int setBytes(int value, byte[]bytes, int offset){
		return outputSeter.setBytes(value, bytes, offset);
	}	

	public int setBytes(double value, byte[]bytes, int offset){
		return outputSeter.setBytes(value, bytes, offset);
	}	

	public int getIntValue(byte[]bytes, int offset){
		return inputSeter.getIntValue(bytes, offset);
	}	

	public double getDoubleValue(byte[]bytes, int offset){
		return inputSeter.getDoubleValue(bytes, offset);
	}	

	public byte[] getOutputBytes(double[][] datas) {
		byte[] bytes = new byte[datas.length*outputFormat.getChannels()*outputSimpleByteSize];
		int byteOffset = 0;
		int channel = outputFormat.getChannels();
		if(datas[0].length>=channel){
			for(double[]data:datas){
				for(int i=0; i<channel; i++){
					byteOffset += setBytes(data[i], bytes, byteOffset);
				}
			}
		}else if(datas[0].length == 1){
			for(double[]data:datas){
				for(int i=0; i<channel; i++){
					byteOffset += setBytes(data[0], bytes, byteOffset);
				}
			}
		}else{
			for(double[]data:datas){
				int i=0;
				for(; i<data.length; i++){
					byteOffset += setBytes(data[0], bytes, byteOffset);
				}
				for(;i<channel; i++){
					byteOffset += setBytes(0, bytes, byteOffset);
				}
			}
		}
		
		
		
		return bytes;

	}
	
	public double[][] getinputDoubles(byte[]bytes, int frameOffset, int frameLength){
		int channel = inputFormat.getChannels();
		if(channel==0){
			return null;
		}
		
		int frameByteSize = channel*inputSimpleByteSize;
		int inputTotalFrame = bytes.length/frameByteSize;
		if(inputTotalFrame<500){
			return null;
		}

		inputTotalFrame = Math.min(inputTotalFrame, (int)inputFormat.getSampleRate());
		frameOffset = frameOffset<inputTotalFrame? frameOffset : (frameOffset-200)%(inputTotalFrame-200)+200;
		
		double[][] datas = new double[frameLength][channel];		
		for(int i=0; i<frameLength; i++){
			int byteOffset = frameOffset*frameByteSize;
			for(int j=0; j<channel; j++){
				datas[i][j] = getDoubleValue(bytes, byteOffset);
				byteOffset += inputSimpleByteSize;
			}
			
			frameOffset++;
			if(frameOffset>=inputTotalFrame){
				frameOffset = 200;
			}
		}
		
		return datas;
	}
	
	public static void main(String[] args){
		//System.out.println(123*256+102);
		byte[] bytes = new byte[10];

		AudioFormat outputFormat = new AudioFormat(44100, 16, 1, true, true);
		DataTransformer dtf = new DataTransformer(outputFormat, outputFormat); 
		
		int[] simples = {-32769, -32768, -32767, -1, 0, 1, 32767, 32768, 65534, 65535, 65536, 1234, -1234 };
		for(int s=0; s<simples.length; s++){
			int offset = 2;
			int value = simples[s];
			System.out.println("\n"+value);
			System.out.printf("%02X\n", value);
			
			dtf.setBytes(value, bytes, offset);

			for(int i=offset; i<offset+dtf.outputSimpleByteSize; i++){
				System.out.printf("%02X", bytes[i]);
			}

			value = dtf.getIntValue(bytes, offset);

			System.out.println("\n"+value);
		}
		


	}


}
