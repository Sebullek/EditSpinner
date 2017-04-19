package com.sebullek.editspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    EditText editText;
    EditText editText2;
    TextView textView;
    Spinner spinner;
    SpinnerAdapter adapter;
    EditSpinner editSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);

        textView = (TextView)findViewById(R.id.textView);

        //boolean isClicked = editText.performClick();
        //System.out.println("isClicked = " + isClicked);

        /*
        editText.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Log.i(TAG, "onSingleClick");
                textView.setText("Single Click editTest");
            }

            @Override
            public void onDoubleClick(View v) {
                Log.i(TAG, "onDoubleClick");
                textView.setText("Double Click editTest");
            }
        });
*/
        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.edits_array));
        spinner.setAdapter(adapter);

        editSpinner = (EditSpinner) findViewById(R.id.editSpinner);
        editSpinner.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Log.i(TAG, "onSingleClick");
                textView.setText("Single Click EditSpinner");
            }

            @Override
            public void onDoubleClick(View v) {
                Log.i(TAG, "onDoubleClick");
                textView.setText("Double Click EditSpinner");
            }
        });


        editText.setOnClickListener(onOnClickEvent);
        editText2.setOnClickListener(onOnClickEvent);
    }

    private View.OnClickListener onOnClickEvent = new DoubleClickListener() {

        @Override
        public void onSingleClick(View v) {

            switch (v.getId())
            {
                case R.id.editText:
                    Log.i(TAG, "onSingleClick");
                    textView.setText("onOnClickEvent Single Click EditSpinner1");
                    break;
                case R.id.editText2:
                    Log.i(TAG, "onSingleClick");
                    textView.setText("onOnClickEvent Single Click EditSpinner2");
                    break;
            }

        }

        @Override
        public void onDoubleClick(View v) {
            switch (v.getId())
            {
                case R.id.editText:
                    Log.i(TAG, "onSingleClick");
                    textView.setText("onOnClickEvent Double Click EditSpinner1");
                    break;
                case R.id.editText2:
                    Log.i(TAG, "onSingleClick");
                    textView.setText("onOnClickEvent Double Click EditSpinner2");
                    break;
            }
        }
    };

}
