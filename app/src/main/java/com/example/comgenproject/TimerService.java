package com.example.comgenproject;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

public class TimerService extends Service {

    private CountDownTimer countDownTimer;
    private long remainingTimeInMillis;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long totalTimeInMillis = intent.getLongExtra("totalTimeInMillis", 0);
        if (totalTimeInMillis > 0) {
            startTimer(totalTimeInMillis);
        }

        return START_STICKY;
    }

    private void startTimer(long totalTimeInMillis) {
        remainingTimeInMillis = totalTimeInMillis;

        countDownTimer = new CountDownTimer(remainingTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTimeInMillis = millisUntilFinished;

                // Broadcast the remaining time to the activity
                Intent broadcastIntent = new Intent("com.example.TIMER_UPDATE");
                broadcastIntent.putExtra("remainingTime", remainingTimeInMillis);
                sendBroadcast(broadcastIntent);
            }

            @Override
            public void onFinish() {
                // Timer finished
                Toast.makeText(TimerService.this, "Time's up!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
