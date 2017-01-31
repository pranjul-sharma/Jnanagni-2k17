package com.example.pranjul.materialtest;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {
    private Button send;
    private EditText message;
    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), android.R.style.Theme_Holo);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View layout= localInflater.inflate(R.layout.fragment_feedback, container, false);
        send=(Button)layout.findViewById(R.id.send);
        message=(EditText)layout.findViewById(R.id.message);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent=new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_TEXT, message.getText());
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"singhamitch@gmail.com"});
                startActivity(emailIntent);
            }
        });
        return layout;
    }
}
