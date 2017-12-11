package YModem;

import android.content.res.Resources;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/21.
 */

public class YModemFileBody {

	private static final byte SOH = 0x01; /* Start Of Header */
	private static final byte STX = 0x02; /* Start Of Text (used like SOH but means 1024 block size) */
	private static final int SOHMODE = 128;
	private static final int STXMODE = 1024;
	private byte[] FileContent;
	private byte[][] GroupContent;
	private SinglePacket[] mSinglePackets;
	private String FileName;
	private long FileLength;
	private boolean isSTX;
	private int GroupNum = 0;
	private DataProcessing mDataProcessing;

	public YModemFileBody(String filepath,boolean isSTX) {
		this.isSTX = isSTX;
		try {
			getFileContent(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mDataProcessing = new DataProcessing(FileContent, isSTX);
		GroupNum = mDataProcessing.getGroupNum();
		GroupContent = mDataProcessing.getOutputData();

		mSinglePackets = new SinglePacket[GroupNum];
		for (int i =0;i<GroupNum;i++){
			mSinglePackets[i] = new SinglePacket(blockData(GroupContent[i],i+1,isSTX),i+1);
		}
	}

	public SinglePacket[] getallSinglePackets() {
		return mSinglePackets;
	}

	public SinglePacket getSinglePackets(int Snum){
		return mSinglePackets[Snum-1];
	}

	public byte[] getFileContent() {
		return FileContent;
	}

	public String getFileName() {
		return FileName;
	}

	public long getFileLength() {
		return FileLength;
	}

	public int getGroupNum() {
		return GroupNum;
	}

	//获取文件名、内容、大小
	private void getFileContent(String filepath) throws IOException {

		File sd = Environment.getExternalStorageDirectory();
		boolean can_write = sd.canWrite();
		if (!can_write)
			throw new Resources.NotFoundException("Sd卡不可读！");
		File f = new File(Environment.getExternalStorageDirectory(),"arm_mec.bin");
		if (!f.exists())
			throw new FileNotFoundException(filepath+"未找到该文件！");

		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			byte[] filecontent = new byte[fs.available()];
			while (fs.read(filecontent) != -1) {
				// System.out.println("reading");
			}
			FileContent = filecontent;
			FileName = f.getName();
			FileLength = filecontent.length;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//单个包添加帧头CRC校验
	public byte[] blockData(byte[] data, int Snum, boolean isSTX) {
		byte[] returnblockdata;
		byte[] blockdata;
		if (isSTX) {
			blockdata = new byte[1024];
			returnblockdata = new byte[1029];
			returnblockdata[0] = STX;
		} else {
			blockdata = new byte[128];
			returnblockdata = new byte[133];
			returnblockdata[0] = SOH;
		}

		returnblockdata[1] = (byte) Snum;
//		Log.w("snum",String.valueOf(Snum));
		returnblockdata[2] = (byte) (255 - Snum);
//		Log.w("snum",String.valueOf(255-Snum));

//		Log.w("报头",returnblockdata[0]+ " " +returnblockdata[1]+ " " +returnblockdata[2]);
		try {
			switch (data.length) {
				case 1024:
					if (!isSTX) throw new Exception("模式为SOH，长度为1024");
					blockdata = data;
					break;
				case 128:
					if (isSTX) throw new Exception("模式为STX，长度为128");
					blockdata = data;
					break;
				default:
					throw new Exception("打包数据包长度不符合！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] Crc16 = CRCUtil.Byte_CRC_XModem(blockdata);
		for (int i = 0; i < blockdata.length; i++) {
			returnblockdata[i+3] = blockdata[i];
		}
		returnblockdata[returnblockdata.length - 2] = Crc16[1];
		returnblockdata[returnblockdata.length - 1] = Crc16[0];

		return returnblockdata;
	}

	//将内容分组
	public class DataProcessing {
		byte [] mFileData;
		byte [] [] mOutputData;
		boolean isSTX = false;
		int GroupNum = 1;

		public DataProcessing(byte[] fileData, boolean IsSTX) {
			mFileData = fileData;
			this.isSTX = IsSTX;

			int DataLength;
			if (isSTX) {
				DataLength = STXMODE;
			}else {
				DataLength = SOHMODE;
			}
			try {
				GroupNum = 1 + fileData.length / DataLength;
				mOutputData = new byte[GroupNum][DataLength];

				int filelength = 0;

				for (int i = 0; i < GroupNum; i++) {
					for (int j = 0; j < DataLength; j++) {
						if (filelength < fileData.length) {
							mOutputData[i][j] = fileData[filelength];
							filelength++;
						} else {
							//不满1024的部分全部填充00
							mOutputData[i][j] = 0x00;
						}
//					Log.w("赋值"," "+mOutputData[i][j]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public byte[][] getOutputData() {
			return mOutputData;
		}

		public int getGroupNum() {
			return GroupNum;
		}
	}

}
