package com.nutriapp_android.frgments;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutriapp_android.MainActivity;
import com.nutriapp_android.R;
import com.nutriapp_android.adapter.Pager_RecetaAdapter;



public class MenuDelDiaFragment extends Fragment {


	private FragmentActivity a;
	private View view;
	
	private ViewPager vPagerReceta1;
	private ViewPager vPagerReceta2;
	private ViewPager vPagerReceta3;
	private ViewPager vPagerReceta4;
	private ViewPager vPagerReceta5;

	private Pager_RecetaAdapter mPagerRecetaAdapter1;
	private Pager_RecetaAdapter mPagerRecetaAdapter2;
	private Pager_RecetaAdapter mPagerRecetaAdapter3;
	private Pager_RecetaAdapter mPagerRecetaAdapter4;
	private Pager_RecetaAdapter mPagerRecetaAdapter5;

	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
	
		 a = (FragmentActivity) activity;
		 
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
		 
		 
		 vPagerReceta1 = (ViewPager) view.findViewById(R.id.viewPagerReceta1);
		 vPagerReceta2 = (ViewPager) view.findViewById(R.id.viewPagerReceta2);
		 vPagerReceta3 = (ViewPager) view.findViewById(R.id.viewPagerReceta3);
		 vPagerReceta4 = (ViewPager) view.findViewById(R.id.viewPagerReceta4);
		 vPagerReceta5 = (ViewPager) view.findViewById(R.id.viewPagerReceta5);
		 
		 initialisePaging();
	
		 
		 		return view;
	}


	public void initialisePaging() {
		// TODO Auto-generated method stub
		   List<Fragment> fragments_vp1 = new Vector<Fragment>();
		   List<Fragment> fragments_vp2 = new Vector<Fragment>();
		   List<Fragment> fragments_vp3 = new Vector<Fragment>();
		   List<Fragment> fragments_vp4 = new Vector<Fragment>();
		   List<Fragment> fragments_vp5 = new Vector<Fragment>();
		   
		   fragments_vp1 = ponerFragments(fragments_vp1, 1);
		   fragments_vp2 = ponerFragments(fragments_vp2, 2);
		   fragments_vp3 = ponerFragments(fragments_vp3, 3);
		   fragments_vp4 = ponerFragments(fragments_vp4, 4);
		   fragments_vp5 = ponerFragments(fragments_vp5, 5);
		   
		   mPagerRecetaAdapter1 = new Pager_RecetaAdapter(MainActivity.a.getSupportFragmentManager(), fragments_vp1 );
		   mPagerRecetaAdapter2 = new Pager_RecetaAdapter(MainActivity.a.getSupportFragmentManager(), fragments_vp2 );
		   mPagerRecetaAdapter3 = new Pager_RecetaAdapter(MainActivity.a.getSupportFragmentManager(), fragments_vp3 );
		   mPagerRecetaAdapter4 = new Pager_RecetaAdapter(MainActivity.a.getSupportFragmentManager(), fragments_vp4 );
		   mPagerRecetaAdapter5 = new Pager_RecetaAdapter(MainActivity.a.getSupportFragmentManager(), fragments_vp5 );
		   
		   vPagerReceta1.setAdapter(mPagerRecetaAdapter1);
		   vPagerReceta2.setAdapter(mPagerRecetaAdapter2);
		   vPagerReceta3.setAdapter(mPagerRecetaAdapter3);
		   vPagerReceta4.setAdapter(mPagerRecetaAdapter4);
		   vPagerReceta5.setAdapter(mPagerRecetaAdapter5);
		   
		   

	}


	private List<Fragment> ponerFragments(List<Fragment> lista, int j) {
		// TODO Auto-generated method stub
		for (int i =0;i<8;i++){
			Fragment fragment = new RecetaListItemFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("comida", j);
			fragment.setArguments(bundle);
			lista.add(fragment);
		}
		return lista;
	}
	


}

	