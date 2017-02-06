package com.example.pranjul.materialtest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        toolbar=(Toolbar)findViewById(R.id.toolbarevent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        setUpViewPager(viewPager);
        tabs=(TabLayout)findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }
    static class FragmentAdapter extends FragmentPagerAdapter {
        private  List<Fragment> fragmentList=new ArrayList<>();
        private final List<String> stringList=new ArrayList<>();


        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(EventFragment fragment,String title){
            fragmentList.add(fragment);
            stringList.add(title);

        }

        @Override
        public  String getPageTitle(int position) {
            return stringList.get(position);
        }
    }



    private void setUpViewPager(ViewPager upViewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(EventFragment.newInstance(0,"Technical"),"Technical");
        adapter.addFragment(EventFragment.newInstance(1,"Non Technical"),"Non Technical");
        adapter.addFragment(EventFragment.newInstance(2,"Game On"),"Game On");
        adapter.addFragment(EventFragment.newInstance(3,"Fun Events"),"Fun Events");
        adapter.addFragment(EventFragment.newInstance(4,"Cultural"),"Cultural");
        adapter.addFragment(EventFragment.newInstance(5,"Mega Shows"),"Mega Shows");
        upViewPager.setAdapter(adapter);

    }
}
