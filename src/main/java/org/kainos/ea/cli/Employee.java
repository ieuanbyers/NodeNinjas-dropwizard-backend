package org.kainos.ea.cli;

public abstract class Employee {
    private int employeeId;
    private String name;
    private double salary;
    private String bankAccountNo;
    private String natInsuranceNo;

    public Employee(int employeeId, String name, double salary, String bankAccountNo, String natInsuranceNo) {
        setEmployeeId(employeeId);
        setName(name);
        setSalary(salary);
        setBankAccountNo(bankAccountNo);
        setNatInsuranceNo(natInsuranceNo);
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

}