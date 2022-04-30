package com.iutcalendar.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import com.calendar.iutcalendar.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;

public class URLSetterFragment extends Fragment {

    View view;
    Button scan, valide;
    EditText input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_url_setter, container, false);

        scan = view.findViewById(R.id.scanBtn);
        valide = view.findViewById(R.id.submitBtn);
        input = view.findViewById(R.id.inputURL);

        input.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("url_path", ""));

        scan.setOnClickListener(v -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
            intentIntegrator.setOrientationLocked(false);
            intentIntegrator.setCaptureActivity(CaptureActivity.class);

            intentIntegrator.initiateScan();
        });

        valide.setOnClickListener(v -> {
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("url_path", input.getText().toString()).commit();
            Toast.makeText(getContext(), "validé", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}