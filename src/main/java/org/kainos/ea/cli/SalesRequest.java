package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesRequest extends EmployeeRequest  {
    private double comRate;

    public double getComRate() {
        return comRate;
    }

    public void setComRate(double comRate) {
        this.comRate = comRate;
    }

    @JsonCreator
    public SalesRequest(@JsonProperty("Name") String name, @JsonProperty("Salary") double salary, @JsonProperty("BankAccountNo") String bankAccountNo, @JsonProperty("NatInsuranceNo") String natInsuranceNo, @JsonProperty("CommisionRate") double comRate) {
        super(name, salary, bankAccountNo, natInsuranceNo);
        setComRate(comRate);
    }
}
