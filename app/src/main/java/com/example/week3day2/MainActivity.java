package com.example.week3day2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver myBroadcastReceiver;
    ArrayList<Car> listofCars;
    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdapter;
    boolean isBound = false;
    Messenger mMessenger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMainRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("car_broadcast");
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        myBroadcastReceiver = new BroadcastReceiver(){
            public void onReceive(Context context, Intent intent) {
                ArrayList<Car> listofCars = intent.getExtras().getParcelableArrayList("listofCars");
                Toast.makeText(context,"Receiving info= " + listofCars.get(0).getName(), Toast.LENGTH_LONG).show();
                Log.d("TAG", "onReceive: ");

                rvAdapter = new RecyclerViewAdapter(listofCars);
                recyclerView.setAdapter(rvAdapter);

            }
        };
        registerReceiver(myBroadcastReceiver, intentFilter);



    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBound = true;


            mMessenger = new Messenger(service);

            Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);

            Bundle bundle = new Bundle();
            bundle.putString("USA", "LALALALAND");

            msg.setData(bundle);

            try {
                mMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // unbind or process might have crashes
            mMessenger = null;
            isBound = false;
        }
    };

    public void onClick(View view){
        Log.d("TAG", "Starting Intent Service");
        Intent intent1 = new Intent(this, MyIntentService.class);
        //intent1.setAction(ACTION_ONE);
        startService(intent1);
    }
}
