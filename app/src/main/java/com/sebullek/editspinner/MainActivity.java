package com.sebullek.editspinner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
    EditSpinner editSpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // you can use this in an Activity to get your layout root view, then pass it to disableAllEditText().
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        disableAllEditText(viewGroup);

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.edits_array));
        spinner.setAdapter(adapter);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editSpinner = (EditSpinner) findViewById(R.id.editSpinner);
        editSpinner2 = (EditSpinner) findViewById(R.id.editSpinner2);

        textView = (TextView)findViewById(R.id.textView);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.edits_array));
        editSpinner.setAdapter(adapter);
        editSpinner2.setAdapter(adapter);


        editText.setOnClickListener(onOnClickEvent);
        editText2.setOnClickListener(onOnClickEvent);
        editSpinner.setOnClickListener(doubleClickListener);
        editSpinner2.setOnClickListener(doubleClickListener);

        editText.setOnEditorActionListener(onEditorActionListener);
        editText2.setOnEditorActionListener(onEditorActionListener);
        editSpinner.setOnEditorActionListener(onEditorActionListener);
        editSpinner2.setOnEditorActionListener(onEditorActionListener);

        editText.setOnFocusChangeListener(onFocusChangeListener);
        editText2.setOnFocusChangeListener(onFocusChangeListener);
        editSpinner.setOnFocusChangeListener(onFocusChangeListener);
        editSpinner2.setOnFocusChangeListener(onFocusChangeListener);

    }


    private DoubleClickListener doubleClickListener = new DoubleClickListener() {
        @Override
        public void onSingleClick(View v) {
            Log.i(TAG, "onSingleClick " + v.getId());
            textView.setText("Single Click EditSpinner");
            disableEditText(v);

            EditSpinner editSpinner = (EditSpinner) v;
            editSpinner.showDropDown();
        }

        @Override
        public void onDoubleClick(View v) {
            Log.i(TAG, "onDoubleClick");
            textView.setText("Double Click EditSpinner");
            unableEditText(v);
            //z disable to enable
        }
    };

    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            //disableEditText(v);
            if (!hasFocus) {
                // your action here

                Log.i(TAG, "!hasFocus " + v.getId());
                disableEditText(v);
            }

            Log.i(TAG, "onFocusChange " + v.getId());
        }
    };

    private EditText.OnEditorActionListener onEditorActionListener = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Log.i(TAG, "onEditorAction()");

            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                Log.i(TAG, "actionId " + v.getId());
                disableEditText(v);
                return true;
            }
            Log.i(TAG, "onEditorAction " + v.getId());
            return false;
        }
    };

    private View.OnClickListener onOnClickEvent = new DoubleClickListener() {

        @Override
        public void onSingleClick(View v) {

            disableEditText(v);

            switch (v.getId())
            {
                case R.id.editText:
                    textView.setText("onOnClickEvent Single Click EditSpinner1");
                    Log.i(TAG, "onSingleClick");

                    //EditText editText = (EditText)findViewById(v.getId());
                    //editText.setInputType(InputType.TYPE_NULL);
                    break;
                case R.id.editText2:
                    textView.setText("onOnClickEvent Single Click EditSpinner2");
                    Log.i(TAG, "onSingleClick");
                    break;
            }

        }

        @Override
        public void onDoubleClick(View v) {

            unableEditText(v);

            switch (v.getId())
            {
                case R.id.editText:
                    textView.setText("onOnClickEvent Double Click EditSpinner1");
                    Log.i(TAG, "onDoubleClick");

                    //EditText editText = (EditText)findViewById(v.getId());
                    //editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                case R.id.editText2:
                    textView.setText("onOnClickEvent Double Click EditSpinner2");
                    Log.i(TAG, "onDoubleClick");
                    break;
            }
        }
    };

    private void disableEditText(View v) {
        //Log.i(TAG, "disableEditText");
        //Log.i(TAG, "disableEditText " + v.getId());


        EditText editText = (EditText)findViewById(v.getId());
        editText.setInputType(InputType.TYPE_NULL);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void unableEditText(View v) {
        //Log.i(TAG, "unableEditText");
        //Log.i(TAG, "disableEditText " + v.getId());

        EditText editText = (EditText)findViewById(v.getId());
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void disableAllEditText(ViewGroup viewGroup) {

        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup)
                disableAllEditText((ViewGroup) view);
            else if (view instanceof EditText) {
                EditText editText = (EditText) view;
                editText.setText(i + " id: " + editText.getId());
                disableEditText(editText);
            }

        }

    }
}
