package com.example.pranjul.materialtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

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
import java.util.HashMap;

class BackgroundTask extends AsyncTask<String,Void,String> {
    private Context context;
    private HashMap<String,String> mMap;
    private ProgressDialog progressDialog=null;
    private String data;
    private boolean prefChange=false,evtReg=false,evtRegList=false,evtUnreg=false;
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
                    email=strings[1];
                    event_name=strings[2];
                    mMap=new HashMap<>();
                    initMap();
                    event_name=mMap.get(event_name);
                    data = URLEncoder.encode("tag", "UTF-8") + "=" + URLEncoder.encode(EVENT_REG_TAG, "UTF-8") + "&"
                            +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(event_name,"UTF-8");
                    evtReg=true;
                    break;
                case EVENT_UNREG_TAG:
                    url=new URL(event_reg_url);
                    email=strings[1];
                    event_name=strings[2];
                    mMap=new HashMap<>();
                    initMap();
                    event_name=mMap.get(event_name);
                    data = URLEncoder.encode("tag", "UTF-8") + "=" + URLEncoder.encode(EVENT_UNREG_TAG, "UTF-8") + "&"
                            +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(event_name,"UTF-8");
                    evtUnreg=true;
                    break;
                case EVENT_REG_LIST_TAG:
                    url=new URL(event_reg_url);
                    email=strings[1];
                    data=URLEncoder.encode("tag","UTF-8")+"="+URLEncoder.encode(EVENT_REG_LIST_TAG,"UTF-8")+"&"
                            +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
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
            }if(status.charAt(0)=='0' && evtUnreg && jsonObject.getString("regstatus").charAt(0)=='1'){
                msg="unregistered successfully . Please refresh your registered list .";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(msg).setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            if(!msg.equals("")){
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            if ((status.charAt(0)=='0' && evtRegList)) {
                JSONArray jsonArray=jsonObject.getJSONArray("list");
                DashBoardActivity.evtRegList.clear();
                for (int i=0;i<jsonArray.length();i++){
                    JSONArray array= (JSONArray) jsonArray.get(i);
                    DashBoardActivity.evtRegList.add(array.getString(1));
                }
            }
            if (status.charAt(0)=='0' && prefChange) {
                String fname = jsonObject.getString("fname");
                String lname = jsonObject.getString("lname");
                String email = jsonObject.getString("email");
                SharedPreferences sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (!sharedPreferences.getBoolean("IS_SIGNED_IN", false)) {
                    editor.putBoolean("IS_SIGNED_IN", true);
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

    private void initMap() {
        mMap.put("hydroriser","tevent-0");
        mMap.put("ci-pher","tevent-1");
        mMap.put("electroguisal","tevent-2");
        mMap.put("annihilator","tevent-3");
        mMap.put("appitude","tevent-4");
        mMap.put("ex-gesis","tevent-5");
        mMap.put("concatenation","tevent-6");
        mMap.put("electricio","tevent-7");
        mMap.put("tinkerer","tevent-8");
        mMap.put("nopc","tevent-9");
        mMap.put("inclino","tevent-10");
        mMap.put("cuandigo","tevent-11");
        mMap.put("ameliorator","tevent-12");
        mMap.put("abhivyakti","ntevent-0");
        mMap.put("third vision","ntevent-1");
        mMap.put("mist treasure hunt","ntevent-2");
        mMap.put("q-cognito","ntevent-3");
        mMap.put("freedoscrawl","ntevent-4");
        mMap.put("kalakriti","ntevent-5");
        mMap.put("crafts-villa","ntevent-6");
        mMap.put("enthuse","ntevent-7");
        mMap.put("cricket keeda","ntevent-8");
        mMap.put("fancy footwork","cevent-0");
        mMap.put("sargam","cevent-1");
        mMap.put("kritika","cevent-2");
        mMap.put("lol","cevent-3");
        mMap.put("nautankishala","cevent-4");
        mMap.put("samagam","workshop-0");
        mMap.put("celebrity visit","workshop-1");
        mMap.put("startup fair","workshop-2");
        mMap.put("rock syndrome","workshop-3");
        mMap.put("carrom","sevent-0");
        mMap.put("table tennis","sevent-1");
        mMap.put("chess","sevent-2");
        mMap.put("badminton","sevent-3");
        mMap.put("need for speed","sevent-4");
        mMap.put("counter strike","sevent-5");
        mMap.put("fifa","sevent-6");
        mMap.put("rubik\'s cube","fevent-0");
        mMap.put("mini-militia","fevent-1");
        mMap.put("bowling","fevent-2");
        mMap.put("dart","fevent-3");
        mMap.put("throwball","fevent-4");
    }

}

