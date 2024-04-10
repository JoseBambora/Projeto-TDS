package com.ruirua.sampleguideapp.ui.trails;

import static com.ruirua.sampleguideapp.ui.utils.UIFuns.configureTheme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class TrailActivity extends AppCompatActivity implements GoBackInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureTheme(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new TrailListFragment())
                    .commitNow();
        }

        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(v -> this.goBack());
    }

    @Override
    public void goBack() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            finish();
    }
}
