package com.ruirua.sampleguideapp.ui.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.user.UserInfo;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.ui.MainActivity;

public class UserFragment extends Fragment {
    private UserInfo ui;
    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setFieldsAux(View field, String textview, String edittext) {
        TextView tv = field.findViewById(R.id.textId);
        TextView et = field.findViewById(R.id.editId);
        tv.setText(textview);
        et.setText(edittext);
    }
    private UserInfo teste() {
        UserInfo ui = new UserInfo();
        ui.setEmail("abc@gmail.com");
        ui.setUsername("abc");
        ui.setUser_type("Premium");
        ui.setLast_login("Agora");
        ui.setLast_name("def");
        return ui;
    }
    @SuppressLint("SetTextI18n")
    private void setFields(View view) {
        LiveData<UserInfo> liveData = UserRepository.getInstance().getUserLoggedInfo();
        liveData.observe(getViewLifecycleOwner(), ui -> {

            Log.d("DebugApp",ui.toString());
            View f1 = view.findViewById(R.id.field1);
            setFieldsAux(f1,"Nome de Utilizador",ui.getUsername());

            View f2 = view.findViewById(R.id.field2);
            setFieldsAux(f2,"Último nome",ui.getLast_name());


            View f3 = view.findViewById(R.id.field3);
            setFieldsAux(f3,"Email",ui.getEmail());


            View f4 = view.findViewById(R.id.field4);
            setFieldsAux(f4,"Tipo de utilizador",ui.getUser_type());


            View f5 = view.findViewById(R.id.field5);
            setFieldsAux(f5,"Último login",ui.getLast_login());

            TextView tv = view.findViewById(R.id.message_user);
            tv.setText("Olá " + ui.getUsername() + "!");
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        setFields(view);
        MaterialButton bt = view.findViewById(R.id.goBack);
        bt.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        return view;
    }
}