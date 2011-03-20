package jp.lnc.android.send_activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SleepSendActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Timerを設定する
		timer = new Timer(true);
		TimerTask timerTask = new exsampleTimerTask();
		timer.schedule(timerTask, 200000, 10000); // 初回起動の遅延と周期指定。単位はms

		setContentView(R.layout.main);
	}

	private void sendIntent() {
		Log.d("test", "Send Intent");
		String strUrl = "http://hatena.ne.jp/";

		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
		startActivity(intent);

	}

	/** Called when the activity is first created. */
	int tickcount;
	Timer timer = null; // タイマを定義
	TextView mDisplayText; // 表示領域の定義

	class exsampleTimerTask extends TimerTask {

		final android.os.Handler handler = new android.os.Handler();

		public void run() {
			// TODO Auto-generated method stub
			handler.post(new Runnable() {
				public void run() {
					sendIntent();
				}
			});
		}

	}
}