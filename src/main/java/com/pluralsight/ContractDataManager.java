package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ContractDataManager {
    private static final String FILE_NAME = "Contracts.csv";

    //method to save the contract in the csv file
    public void saveContract(Contract contract) {
        try {//try cacth block to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            //formatting the common information in that a contract has
            String commonInfo = contract.getDate() + "|" + contract.getName() + "|"
                    + contract.getEmail() + "|" + contract.getVehicle().toString();

            //initialize a line that will store csv format of either sale or lease
            String line;
            //if the contract is an instance of the sales contract
            if (contract instanceof SalesContract) {
                SalesContract sale = (SalesContract) contract;//cast down the sale contract to access its methods
                //add the line for sale contract in csv format including common information from the contract
                line = "SALE|" + commonInfo
                        + sale.getSalesTaxAmount() + "|" + sale.getRecordingFee()
                        + "|" + sale.getProcessingFee() + "|" + sale.getTotalPrice()
                        + "|" + (sale.isFinance() ? "YES" : "NO") + "|" + sale.getMonthlyPayment();
                //check if the contract is an instance of the lease contract
            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;//cast down to access the methods of the lease contract
                //add the line for lease contract in csv format including common information from the contract
                line = "LEASE|" + commonInfo + "|" +
                        lease.getExpectedEndingValue() + "|" +
                        lease.getLeaseFee() + "|" +
                        lease.getTotalPrice() + "|" +
                        lease.getMonthlyPayment();

            } else {//print a message if the contract is neither lease or sale and return
                System.out.println("Unknown contract type, not saved.");
                return;
            }//add the line to the csv file and go the the next line
            writer.write(line);
            writer.newLine();

        } catch (Exception e) {//catch the exception and print a message
            System.out.println("Error saving contract: " + e.getMessage());
        }


    }


}
