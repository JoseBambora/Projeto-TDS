package com.ruirua.sampleguideapp.ui.user;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class UserActivity extends OurActivity implements GoBackInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String action = getIntent().getStringExtra("action");
        Fragment fragment = "register".equals(action) ? new RegisterFragment(this,getSupportFragmentManager()) : "login".equals(action) ? new LoginFragment(this,getSupportFragmentManager()) : new UserFragment(this);
        UIFuns.changeFragmentNoPushStack(getSupportFragmentManager(),fragment);
        setOnClicks();
    }
    private void setOnClicks() {
        findViewById(R.id.fab).setOnClickListener(v -> UIFuns.changeFragment(getSupportFragmentManager(),new SettingsFragment(this)));
    }

    @Override
    public void goBack() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            finish();
    }
}

