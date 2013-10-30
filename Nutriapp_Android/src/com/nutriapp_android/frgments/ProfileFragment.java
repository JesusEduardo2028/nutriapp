package com.nutriapp_android.frgments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nutriapp_android.MainActivity;
import com.nutriapp_android.R;



public class ProfileFragment extends Fragment {

	public static ProfileFragment profileFragment;
	private Activity a;
	private View view;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		profileFragment = this;
		 a = activity;
		super.onAttach(activity);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
				
		if (view != null){
			ViewGroup parent = (ViewGroup)view.getParent();
			if (parent != null){
				parent.removeView(view);
			}
		}
		 view = inflater.inflate(R.layout.layout_fragment_profile, container,
				false);
		 
		 		return view;
	}
	


}
