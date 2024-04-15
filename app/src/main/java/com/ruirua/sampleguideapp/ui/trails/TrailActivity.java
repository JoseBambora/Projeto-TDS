package com.ruirua.sampleguideapp.ui.trails;

import android.os.Bundle;

import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class TrailActivity extends OurActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIFuns.changeFragmentNoPushStack(getSupportFragmentManager(),new TrailListFragment(getSupportFragmentManager()));
    }

}
