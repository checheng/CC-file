package YModem;

/**
 * Created by Administrator on 2017/4/11.
 */

class Timer {

	private long startTime = 0;
	private long stopTime = 0;
	private long timeout = 0;
	isTimeout mIsTimeout = null;

	public void ReIstimeout (isTimeout istimeout){
		this.mIsTimeout = istimeout;
	}

	public Timer(long timeout) {
		this.timeout = timeout;
	}

	public Timer start() {
		this.startTime = System.currentTimeMillis();
		this.stopTime = 0;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(timeout);
					if (isExpired()){
						mIsTimeout.timeExpired(true);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		return this;
	}

	public void stop() {
		this.stopTime = System.currentTimeMillis();
	}

	public boolean isExpired() {
		if (stopTime ==0)stopTime = System.currentTimeMillis();
		return (stopTime> startTime + timeout);
	}

	public long getStartTime() {
		return this.startTime;
	}

	public long getStopTime() {
		return this.stopTime;
	}

	public long getTotalTime() {
		return this.stopTime - this.startTime;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isWorking() {
		return (stopTime != 0);
	}


}