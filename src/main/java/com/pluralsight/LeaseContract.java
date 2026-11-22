package com.pluralsight;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseContract extends Contract {
    //final static variabiles for expeced eding value and lease fee rate
    private static final BigDecimal EXPECTED_ENDING_VALUE = new BigDecimal("0.50");
    private static final BigDecimal LEASE_FEE_RATE = new BigDecimal("0.07");

    private BigDecimal expectedEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract(String date, String name, String email, Vehicle vehicle) {
        super(date, name, email, vehicle);

        //vehicle price
        BigDecimal price = vehicle.getPrice();

        this.expectedEndingValue = price.multiply(EXPECTED_ENDING_VALUE).setScale(2, RoundingMode.HALF_UP);
        this.leaseFee = price.multiply(LEASE_FEE_RATE).setScale(2, RoundingMode.HALF_UP);

    }

    public BigDecimal getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(BigDecimal expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public BigDecimal getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(BigDecimal leaseFee) {
        this.leaseFee = leaseFee;
    }

    //method to calculate total price
    @Override
    public BigDecimal getTotalPrice() {
        return expectedEndingValue.add(leaseFee);

    }

    //method to calculate the monthly lease payment
    @Override
    public BigDecimal getMonthlyPayment() {
        BigDecimal financeRate = new BigDecimal("0.04");

        int months = 36;

        //the amount being financed is the total lease price
        BigDecimal principal = getTotalPrice();
        //monthly rate calculation is annual rate divided by 12 months
        BigDecimal monthlyRate = financeRate.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);

        //a part of the loan formula, the (1+r)^n
        BigDecimal growthFormula = BigDecimal.ONE.add(monthlyRate).pow(months);

        return principal.multiply(monthlyRate)                  // P × r
                .multiply(growthFormula)                        // × (1+r)^n
                .divide(growthFormula.subtract(BigDecimal.ONE), // ÷ ((1+r)^n − 1)
                        2, RoundingMode.HALF_UP);               // round to cents
    }

}
