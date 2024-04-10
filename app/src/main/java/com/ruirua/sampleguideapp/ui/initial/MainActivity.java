
package com.ruirua.sampleguideapp.ui.initial;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIFuns.configureTheme(this);
        if (savedInstanceState == null)
        {
            Fragment f = new MenuInicialFragment();
            UIFuns.changeFragment(this,f,getSupportFragmentManager(),f);
        }
        permissions();
    }

    private void permissions() {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
        }
        if(UIFuns.permissionsGoogleMap().resolveActivity(this.getPackageManager()) == null) {
            Toast.makeText(this, "Google Maps não instalado e algumas funcionalidades poderão não funcionar.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
