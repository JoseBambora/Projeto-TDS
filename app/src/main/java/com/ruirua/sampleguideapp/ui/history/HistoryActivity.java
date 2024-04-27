package com.ruirua.sampleguideapp.ui.history;
import android.os.Bundle;

import com.ruirua.sampleguideapp.ui.utils.OurActivity;
import com.ruirua.sampleguideapp.ui.utils.UIFuns;

public class HistoryActivity extends OurActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIFuns.changeFragmentNoPushStack(getSupportFragmentManager(),new HistoryFragment());
    }
}
