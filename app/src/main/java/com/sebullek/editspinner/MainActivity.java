package com.sebullek.editspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {




    private static final String TAG = "MainActivity";

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);

        textView = (TextView)findViewById(R.id.textView);

        //boolean isClicked = editText.performClick();
        //System.out.println("isClicked = " + isClicked);

        editText.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Log.i(TAG, "onSingleClick");
                textView.setText("Single Click");
            }

            @Override
            public void onDoubleClick(View v) {
                Log.i(TAG, "onDoubleClick");
                textView.setText("Double Click");
            }
        });
    }
}
