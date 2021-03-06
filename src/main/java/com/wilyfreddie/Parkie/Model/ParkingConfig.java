package com.wilyfreddie.Parkie.Model;

public class ParkingConfig {
    public static int slotsAvailable = 2;
    public static float rate = 50;

    public static int getSlotsAvailable() {
        return slotsAvailable;
    }

    public static float getRate() {
        return rate;
    }
}
