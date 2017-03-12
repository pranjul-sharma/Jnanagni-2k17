package com.example.pranjul.materialtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {
    Button btnEvtList;
    static List<String> evtRegList=new ArrayList<>();
    FloatingActionButton btnEvent;
    TextView fname,lname,emailView;
    ListView regList;
    boolean check_logout=false;
    String first_name,last_name,email;
    Toolbar toolbar;
    boolean taskCheck=false;
    MyListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Intent intent=getIntent();
        first_name=intent.getStringExtra("fname");
        last_name=intent.getStringExtra("lname");
        email=intent.getStringExtra("email");
        fname=(TextView)findViewById(R.id.textView2);
        toolbar=(Toolbar)findViewById(R.id.toolbar_dashboard);
        regList=(ListView)findViewById(R.id.reg_list_view);
        btnEvtList=(Button)findViewById(R.id.button2);
        toolbar.setTitle("DashBoard");
        setSupportActionBar(toolbar);
        lname=(TextView)findViewById(R.id.textView3);
        emailView=(TextView)findViewById(R.id.textView4);
        fname.setText(first_name);
        lname.setText(last_name);
        emailView.setText(email);
        btnEvent=(FloatingActionButton) findViewById(R.id.button_event);

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardActivity.this,Main3Activity.class));
            }


        });

        btnEvtList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BackgroundTask backgroundTask=new BackgroundTask(DashBoardActivity.this){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        listAdapter=new MyListAdapter(DashBoardActivity.this,evtRegList);
                        regList.setAdapter(listAdapter);
                    }
                };
                backgroundTask.execute("evt-reglist",email);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (check_logout){
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.dashboard_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("logout")){
            SharedPreferences sharedPreferences=getSharedPreferences("myPreferences",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("F_NAME","").putString("L_NAME","").putString("USER_NAME","");
            editor.putBoolean("IS_SIGNED_IN",false);
            check_logout=true;
            editor.apply();
            startActivity(new Intent(DashBoardActivity.this,HomeActivity.class));
            return true;
        }
        if (item.getTitle().equals("share this app")){
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Jnanagni 2K17");
                String shareStr = "\nDownload our college techfest Jnanagni 2K17 android app from the link :-\n\n";
                shareStr = shareStr + "paste_link_here \n\n";
                i.putExtra(Intent.EXTRA_TEXT, shareStr);
                startActivity(Intent.createChooser(i, "select any way to spread"));
            } catch(Exception e) {
            }
        }
        return super.onOptionsItemSelected(item);
    }

    class MyListAdapter extends BaseAdapter{
        Context context;
        TextView ttname;
        Button btnDesc,btnUnreg;
        List<String> list=new ArrayList<>();

        MyListAdapter(Context context,List<String> list){
            this.list=list;
            this.context=context;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View myView=LayoutInflater.from(context).inflate(R.layout.list_item_row,viewGroup,false);
            ttname=(TextView)myView.findViewById(R.id.list_item_textview);
            btnDesc=(Button)myView.findViewById(R.id.btn_desc);
            btnUnreg=(Button)myView.findViewById(R.id.btn_unreg);

            ttname.setText(list.get(i));


            btnDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(DashBoardActivity.this,EventInfoActivity.class);
                    intent.putExtra("eventName",list.get(i));
                    context.startActivity(intent);
                }
            });

            btnUnreg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BackgroundTask backgroundTask=new BackgroundTask(context);
                    String extra=list.get(i);
                    backgroundTask.execute("evt-unregister",email,extra.toLowerCase());
                }
            });
            return myView;
        }




    }

}
