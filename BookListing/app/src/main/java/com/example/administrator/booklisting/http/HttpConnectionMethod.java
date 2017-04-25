package com.example.administrator.booklisting.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/4/24.
 */

public class HttpConnectionMethod {


	/**
	 * 获取指定URL的响应字符串
	 * @param urlString
	 * @return
	 */
	public static String getURLResponse(String urlString){
		HttpURLConnection conn = null; //连接对象
		InputStream is = null;
		String resultData = "";
		try {
			URL url = new URL(urlString); //URL对象
			conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接
			conn.setDoInput(true); //允许输入流，即允许下载
			conn.setDoOutput(true); //允许输出流，即允许上传
			conn.setUseCaches(false); //不使用缓冲
			conn.setRequestMethod("GET"); //使用get请求
			if (conn.getResponseCode()!=200){
				return "错误代码："+conn.getResponseCode();
			}
			is = conn.getInputStream();   //获取输入流，此时才真正建立链接
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bufferReader = new BufferedReader(isr);
			String inputLine  = "";
			while((inputLine = bufferReader.readLine()) != null){
				resultData += inputLine + "\n";
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				conn.disconnect();
			}
		}
		return resultData;
	}




	private String myparams;
	private Bitmap bitmap;
	public Bitmap myImageBitmap(String s ){
		myparams =s;
		getImage();
		if (bitmap!=null){
			return bitmap;
		}else return null;
	}

	//获取图片
	public  void getImage (){
		Thread mythread = new Thread(new Runnable() {
			@Override
			public void run() {
				 bitmap = getImageBitmap(myparams);
			}
		});
		try {
			mythread.start();
			mythread.join();
		}catch ( Exception e){
			e.printStackTrace();
		}
	}

	private Bitmap getImageBitmap(String url){
		URL imgUrl = null;
		Bitmap bitmap = null;
		try {
			imgUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return bitmap;
	}

	/*class ImgAsyncTask extends AsyncTask<String, Void, Bitmap> {

		private Bitmap mBitmap = null;

		public Bitmap getBitmap() {
			return mBitmap;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap b = getImageBitmap(params[0]);
			return b;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {
				mBitmap = result;
			} else mBitmap = null;
		}
	}*/
}
