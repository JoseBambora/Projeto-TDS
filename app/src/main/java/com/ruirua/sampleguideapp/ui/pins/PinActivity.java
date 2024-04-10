package com.ruirua.sampleguideapp.ui.pins;
import static com.ruirua.sampleguideapp.ui.utils.UIFuns.configureTheme;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class PinActivity extends AppCompatActivity implements GoBackInterface {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureTheme(this);

        Fragment fragment = new PinFragment(this,1);
        UIFuns.changeFragmentNoPushStack(getSupportFragmentManager(),fragment);
    }

    @Override
    public void goBack() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            finish();
    }
}
