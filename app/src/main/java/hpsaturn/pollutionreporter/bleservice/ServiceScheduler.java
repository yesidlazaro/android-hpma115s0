package hpsaturn.pollutionreporter.bleservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hpsaturn.tools.Logger;

import java.util.Calendar;

import hpsaturn.pollutionreporter.Config;

/**
 * Created by Antonio Vanegas @hpsaturn on 3/24/17.
 */

public class ServiceScheduler extends BroadcastReceiver {

    public static final String TAG = ServiceScheduler.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        startScheduleService(context, Config.DEFAULT_INTERVAL);

    }

    public static void startScheduleService(Context context,long repeatTime){
        Logger.d(TAG, "startScheduleService: ipcamera" );
        AlarmManager service = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent i = new Intent(context, ServiceReceiver.class);
            PendingIntent pending = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
            Calendar cal = Calendar.getInstance();
            // Start x seconds after boot completed
            cal.add(Calendar.SECOND, Config.TIME_AFTER_START);
            // Fetch every 30 seconds
            // InexactRepeating allows Android to optimize the energy consumption
            service.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), repeatTime, pending);
            // service.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
            // REPEAT_TIME, pending);
    }

    public static void stopSheduleService(Context context){
        Logger.d(TAG,"stopSheduleService:");
        AlarmManager service = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, ServiceReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        service.cancel(pending);
    }

}