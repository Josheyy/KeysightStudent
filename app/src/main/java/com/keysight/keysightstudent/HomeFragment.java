package com.keysight.keysightstudent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View.OnClickListener;

public class HomeFragment extends Fragment {

    private View view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        //Add on create methods here.
        addPlacementListener();
        return view;
    }

    public void addPlacementListener() {
        ImageButton placementButton =(ImageButton)view.findViewById(R.id.placementButton);
        placementButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://jobsearch.keysight.com/search/?q=UK&q2=&alertId=&title=&location=&shifttype=intern&department=&date="));
                startActivity(intent);
            }
        });
    }

}
