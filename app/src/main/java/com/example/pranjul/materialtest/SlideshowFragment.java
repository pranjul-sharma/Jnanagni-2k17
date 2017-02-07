package com.example.pranjul.materialtest;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlideshowFragment extends Fragment {
    private ImageSwitcher imageSwitcher;

    public SlideshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_slideshow, container, false);
        return layout;
    }

}
