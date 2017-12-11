package YModem;

/**
 * Created by Administrator on 2017/4/19.
 */

public class YModemState {
	int State = 0;
	//状态
	public final static int NORMAL_MODE = 1212;
	public final static int PRE_ENTER_UPDATAMODE = 1213;
	public final static int ENTER_UPDATAMODE = 1214;
	public final static int PRE_CHOSEN = 1241;
	public final static int AFTER_CHOSEN = 1242;
	public final static int PRE_TRANSFER_FILE_HEADER = 1215;
	public final static int TRANSFER_FILE_HEADER = 1216;
	public final static int PRE_TRANSFER_FILE_BODY = 1217;
	public final static int TRANSFER_FILE_BODY = 1218;
	public final static int PRE_END_FILE_BODY = 1219;
	public final static int END_FILE_BODY = 1220;
	public final static int PRE_FINSH = 1221;
	public final static int FINSH = 1222;
	public final static int PRE_QUIT = 1245;
	public final static int JUMP_OUT = 1257;
	public final static int BREAK_OUT = 1246;

	//指令
	public final static int WRITE_IAP = 1567;
	public final static int RESET_CODE = 1432;
	public final static int CHOSEN_UPDATAMODE = 1541;
	public final static int SEND_HEADER = 1564;
	public final static int SEND_S_PACKAGE = 1641;
	public final static int SEND_EOT = 1714;
	public final static int SEND_EMPTY_PACKAGE = 15614;




	public YModemState() {
		State = NORMAL_MODE;
	}

	public YModemState(int state) {
		State = state;
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}
}
