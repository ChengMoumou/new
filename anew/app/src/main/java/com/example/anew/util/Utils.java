package com.example.anew.util;

import android.content.Context;

public class Utils {
    public static int dip2px(Context context, Float dpValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
}
