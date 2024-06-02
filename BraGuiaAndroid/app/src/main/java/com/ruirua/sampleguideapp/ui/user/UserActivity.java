package com.ruirua.sampleguideapp.ui.user;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class UserActivity extends OurActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String action = getIntent().getStringExtra("action");
        Fragment fragment = "register".equals(action) ? new RegisterFragment(this,getSupportFragmentManager()) : "login".equals(action) ? new LoginFragment(this,getSupportFragmentManager()) : new UserFragment();
        UIFuns.changeFragmentNoPushStack(getSupportFragmentManager(),fragment);
    }
}

