package com.example.pranjul.materialtest;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ViewSwitcher.ViewFactory {
    private ImageSwitcher imageSwitcher;
    private int images[]={R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
                            R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j};
    //private ThreadClass obj;
    private int currPos= new Random().nextInt(10);
    private boolean isFragmentActive;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_home, container, false);
        imageSwitcher=(ImageSwitcher)layout.findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(HomeActivity.currObject,
                android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(HomeActivity.currObject,
                android.R.anim.fade_out));
        imageSwitcher.setImageResource(images[currPos]);
        isFragmentActive=true;
        final Random rand=new Random();
        imageSwitcher.postDelayed(new Runnable() {
            @Override
            public void run() {
                int pos=rand.nextInt(10);
                while(pos==currPos)
                    pos=rand.nextInt(10);
                currPos=pos;
                imageSwitcher.setImageDrawable(null);
                imageSwitcher.setImageResource(images[currPos]);
                imageSwitcher.postDelayed(this, 4000);
            }
        }, 4000);
        return layout;
    }
    /*public class ImageAdapter extends BaseAdapter {

        private Context ctx;

        public ImageAdapter(Context c) {
            ctx = c;
        }

        public int getCount() {

            return images.length;
        }

        public Object getItem(int arg0) {

            return arg0;
        }

        public long getItemId(int arg0) {

            return arg0;
        }

        public View getView(int arg0, View arg1, ViewGroup arg2) {
            ImageView iView = new ImageView(ctx);
            iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iView.setLayoutParams(new
                    ImageSwitcher.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            iView.setImageResource(images[arg0]);
            return iView;
        }
    }*/

    @Override
    public View makeView() {
        ImageView iView = new ImageView(HomeActivity.currObject);
        iView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iView.setLayoutParams(new ImageSwitcher.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        return iView;
    }

    /*private class ThreadClass extends AsyncTask<Void, Void, Void> {
        @Override

        protected Void doInBackground(Void... voids) {

            return null;
        }
    */
}
