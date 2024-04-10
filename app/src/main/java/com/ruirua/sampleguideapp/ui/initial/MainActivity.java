
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
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class MainActivity extends AppCompatActivity implements GoBackInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIFuns.configureTheme(this);
        if (savedInstanceState == null)
        {
            UIFuns.changeFragment(getSupportFragmentManager(),new MenuInicialFragment());
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
            UIFuns.changeFragment(getSupportFragmentManager(),new SettingsFragment(this));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goBack() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
    }
}
