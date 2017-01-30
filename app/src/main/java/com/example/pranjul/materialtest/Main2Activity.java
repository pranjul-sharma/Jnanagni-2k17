package com.example.pranjul.materialtest;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView)findViewById(R.id.textJnanagi);
        btn=(Button)findViewById(R.id.button);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"Neptune.otf");
        textView.setTypeface(typeface);
    }

    public void callMe(View view){
        startActivity(new Intent(this,HomeActivity.class));
    }
}
