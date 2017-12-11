package YModem;

/**
 * Created by Administrator on 2017/4/28.
 */

public class CRCUtil {
	public static int CRC_XModem(byte[] bytes){
		int crc = 0x00;          // initial value
		int polynomial = 0x1021;
		for (int index = 0 ; index< bytes.length; index++) {
			byte b = bytes[index];
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b   >> (7-i) & 1) == 1);
				boolean c15 = ((crc >> 15    & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit) crc ^= polynomial;
			}
		}
		crc &= 0xffff;

		return crc;
	}

	public static byte[] Byte_CRC_XModem(byte[] block) {
		byte[] crcYM = new byte[2];
		int crc  =  CRC_XModem(block);
		crcYM[0] = (byte) (crc & 0xff);
		crcYM[1] =  (byte) ((crc >> 8) & 0xff);
//		String c = new BigInteger(1, crcYM).toString(16);
//		Log.w("查看校验",c);
		return  crcYM;
	}

}
