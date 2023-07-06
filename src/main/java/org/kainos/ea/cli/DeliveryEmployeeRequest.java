package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryEmployeeRequest extends EmployeeRequest {

    @JsonCreator
    public DeliveryEmployeeRequest(@JsonProperty("name") String name,@JsonProperty("salary") double salary, @JsonProperty("bankAccountNo") String bankAccountNo, @JsonProperty("natInsuranceNo") String natInsuranceNo) {
        super(name, salary, bankAccountNo, natInsuranceNo);
    }
}
