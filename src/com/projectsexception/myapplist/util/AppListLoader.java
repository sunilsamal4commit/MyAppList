package com.projectsexception.myapplist.util;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import com.projectsexception.myapplist.model.AppInfo;

public class AppListLoader extends AbstractListLoader {
    
    private final PackageManager mPm;
    private final boolean mHideSystemApps;

    public AppListLoader(Context context) {
        super(context);

        // Retrieve the package manager for later use; note we don't
        // use 'context' directly but instead the save global application
        // context returned by getContext().
        mPm = getContext().getPackageManager();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        mHideSystemApps = prefs.getBoolean("hide_system_apps", true);
    }

    @Override
    public List<AppInfo> loadAppInfoList() {
        return AppUtil.loadAppInfoList(mPm, mHideSystemApps);
    }

    @Override
    public boolean isPackageIntentReceiver() {
        return false;
    }
}