package org.kainos.ea.cli;

public class EmployeeRequest {
    private String name;
    private double salary;
    private String bankAccountNo;
    private String natInsuranceNo;

    public EmployeeRequest(String name, double salary, String bankAccountNo, String natInsuranceNo) {
        setName(name);
        setSalary(salary);
        setBankAccountNo(bankAccountNo);
        setNatInsuranceNo(natInsuranceNo);
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

    public String getNatInsuranceNo() {
        return natInsuranceNo;
    }

    public void setNatInsuranceNo(String natInsuranceNo) {
        this.natInsuranceNo = natInsuranceNo;
    }
}
