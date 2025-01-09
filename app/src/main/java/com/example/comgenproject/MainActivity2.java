package com.example.comgenproject;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private EditText hoursEt, minutesEt, secondsEt;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long totalTimeInMillis; // Time in milliseconds
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout parentRLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        parentRLayout.setLayoutParams(layoutParams);

        Button chargeStatusBTN = new Button(this);
        chargeStatusBTN.setId(View.generateViewId());
        chargeStatusBTN.setText("Get Phone Charging Status");
        chargeStatusBTN.setBackgroundColor(getResources().getColor(R.color.white));
        chargeStatusBTN.setOnClickListener(v -> {

           getChargeState();
        });
        RelativeLayout.LayoutParams goBTNParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        goBTNParams.addRule(RelativeLayout.ALIGN_PARENT_TOP); // Align at the top
        goBTNParams.addRule(RelativeLayout.CENTER_HORIZONTAL); // Center the button horizontally
        chargeStatusBTN.setLayoutParams(goBTNParams);

        parentRLayout.addView(chargeStatusBTN);

        LinearLayout timeLLayout = new LinearLayout(this);
        timeLLayout.setId(View.generateViewId());
        timeLLayout.setOrientation(LinearLayout.HORIZONTAL);
        timeLLayout.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams timingRlParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        timingRlParams.addRule(RelativeLayout.BELOW, chargeStatusBTN.getId()); // Position below the ImageView
        timingRlParams.addRule(RelativeLayout.CENTER_HORIZONTAL); // Center horizontally
        timingRlParams.setMargins(0, 20, 0, 0); // Add some spacing below the ImageView
        timeLLayout.setLayoutParams(timingRlParams);


        // Hours EditText
        hoursEt = new EditText(this);
        hoursEt.setHint("Hours");
        hoursEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        hoursEt.setId(TextView.generateViewId());
        RelativeLayout.LayoutParams hoursEtParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        hoursEtParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        hoursEtParams.setMargins(0, 20, 0, 0);
        hoursEt.setLayoutParams(hoursEtParams);
        timeLLayout.addView(hoursEt);

        // Minutes EditText
        minutesEt = new EditText(this);
        minutesEt.setHint("Minutes");
        minutesEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        minutesEt.setId(TextView.generateViewId());
        RelativeLayout.LayoutParams minutesEtParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        minutesEtParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        minutesEtParams.setMargins(0, 20, 0, 0);
        minutesEt.setLayoutParams(minutesEtParams);
        timeLLayout.addView(minutesEt);

        // Seconds EditText
        secondsEt = new EditText(this);
        secondsEt.setHint("Seconds");
        secondsEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        secondsEt.setId(TextView.generateViewId());
        RelativeLayout.LayoutParams secondsEtParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        secondsEtParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        secondsEtParams.setMargins(0, 20, 0, 0);
        secondsEt.setLayoutParams(secondsEtParams);
        timeLLayout.addView(secondsEt);

        // Timer TextView to display countdown
        timerTextView = new TextView(this);
        timerTextView.setId(TextView.generateViewId());
        timerTextView.setTextSize(24);
        timerTextView.setText("00h 00m 00s");
        timerTextView.setId(TextView.generateViewId());
        RelativeLayout.LayoutParams timerTextViewParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        timerTextViewParams.addRule(RelativeLayout.BELOW, timeLLayout.getId());
        timerTextViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        timerTextViewParams.setMargins(0, 20, 0, 0);
        timerTextView.setLayoutParams(timerTextViewParams);
        parentRLayout.addView(timerTextView);

        parentRLayout.addView(timeLLayout);

        // Start Button
        Button startButton = new Button(this);
        if (isTimerRunning) {
            startButton.setText("Stop Timer");
        }
        else {
            startButton.setText("Start Timer");
        }

        startButton.setId(TextView.generateViewId());
        RelativeLayout.LayoutParams startButtonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        startButtonParams.addRule(RelativeLayout.BELOW, timerTextView.getId());
        startButtonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        startButtonParams.setMargins(0, 20, 0, 0);
        startButton.setLayoutParams(startButtonParams);
        parentRLayout.addView(startButton);

        // Set onClickListener for Start Button
        startButton.setOnClickListener(v -> {
            if (isTimerRunning) {
                Toast.makeText(this, "Timer is already running!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get the values from the EditTexts
            String hoursStr = hoursEt.getText().toString();
            String minutesStr = minutesEt.getText().toString();
            String secondsStr = secondsEt.getText().toString();

            // Check if inputs are empty or invalid
            if (TextUtils.isEmpty(hoursStr) || TextUtils.isEmpty(minutesStr) || TextUtils.isEmpty(secondsStr)) {
                Toast.makeText(this, "Please enter valid time!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Convert values to integers
            int hours = Integer.parseInt(hoursStr);
            int minutes = Integer.parseInt(minutesStr);
            int seconds = Integer.parseInt(secondsStr);

            // Convert hours, minutes, and seconds to total milliseconds
            totalTimeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;

            if (totalTimeInMillis == 0) {
                Toast.makeText(this, "Please select a valid time!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Start the countdown timer
            startTimer();
        });


        setContentView(parentRLayout);

    }

    private void getChargeState() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        if (batteryStatus != null) {
            // Get battery status
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            // Get charging method
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            // Get battery percentage
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = level * 100 / (float) scale;

            // Display the charge state
            String chargingMethod = usbCharge ? "USB Charging" : (acCharge ? "AC Charging" : "Not Charging");
            String message = String.format("Battery: %.0f%%\nState: %s", batteryPct, chargingMethod);

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isTimerRunning = true;
                totalTimeInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                timerTextView.setText("Time's up!");
                Toast.makeText(MainActivity2.this, "Timer Finished!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void updateTimerText() {
        int hours = (int) (totalTimeInMillis / (1000 * 60 * 60));
        int minutes = (int) (totalTimeInMillis % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) (totalTimeInMillis % (1000 * 60)) / 1000;

        // Format the time and update the TextView
        String timeFormatted = String.format("%02dh %02dm %02ds", hours, minutes, seconds);
        timerTextView.setText(timeFormatted);
    }


}