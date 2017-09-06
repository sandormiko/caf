package caf.admin.employees.control;


import caf.admin.employees.entity.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class EmployeeBuilder {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private boolean hasPremiumAccess;

    private EmployeeBuilder(){}

    public static EmployeeBuilder newBuilder(){
        return new EmployeeBuilder();
    }

    public EmployeeBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder withBirthDate(LocalDate date) {
        this.birthDate = date;
        return this;
    }

    public EmployeeBuilder withPremiumAccess() {
        this.hasPremiumAccess = true;
        return this;
    }

    public EmployeeBuilder withoutPremiumAccess() {
        this.hasPremiumAccess = false;
        return this;
    }

    public Employee build(){
        return new Employee(firstName, lastName, birthDate, hasPremiumAccess);
    }

}


