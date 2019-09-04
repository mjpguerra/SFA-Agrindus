package com.mobilefast.assinatura;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

class GraphicsActivity extends AppCompatActivity {
    // set to true to test Picture
    private static final boolean TEST_PICTURE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        if (TEST_PICTURE) {
            ViewGroup vg = new PictureLayout(this);
            vg.addView(view);
            view = vg;
        }

        super.setContentView(view);
    }
}