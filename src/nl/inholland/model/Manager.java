package nl.inholland.model;

import java.time.LocalDate;

public class Manager extends User {

    public Manager(int id, String firstName, String lastName, String email, String password, LocalDate dateOfBirth) {
        super(id, firstName, lastName, email, password, dateOfBirth, UserType.ADMIN);
    }
}
