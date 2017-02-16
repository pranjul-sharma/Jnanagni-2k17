package com.example.pranjul.materialtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;




public class RegisterFragment extends Fragment{
    private EditText firstName,lastName,email,phone,college;
    private TextInputLayout firstNameLayout,emailLayout,phoneLayout;





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
        college=(EditText)view.findViewById(R.id.college);
        Button btnReg=(Button)view.findViewById(R.id.btnReg);

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



        RegistrationTask registrationTask=new RegistrationTask(getActivity());
        registrationTask.execute(firstName.getText().toString(),lastName.getText().toString(),
                email.getText().toString(),phone.getText().toString(),college.getText().toString());


    }



    public class RegistrationTask extends AsyncTask<String,Void,String>{
        Context context;
        ProgressDialog progressDialog=null;
        String register_url="http://test.jnanagni17.in/pre-register/api";

        private RegistrationTask(Context context) {
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(context);
            progressDialog.setMessage("Registering...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            String first=strings[0];
            String last=strings[1];
            String email=strings[2];
            String phone=strings[3];
            String college=strings[4];


            try {
                URL url=new URL(register_url);

                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestProperty("Api-Key","e80f491806701ca2c737b01e7ba5a37d");
                httpURLConnection.setRequestMethod("POST");


                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data= URLEncoder.encode("first-name","UTF-8")+"="+URLEncoder.encode(first,"UTF-8")+"&"
                        + URLEncoder.encode("last-name","UTF-8")+"="+URLEncoder.encode(last,"UTF-8")+"&"
                        + URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        + URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                        + URLEncoder.encode("college","UTF-8")+"="+URLEncoder.encode(college,"UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                String jsonResponse;
                while ((jsonResponse=reader.readLine())!=null){
                        stringBuilder.append(jsonResponse);
                }
                reader.close();
                inputStream.close();

                JSONObject jsonObject=new JSONObject(stringBuilder.toString());
                httpURLConnection.disconnect();

                return jsonObject.getString("msg");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "Connection Failed";
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setMessage(s).setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
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
            firstNameLayout.setError("Field can't be empty!!!");
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

