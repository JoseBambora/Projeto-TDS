package com.ruirua.sampleguideapp.ui.utils;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;

public class OurActivity extends AppCompatActivity implements GoBackInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIFuns.configureTheme(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        setOnClick();
    }
    @Override
    public void goBack() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            UIFuns.configureTheme(this);
        }
        else
            UIFuns.finishActivity(this);
    }

    public void setOnClick() {
        findViewById(R.id.fab).setOnClickListener(v -> UIFuns.changeFragment(getSupportFragmentManager(),new SettingsFragment(this)));
        findViewById(R.id.backbt).setOnClickListener(v -> goBack());
        // ^ SUBSTITUIR fab PELO ID DO BOTÃO VOLTAR ^
        // EM TODAS AS ATIVADADES (excepto esta), REMOVER O MÉTODO SETONCLICK E TIRAR TODAS AS SUAS CHAMADAS.
    }
}
