package function_class;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/23.
 */

public class MyToast {
	/**UI线程中使用，千万别在非UI中使用*/
	public static void show(Context context, CharSequence text, int duration) {
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.CENTER,0,0);
		toast.show();
	}

	/**
	 * 在非UI线程中，这个方法可以将Toast显示在UI线程
	 * 原理，追加toast在消息队列中
	 * 千万别在UI线程中使用
	 * */
	public static void showOnUIThreadx(final Context context,final CharSequence text,final  int duration) {

		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(context, text, duration);
				toast.setGravity(Gravity.CENTER,0,0);
				toast.show();
			}
		});

	}

	/**
	 * 在非UI线程中，这个方法可以将Toast显示在UI线程
	 * 原理，追加toast在消息队列中
	 * 千万别在UI线程中使用
	 * */
	public static void showOnUIThread2(final Context context,final CharSequence text,final  int duration) {
		Looper.prepare();
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.CENTER,0,0);
		toast.show();
		Looper.loop();
	}
}
