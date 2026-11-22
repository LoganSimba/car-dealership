package com.pluralsight;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalesContract extends Contract {
    //final tax rate and price threshold
    private static final BigDecimal TAX_RATE = new BigDecimal("0.05");
    private static final BigDecimal PRICE_THRESHOLD = new BigDecimal("10000");

    private BigDecimal salesTaxAmount;
    private BigDecimal recordingFee;
    private BigDecimal processingFee;
    private boolean finance;

    public SalesContract(String date, String name, String email, Vehicle vehicle, boolean finance) {
        super(date, name, email, vehicle);
        //vehicle price
        BigDecimal price = vehicle.getPrice();
        //price with sales tax included
        this.salesTaxAmount = price.multiply(TAX_RATE);
        this.recordingFee = new BigDecimal("100");
        //boolean to check if the fee will be 295 or 495 depending on the vehicle price
        this.processingFee = price.compareTo(PRICE_THRESHOLD) < 0 ? new BigDecimal("295") : new BigDecimal("495");
        this.finance = finance;
    }

    public BigDecimal getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(BigDecimal salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public BigDecimal getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(BigDecimal recordingFee) {
        this.recordingFee = recordingFee;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public boolean isFinance() {
        return finance;
    }

    //get total price methods that adds all fees including tax
    @Override
    public BigDecimal getTotalPrice() {
        return getVehicle().getPrice()
                .add(salesTaxAmount)
                .add(recordingFee)
                .add(processingFee);
    }

    //method to calculate monthly payment
    @Override
    public BigDecimal getMonthlyPayment() {
        //if the vehicle did not finance, the price will be 0
        if (!finance) {
            return BigDecimal.ZERO;
        }
        //initialize the annual rate and months
        BigDecimal annualRate;
        int months;
        //if the vehicle price is 10000 or more, the anual rate is 4.25% , if lower annualr rate is 5.25%
        if (getVehicle().getPrice().compareTo(PRICE_THRESHOLD) >= 0) {
            annualRate = new BigDecimal("0.0425");
            months = 48;
        } else {
            annualRate = new BigDecimal("0.0525");
            months = 24;
        }

        BigDecimal principal = getTotalPrice();
        //monthly rate calculations is annual rate divide 12 months
        BigDecimal monthlyRate = annualRate.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);

        //a part of the loan formula in the (1+r)^n
        BigDecimal growthFormula = BigDecimal.ONE.add(monthlyRate).pow(months);
        //return the answer calulations for loan
        return principal.multiply(monthlyRate)   // P × r
                .multiply(growthFormula)                 // × (1+r)^n
                .divide(growthFormula.subtract(BigDecimal.ONE),   // ÷ ((1+r)^n − 1)
                        2, RoundingMode.HALF_UP); // round to cents

    }
}
