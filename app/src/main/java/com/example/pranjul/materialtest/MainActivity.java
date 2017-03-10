package com.example.pranjul.materialtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    SharedPreferences sharedPreferences=getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                    if(!sharedPreferences.getBoolean("IS_SIGNED_IN",false)){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));}
                    else {
                        Intent intent=new Intent(MainActivity.this,DashBoardActivity.class);
                        intent.putExtra("fname",sharedPreferences.getString("F_NAME",""));
                        intent.putExtra("lname",sharedPreferences.getString("L_NAME",""));
                        intent.putExtra("email",sharedPreferences.getString("USER_NAME",""));
                        startActivity(intent);
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
