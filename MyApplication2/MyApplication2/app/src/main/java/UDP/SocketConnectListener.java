package UDP;

/**
 * Created by Administrator on 2017/4/18.
 */

public abstract class SocketConnectListener {
	// 网络状态回调
	public abstract void OnConnectStatusCallBack(NetworkState networkState);

	// 接收数据回调
	public abstract void OnReceiverCallBack(int nLength, byte[] data);
}
