package com.example.week3day2;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    String name;
    String color;
    String engine;
    String imageUrl;


    public Car(String name, String color, String engine, String imageUrl) {
        this.name = name;
        this.color = color;
        this.engine = engine;
        this.imageUrl = imageUrl;
    }

    protected Car(Parcel in) {
        name = in.readString();
        color = in.readString();
        engine = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(color);
        dest.writeString(engine);
        dest.writeString(imageUrl);
    }
}
