package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private static Scanner scanner = new Scanner(System.in);


    public UserInterface() {

    }

    public void display() {
        init();

        boolean appRunning = true;
        while (appRunning) {

            System.out.println("\n=== Car Dealership Menu ===");
            System.out.println("1 - Find vehicles by price");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find all vehicles by mileage");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List all vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Sell/Lease a Vehicle");
            System.out.println("99 - Quit");
            System.out.print("Enter option: ");
            //variable to store user input
            String command = scanner.nextLine().trim();

            switch (command) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeAndModel();
                case "3" -> processGetByYearRequest();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehicleRequest();
                case "8" -> processGetByPriceRequest();
                case "9" -> processGetByPriceRequest();
                case "10" -> processGetByPriceRequest();
                case "99" -> {
                    System.out.println("Goodbye!");
                    appRunning = false;
                }
                default -> System.out.println("Invalid option, try again.");
            }


        }

    }

    private void init() {

        //create a dealership file manager
        DealershipFileManager dealership = new DealershipFileManager();
        this.dealership = dealership.getDealership();

    }


    //method to return all arraylists
    public void processGetAllVehicleRequest() {
        //calls displayVehicles method and hads it a method that returns all arraylsits
        displayVehicles(dealership.getAllVehicles());
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        //looper over the list and print it to the screen
        for (Vehicle vehicle : vehicles) {
            //make this better, tables and such
            System.out.println(vehicle.toString());
        }
    }

    //add method to asks user for the min and max price
    public void processGetByPriceRequest() {
        // ask the user for the minimum price
        System.out.print("Min price: ");
        BigDecimal min = new BigDecimal(scanner.nextLine().trim());

        // ask the user for the maximum price
        System.out.print("Max price: ");
        BigDecimal max = new BigDecimal(scanner.nextLine().trim());

        // search the inventory and print the matching vehicles
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    //method to process and get the make and model
    public void processGetByMakeAndModel() {
        System.out.println("Make: ");//ask the user and store in a variable
        String make = scanner.nextLine().trim();

        System.out.println("Model: ");//ask user the model and store in a variable
        String model = scanner.nextLine().trim();
        //call the display vehicle method that will print and hand it an arraylist which returned filtered make and model
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));

    }

    // ask the user for a year range, then display the matching vehicles
    public void processGetByYearRequest() {
        System.out.println("Car year ");
        System.out.print("Min year: "); // ask for the starting year and store that in a variable
        int minYear = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Max year: ");//prompt user for year, store it in a variable
        int maxYear = Integer.parseInt(scanner.nextLine().trim());
        // get the vehicle in the range and print them
        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
    }

    //method that shows vehicle by color
    public void processGetByColorRequest() {
        System.out.println("Color: ");//prompt user for color input and store it
        String color = scanner.nextLine().trim();
        // print the vehicles by calling the displayVehicle method that prints the return value of a method that filters by color
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    //method to get vehicles between min and max mileage
    public void processGetByMileageRequest() {
        System.out.println("Mileage range");//ask user for the min mileage
        System.out.print("Min: ");
        int minMileage = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Max: ");//ask user for the max mileage and store it in a variable
        int maxMileage = Integer.parseInt(scanner.nextLine().trim());
        // get the vehicles between min and max mileage, then print them
        displayVehicles(dealership.getVehiclesByMileage(minMileage, maxMileage));

    }

    //ask user for a vehicle type, then display the matching vehicles
    public void processGetByVehicleTypeRequest() {
        System.out.print("Vehicle type: "); // ask the user for the type, if its suv, van..
        String type = scanner.nextLine().trim();//store the user answer in a viable

        //get the vehicles that match the type, then print them
        displayVehicles(dealership.getVehiclesByType(type));
    }


}