package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        //create the dealership with no inventory
        this.inventory = new ArrayList<>();
    }

    //add vehicles to the inventory
    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    //get all the vehicles
    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;//return the inventory
    }
    //remove vehicle method
    public void removeVehicle(Vehicle vehicle) {
        //take the vehicle passed in out of the inventory list
        inventory.remove(vehicle);//use .remove method to remove the vehicle from the list
    }
    //method to get vehicles by price
    public ArrayList<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        //create an arraylist that will hold the filtered result/desired vehivles
        ArrayList<Vehicle> result = new ArrayList<>();
        //iterate through the inventory arraylist
        for(Vehicle vehicle : inventory){
            //if the vehicle price is between min and max, add it to the result arraylist
            if(vehicle.getPrice().compareTo(min) >= 0 && vehicle.getPrice().compareTo(max) <= 0){
                result.add(vehicle);
            }
        }

        return result;//return the filtered results
    }
    //method to get vehicles by make and model
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        //create an arraylist that will store the result
        ArrayList<Vehicle> result = new ArrayList<>();
        //iterate throught the arraylist
        for(Vehicle vehicle : inventory){
            //if the there is a match in make and model, add it to the arraylist
            if(vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                result.add(vehicle);//add it to the arraylist
            }
        }
        return result;
    }
    //method to get vehicles by year
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> result = new ArrayList<>();//arraylist to store the filtered desired results
        //iterate through the inventry, for each cas, if the year is between min and max, add it to the result
        for(Vehicle vehicle : inventory){
            if(vehicle.getYear() >= min && vehicle.getYear() <= max){
                result.add(vehicle);
            }
        }

        return result;//return the new arralyist
    }
    //method to get vehicles by color
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> result = new ArrayList<>();//create arraylist to store the result

        for(Vehicle vehicle : inventory){//iterate thorugh the list and check if the car has that color, if so add it
            if(vehicle.getColor().equalsIgnoreCase(color)){
                result.add(vehicle);
            }
        }

        return result;
    }

    //method to get vehicles by mileage
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        //arraylis to store the desired results
        ArrayList<Vehicle> result = new ArrayList<>();
        //iterate through the list, if the vehicle mileage is betweern min and max , add it to result
        for(Vehicle vehicle : inventory){
            if(vehicle.getOdometer() >= min && vehicle.getOdometer() <= max){
                result.add(vehicle);
            }
        }
        return result;
    }
    //method to get vehicles by type
    public ArrayList<Vehicle> getVehiclesByType(String type) {
        //arraylist to store the desired result
        ArrayList<Vehicle> result = new ArrayList<>();

        //iterate through the list and check if the vehicle type match if so,add to the list
        for(Vehicle vehicle : inventory){
            if(vehicle.getVehicleType().equalsIgnoreCase(type)){
                result.add(vehicle);
            }
        }
        return result;//return the filtered arraylist
    }

}