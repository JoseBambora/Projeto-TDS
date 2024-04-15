package com.ruirua.sampleguideapp.ui.trails;

import android.os.Bundle;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class TrailActivity extends OurActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIFuns.changeFragment(getSupportFragmentManager(),new TrailListFragment(getSupportFragmentManager()));
    }

}
