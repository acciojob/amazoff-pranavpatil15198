package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(){};

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        int time = 0;
        String hrs = deliveryTime.substring(0, 2);
        String mins = deliveryTime.substring(3);
        time += Integer.parseInt(hrs)*60 + Integer.parseInt(mins);

        this.id = id;
        this.deliveryTime = time;

    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
