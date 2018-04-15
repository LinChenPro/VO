package mediaformat.outils.format;

import javax.sound.sampled.AudioFormat;

public abstract class DataSeter{
	int minValue;
	int maxValue;
	int amplitude;
	double amplitude_D;
	int simpleByteSize;	
	Encoder encoder;
	
	public DataSeter(Encoder encoder, int simpleByteSize){
		this.simpleByteSize = simpleByteSize;
		this.encoder = encoder;
		amplitude_D = Math.pow(256, simpleByteSize)/2;
		amplitude = new Double(amplitude_D).intValue();
	}
	
	protected int getValuableInt(int i){
		return Math.min(Math.max(i, minValue), maxValue);
	}
	public int setBytes(double value, byte[]bytes, int offset){
		return setBytes(doubleToInt(value), bytes, offset);
	}

	public int setBytes(int value, byte[]bytes, int offset){
		value = getValuableInt(value);
		return encoder.setBytes(value, bytes, offset, simpleByteSize);
	}

	public Double getDoubleValue(byte[]bytes, int offset){
		Integer value = getIntValue(bytes, offset);
		if(value==null){
			return null;
		}
		
		return intToDouble(value);
	}

	public static DataSeter getDataSeter(AudioFormat audioFormat, Encoder encoder, int simpleByteSize) {
		if(audioFormat.getEncoding() == AudioFormat.Encoding.PCM_UNSIGNED){
			return new UnsignedDataSeter(encoder, simpleByteSize);
		}else if(audioFormat.getEncoding() == AudioFormat.Encoding.PCM_UNSIGNED){
			return new SignedDataSeter(encoder, simpleByteSize);
		}else{
			if(simpleByteSize>1){
				return new SignedDataSeter(encoder, simpleByteSize);
			}else{
				return new UnsignedDataSeter(encoder, simpleByteSize);
			}
		}
	}

	public abstract int doubleToInt(double d);
	public abstract double intToDouble(int i);
	public abstract Integer getIntValue(byte[]bytes, int offset);

	public static class SignedDataSeter extends DataSeter{
		public SignedDataSeter(Encoder encoder, int simpleByteSize) {
			super(encoder, simpleByteSize);
			minValue = -amplitude;
			maxValue = amplitude-1;
		}
		
		public int doubleToInt(double d){
			int i = new Double(d*amplitude).intValue();
			return getValuableInt(i);
		}
		
		public double intToDouble(int i){
			i = getValuableInt(i);
			return i/amplitude_D;
		}

		public Integer getIntValue(byte[]bytes, int offset){
			return encoder.getSignedValue(bytes, offset, simpleByteSize);
		}	
	}

	public static class UnsignedDataSeter extends DataSeter{
		public UnsignedDataSeter(Encoder encoder, int simpleByteSize) {
			super(encoder, simpleByteSize);
			minValue = 0;
			maxValue = amplitude*2-1;
		}
		
		public int doubleToInt(double d){
			int i = new Double(d*amplitude).intValue()+amplitude;
			return getValuableInt(i);
		}
		
		public double intToDouble(int i){
			i = getValuableInt(i)-amplitude;
			return i/amplitude_D;
		}

		public Integer getIntValue(byte[]bytes, int offset){
			return encoder.getUnsignedValue(bytes, offset, simpleByteSize);
		}	
	}


}

