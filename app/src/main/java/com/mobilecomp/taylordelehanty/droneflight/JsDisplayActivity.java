package com.mobilecomp.taylordelehanty.droneflight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class JsDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_display);
        TextView tv = (TextView) findViewById(R.id.jsScript);
        tv.setText(Commands.jsString);
    }

}
