package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryEmployeeUpdateRequest {
    private String name;
    private double salary;
    private String bankAccountNo;

    @JsonCreator
    public DeliveryEmployeeUpdateRequest(@JsonProperty("name") String name, @JsonProperty("salary") double salary, @JsonProperty("bankAccountNo") String bankAccountNo) {
        setName(name);
        setSalary(salary);
        setBankAccountNo(bankAccountNo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }
}
