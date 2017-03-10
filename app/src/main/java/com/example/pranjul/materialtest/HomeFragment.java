package com.example.pranjul.materialtest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Random;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextInputLayout layoutEmail,layoutPassword;
    EditText editTextEmail,editTextPassword;
    Button btnLogin;
    Thread thread;
    private boolean isFragmentActive;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView= inflater.inflate(R.layout.fragment_home, container, false);
        final SharedPreferences sharedPreferences=getActivity().getSharedPreferences("myPreferences",Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("IS_SIGNED_IN",false)){
            LinearLayout linearLayout=(LinearLayout)myView.findViewById(R.id.layout_home);
            linearLayout.setVisibility(View.GONE);
        }
        layoutEmail=(TextInputLayout)myView.findViewById(R.id.user_layout);
        layoutPassword=(TextInputLayout)myView.findViewById(R.id.password_layout);
        editTextEmail=(EditText)myView.findViewById(R.id.user_name);
        editTextPassword=(EditText)myView.findViewById(R.id.text_password);
        btnLogin=(Button)myView.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isNetworkEnabled()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    AlertDialog alert=builder.setMessage("Please check your device internet connection.").
                            setTitle("Internet Unavailable").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create();
                    alert.show();
                }
                else {
                    loginProcess();
                }
            }
        });
        return myView;
    }

    private boolean isNetworkEnabled(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(HomeActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();
    }

    public void loginProcess() {

        if (!validateEmail())
            return;

        BackgroundTask registrationTask = new BackgroundTask(getActivity());
        registrationTask.execute("login", editTextEmail.getText().toString(), editTextPassword.getText().toString());

    }

    private boolean validateEmail() {
        String emailStr=editTextEmail.getText().toString().trim();
        if(emailStr.isEmpty() || !isValidEmail(emailStr)){
            layoutEmail.setError("invalid email");
            requestFocus(editTextEmail);
            return false;
        }else {
            layoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
