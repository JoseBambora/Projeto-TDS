package com.ruirua.sampleguideapp.ui.initial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.ui.pins.PinActivity;
import com.ruirua.sampleguideapp.ui.trails.TrailActivity;
import com.ruirua.sampleguideapp.ui.user.UserActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

import java.util.HashMap;
import java.util.Map;

public class MenuInicialFragment extends Fragment {
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inicial_menu, container, false);
        activity = getActivity();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setOnClicks(getView());
        hideButtons(getView());

    }

    private void hideButtons(View view) {
        Button buttonUserInfo = view.findViewById(R.id.userInfo);
        Button buttonPin1 = view.findViewById(R.id.buttonPin1);
        UserRepository ur = UserRepository.getInstance();
        Log.d("DebugApp","Entrou agora " + ur.isLogged() + " " + ur.isPremium());
        if (!ur.isLogged())
            buttonUserInfo.setVisibility(View.GONE);
        else
            buttonUserInfo.setVisibility(View.VISIBLE);
        if (!ur.isPremium())
            buttonPin1.setVisibility(View.GONE);
        else
            buttonPin1.setVisibility(View.VISIBLE);
    }

    private Map<String,String> setAction(String value) {
        Map<String,String> params = new HashMap<>();
        params.put("action", value);
        return params;
    }
    private void googleMaps() {
        double originLat = 41.4417;
        double originLng = -8.2966;
        double destLat = 41.5503;
        double destLng = -8.4201;

        Intent mapIntent = UIFuns.intentGoogleMaps(originLat,originLng,destLat,destLng);
        if (mapIntent.resolveActivity(activity.getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(activity, "Google Maps não está instalado", Toast.LENGTH_SHORT).show();
        }
    }
    private void setOnClicks(View view) {
        Button buttonRegister = view.findViewById(R.id.buttonRegister);
        Button buttonLogin = view.findViewById(R.id.buttonLogin);
        Button buttonList = view.findViewById(R.id.buttonTourRoutes);
        Button buttonEmergency = view.findViewById(R.id.buttonEmergency);
        Button buttonUserInfo = view.findViewById(R.id.userInfo);
        Button buttonPin1 = view.findViewById(R.id.buttonPin1);
        Button buttonGM = view.findViewById(R.id.googleMaps);

        Context context = activity.getApplicationContext();

        buttonRegister.setOnClickListener(v -> UIFuns.changeActivity(context,UserActivity.class,setAction("register")));
        buttonLogin.setOnClickListener(v -> UIFuns.changeActivity(context,UserActivity.class,setAction("login")));
        buttonList.setOnClickListener(v -> UIFuns.changeActivity(context,TrailActivity.class,null));
        buttonUserInfo.setOnClickListener(v -> UIFuns.changeActivity(context,UserActivity.class,setAction("userinfo")));
        buttonPin1.setOnClickListener(v ->  UIFuns.changeActivity(context, PinActivity.class,null));
        buttonEmergency.setOnClickListener(v -> Toast.makeText(requireContext(), "Funcionalidade desativada para evitar destrastes", Toast.LENGTH_SHORT).show());
        buttonGM.setOnClickListener(v ->googleMaps());
    }
}
