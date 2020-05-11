package com.example.beex.utils.Mask;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by Tox on 12/9/15.
 */
public class MaskTextWatcher implements TextWatcher {

    private MaskFormatter formatter;

    /**
     * A reference of watched TextView (or EditText)
     */
    private TextView textView;

    private String currentText;

    public MaskTextWatcher(TextView textView, MaskFormatter formatter) {
        this.textView = textView;
        this.formatter = formatter;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!charSequence.toString().equals(currentText)) {
            currentText = formatter.format(charSequence.toString());
            textView.setText(currentText);
            if (textView instanceof EditText) {
                EditText editText = (EditText) textView;
                editText.setSelection(currentText.length());
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
