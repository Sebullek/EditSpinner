package com.sebullek.editspinner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Bastek on 17.04.2017.
 */

public class EditSpinner extends EditText {



    public EditSpinner(Context context) {
        super(context);
    }

    public EditSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFromAttributes(context, attrs, 0);
    }

    public EditSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFromAttributes(context, attrs, defStyleAttr);
    }

    private void initFromAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
    }


}
