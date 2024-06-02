
package com.ruirua.sampleguideapp.ui.initial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.ruirua.sampleguideapp.Permissions;
import com.ruirua.sampleguideapp.sensors.LocationService;
import com.ruirua.sampleguideapp.sensors.OurLocationListener;
import com.ruirua.sampleguideapp.ui.utils.GoBackInterface;
import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends OurActivity implements GoBackInterface {
    private static boolean serviceOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            UIFuns.changeFragment(getSupportFragmentManager(),new MenuInicialFragment());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void permissions() {
        Permissions.permission_call = checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
        Permissions.permission_google_maps = UIFuns.permissionsGoogleMap().resolveActivity(this.getPackageManager()) != null;
        Permissions.permission_notitications = ActivityCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        Permissions.permission_location = OurLocationListener.getInstance().hasPermissions(this);
        List<String> permissions = new ArrayList<>();
        if (!Permissions.permission_call)
            permissions.add(Manifest.permission.CALL_PHONE);
        if (!Permissions.permission_google_maps)
            Toast.makeText(this, "Google Maps não instalado e algumas funcionalidades poderão não funcionar.", Toast.LENGTH_SHORT).show();
        if (!Permissions.permission_notitications)
            permissions.add(Manifest.permission.POST_NOTIFICATIONS);
        if (!Permissions.permission_location){
            permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        else if(!serviceOn) {
            startService(new Intent(this, LocationService.class));
            serviceOn = true;
        }
        if(!permissions.isEmpty())
            requestPermissions(permissions.toArray(new String[0]), 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            Permissions.permission_call = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
            Permissions.permission_notitications = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
            Permissions.permission_location = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED;
            if(!serviceOn) {
                startService(new Intent(this, LocationService.class));
                serviceOn = true;
            }
        }
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
    }
}
