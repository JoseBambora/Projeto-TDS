package com.ruirua.sampleguideapp.ui.trails;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruirua.sampleguideapp.R;

public class TrailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new TrailListFragment())
                    .commitNow();
        }

        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(v -> finish());
    }
}
