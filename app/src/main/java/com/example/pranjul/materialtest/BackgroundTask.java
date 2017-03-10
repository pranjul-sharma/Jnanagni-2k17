package com.example.pranjul.materialtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
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

class BackgroundTask extends AsyncTask<String,Void,String> {
    private Context context;
    private ProgressDialog progressDialog=null;
    private String data;
    private boolean prefChange=false,evtReg=false,evtRegList=false;
    BackgroundTask(Context context) {
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected String doInBackground(String... strings) {
        String taskTag=strings[0];
        final String REGISTER_TAG="register";
        final String LOGIN_TAG="login";
        final String EVENT_REG_TAG="evt-register";
        final String EVENT_REG_LIST_TAG="evt-reglist";
        final String EVENT_UNREG_TAG="evt-unregister";
        String register_url="https://jnanagni17.in/api/user";
        String event_reg_url="https://jnanagni17.in/api/event";
        URL url;
        String event_name;
        String email,first,last,phone,college,password,gender;

        JSONObject jsonObject;


        try {
            switch (taskTag) {
                case REGISTER_TAG:
                    url=new URL(register_url);
                    first=strings[1];
                    last=strings[2];
                    email=strings[3];
                    phone=strings[4];
                    college=strings[5];
                    password=strings[6];
                    gender=strings[7];

                    data = URLEncoder.encode("tag", "UTF-8") + "=" + URLEncoder.encode(REGISTER_TAG, "UTF-8") + "&"
                            + URLEncoder.encode("first-name", "UTF-8") + "=" + URLEncoder.encode(first, "UTF-8") + "&"
                        + URLEncoder.encode("last-name", "UTF-8") + "=" + URLEncoder.encode(last, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&"
                        + URLEncoder.encode("college", "UTF-8") + "=" + URLEncoder.encode(college, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                        +  URLEncoder.encode("conf-password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                            + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender.charAt(0)+"", "UTF-8");
                    break;
                case LOGIN_TAG:
                    url=new URL(register_url);
                    email=strings[1];
                    String pass=strings[2];

                    data = URLEncoder.encode("tag", "UTF-8") + "=" + URLEncoder.encode(LOGIN_TAG, "UTF-8") + "&"
                            +URLEncoder.encode("fritolay","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("kingfisher","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
                    prefChange=true;
                    break;

                case EVENT_REG_TAG:
                    url=new URL(event_reg_url);
                    Log.v("REG_URL","ab select hua");
                    email=strings[1];
                    event_name=strings[2];
                    data = URLEncoder.encode("tag", "UTF-8") + "=" + URLEncoder.encode(EVENT_REG_TAG, "UTF-8") + "&"
                            +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(event_name,"UTF-8");
                    evtReg=true;
                    break;
                case EVENT_UNREG_TAG:
                    url=new URL(event_reg_url);
                    email=strings[1];
                    event_name=strings[2];
                    data = URLEncoder.encode("tag", "UTF-8") + "=" + URLEncoder.encode(EVENT_REG_TAG, "UTF-8") + "&"
                            +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(event_name,"UTF-8");
                    break;
                case EVENT_REG_LIST_TAG:
                    url=new URL(event_reg_url);
                    email=strings[1];
                    data=URLEncoder.encode("tag","UTF-8")+"="+URLEncoder.encode(EVENT_REG_LIST_TAG,"UTF-8")+"&"
                            +URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
                    evtRegList=true;
                    break;
                default:url=new URL(register_url);
            }

            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestProperty("Api-Key","e80f491806701ca2c737b01e7ba5a37d");
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
                String jsonResponse;
                while ((jsonResponse = reader.readLine()) != null) {
                    stringBuilder.append(jsonResponse);
                }
                reader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Connection Failed";
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        try {
            JSONObject jsonObject = new JSONObject(s);
            String status = jsonObject.getString("status");
            String msg = jsonObject.getString("msg");
            if(status.charAt(0)=='0' && evtReg && jsonObject.getString("regstatus").charAt(0)=='2'){
                msg="sucessfully registered for this event . Please check your registered events list for more.";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(msg).setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();



            if ((status.charAt(0)=='0' && evtRegList)) {
                JSONArray jsonArray=jsonObject.getJSONArray("list");
                for (int i=0;i<jsonArray.length();i++){
                    DashBoardActivity.evtRegList.add(jsonArray.getJSONObject(i).getString("name"));
                    //DashBoardActivity.evtRegList.notifyAll();
                }
            }
            if (status.charAt(0)=='0' && prefChange) {
                Log.v("LOGIN","chl rha hai");
                String fname = jsonObject.getString("fname");
                String lname = jsonObject.getString("lname");
                String email = jsonObject.getString("email");
                SharedPreferences sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (!sharedPreferences.getBoolean("IS_SIGNED_IN", false)) {
                    editor.putBoolean("IS_SIGNED_IN", true);
                    Log.v("LOGIN"," intent chl rha hai");
                    editor.putString("USER_NAME", email);
                    editor.putString("F_NAME", fname);
                    editor.putString("L_NAME", lname);
                    editor.apply();
                    Intent i = new Intent(context, DashBoardActivity.class);
                    i.putExtra("fname", fname).putExtra("lname", lname).putExtra("email", email);
                    context.startActivity(i);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

