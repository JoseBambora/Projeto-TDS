package com.ruirua.sampleguideapp.ui.user;


import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.notifications.OurNotifications;
import com.ruirua.sampleguideapp.ui.pins.PinActivity;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class UserActivity extends OurActivity {

    private void testNotif(){
        PendingIntent activityPendingIntent = OurNotifications.assignIntent(this, PinActivity.class);
        OurNotifications.sendNotification(this,"User","Teste",activityPendingIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String action = getIntent().getStringExtra("action");
        Fragment fragment = "register".equals(action) ? new RegisterFragment(this,getSupportFragmentManager()) : "login".equals(action) ? new LoginFragment(this,getSupportFragmentManager()) : new UserFragment(this);
        UIFuns.changeFragmentNoPushStack(getSupportFragmentManager(),fragment);
        setOnClicks();
        testNotif();

    }
    private void setOnClicks() {
        findViewById(R.id.fab).setOnClickListener(v -> UIFuns.changeFragment(getSupportFragmentManager(),new SettingsFragment(this)));
    }
}

