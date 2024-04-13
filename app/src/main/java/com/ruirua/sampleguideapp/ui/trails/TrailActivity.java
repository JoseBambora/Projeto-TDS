package com.ruirua.sampleguideapp.ui.trails;

import android.os.Bundle;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class TrailActivity extends OurActivity implements GoBackInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new TrailListFragment())
                    .commitNow();
        }
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
            UIFuns.finishActivity(this);
    }
}
