package YModem;

import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import UDP.NetworkState;
import UDP.SocketConnectListener;
import UDP.UDPSocketClientManage;

import static YModem.YModemState.AFTER_CHOSEN;
import static YModem.YModemState.BREAK_OUT;
import static YModem.YModemState.CHOSEN_UPDATAMODE;
import static YModem.YModemState.END_FILE_BODY;
import static YModem.YModemState.ENTER_UPDATAMODE;
import static YModem.YModemState.FINSH;
import static YModem.YModemState.JUMP_OUT;
import static YModem.YModemState.NORMAL_MODE;
import static YModem.YModemState.PRE_CHOSEN;
import static YModem.YModemState.PRE_END_FILE_BODY;
import static YModem.YModemState.PRE_ENTER_UPDATAMODE;
import static YModem.YModemState.PRE_FINSH;
import static YModem.YModemState.PRE_QUIT;
import static YModem.YModemState.PRE_TRANSFER_FILE_BODY;
import static YModem.YModemState.PRE_TRANSFER_FILE_HEADER;
import static YModem.YModemState.RESET_CODE;
import static YModem.YModemState.SEND_EMPTY_PACKAGE;
import static YModem.YModemState.SEND_EOT;
import static YModem.YModemState.SEND_HEADER;
import static YModem.YModemState.SEND_S_PACKAGE;
import static YModem.YModemState.TRANSFER_FILE_BODY;
import static YModem.YModemState.TRANSFER_FILE_HEADER;
import static YModem.YModemState.WRITE_IAP;


/**
 * Created by Administrator on 2017/4/18.
 */

public class YModemRequest {
	protected static final byte SOH = 0x01; /* Start Of Header */
	protected static final byte STX = 0x02; /* Start Of Text (used like SOH but means 1024 block size) */
	protected static final byte EOT = 0x04; /* End Of Transmission */
	protected static final byte ACK = 0x06; /* ACKnowlege */
	protected static final byte NAK = 0x15; /* Negative AcKnowlege */
	protected static final byte CAN = 0x18; /* CANcel character */

	protected static final byte CPMEOF = 0x1A;
	protected static final byte ST_C = 'C';

	protected static final int MAXERRORS = 10;

	protected static final int BLOCK_TIMEOUT = 1000;
	protected static final int REQUEST_TIMEOUT = 3000;
	protected static final int WAIT_FOR_RECEIVER_TIMEOUT = 60_000;
	protected static final int SEND_BLOCK_TIMEOUT = 10_000;

	private UDPSocketClientManage mUdpSocketClientManage = null;
	private String mFilepath;
	private boolean isSTX = true;
	private int sendCode = 0;
	private final String mIP;
	private final int mPort;
	private YModemState mState;
	private Timer mTimer;
	private Comfirm comfirm;
	private YModemFileBody mYModemFileBody;
	private int Snum = 1;
	private byte []data ;

	public YModemRequest(String Filepath, String IP, int Port) {
		mIP = IP;
		mPort = Port;
		mState = new YModemState();

		//超时退出升级模式
		mTimer = new Timer(20000);
		mTimer.ReIstimeout(new isTimeout() {
			@Override
			public void timeExpired(boolean istimeout) {
				if (istimeout = true) {
					QuitUpdataMode();
				}
			}
		});
		comfirm = new Comfirm();
		mFilepath = Filepath;
		try {
			mYModemFileBody = new YModemFileBody(mFilepath, isSTX);
//			mYModemFileBody.getSinglePackets(1).


			mUdpSocketClientManage = new UDPSocketClientManage();
			mUdpSocketClientManage.RegSocketConnectListener(new SocketConnectListener() {
				@Override
				public void OnConnectStatusCallBack(NetworkState networkState) {

				}

				@Override
				public void OnReceiverCallBack(int nLength, byte[] mydata) {
					if(mydata[0] ==NAK)Log.w("123","重发");
					data = new byte[nLength];
					data = Arrays.copyOfRange(mydata,0,nLength);
					mTimer.stop();


//					Log.w("receiveData", Arrays.toString(data) );
					if (data.length <2)
					Log.w("receiveData",Integer.toHexString(data[0]) +"    长度" +nLength);
					//接收到ACK确认收到，C继续传输，NAK,则重发或者退出
					switch (mState.getState()) {
						case PRE_ENTER_UPDATAMODE:
							//软重启后，收到wustar字符串，进入选择模式
							if (data.length >= 28) {
								byte []ss = Arrays.copyOfRange(data,data.length-27,data.length-21);
								byte []s1 = {0x77, 0x75 , 0x73 , 0x74 , 0x61, 0x72};
								Log.w("receive",  Arrays.toString(ss)+ "      "+Arrays.equals(ss,s1));
								if (Arrays.equals(ss,s1)) {
									mState.setState(ENTER_UPDATAMODE);
									Chosen_updatamode();
								} else {
									//软重启未成功！
								}
							}
							break;
						case PRE_CHOSEN:
							switch (data[0]) {
								case ST_C:
									//收到C后开始传输报头
									mState.setState(PRE_TRANSFER_FILE_HEADER);
									try {
										Thread.sleep(500);
									}catch (Exception e){e.printStackTrace();}
									SendHeader();
									break;
								default:
									break;
							}

							break;
						case PRE_TRANSFER_FILE_HEADER:
							//收到C开始传输文件名及大小
							switch (data[0]) {
								case ACK:
									comfirm.ReceiveACK(data[0]);
									Log.w("body", "ack:" + comfirm.getACKtime() + "  " + comfirm.isReceiveACK());
									mTimer.start();
									break;
								case ST_C:
									comfirm.ReceiveC(data[0]);
									Log.w("body", "C:" + comfirm.getCtime() + "  " + comfirm.isReceiveC());
									//可以开始传输表头
									if (comfirm.Comfirmdata()) {
										mState.setState(TRANSFER_FILE_HEADER);
										SendBody();
										comfirm.Clear();
										//进入预传输本体模式
									} else {
										//没收到ACK 或者没收到C 或者顺序不对
									}
									break;
								case NAK:
									//重发报头
									SendHeader();
									break;
								default:
									break;
							}
							break;
						case PRE_TRANSFER_FILE_BODY:
							//收到ACK确认后,完成发送后发送EOT
							switch (data[0]) {
								case ACK:
									if (Snum <= mYModemFileBody.getGroupNum()) {
										//确认收到该包
										if (mYModemFileBody.getSinglePackets(Snum).isCompleted()){
											Snum++;
										}
										//传下个包
										SendBody();
										mYModemFileBody.getSinglePackets(Snum).setReceivedData(true);
									} else {
										//发送完毕
										mState.setState(TRANSFER_FILE_BODY);
										Snum = 1;
										SendEOT();
									}
									break;
								case NAK:
									//当前包传输失败
									SendBody();
									break;
								default:
									break;
							}
							break;
						case PRE_END_FILE_BODY:
							switch (data[0]) {
								case ACK:
									comfirm.ReceiveACK(data[0]);
									Log.w("EOT", "ack:" + comfirm.getACKtime() + "  " + comfirm.isReceiveACK());
									break;
								case ST_C:
									comfirm.ReceiveC(data[0]);
									Log.w("EOT", "C:" + comfirm.getCtime() + "  " + comfirm.isReceiveC());
									if (comfirm.Comfirmdata()) {
										mState.setState(END_FILE_BODY);
										SendZERO();
										comfirm.Clear();
									}
									break;
								case NAK:
									SendEOT();
									break;
								default:
									break;
							}
							//收到ACK确认后，再收到C，开始发送全‘0’数据包
							break;
						case PRE_FINSH:
							if (data[0] == ACK) {
								Log.w("结束", "ack");
								mState.setState(FINSH);
							}
							//接收到ACK，发送退出中断指令，完成升级
							break;
						case PRE_QUIT:
							/**临时代替**/
							if (data[0] == CPMEOF) {
								Log.w("quit", "recieve");
								mState.setState(NORMAL_MODE);
							}
							//准备退出
						default:
							break;
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mUdpSocketClientManage.Close();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					mUdpSocketClientManage.Connect();
					mUdpSocketClientManage.setNetworkParameter(mIP, mPort);//59.172.153.70  //192.168.2.253
					while (true) {
						Thread.sleep(100);
						switch (sendCode) {
							case WRITE_IAP:
								//进入升级模式
								byte[] send_starCode = {0x01, 0x10, 0x00, 0x04, 0x00, 0x01, 0x02, 0x07, (byte) 0xC7, (byte) 0xE4, 0x76};
								mUdpSocketClientManage.send(send_starCode, send_starCode.length);
								Thread.sleep(1000);
								byte[] send_resetcode = {0x01, 0x10, 0x00, 0x01, 0x00, 0x01, 0x02, 0x00, 0x11, 0x67, (byte) 0x8D};
								mUdpSocketClientManage.send(send_resetcode, send_resetcode.length);
								mTimer.start();
								sendCode = 0;
								Log.w("指令：", "发送进入升级指令");
								break;
							case RESET_CODE:
								//发送软重启指令 超时不退出
								byte[] send_resetCode = {0x01, 0x10, 0x00, 0x01, 0x01, 0x02, 0x00, 0x11, 0x67, (byte) 0x8D};
								mUdpSocketClientManage.send(send_resetCode, send_resetCode.length);
								sendCode = 0;
								Log.w("指令：", "软重启");
								break;
							case CHOSEN_UPDATAMODE:
								//选择升级模式模式
								byte[] choesn_Code = {0x31};
								mUdpSocketClientManage.send(choesn_Code, choesn_Code.length);
								mTimer.start();
								sendCode = 0;
								break;
							case JUMP_OUT:
								//跳出当前模式
								byte[] jump_out = {0x18, 0x18};
								mUdpSocketClientManage.send(jump_out, jump_out.length);
								mState.setState(NORMAL_MODE);
								sendCode = 0;
								break;
							case BREAK_OUT:
								//下次不再进入升级模式升级模式
								byte[] send_stopCode = {0x01, 0x10, 0x00, 0x04, 0x00, 0x01, 0x02, 0x07, (byte) 0xFE, 0x24, 0x64};
								mUdpSocketClientManage.send(send_stopCode, send_stopCode.length);
								sendCode = 0;
								Log.w("指令：", "发送退出升级指令");
								break;
							case SEND_HEADER:
								//进入传输报头  PRE_TRANSFER_FILE_HEADER
								byte[] theHeader = UpDataHeader(mYModemFileBody.getFileName(), mYModemFileBody.getFileLength());
								mUdpSocketClientManage.send(theHeader, theHeader.length);
								mTimer.start();
								sendCode = 0;
								break;
							case SEND_S_PACKAGE:
								//单个数据包
								byte[] filebody;
								Thread.sleep(200);
//								filebody = mYModemFileBody.getSinglePackets(Snum).getTheData();
//								byte[] fbody1 = new byte[500];
//								byte[] fbody2 = new byte[529];
//								System.arraycopy(filebody, 0, fbody1, 0, 500);
//								System.arraycopy(filebody, 500, fbody2, 0, 529);
//								Log.w("序号", "" + Snum);
//										Log.w("fbody1"," "+Arrays.toString(fbody1));
//										Log.w("fbody2",Arrays.toString(fbody2));
								mUdpSocketClientManage.send(mYModemFileBody.getSinglePackets(Snum).getFbody1(),
										mYModemFileBody.getSinglePackets(Snum).getFbody1().length);
								Thread.sleep(10);
								mUdpSocketClientManage.send(mYModemFileBody.getSinglePackets(Snum).getFbody2(),
										mYModemFileBody.getSinglePackets(Snum).getFbody2().length);
//										Log.w("Sendbody", "序号:" + Snum + "状态" + mState.getState());
								mTimer.start();
								sendCode = 0;
								break;
							case SEND_EOT :
								//发送EOT
								if (mState.getState() == TRANSFER_FILE_BODY) {
									byte[] Eot = {EOT};
									mUdpSocketClientManage.send(Eot, Eot.length);
									mState.setState(PRE_END_FILE_BODY);
								}
								mTimer.start();
								sendCode = 0;
								break;
							case SEND_EMPTY_PACKAGE:
								//全空字符包
								if (mState.getState() == END_FILE_BODY) {
									byte[] theZERO = ZeroData();
									mUdpSocketClientManage.send(theZERO, theZERO.length);
									mState.setState(PRE_FINSH);
								}
								mTimer.start();
								sendCode = 0;
								break;
							default:
								sendCode = 0;
								break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					mUdpSocketClientManage.Close();
				}
			}
		}).start();

		//进入预升级模式
		EnterUpdataMode();
	}


	//发送指令进入升级模式
	private void EnterUpdataMode() {
		if (mState.getState() == NORMAL_MODE) {
			sendCode = WRITE_IAP;
			mState.setState(PRE_ENTER_UPDATAMODE);
		} else {
		}
	}

	//发送软重启指令
	private void ResetRquest() {
		sendCode = RESET_CODE;
	}

	//选择升级模式
	private void Chosen_updatamode() {
		if (mState.getState() == ENTER_UPDATAMODE){
			sendCode = CHOSEN_UPDATAMODE;
			mState.setState(PRE_CHOSEN);
			Log.w("指令：", "选择升级模式1！");
		}else {
		}

	}

	//发送指令跳出升级模式
	private void QuitUpdataMode() {
		sendCode = JUMP_OUT;

	}

	//发送报头
	private void SendHeader() {
		if (mState.getState() == AFTER_CHOSEN || mState.getState() == PRE_TRANSFER_FILE_HEADER) {
			sendCode = SEND_HEADER;
			mState.setState(PRE_TRANSFER_FILE_HEADER);
		}
	}

	//从发送单个包
	private void SendBody() {
		if (mState.getState() == TRANSFER_FILE_HEADER || mState.getState() == PRE_TRANSFER_FILE_BODY) {
			//偷懒用Snum全局变量
			byte[] filebody;
			if (Snum <= mYModemFileBody.getGroupNum()) {
				sendCode = SEND_S_PACKAGE;
				mYModemFileBody.getSinglePackets(Snum).setFinishSend(true);
			}
			if (mState.getState() != PRE_TRANSFER_FILE_BODY)
				mState.setState(PRE_TRANSFER_FILE_BODY);
		}
	}

	//发送EOT
	private void SendEOT() {
		sendCode = SEND_EOT ;
	}

	//发送全零数据包
	private void SendZERO() {
		sendCode = SEND_EMPTY_PACKAGE;
	}


	//传输全零包
	private byte[] ZeroData() {
		byte[] returnZero = new byte[133];
		byte[] Zero = new byte[128];
		returnZero[0] = SOH;
		returnZero[1] = 0x00;
		returnZero[2] = (byte) 0xFF;
		int j = 0;
		while (j < 128) {
			returnZero[j + 3] = 0x00;
			Zero[j] = 0x00;
			j++;
		}
		byte[] Crc16 =  CRCUtil.Byte_CRC_XModem(Zero);
		returnZero[131] = Crc16[0];
		returnZero[132] = Crc16[1];
		Log.w("CRC", (byte) (Crc16[0] & 0xff)+ "  " +(( Crc16[1] >> 8) & 0xff));
		return returnZero;
	}

	//传输文件头
	private byte[] UpDataHeader(String name, long size) {
		byte[] returnheader = new byte[133];
		returnheader[0] = SOH;
		returnheader[1] = 0x00;
		returnheader[2] = (byte) 0xFF;
		byte[] namebyte;
		if (name.length() != 0) {
			namebyte = name.getBytes();
		} else {
			namebyte = new byte[]{0x00};
		}
		byte[] sizebyte = long2StrBytes(size);
		for (int i = 0; i < namebyte.length; i++) {
			returnheader[i + 3] = namebyte[i];
		}
		returnheader[4 + namebyte.length] = 0x00;
		for (int i = 0; i < sizebyte.length; i++) {
			returnheader[i + 4 + namebyte.length] = sizebyte[i];
		}
		int j = 5 + namebyte.length + sizebyte.length;
		while (j < 131) {
			returnheader[j] = 0x00;
			j++;
		}
		byte[] CRCcontent = new byte[128];
		for (int i = 0; i <CRCcontent.length; i++) {
			CRCcontent[i] = returnheader[i+3];
//			Log.w("CRCcontent","序号"+i+":"+Integer.toHexString((int)CRCcontent[i]));
		}
		byte[] Crc16 =  CRCUtil.Byte_CRC_XModem(CRCcontent);
		returnheader[returnheader.length - 2] = Crc16[1];
		returnheader[returnheader.length - 1] = Crc16[0];
		return returnheader;
	}


	//long转String
	private byte[] long2StrBytes(long size){
		Log.w("1231",Long.toString(size));
		return Long.toString(size).getBytes();
	}
	//long转byte[] ，不超过2*32次方
	private byte[] long2bytes(long size) {
		Log.w("总长度"," "+size);
		List<Byte> bytelist =new ArrayList<Byte>();
		if (size == 0) {
			return new byte[]{0x00};
		}
		int m = 0;
		m = (int) (size >> 56) & 0xFF;
		if (m>0){
			bytelist.add((byte)((size >> 56) & 0xFF));
		}
		m = (int) ((size >> 48) & 0xFF);
		if (m>0){
			bytelist.add((byte)((size >> 48) & 0xFF));
		}
		m = (int) ((size >> 40) & 0xFF);
		if (m>0){
			bytelist.add((byte)((size >> 40) & 0xFF));
		}
		m = (int) ((size >> 32) & 0xFF);
		if (m>0){
			bytelist.add((byte)((size >> 32) & 0xFF));
		}
		m = (int) ((size >> 24) & 0xFF);
		if (m>0){
			bytelist.add((byte)((size >> 24) & 0xFF));
		}
		m = (int) ((size >> 16) & 0xFF);
		if (m>0){
			bytelist.add((byte)((size >> 16) & 0xFF));
		}
		m = (int) ((size >> 8) & 0xFF);
		if (m>0){
			bytelist.add((byte)((size >> 8) & 0xFF));
		}
		m = (int) (size & 0xFF);
		bytelist.add((byte)(size & 0xFF));

		byte [] scr = new byte[bytelist.size()];
		for (int i = 0;i<bytelist.size();i++){
			scr [i] = bytelist.get(i);
		}
		String ss = new BigInteger(1,scr).toString(10);
		Log.w("报头长度", Arrays.toString(bytelist.toArray()) +"，打印数组"+ss);
		return scr;
	}

	//收到ACK确认后再收到C返回真
	private class Comfirm {

		boolean receiveACK, receiveC;
		long ACKtime, Ctime;

		private Comfirm() {
			receiveACK = false;
			receiveC = false;
			ACKtime = 0;
			Ctime = 0;
		}

		private void ReceiveACK(byte data) {
			if (data == ACK) {
				receiveACK = true;
				ACKtime = System.currentTimeMillis();
			}
		}

		private void ReceiveC(byte data) {
			if (data == ST_C) {
				receiveC = true;
				Ctime = System.currentTimeMillis();
			}
		}

		private void Clear() {
			receiveACK = false;
			receiveC = false;
			ACKtime = 0;
			Ctime = 0;
		}

		public boolean isReceiveACK() {
			return receiveACK;
		}

		public boolean isReceiveC() {
			return receiveC;
		}

		public long getACKtime() {
			return ACKtime;
		}

		public long getCtime() {
			return Ctime;
		}

		private boolean Comfirmdata() {
			Log.w("Comfirmdata", "" + ((ACKtime < Ctime) && receiveACK && receiveC));
			return (ACKtime < Ctime) && receiveACK && receiveC;
		}
	}
}
