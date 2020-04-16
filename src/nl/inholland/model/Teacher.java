package nl.inholland.model;

import java.time.LocalDate;

public class Teacher extends User {

    private final Double salary;

    public Teacher(int id, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, Double salary) {
        super(id, firstName, lastName, email, password, dateOfBirth, UserType.EDITOR);

        this.salary = salary;
    }

    public Double getSalary(){
        return salary;
    }
}
