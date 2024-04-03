package com.ruirua.sampleguideapp.ui;

import android.content.Intent;
import android.os.Bundle;
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

public class MenuInicialFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inicial_menu, container, false);

        setOnClicks(view);
        hideButtons(view);

        return view;
    }

    private void hideButtons(View view) {
        Button buttonUserInfo = view.findViewById(R.id.userInfo);
        Button buttonPin1 = view.findViewById(R.id.buttonPin1);
        UserRepository ur = UserRepository.getInstance();
        if (!ur.isLogged()) {
            buttonUserInfo.setVisibility(View.GONE);
        }
        if (!ur.isPremium()) {
            buttonPin1.setVisibility(View.GONE);
        }
    }

    private void setOnClicks(View view) {
        Button buttonRegister = view.findViewById(R.id.buttonRegister);
        Button buttonLogin = view.findViewById(R.id.buttonLogin);
        Button buttonList = view.findViewById(R.id.buttonTourRoutes);
        Button buttonEmergency = view.findViewById(R.id.buttonEmergency);
        Button buttonUserInfo = view.findViewById(R.id.userInfo);
        Button buttonPin1 = view.findViewById(R.id.buttonPin1);

        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), UserActivity.class);
            intent.putExtra("action", "register");
            startActivity(intent);
        });

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), UserActivity.class);
            intent.putExtra("action", "login");
            startActivity(intent);
        });

        buttonList.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), TrailActivity.class);
            startActivity(intent);
        });

        buttonEmergency.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Funcionalidade desativada para evitar destrastes", Toast.LENGTH_SHORT).show();
        });

        buttonUserInfo.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), UserActivity.class);
            intent.putExtra("action", "userinfo");
            startActivity(intent);
        });

        buttonPin1.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), PinActivity.class);
            startActivity(intent);
        });
    }
}
