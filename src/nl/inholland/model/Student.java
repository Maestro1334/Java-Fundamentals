package nl.inholland.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Student extends User {
    private final String group;
    private final Map<String, Integer> grades;
    private Integer retakes = 0;

    public Student(int id, String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String group) {
        super(id, firstName, lastName, email, password, dateOfBirth, UserType.BASIC);

        this.group = group;
        this.grades = new HashMap<>();

        initGrades();
    }

    public void addGrade(String subject, Integer grade) {
        if (grades.get(subject) != 0) {
            addRetake();
        }
        grades.put(subject, grade);
    }

    private void initGrades() {
        grades.put("Java", 0);
        grades.put("CSharp", 0);
        grades.put("Python", 0);
        grades.put("PHP", 0);
    }

    public Boolean hasPassed() {

        Integer total = 0;
        Integer i = 0;

        for (String key : grades.keySet()) {
            total += grades.get(key);
            i++;
        }

        return total / i >= 55;
    }

    public String getGroup(){
        return group;
    }

    public int getJava(){
        return grades.get("Java");
    }

    public int getCSharp() {
        return grades.get("CSharp");
    }

    public int getPython() {
        return grades.get("Python");
    }

    public int getPHP() {
        return grades.get("PHP");
    }

    public Map<String, Integer> getGrades() { return grades; }

    public int getRetakes() {
        return retakes;
    }

    public void addRetake() {
        retakes++;
    }
}
