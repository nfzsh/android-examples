package com.example.myexample03.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static SharedPreferences sf = create();
    private static final String PRE_FILE = "pre_my";
    private static final String MYEDIT = "myedit";

    private static SharedPreferences create(){
        return MyApplication.getInstance()
                .getSharedPreferences(PRE_FILE, Context.MODE_PRIVATE);
    }
    public static void putMyedit (String myedit) {
        sf.edit().putString(MYEDIT, myedit).apply();
    }

    public static String getMyedit() {
        return sf.getString(MYEDIT, "");
    }
}
