package sockettest.example.com.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import YModem.SinglePacket;
import YModem.YModemRequest;

public class thePageFive extends BaseAcitivity {

	private Button mUpdata;
	private final static  String UPDATA_FILE = "/storage/emulated/0/updatafile.bin";
	private YModemRequest mYModemRequest = null;

	private SinglePacket mDataProcessing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_the_page_five);
		init();
	}
	private void init(){
		mUpdata = (Button)findViewById(R.id.updata);
		mUpdata.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						mYModemRequest = new YModemRequest(UPDATA_FILE,"10.10.100.254",8899); //200.100.1.102   10.10.100.254
					}
				}).start();
			}
		});
/*		byte[] b = new byte[] {
				(byte)0x61,(byte)0x72,(byte)0x6D,(byte)0x5F,(byte)0x6D,(byte)0x65,(byte)0x63,(byte)0x2E,
				(byte)0x62,(byte)0x69,(byte)0x6E,(byte)0x00,(byte)0x33,(byte)0x37,(byte)0x34,(byte)0x31,
				(byte)0x32,(byte)0x30,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00 };
		int a = CRC_XModem(b);
		Log.w("CRC X",Integer.toBinaryString(a)+"       "+Integer.toHexString(CRC_XModem(b)).toUpperCase());*/
	}



}
