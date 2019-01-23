package com.example.week3day2;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class MyIntentService extends IntentService {
    Random random;
    ArrayList<Car> listOfCars;
    public MyIntentService() {
        super("");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        listOfCars = new ArrayList<>();
        random = new Random();
        listOfCars.add(new Car("Corvette", "Red", "Electric",
                "https://www.chevrolet.com/content/dam/chevrolet/na/us/english/index/vehicles/2019/performance/corvette-grand-sport/colorizer/01-images/2019-corvette-grandsport-coupe-1lt-gkz-colorizer.jpg?imwidth=1200"));
        listOfCars.add(new Car("Raptor", "Silver", "Hybrid", "https://di-uploads-pod12.dealerinspire.com/kengrodyfordorangecounty/uploads/2017/06/Ford-Raptor-Silver.png"));
        listOfCars.add(new Car("SmartCar", "Orange", "Electric", "https://www.smartusa.com/resources/img/offers/offers-2018-ev-cabrio.png"));
        listOfCars.add(new Car("Delorean", "Gold", "Hybrid", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Gold-D.jpg/220px-Gold-D.jpg"));
        listOfCars.add(new Car("Batmobile", "Black", "Hybrid", "https://i.ytimg.com/vi/Qm5FYTW6SF0/maxresdefault.jpg"));
        listOfCars.add(new Car("Veyron", "White", "Gas", "https://rmsothebys-cache.azureedge.net/9/0/7/3/0/6/9073063f98034b2e3933284d94bc433d44636a9f.jpg"));
        listOfCars.add(new Car("M3", "BabyBlue", "Gas", "https://i.ytimg.com/vi/c7y07BFsPCY/maxresdefault.jpg"));
        listOfCars.add(new Car("Bug", "Pink", "Gas", "https://i.pinimg.com/originals/5f/5a/0d/5f5a0d494e8564aae61ee4bb0b809fd6.jpg"));
        ArrayList<Car> carArrayList = new ArrayList<>();
        int counter =0;

        while(counter <5){
            carArrayList.add(listOfCars.get(random.nextInt(8)));
            counter++;
        }
        Log.d("TAG", "Random list generated: " + carArrayList.get(0).getName());
        startBroadcast(carArrayList);

        stopSelf();
    }

    private void startBroadcast(ArrayList<Car> arrayList){
        Intent intent = new Intent();
        intent.setAction("car_broadcast");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listofCars", (ArrayList<? extends Parcelable>)arrayList);
        intent.putExtras(bundle);
        Log.d("TAG", "onCreate: SENDING bROADCAST");
        //LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        sendBroadcast(intent);
    }
}
