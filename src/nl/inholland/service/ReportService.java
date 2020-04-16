package nl.inholland.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import nl.inholland.model.Student;

public class ReportService {

    public void SaveReports(List<Student> students) {

        for (Student student : students){
            String result;
            if (student.hasPassed()){
                result = "Has passed";
            } else {
                result = "Has not passed";
            }

            // Create report document text list
            List<String> lines = Arrays.asList(
                    "Report of student " + student.getFullName(),
                    "",
                    String.format("%-25s%d", "Student Id", student.getId()),
                    String.format("%-25s%s", "First Name", student.getFirstName()),
                    String.format("%-25s%s", "Last Name", student.getLastName()),
                    String.format("%-25s%s", "Birthdate", student.getDateOfBirth()),
                    String.format("%-25s%d", "Age", student.getAge()),
                    String.format("%-25s%s", "Group", student.getGroup()),
                    "",
                    String.format("%15s", "COURSES"),
                    "",
                    String.format("%-25s%d", "Java", student.getJava()),
                    String.format("%-25s%d", "CSharp", student.getCSharp()),
                    String.format("%-25s%d", "Python", student.getPython()),
                    String.format("%-25s%d", "PHP", student.getPHP()),
                    "",
                    String.format("%15s", "RESULTS"),
                    "",
                    String.format("%-25s%s	", "Result", result),
                    String.format("%-25s%d", "Retakes", student.getRetakes()));

            // Create file or get file location
            Path file = Paths.get(student.getId() + " " + student.getFirstName() + " " + student.getLastName() + ".txt");

            try {
                // Write to the file with the lines and overwrite if file already exist
                Files.write(file, lines, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
