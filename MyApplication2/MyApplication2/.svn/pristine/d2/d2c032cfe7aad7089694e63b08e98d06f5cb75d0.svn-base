package YModem;

import android.util.Log;

/**
 * Created by Administrator on 2017/4/18.
 */

public class SinglePacket{
	private  byte[] theData;
	private  boolean FinishSend,ReceivedData;
	private  int Num;
	byte[] fbody1 ;
	byte[] fbody2 ;

	public SinglePacket(byte[] data, int num) {
		theData = data;
		Num = num;
		FinishSend = false;
		ReceivedData = false;
		//受限制于UDP传输的缓存1024个大小，单包1029个字节需要分开打包发送
		if (theData.length==1029){
			fbody1 = new byte[500];
			fbody2 = new byte[529];
			System.arraycopy(theData, 0, fbody1, 0, 500);
			System.arraycopy(theData, 500, fbody2, 0, 529);
		}
	}

	public byte[] getTheData() {
		return theData;
	}

	public void setTheData(byte[] theData) {
		this.theData = theData;
	}

	public boolean isFinishSend() {
		return FinishSend;
	}

	public void setFinishSend(boolean finishSend) {
		FinishSend = finishSend;
	}

	public boolean isReceivedData() {
		return ReceivedData;
	}

	public void setReceivedData(boolean receivedData) {
		ReceivedData = receivedData;
	}

	public boolean isCompleted (){
		Log.w("warn","发送序号"+Num+"的包并接收到ACK");
		return ReceivedData&&FinishSend;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public byte[] getFbody1() {
		if (fbody1.length>0)
		return fbody1;
		else return null;
	}

	public byte[] getFbody2() {
		if (fbody2.length>0)
		return fbody2;
		else return null;
	}
}
