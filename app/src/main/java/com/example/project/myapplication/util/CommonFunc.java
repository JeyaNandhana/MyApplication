package com.example.project.myapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CommonFunc {
    public static boolean isInternetAvailable(final Context context) {

        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        return !((networkInfo == null) || (!networkInfo.isConnected()) || (!networkInfo.isAvailable()));
    }
}
