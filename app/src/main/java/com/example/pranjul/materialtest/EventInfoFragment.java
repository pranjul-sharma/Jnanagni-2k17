package com.example.pranjul.materialtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pranjul on 31-01-2017.
 */

public class EventInfoFragment extends Fragment {
    TextView eventDesc,eventTimeVenue,eventJudgementalCriteria,eventCoordinators,eventTask,eventPrerequisites;
    String str="Coming soon ";
    String eventName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        eventName=bundle.getString("eventName");
    }

    ImageView eventImage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.event_info_fragment,container,false);
        eventDesc=(TextView)view.findViewById(R.id.text_event_desc);
        eventTimeVenue=(TextView)view.findViewById(R.id.text_event_timevenue);

        eventImage=(ImageView)view.findViewById(R.id.image_event_info);
        eventTask=(TextView)view.findViewById(R.id.text_event_task);
        eventPrerequisites=(TextView)view.findViewById(R.id.text_event_prerequisite);
        eventJudgementalCriteria=(TextView)view.findViewById(R.id.text_event_judgemental_criteria);
        eventCoordinators=(TextView)view.findViewById(R.id.text_event_coordinators);
        Toast.makeText(getActivity(),eventName+" clicked",Toast.LENGTH_SHORT).show();
        eventDesc.setText(str);
        eventTimeVenue.setText(str);
        eventTask.setText(str);
        eventImage.setImageResource(R.drawable.cipher);
        eventPrerequisites.setText(str);
        eventJudgementalCriteria.setText(str);
        eventCoordinators.setText(str);
        return view;
    }

}

