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
        int id = Integer.parseInt(getIntent().getStringExtra("pinid"));
        Fragment fragment = new PinFragment(id);
        UIFuns.changeFragmentNoPushStack(getSupportFragmentManager(),fragment);
    }
}
