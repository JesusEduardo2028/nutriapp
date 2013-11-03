package com.nutriapp_android.frgments;

import java.util.Calendar;

import com.nutriapp_android.R;
import com.nutriapp_android.adapter.MultiSpinnerAdapter;
import static com.nutriapp_android.config.ConstantsClient.ARRAY_SALUD;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

public class UserProfileFragment extends Fragment {
	public static EditText fecha_nacimiento, peso_actual, peso_meta, estatura;
	public static RadioButton sexo;
	public static MultiSpinnerAdapter estado_salud;
	private int year, month, day;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        
        sexo = (RadioButton) view.findViewById(R.id.radioFemale);
        peso_actual = (EditText) view.findViewById(R.id.inputPesoActual);
        peso_meta = (EditText) view.findViewById(R.id.inputPesoMeta);
        estatura = (EditText) view.findViewById(R.id.inputEstatura);
        fecha_nacimiento = (EditText) view.findViewById(R.id.inputFechaNacimiento);
        
        Button fecha = (Button) view.findViewById(R.id.btnFechaNacimiento);
        fecha.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new SelectDateFragment();
				newFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
			}
		});
          
        estado_salud = (MultiSpinnerAdapter) view.findViewById(R.id.inputEstadoSalud);  
        estado_salud.setItems(ARRAY_SALUD);
        
        seleccionarFechaActual();
        
        return view;
    }
	
	private void seleccionarFechaActual() {
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		fecha_nacimiento.setText(month+"/"+day+"/"+year);
	}
	
	private void modificarFechaVista(int year, int month, int day) {
		fecha_nacimiento.setText(month+"/"+day+"/"+year);
	}
	
	@SuppressLint("ValidFragment")
	public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar calendar = Calendar.getInstance();
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
		}
		
		public void onDateSet(DatePicker view, int yy, int mm, int dd) {
			modificarFechaVista(yy, mm+1, dd);
		}
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

}
