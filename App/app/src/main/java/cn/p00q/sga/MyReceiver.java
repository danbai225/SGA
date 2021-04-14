package cn.p00q.sga;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import cn.p00q.sga.utils.RootUtils;

public class MyReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context,"启动完成===>start!",Toast.LENGTH_SHORT).show();
        Log.i("charge completed", "启动完成");
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
//            Intent i1 = new Intent(context, MyService.class);
//            context.startService(i1);
        }
    }

}