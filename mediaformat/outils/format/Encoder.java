package mediaformat.outils.format;

public interface Encoder{
	public int setBytes(int value, byte[]bytes, int offset, int size);
	public Integer getSignedValue(byte[] bytes, int offset, int size);
	public Integer getUnsignedValue(byte[] bytes, int offset, int size);
	
	public static Encoder ENCODER_BIG_ENDIAN = new Encoder(){
		@Override
		public int setBytes(int value, byte[]bytes, int offset, int size) {
			try{
				for(int i=size-1; i>=0; i--){
					bytes[offset+i] = (byte) (value & 0xFF);
					value >>= 8;
				}
				return size;
			}catch(Exception e){
				return 0;
			}
		}

		@Override
		public Integer getSignedValue(byte[] bytes, int offset, int size) {
			try{
				int value = (bytes[offset]) << (8*(size-1));
				for(int i=1; i<size; i++){
					value = value | ((bytes[offset + i] &0xFF) << (8*(size-i-1)));
				}
				return value;
			}catch(Exception e){
				return null;
			}
		}
		
		@Override
		public Integer getUnsignedValue(byte[] bytes, int offset, int size) {
			try{
				int value = 0;
				for(int i=0; i<size; i++){
					value = value | ((bytes[offset + i] &0xFF) << (8*(size-i-1)));
				}
				return value;
			}catch(Exception e){
				return null;
			}
		}
	};
	
	public static Encoder ENCODER_LITTLE_ENDIAN = new Encoder(){
		@Override
		public int setBytes(int value, byte[]bytes, int offset, int size) {
			try{
				for(int i=0; i<size; i++){
					bytes[offset+i] = (byte) (value & 0xFF);
					value >>= 8;
				}
				return size;
			}catch(Exception e){
				return 0;
			}
		}

		@Override
		public Integer getSignedValue(byte[] bytes, int offset, int size) {
			try{
				int value = 0;
				int i=0;
				for(; i<size-1; i++){
					value = value | ((bytes[offset + i] & 0xFF) << (8*i));
				}
				value = value | ((bytes[offset + i]) << (8*i));
				
				return value;
			}catch(Exception e){
				return null;
			}
		}
		
		@Override
		public Integer getUnsignedValue(byte[] bytes, int offset, int size) {
			try{
				int value = 0;
				for(int i=0; i<size; i++){
					value = value | ((bytes[offset + i] & 0xFF) << (8*i));
				}
				
				return value;
			}catch(Exception e){
				return null;
			}
		}
		
	};

}

