package com.example.airbusmetaverseinterface;

public class Passenger {
    private String name;
    private String email;
    private String dob;
    private String seat;
    public Passenger(){

    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getDob(){
        return dob;
    }
    public String getSeat(){ return seat;}
    public Passenger(String name, String email, String dob){
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
    public Passenger(String seat){
        this.seat = seat;
    }
}
