package com.example.pranjul.materialtest;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
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
                            R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
                            R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o,
            R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s};
    //private ThreadClass obj;
    private int currPos= new Random().nextInt(19);
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
        imageSwitcher.setImageDrawable(null);
        imageSwitcher.setImageResource(images[currPos]);
        isFragmentActive=true;
        final Random rand=new Random();
        imageSwitcher.postDelayed(new Runnable() {
            @Override
            public void run() {
                int pos=rand.nextInt(19);
                while(pos==currPos)
                    pos=rand.nextInt(19);
                currPos=pos;
                imageSwitcher.setImageDrawable(null);
                imageSwitcher.setImageResource(images[currPos]);
                imageSwitcher.postDelayed(this, 4000);
            }
        }, 4000);
        return layout;
    }

    @Override
    public View makeView() {
        ImageView iView = new ImageView(HomeActivity.currObject);
        iView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FullScreenActivity.fImage= BitmapFactory.decodeResource(HomeActivity.currObject.getResources(), images[currPos]);
                startActivity(new Intent(HomeActivity.currObject, FullScreenActivity.class));
            }
        });
        iView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iView.setLayoutParams(new ImageSwitcher.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        return iView;
    }

}
