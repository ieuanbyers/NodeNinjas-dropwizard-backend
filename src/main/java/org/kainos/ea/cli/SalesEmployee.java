package org.kainos.ea.cli;

public class SalesEmployee  extends  Employee {

    private float commisionRate;


    public float getCommisionRate() {
        return commisionRate;
    }

    public void setCommisionRate(float commisionRate) {
        this.commisionRate = commisionRate;
    }

    public SalesEmployee(int employeeId, String name, double salary, float comR) {
        super(employeeId, name, salary);
        setCommisionRate(comR);
    }
}
