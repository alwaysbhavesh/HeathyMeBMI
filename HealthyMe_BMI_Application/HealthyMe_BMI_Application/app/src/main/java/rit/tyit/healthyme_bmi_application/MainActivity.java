package rit.tyit.healthyme_bmi_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    TextView mcurrentheight;
    TextView mcurrentweight, mcurrentage;
    ImageView mincrementage, mdecrementage, mincrementweight, mdecrementweight;
    SeekBar mseekbarforheight;
    Button mcalculatebmi;
    RelativeLayout mmale, mfemale;

    int intweight = 55;
    int intage = 22;
    int currentprogress;
    String mintprogress = "155";
    String typerofuser = "0";
    String weight2 = "55";
    String age2 = "22";

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mcurrentage = findViewById(R.id.currentage);
        mcurrentweight = findViewById(R.id.currentweight);
        mcurrentheight = findViewById(R.id.currentheight);
        mincrementage = findViewById(R.id.incrementage);
        mdecrementage = findViewById(R.id.decrementage);
        mincrementweight = findViewById(R.id.incremetweight);
        mdecrementweight = findViewById(R.id.decrementweight);
        mcalculatebmi = findViewById(R.id.calculatebmi);
        mseekbarforheight = findViewById(R.id.seekbarforheight);
        mmale = findViewById(R.id.male);
        mfemale = findViewById(R.id.female);

        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalefocus));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalenotfocus));
                typerofuser = "Male";
            }
        });

        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalefocus));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalenotfocus));
                typerofuser = "Female";
            }
        });

        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(155);
        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentprogress = progress;
                mintprogress = String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mincrementweight.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            private int mInterval = 100; // Interval for continuous increment in milliseconds

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button is pressed, start continuous increment
                        mHandler = new Handler();
                        mHandler.postDelayed(mWeightIncrementer, mInterval);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Button is released, stop continuous increment
                        if (mHandler != null) {
                            mHandler.removeCallbacks(mWeightIncrementer);
                        }
                        if (intweight < 500) {
                            intweight++;
                        }
                        weight2 = String.valueOf(intweight);
                        mcurrentweight.setText(weight2);
                        break;
                }
                return true;
            }

            private Runnable mWeightIncrementer = new Runnable() {
                @Override
                public void run() {
                    // Perform continuous increment
                    if (intweight < 500) {
                        intweight++;
                    }
                    weight2 = String.valueOf(intweight);
                    mcurrentweight.setText(weight2);
                    mHandler.postDelayed(this, mInterval);
                }
            };
        });

        mdecrementweight.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            private int mInterval = 100; // Interval for continuous decrement in milliseconds

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button is pressed, start continuous decrement
                        mHandler = new Handler();
                        mHandler.postDelayed(mWeightDecrementer, mInterval);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Button is released, stop continuous decrement
                        if (mHandler != null) {
                            mHandler.removeCallbacks(mWeightDecrementer);
                        }
                        // Perform single decrement
                        if (intweight > 1) { // Check if weight is greater than 1 kg
                            intweight--;
                        }
                        weight2 = String.valueOf(intweight);
                        mcurrentweight.setText(weight2);
                        break;
                }
                return true;
            }
            private Runnable mWeightDecrementer = new Runnable() {
                @Override
                public void run() {
                    // Perform continuous decrement
                    if (intweight > 1) { // Check if weight is greater than 1 kg
                        intweight--;
                    }
                    weight2 = String.valueOf(intweight);
                    mcurrentweight.setText(weight2);
                    mHandler.postDelayed(this, mInterval);
                }
            };
        });

        mincrementage.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            private int mInterval = 100; // Interval for continuous increment in milliseconds

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button is pressed, start continuous increment
                        mHandler = new Handler();
                        mHandler.postDelayed(mAgeIncrementer, mInterval);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Button is released, stop continuous increment
                        if (mHandler != null) {
                            mHandler.removeCallbacks(mAgeIncrementer);
                        }
                        // Perform single increment
                        if (intage < 120) { // Check if age is less than 120 years
                            intage++;
                        }
                        age2 = String.valueOf(intage);
                        mcurrentage.setText(age2);
                        break;
                }
                return true;
            }

            private Runnable mAgeIncrementer = new Runnable() {
                @Override
                public void run() {
                    // Perform continuous increment
                    if (intage < 120) { // Check if age is less than 120 years
                        intage++;
                    }
                    age2 = String.valueOf(intage);
                    mcurrentage.setText(age2);
                    mHandler.postDelayed(this, mInterval);
                }
            };
        });

        mdecrementage.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            private int mInterval = 100; // Interval for continuous decrement in milliseconds

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button is pressed, start continuous decrement
                        mHandler = new Handler();
                        mHandler.postDelayed(mAgeDecrementer, mInterval);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Button is released, stop continuous decrement
                        if (mHandler != null) {
                            mHandler.removeCallbacks(mAgeDecrementer);
                        }
                        // Perform single decrement
                        if (intage > 1) { // Check if age is greater than 1 year
                            intage--;
                        }
                        age2 = String.valueOf(intage);
                        mcurrentage.setText(age2);
                        break;
                }
                return true;
            }

            private Runnable mAgeDecrementer = new Runnable() {
                @Override
                public void run() {
                    // Perform continuous decrement
                    if (intage > 1) { // Check if age is greater than 1 year
                        intage--;
                    }
                    age2 = String.valueOf(intage);
                    mcurrentage.setText(age2);
                    mHandler.postDelayed(this, mInterval);
                }
            };
        });


        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (typerofuser.equals("0")) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));

                    ImageView icon = layout.findViewById(R.id.toast_icon);
                    icon.setImageResource(R.drawable.ic_launcher);

                    TextView text = layout.findViewById(R.id.toast_text);
                    text.setText("Select Your Gender First");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
//                else if (mintprogress.equals("0")) {
//                    Toast.makeText(getApplicationContext(), "Select Your Height First", Toast.LENGTH_SHORT).show();
//                } else if (intage == 0 || intage < 0) {
//                    Toast.makeText(getApplicationContext(), "Age is Incorrect", Toast.LENGTH_SHORT).show();
//                } else if (intweight == 0 || intweight < 0) {
//                    Toast.makeText(getApplicationContext(), "Weight Is Incorrect", Toast.LENGTH_SHORT).show();
//                }
                else {

                    Intent intent = new Intent(MainActivity.this, bmiactivity.class);
                    intent.putExtra("gender", typerofuser);
                    intent.putExtra("height", mintprogress);
                    intent.putExtra("weight", weight2);
                    intent.putExtra("age", age2);
                    startActivity(intent);

                }
            }
        });

    }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

            startActivity(new Intent(MainActivity.this, InstructionsActivity.class));

        return super.onOptionsItemSelected(item);
    }

    }