package com.ruirua.sampleguideapp.ui.user;

import static com.ruirua.sampleguideapp.ui.utils.UIFuns.configureTheme;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.ui.utils.Settings;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

import java.util.Set;

public class LoginFragment extends Fragment {
    private FragmentActivity activity;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        rootView.getContext().getTheme().applyStyle(R.style.AppThemeDark, true);
        setOnClicks(rootView);
        activity = getActivity();
        return rootView;
    }

    private void setOnClicks(View view) {
        Button buttonLoginParaRegister = view.findViewById(R.id.buttonLoginParaRegister);
        Button buttonLoginVoltar = view.findViewById(R.id.buttonLoginVoltar);
        Button buttonLoginLogin = view.findViewById(R.id.buttonLoginLogin);

        buttonLoginVoltar.setOnClickListener(v -> UIFuns.goBack(activity));
        buttonLoginParaRegister.setOnClickListener(v -> UIFuns.changeFragment(activity,this,activity.getSupportFragmentManager(),new RegisterFragment()));
        buttonLoginLogin.setOnClickListener(v -> login(view));
    }

    private void login(View view) {
        EditText editTextUsername = view.findViewById(R.id.editLoginTextUsername);
        EditText editTextPassword = view.findViewById(R.id.editLoginTextPassword);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        UserRepository ur = UserRepository.getInstance();
        Log.d("DebugApp","A realizar login " + ur + " " + ur.isLogged());
        ur.login(username,password,this::postLogin);
        //Toast.makeText(activity, "A realizar login", Toast.LENGTH_SHORT).show();

    }

    private void postLogin(boolean success) {
        Log.d("DebugApp","Login feito " + success);
        if (success) {
            UserRepository ur = UserRepository.getInstance();
            String t1 = ur.getCsrfToken();
            String t2 = ur.getSessionId();
            Toast.makeText(activity, "Login Feito", Toast.LENGTH_SHORT).show();
            Log.d("DebugApp", "Csrftoken: " + t1);
            Log.d("DebugApp", "Sessionid: " + t2);
            UIFuns.goBack(activity);
        }
        else {
            Toast.makeText(activity, "Dados Incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}