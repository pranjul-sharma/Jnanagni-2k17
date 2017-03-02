package com.example.pranjul.materialtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

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

/**
 * Created by Pranjul on 02-03-2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context context;
    ProgressDialog progressDialog=null;
    String register_url="http://test.jnanagni17.in/pre-register/api";

    BackgroundTask(Context context) {
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
        String password=strings[5];
        String gender=strings[6];


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
                    + URLEncoder.encode("college","UTF-8")+"="+URLEncoder.encode(college,"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                    +URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8");
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

