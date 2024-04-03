package com.ruirua.sampleguideapp.ui.utils;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Map;
import java.util.Stack;

public class StackUI {

    private final Stack<StackUIElement> stack;
    private StackUI() {
        stack  = new Stack<>();
    }

    private static StackUI instance = null;
    public static StackUI getInstance() {
        if(instance == null)
            instance = new StackUI();
        return instance;
    }

    public void pushElement(Activity activity, Fragment fragment, FragmentManager fm) {
        stack.push(new StackUIElement(activity,fragment,fm));
    }
    public void pushElement(Activity activity, Map<String,String> params) {
        stack.push(new StackUIElement(activity,params));
    }

    public StackUIElement goBack() {
        return stack.pop();
    }
}
