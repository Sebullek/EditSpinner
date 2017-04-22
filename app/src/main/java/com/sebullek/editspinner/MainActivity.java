package com.sebullek.editspinner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    EditText editText;
    EditText editText2;
    TextView textView;
    EditSpinner editSpinner;
    EditSpinner editSpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // you can use this in an Activity to get your layout root view, then pass it to disableAllEditText().
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        disableAllEditText(viewGroup);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editSpinner = (EditSpinner) findViewById(R.id.editSpinner);
        editSpinner2 = (EditSpinner) findViewById(R.id.editSpinner2);

        textView = (TextView)findViewById(R.id.textView);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.edits_array));
        editSpinner.setAdapter(adapter);
        editSpinner2.setAdapter(adapter);


        editText.setOnClickListener(onClickEvent);
        editText2.setOnClickListener(onClickEvent);
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

            disableEditText(v);

            checkSingleClickView(v);

            // show a list
            EditSpinner editSpinner = (EditSpinner) v;
            editSpinner.showDropDown();
        }

        @Override
        public void onDoubleClick(View v) {

            checkDoubleClickView(v);

            enableEditText(v);
        }
    };

    private View.OnClickListener onClickEvent = new DoubleClickListener() {

        @Override
        public void onSingleClick(View v) {

            disableEditText(v);

            checkSingleClickView(v);

        }

        @Override
        public void onDoubleClick(View v) {

            enableEditText(v);

            checkDoubleClickView(v);
        }
    };


    // Check which View was clicked
    private void checkSingleClickView(View v) {
        switch (v.getId())
        {
            case R.id.editText:
                textView.setText("Single Click editText");
                break;
            case R.id.editText2:
                textView.setText("Single Click editText2");
                break;
            case R.id.editSpinner:
                textView.setText("Single Click editSpinner");
                break;
            case R.id.editSpinner2:
                textView.setText("Single Click EditSpinner2");
                break;
        }
    }

    private void checkDoubleClickView(View v) {
        switch (v.getId())
        {
            case R.id.editText:
                textView.setText("Double Click editText");
                break;
            case R.id.editText2:
                textView.setText("Double Click editText2");
                break;
            case R.id.editSpinner:
                textView.setText("Double Click editSpinner");
                break;
            case R.id.editSpinner2:
                textView.setText("Double Click EditSpinner2");
                break;
        }
    }


    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            //disableEditText(v);


            if (!hasFocus) {
                // end focusing on View

                disableEditText(v);
            }

            if (hasFocus) {

                // start focusing on View
                // call onClick method

                v.performClick();
            }
        }
    };

    private EditText.OnEditorActionListener onEditorActionListener = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {

                // End typing text

                disableEditText(v);
                return true;
            }
            return false;
        }
    };

    private void disableEditText(View v) {

        // disable typing

        EditText editText = (EditText)findViewById(v.getId());
        editText.setInputType(InputType.TYPE_NULL);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void enableEditText(View v) {

        // enable typing

        EditText editText = (EditText)findViewById(v.getId());
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void disableAllEditText(ViewGroup viewGroup) {

        // disable typing in all of the EditTexts

        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup)
                disableAllEditText((ViewGroup) view);
            else if (view instanceof EditText) {
                EditText editText = (EditText) view;
                //editText.setText(i + " id: " + editText.getId());
                disableEditText(editText);
            }

        }

    }
}
