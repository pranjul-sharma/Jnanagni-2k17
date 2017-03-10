package com.example.pranjul.materialtest;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class RegisterFragment extends Fragment {
    private EditText firstName,lastName,email,phone,college,password;
    private TextInputLayout firstNameLayout,emailLayout,phoneLayout;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        //creating reference to views
        firstNameLayout=(TextInputLayout)view.findViewById(R.id.first_layout);
        emailLayout=(TextInputLayout)view.findViewById(R.id.email_layout);
        phoneLayout=(TextInputLayout)view.findViewById(R.id.phone_layout);
        firstName=(EditText)view.findViewById(R.id.first_name);
        lastName=(EditText)view.findViewById(R.id.last_name);
        email=(EditText)view.findViewById(R.id.email);
        phone=(EditText)view.findViewById(R.id.phone);
        password=(EditText)view.findViewById(R.id.password);
        college=(EditText)view.findViewById(R.id.college);
        radioGroup=(RadioGroup)view.findViewById(R.id.radio_gender_group);

        radioButton=(RadioButton)radioGroup.findViewById(R.id.radio_male);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=(RadioButton)radioGroup.findViewById(i);
            }
        });
        Button btnReg=(Button)view.findViewById(R.id.btnreg);
        Typeface tf=Typeface.createFromAsset(getContext().getAssets(),"Neptune.otf");
        btnReg.setTypeface(tf);


        //for input text validation
        firstName.addTextChangedListener(new MyTextWatcher(firstName));
        email.addTextChangedListener(new MyTextWatcher(firstName));
        phone.addTextChangedListener(new MyTextWatcher(firstName));

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if internet is not available
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
                //otherwise do
                else {
                    submitDetails();
                }
            }
        });
        return view;
    }

    private boolean isNetworkEnabled(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(HomeActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();
    }

    private void submitDetails()  {
        if(!validateFirstName())
            return;
        if (!validateEmail())
            return;
        if (!validatePhone())
            return;
        BackgroundTask registrationTask=new BackgroundTask(getActivity());
        registrationTask.execute("register",firstName.getText().toString(),lastName.getText().toString(),
                email.getText().toString(),phone.getText().toString(),college.getText().toString(),password.getText().toString(),radioButton.getText().toString());
    }


    private class MyTextWatcher implements TextWatcher {
        View view;
        private MyTextWatcher(View view) {
            this.view=view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.first_name:
                    validateFirstName();
                    break;
                case R.id.email:
                    validateEmail();
                    break;
                case R.id.phone:
                    validatePhone();
                    break;
            }
        }


    }

    private boolean validateFirstName() {
        String first=firstName.getText().toString().trim();

        if (first.isEmpty()){
            firstNameLayout.setError("Field can't be empty!");
            requestFocus(firstName);
            return false;
        }else{
            firstNameLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        String phoneNo=phone.getText().toString().trim();
        if(phoneNo.isEmpty()|| !isValidPhone(phoneNo)){
            phoneLayout.setError("invalid phone number");
            requestFocus(phone);
            return false;
        }else if(phoneNo.toCharArray().length!=10){
            phoneLayout.setError("number must contain 10 digits");
            requestFocus(phone);
            return false;
        }else{
            phoneLayout.setErrorEnabled(false);
        }

        return true;

    }

    private boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    private boolean validateEmail() {
        String emailStr=email.getText().toString().trim();
        if(emailStr.isEmpty() || !isValidEmail(emailStr)){
            emailLayout.setError("invalid email");
            requestFocus(email);
            return false;
        }else {
            emailLayout.setErrorEnabled(false);
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

