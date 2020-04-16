package nl.inholland.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class User {

    private final Integer id;
    private String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final UserType userType;
    private final Integer age;


    public User(Integer id, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, UserType userType) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userType = userType;
        age = setAge();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int setAge() {
        LocalDate currentDate = LocalDate.now();
        if ((dateOfBirth != null)) {
            return Period.between(dateOfBirth, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public Integer getAge() {
        return age;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getPassword(){
        return password;
    }

    public UserType getUserType(){
        return userType;
    }

    public int getId() {
        return id;
    }
}