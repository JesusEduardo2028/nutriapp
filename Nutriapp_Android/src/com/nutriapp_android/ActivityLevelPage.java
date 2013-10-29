package com.nutriapp_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class ActivityLevelPage extends Fragment {
	public static RadioGroup actividades;
	public static RadioGroup objetivos;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_activity_level, container, false);
        
        actividades = (RadioGroup) view.findViewById(R.id.radioBtnActividad);
        objetivos = (RadioGroup) view.findViewById(R.id.radioBtnObjetivos);
        
        return view;
    }
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

}
