package nl.inholland.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Teacher extends User {

    private final BigDecimal salary;

    public Teacher(int id, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, BigDecimal salary) {
        super(id, firstName, lastName, email, password, dateOfBirth, UserType.EDITOR);

        this.salary = salary;
    }

    public BigDecimal getSalary(){
        return salary;
    }
}
