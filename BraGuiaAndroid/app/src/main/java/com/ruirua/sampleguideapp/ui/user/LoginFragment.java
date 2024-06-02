package com.ruirua.sampleguideapp.ui.user;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class LoginFragment extends Fragment {
    private GoBackInterface goBackInterface;
    private FragmentManager fragmentManager;

    public LoginFragment(GoBackInterface goBackInterface, FragmentManager fragmentManager) {
        this.goBackInterface = goBackInterface;
        this.fragmentManager = fragmentManager;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        setOnClicks(rootView);
        rootView.findViewById(R.id.progress_bar_1).setVisibility(View.GONE);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        UIFuns.configureTheme(getActivity());
    }

    private void setOnClicks(View view) {
        Button buttonLoginParaRegister = view.findViewById(R.id.buttonLoginParaRegister);
        Button buttonLoginLogin = view.findViewById(R.id.buttonLoginLogin);

        buttonLoginParaRegister.setOnClickListener(v -> UIFuns.changeFragment(fragmentManager, new RegisterFragment(this.goBackInterface,this.fragmentManager)));

        buttonLoginLogin.setOnClickListener(v -> login(view));
    }
    private void failed(View view) {
        Toast.makeText(getActivity(), "Erro com o servidor backend. A tentar login novamente.", Toast.LENGTH_SHORT).show();
        this.login(view);
    }
    private void login(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        EditText editTextUsername = view.findViewById(R.id.editLoginTextUsername);
        EditText editTextPassword = view.findViewById(R.id.editLoginTextPassword);
        view.findViewById(R.id.progress_bar_1).setVisibility(View.VISIBLE);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        UserRepository ur = UserRepository.getInstance();
        Log.d("DebugApp","A realizar login " + ur + " " + ur.isLogged());
        ur.login(username,password,b -> this.postLogin(b,view),t -> this.failed(view));
        //Toast.makeText(activity, "A realizar login", Toast.LENGTH_SHORT).show();

    }

    private void postLogin(boolean success,View view) {
        Log.d("DebugApp","Login feito " + success);
        if (success) {
            UserRepository ur = UserRepository.getInstance();
            String t1 = ur.getCsrfToken();
            String t2 = ur.getSessionId();
            Toast.makeText(getActivity(), "Login Feito", Toast.LENGTH_SHORT).show();
            Log.d("DebugApp", "Csrftoken: " + t1);
            Log.d("DebugApp", "Sessionid: " + t2);
            goBackInterface.goBack();
        }
        else {
            view.findViewById(R.id.progress_bar_1).setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Dados Incorretos", Toast.LENGTH_SHORT).show();
        }
    }

}