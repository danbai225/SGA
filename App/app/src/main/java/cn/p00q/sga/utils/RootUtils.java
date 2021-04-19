package cn.p00q.sga.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
            }catch (IOException e){
                e.printStackTrace();
                Log.i("RootUtils", "初始化outputStream失败");
            }
        }
    }
    public static void runRootCommand(String command) {

        Process process = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        StringBuffer wifiConf = new StringBuffer();
        try {
            process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataInputStream = new DataInputStream(process.getInputStream());
            dataOutputStream
                    .writeBytes(command+"\n");
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    dataInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                Log.d("exec:",line);
                wifiConf.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            process.waitFor();
            Log.d("shell命令执行结果：",process.exitValue()+"");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
