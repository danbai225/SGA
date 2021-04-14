package cn.p00q.sga.utils;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;

public class RootUtils {
    private volatile static RootUtils rootUtils;
    private static OutputStream outputStream;

    public static RootUtils getInstance() {
        if (rootUtils == null) {
            synchronized (RootUtils.class) {
                if (rootUtils == null) {
                    rootUtils = new RootUtils();
                }
            }
        }
        return rootUtils;
    }
    private RootUtils() {
        if (outputStream == null) {
            try {
                outputStream = Runtime.getRuntime().exec("su").getOutputStream();
                System.out.printf("1111");
            }catch (IOException e){
                e.printStackTrace();
                Log.i("RootUtils", "初始化outputStream失败");
            }
        }
    }
}
