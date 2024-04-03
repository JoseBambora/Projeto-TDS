package com.ruirua.sampleguideapp.ui.utils;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Map;

public class StackUIElement {
    private Activity activitY;
    private Fragment fragment;


    private FragmentManager fm;
    private Map<String,String> params;

    public StackUIElement(Activity activitY, Fragment fragment, FragmentManager fragmentManager) {
        this.activitY = activitY;
        this.fragment = fragment;
        this.fm = fragmentManager;
    }
    public StackUIElement(Activity activitY, Map<String,String> params) {
        this.activitY = activitY;
        this.params = params;
    }

    public Activity getActivitY() {
        return activitY;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public FragmentManager getFm() {
        return fm;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
