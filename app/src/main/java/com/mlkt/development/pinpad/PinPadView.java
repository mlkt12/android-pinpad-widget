package com.mlkt.development.pinpad;

import android.content.Context;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PinPadView extends FrameLayout implements View.OnClickListener {

    private EditText mPasswordField;
    private OnKeyboardClickedListener listener;

    public PinPadView(Context context) {
        super(context);
        init();
    }

    public PinPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PinPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_pinpad, this);
        initViews();
    }

    private void initViews() {
        mPasswordField = $(R.id.password_field);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordLength != 0 && s.length() == passwordLength) {
                    listener.onPasswordFilled(getInputText());
                }
            }
        });

        $(R.id.t9_key_0).setOnClickListener(this);
        $(R.id.t9_key_1).setOnClickListener(this);
        $(R.id.t9_key_2).setOnClickListener(this);
        $(R.id.t9_key_3).setOnClickListener(this);
        $(R.id.t9_key_4).setOnClickListener(this);
        $(R.id.t9_key_5).setOnClickListener(this);
        $(R.id.t9_key_6).setOnClickListener(this);
        $(R.id.t9_key_7).setOnClickListener(this);
        $(R.id.t9_key_8).setOnClickListener(this);
        $(R.id.t9_key_9).setOnClickListener(this);
        $(R.id.t9_key_clear).setOnClickListener(this);
        $(R.id.t9_key_backspace).setOnClickListener(this);
    }

    public void setKeyboardListener(OnKeyboardClickedListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null && v.getTag().equals("number_button")) {
            mPasswordField.append(((TextView) v).getText());
            listener.onKeyboardKeyNumberClicked(((TextView) v).getText().charAt(0));
            return;
        }

        switch (v.getId()) {
            case R.id.t9_key_clear: {
                mPasswordField.setText(null);
                listener.onKeyboardKeyClearClicked();
            }
            break;
            case R.id.t9_key_backspace: {
                Editable editable = mPasswordField.getText();
                int charCount = editable.length();
                if (charCount > 0) {
                    editable.delete(charCount - 1, charCount);
                }
                listener.onKeyboardKeyBackClicked();
            }
            break;
        }
    }

    public String getInputText() {
        return mPasswordField.getText().toString();
    }

    private int passwordLength;
    public void setPasswordLength(int maxLength) {
        passwordLength = maxLength;
        mPasswordField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(passwordLength)});
    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }

    public interface OnKeyboardClickedListener{
        void onKeyboardKeyNumberClicked(Character inputChar);
        void onKeyboardKeyBackClicked();
        void onKeyboardKeyClearClicked();
        void onPasswordFilled(String password);
    }
}
