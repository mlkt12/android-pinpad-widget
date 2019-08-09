package com.mlkt.development.pinpad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PinPadView pv = findViewById(R.id.pinpad);
        pv.setPasswordLength(4);
        pv.setKeyboardListener(new PinPadView.OnKeyboardClickedListener() {
            @Override
            public void onKeyboardKeyNumberClicked(Character inputChar) {
            }

            @Override
            public void onKeyboardKeyBackClicked() {
            }

            @Override
            public void onKeyboardKeyClearClicked() {
            }

            @Override
            public void onPasswordFilled(String password) {
            }
        });

    }
}
