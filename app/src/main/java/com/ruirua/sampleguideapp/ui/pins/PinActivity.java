package com.ruirua.sampleguideapp.ui.pins;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class PinActivity extends OurActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("pinid",-1);
        Fragment fragment = new PinFragment(this,id);
        UIFuns.changeFragmentNoPushStack(getSupportFragmentManager(),fragment);
    }
}
