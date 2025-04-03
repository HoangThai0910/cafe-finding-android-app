package com.example.myapplication.Model;

public class CafeModel {
    private String name;
    private String address;
    private boolean isOpen;
    private double distance; // Khoảng cách

    public CafeModel(String name, String address, boolean isOpen, double distance) {
        this.name = name;
        this.address = address;
        this.isOpen = isOpen;
        this.distance = distance;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public boolean isOpen() { return isOpen; }
    public double getDistance() { return distance; }
}

