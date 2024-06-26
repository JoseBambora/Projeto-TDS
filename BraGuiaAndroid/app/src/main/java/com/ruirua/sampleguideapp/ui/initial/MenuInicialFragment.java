package com.ruirua.sampleguideapp.ui.initial;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.general.App;
import com.ruirua.sampleguideapp.repositories.AppRepository;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.ui.history.HistoryActivity;
import com.ruirua.sampleguideapp.ui.pins.PinActivity;
import com.ruirua.sampleguideapp.ui.pins.PinFragment;
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
        setOnClicks(view);
        AppRepository.getInstance().getAppInfo().observe(getViewLifecycleOwner(), app -> this.fillInfo(view,app));
        hideButtons(view);
        return view;
    }

    private void hideButtons(View view) {
        Button buttonUserInfo = view.findViewById(R.id.userInfo);
        Button buttonTourRoutes = view.findViewById(R.id.buttonTourRoutes);
        Button buttonRegister = view.findViewById(R.id.buttonRegister);
        Button buttonLogin = view.findViewById(R.id.buttonLogin);
        Button buttonLogout = view.findViewById(R.id.buttonLogout);
        Button buttonHist = view.findViewById(R.id.historyButton);

        UserRepository ur = UserRepository.getInstance();
        if (!ur.isLogged()) {
            buttonTourRoutes.setVisibility(View.GONE);
            buttonUserInfo.setVisibility(View.GONE);
            buttonRegister.setVisibility(View.VISIBLE);
            buttonLogin.setVisibility(View.VISIBLE);
            buttonLogout.setVisibility(View.GONE);
            buttonHist.setVisibility(View.GONE);
        }
        else {
            buttonTourRoutes.setVisibility(View.VISIBLE);
            buttonUserInfo.setVisibility(View.VISIBLE);
            buttonRegister.setVisibility(View.GONE);
            buttonLogin.setVisibility(View.GONE);
            buttonLogout.setVisibility(View.VISIBLE);
            buttonHist.setVisibility(View.VISIBLE);
        }
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
        Button buttonUserInfo = view.findViewById(R.id.userInfo);
        Button buttonHist = view.findViewById(R.id.historyButton);
        Button buttonLogout = view.findViewById(R.id.buttonLogout);
        buttonRegister.setOnClickListener(v -> UIFuns.changeActivity(activity,UserActivity.class,setAction("register")));
        buttonLogin.setOnClickListener(v -> UIFuns.changeActivity(activity,UserActivity.class,setAction("login")));
        buttonList.setOnClickListener(v -> UIFuns.changeActivity(activity,TrailActivity.class,null));
        buttonUserInfo.setOnClickListener(v -> UIFuns.changeActivity(activity,UserActivity.class,setAction("userinfo")));
        buttonHist.setOnClickListener(v -> UIFuns.changeActivity(activity, HistoryActivity.class,null));
        buttonLogout.setOnClickListener(v-> {
            UserRepository.getInstance().logout();
            UIFuns.refreshPage(getFragmentManager(),this);
        } );

    }

    private void fillInfo(View v, App app) {
        TextView infoTv = v.findViewById(R.id.app_landing_page_text);
        TextView nameTv = v.findViewById(R.id.textViewHeader);
        TextView descTv = v.findViewById(R.id.textViewDescription);
        Button facebookButton = v.findViewById(R.id.facebookButton);
        Button umLogoButton = v.findViewById(R.id.umLogoButton);

        nameTv.setText(app.getApp_name());
        descTv.setText(app.getApp_desc());
        infoTv.setText(app.getPage_text());
        String facebookUrl = app.getSocialMedia().get(0).getUrl();
        facebookButton.setOnClickListener(view -> {Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(facebookUrl));
            startActivity(intent);});
        String umLogoUrl = app.getPartners().get(0).getUrl();
        umLogoButton.setOnClickListener(view -> {Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(umLogoUrl));
            startActivity(intent);});
    }
}

