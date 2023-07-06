package org.kainos.ea.cli;

public class SalesEmployee  extends  Employee {

    private float commissionRate;

    public SalesEmployee(int employeeId, String name, double salary, String bankAccountNo, String natInsuranceNo, float commissionRate) {
        super(employeeId, name, salary, bankAccountNo, natInsuranceNo);
        setCommissionRate(commissionRate);
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

}
