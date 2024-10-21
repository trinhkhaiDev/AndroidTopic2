package com.example.androidtopic2;

public class Customer {
    private int id;
    private String name;
    private String yyyymm;
    private String address;
    private double usedElectric;

    public Customer(int id, String name, String yyyymm, String address, double usedElectric) {
        this.id = id;
        this.name = name;
        this.yyyymm = yyyymm;
        this.address = address;
        this.usedElectric = usedElectric;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYyyymm() {
        return yyyymm;
    }

    public String getAddress() {
        return address;
    }

    public double getUsedElectric() {
        return usedElectric;
    }
}
