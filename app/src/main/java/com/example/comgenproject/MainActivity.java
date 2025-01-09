package com.example.comgenproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RelativeLayout parentRLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        parentRLayout.setLayoutParams(layoutParams);

        ImageView bannerIV = new ImageView(this);
        bannerIV.setId(View.generateViewId());
        bannerIV.setImageResource(R.drawable.ic_launcher_background);

        RelativeLayout.LayoutParams imageViewParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        imageViewParams.addRule(RelativeLayout.ALIGN_PARENT_TOP); // Align at the top
        imageViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL); // Center horizontally
        bannerIV.setLayoutParams(imageViewParams);

        parentRLayout.addView(bannerIV);

        LinearLayout dateTimeLLayout = new LinearLayout(this);
        dateTimeLLayout.setId(View.generateViewId());
        dateTimeLLayout.setOrientation(LinearLayout.HORIZONTAL);
        dateTimeLLayout.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams dateTimeLLParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        dateTimeLLParams.addRule(RelativeLayout.BELOW, bannerIV.getId()); // Position below the ImageView
        dateTimeLLParams.addRule(RelativeLayout.CENTER_HORIZONTAL); // Center horizontally
        dateTimeLLParams.setMargins(0, 20, 0, 0); // Add some spacing below the ImageView
        dateTimeLLayout.setLayoutParams(dateTimeLLParams);

        TextView timeTV = new TextView(this);
        timeTV.setText(getTime());
        dateTimeLLayout.addView(timeTV);

        TextView spacerTv = new TextView(this);
        spacerTv.setText(" | ");
        dateTimeLLayout.addView(spacerTv);

        TextView dateTV = new TextView(this);
        dateTV.setText(getDate());
        dateTimeLLayout.addView(dateTV);

        parentRLayout.addView(dateTimeLLayout);


        EditText usernameEt = new EditText(this);
        usernameEt.setId(View.generateViewId());
        usernameEt.setHint("UserName");

        RelativeLayout.LayoutParams usernameRLParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Set to MATCH_PARENT for better alignment
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        usernameRLParams.addRule(RelativeLayout.BELOW, dateTimeLLayout.getId()); // Position below LinearLayout
        usernameRLParams.setMargins(0, 20, 0, 0); // Add some spacing
        usernameEt.setLayoutParams(usernameRLParams);

        parentRLayout.addView(usernameEt);

// Create the second EditText for password
        EditText passwordEt = new EditText(this);
        passwordEt.setId(View.generateViewId());
        passwordEt.setHint("Password");

        RelativeLayout.LayoutParams passwordRLParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Set to MATCH_PARENT for better alignment
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        passwordRLParams.addRule(RelativeLayout.BELOW, usernameEt.getId()); // Position below the username EditText
        passwordRLParams.setMargins(0, 20, 0, 0); // Add some spacing
        passwordEt.setLayoutParams(passwordRLParams);

        parentRLayout.addView(passwordEt);

// Create a Button for the "Go" action
        Button goBTN = new Button(this);
        goBTN.setId(View.generateViewId());
        goBTN.setText("Go");
        goBTN.setBackgroundColor(getResources().getColor(R.color.white));

        RelativeLayout.LayoutParams goBTNParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        goBTNParams.addRule(RelativeLayout.BELOW, passwordEt.getId()); // Position below the password EditText
        goBTNParams.addRule(RelativeLayout.CENTER_HORIZONTAL); // Center the button horizontally
        goBTNParams.setMargins(0, 20, 0, 0); // Add some spacing
        goBTN.setLayoutParams(goBTNParams);

        parentRLayout.addView(goBTN);

// Set onClickListener for the "Go" button
        goBTN.setOnClickListener(v -> {

            Intent i = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(i);
        });

// Set the parent layout as the content view
        setContentView(parentRLayout);

    }

    private String getTime() {
        return "11:32 AM";
    }

    private String getDate() {
        return "Wed 8, Jan 2025";
    }


}