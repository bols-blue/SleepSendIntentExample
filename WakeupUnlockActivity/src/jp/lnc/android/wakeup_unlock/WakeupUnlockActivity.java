package jp.lnc.android.wakeup_unlock;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class WakeupUnlockActivity extends Activity {
	private BroadcastReceiver mReceiver;
	private String TAG = "wakeup";
	private Handler screenOFFHandler = new Handler() {

		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			// do something
			// wake up phone
			Log.i(TAG, "ake up the phone and disable keyguard");
			PowerManager powerManager = 
				(PowerManager) jp.lnc.android.wakeup_unlock.WakeupUnlockActivity.this
					.getSystemService(Context.POWER_SERVICE);
			long l = SystemClock.uptimeMillis();
			// false will bring the screen
			// back as bright as it was,
			// true - will dim it
			powerManager.userActivity(l, false);
		}
		
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context arg0, Intent arg1) {
				Log.v(TAG, "Screen OFF onReceive()");
				screenOFFHandler.sendEmptyMessageDelayed(0, 2000L);
			}
		};
//		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
//		registerReceiver(mReceiver, filter);
//		Log.i(TAG, "broadcast receiver registered!");
//		Window window = this.getWindow();
		//to get the window of your activity; use Window.addFlags() to add whichever of the following flags in WindowManager.LayoutParams that you desire: FLAG_DISMISS_KEYGUARD, FLAG_SHOW_WHEN_LOCKED, FLAG_TURN_SCREEN_ON
//		window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
	}

	@Override
	protected void onResume() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);
        Log.i(TAG, "broadcast receiver registered!");
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(mReceiver);
		Log.i(TAG, "broadcast UNregistred!");

	}
}