package com.ruirua.sampleguideapp.ui.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.user.UserInfo;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class UserFragment extends Fragment {
    public UserFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @SuppressLint("SetTextI18n")
    private void setFields(View view) {
        LiveData<UserInfo> liveData = UserRepository.getInstance().getUserLoggedInfo();
        liveData.observe(getViewLifecycleOwner(), ui -> {
            TextView message = view.findViewById(R.id.message_user);
            TextView f1 = view.findViewById(R.id.userName);
            TextView f2 = view.findViewById(R.id.userEmail);
            TextView f3 = view.findViewById(R.id.userType);
            TextView f4 = view.findViewById(R.id.userLastLogin);
            TextView f5 = view.findViewById(R.id.userLastName);
            message.setText("Olá " + ui.getUsername());
            f1.setText(ui.getUsername());
            f2.setText(ui.getEmail());
            f3.setText(ui.getUser_type());
            f4.setText(ui.getLast_login());
            f5.setText(ui.getLast_name());
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        setFields(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        UIFuns.configureTheme(getActivity());
    }
}