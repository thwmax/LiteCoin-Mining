package com.msoderlund.litecoinminer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    boolean mBound = false;
    MinerService mService;

    public int curScreenPos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Log.i("LC", "Main: in onCreate()");
        setTitle("LTCMiner");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("LC", "Main: onServiceConnected()");
            MinerService.LocalBinder binder = (MinerService.LocalBinder) service;
            mService = binder.getService();
            mBound=true;
            Log.i("LC", "Main: Service Connected");
        }

        public void onServiceDisconnected(ComponentName name) {  mBound=false;   }
    };


    public void startMining() {
        Log.i("LC", "Main: startMining()");
        mService.startMiner();
    }

    public void stopMining()
    {
        Log.i("LC", "Main: stopMining()");
        mService.stopMiner();

    }
}
