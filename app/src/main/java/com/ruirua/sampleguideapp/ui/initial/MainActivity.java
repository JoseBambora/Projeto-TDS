
package com.ruirua.sampleguideapp.ui.initial;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.ui.shared.SettingsFragment;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class MainActivity extends OurActivity implements GoBackInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            UIFuns.changeFragment(getSupportFragmentManager(),new MenuInicialFragment());
        permissions();
        setOnClicks();
    }

    private void setOnClicks() {
        findViewById(R.id.fab).setOnClickListener(v -> UIFuns.changeFragment(getSupportFragmentManager(),new SettingsFragment(this)));
    }

    private void permissions() {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
        }
        if(UIFuns.permissionsGoogleMap().resolveActivity(this.getPackageManager()) == null) {
            Toast.makeText(this, "Google Maps não instalado e algumas funcionalidades poderão não funcionar.", Toast.LENGTH_SHORT).show();
        }
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
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
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            UIFuns.configureTheme(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        UIFuns.configureTheme(this);
        Log.d("DebugApp", "onResume aqui: ");
    }


}
