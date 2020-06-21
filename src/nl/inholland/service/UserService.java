package nl.inholland.service;

import nl.inholland.model.Manager;
import nl.inholland.model.Student;
import nl.inholland.model.Teacher;
import nl.inholland.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> userList;
    private List<Student> studentList;
    private List<Teacher> teacherList;
    private List<Manager> managerList;

    public UserService() {
        initUserData();
    }

    private void initUserData(){
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "Emma","Smith","esmith@gmail.com","secret", LocalDate.of(1990, 1, 2),"IT-02-A"));
        studentList.add(new Student(2, "Jack","Brown","jbrown@gmail.com","secret",LocalDate.of(1993, 9, 10),"IT-02-A"));
        studentList.add(new Student(3, "Michael","Garcia","mgarcia@gmail.com","secret",LocalDate.of(1980, 11, 12),"IT-02-B"));
        studentList.add(new Student(4, "Lisa","Jones","ljones@gmail.com","secret",LocalDate.of(1976, 1, 15),"INF-02-A"));
        studentList.add(new Student(5, "John","Miller","jmiller@gmail.com","secret",LocalDate.of(1989, 6, 23),"INF-02-B"));
        studentList.add(new Student(6, "Linda","Martinez","lmartinez@gmail.com","secret",LocalDate.of(1980, 1, 16),"IT-02-A"));
        studentList.add(new Student(7, "Richard","Davis","rdavis@gmail.com","secret",LocalDate.of(1999, 3, 3),"IT-02-A"));
        studentList.add(new Student(8, "Mark","Lopez","mlopez@gmail.com","secret",LocalDate.of(1960, 6, 22),"IT-02-B"));
        studentList.add(new Student(9, "Debora","Hernandez","dhernandez@gmail.com","secret",LocalDate.of(1993, 2, 3),"INF-02-A"));
        studentList.add(new Student(10, "Rick","Moore","rmoore@gmail.com","secret",LocalDate.of(1996, 1, 16),"INF-02-B"));
        studentList.add(new Student(11, "Paul","Smith","psmith@gmail.com","secret",LocalDate.of(2001, 1, 3),"INF-02-B"));

        teacherList = new ArrayList<>();
        teacherList.add(new Teacher(12,"David","Taylor","dtaylor@gmail.com","secret", LocalDate.of(1970, 1, 2), 5200.0));
        teacherList.add(new Teacher(13,"Sophy","Anderson","sanderson@gmail.com","secret", LocalDate.of(1980, 2, 3), 3500.0));
        teacherList.add(new Teacher(14,"James","Jordon","jjordon@gmail.com","secret", LocalDate.of(1995, 3, 16), 6100.0));
        teacherList.add(new Teacher(15,"Susan","Jackson","sjackson@gmail.com","secret", LocalDate.of(1960, 5, 15), 4520.0));
        teacherList.add(new Teacher(16,"Mary","Lee","mlee@gmail.com","secret", LocalDate.of(1988, 4, 8), 5130.0));

        managerList = new ArrayList<>();
        managerList.add(new Manager(17, "Koert", "de Wit", "kdewit@gmail.com","itsasecret",LocalDate.of(1977, 12, 23)));
        managerList.add(new Manager(18, "Karel", "Boom", "kboom@gmail.com","itsasecret",LocalDate.of(1965, 11, 12)));

        userList = new ArrayList<>();
    }

    public List<User> getAllUsers(){
        userList.addAll(studentList);
        userList.addAll(teacherList);
        userList.addAll(managerList);

        return userList;
    }

    public List<Student> getAllStudents(){
        return studentList;
    }

    public List<Teacher> getAllTeachers(){
        return teacherList;
    }

    public List<Manager> getAllManagers(){
        return managerList;
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public void editStudent(Student updatedStudent) {
        // Replaces old student object with updated student object
        studentList.set(getStudentIndexById(updatedStudent.getId(), studentList), updatedStudent);
    }

    public void removeStudent (Student student) {
        studentList.remove(student);
    }

    private int getStudentIndexById(int id, List<Student> studentList) {
        try {
            for (Student student : studentList) {
                if (student.getId() == id) {
                    return studentList.indexOf(student);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

