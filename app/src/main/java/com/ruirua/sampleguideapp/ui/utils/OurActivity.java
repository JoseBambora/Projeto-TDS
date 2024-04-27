package com.ruirua.sampleguideapp.ui.utils;

import android.app.PendingIntent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.notifications.NotificationSystem;
import com.ruirua.sampleguideapp.repositories.PinRepository;
import com.ruirua.sampleguideapp.repositories.UserRepository;
import com.ruirua.sampleguideapp.sensors.OurLocationListener;
import com.ruirua.sampleguideapp.ui.pins.PinActivity;
import com.ruirua.sampleguideapp.ui.history.HistoryFragment;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;
import com.ruirua.sampleguideapp.ui.user.LoginFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OurActivity extends AppCompatActivity implements GoBackInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OurLocationListener.createInstance(this).updateActivity(this);
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
        findViewById(R.id.backbt).setOnClickListener(v -> goBack());
        findViewById(R.id.cebt).setOnClickListener(v -> UIFuns.emergencyCall(this));
    }

    private void sendNotificationsPinClose(List<Pin> pins, double lon, double lan, double alt) {
        if(!pins.isEmpty())
        {
            Pin p = pins.get(0);
            if(p.distance(lon,lan,alt) < 100) {
                Map<String,Integer> params = new HashMap<>();
                params.put("pinid",p.getId());
                PendingIntent activityPendingIntent = NotificationSystem.assignIntent(this, PinActivity.class,params);
                NotificationSystem.sendNotification(this,"Proximidade de um pin", "Está próximo de " + p.getPin_name(),activityPendingIntent );
            }
        }
    }
    public void sendNotifications(double lon, double lan, double alt) {
        if(UserRepository.getInstance().isLogged()){
            LiveData<List<Pin>> getPins = new PinRepository(getApplication()).getAllPins();
            getPins.observe(this, pins -> this.sendNotificationsPinClose(pins,lon,lan,alt));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            UIFuns.changeFragment(getSupportFragmentManager(),new SettingsFragment());
            return true;
        }
        if (id == R.id.action_login) {
            UIFuns.changeFragment(getSupportFragmentManager(), new LoginFragment(this, getSupportFragmentManager()));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
