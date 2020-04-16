package nl.inholland.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.model.Student;

public class ReportView extends GridPane {
    String hasPassed;
    Button editReportButton;
    Label reportTitleLabel, studentIdLabel, firstNameLabel, lastNameLabel, ageLabel, studentIdValue, firstNameValue,
            lastNameValue, ageValue, coursesTitleLabel, javaLabel, cSharpLabel, pythonLabel, phpLabel, javaValue,
            cSharpValue, pythonValue, phpValue, resultsTitleLabel, resultLabel, retakesLabel, resultValue, retakesValue;

    public ReportView(Stage window, Student student) {

        this.setPadding(new Insets(30, 30, 30, 30));
        this.setVgap(10);
        this.setHgap(8);

        window.toFront();

        if (student.hasPassed()) {
            hasPassed = "Passed";
        }
        else {
            hasPassed = "Not Passed";
        }

        reportTitleLabel = new Label("Report of student " + student.getFullName());

        studentIdLabel = new Label("Student ID");
        firstNameLabel = new Label("First Name");
        lastNameLabel = new Label("Last Name");
        ageLabel = new Label("Age");

        studentIdValue = new Label(Integer.toString(student.getId()));
        firstNameValue = new Label(student.getFirstName());
        lastNameValue = new Label(student.getLastName());
        ageValue = new Label(Integer.toString(student.getAge()));

        coursesTitleLabel = new Label("COURSES");

        javaLabel = new Label("Java");
        cSharpLabel = new Label("CSharp");
        pythonLabel = new Label("Python");
        phpLabel = new Label("PHP");

        javaValue = new Label(Integer.toString(student.getJava()));
        cSharpValue = new Label(Integer.toString(student.getCSharp()));
        pythonValue = new Label(Integer.toString(student.getPython()));
        phpValue = new Label(Integer.toString(student.getPHP()));

        resultsTitleLabel = new Label("RESULTS");

        resultLabel = new Label("Result");
        retakesLabel = new Label("Retakes");
        resultLabel = new Label("Result");


        resultValue = new Label(hasPassed);
        retakesValue = new Label(Integer.toString(student.getRetakes()));

        editReportButton = new Button("Add/Update Report");

        GridPane.setConstraints(reportTitleLabel, 0, 0);

        GridPane.setConstraints(studentIdLabel, 0, 3);
        GridPane.setConstraints(firstNameLabel, 0, 4);
        GridPane.setConstraints(lastNameLabel, 0, 5);
        GridPane.setConstraints(ageLabel, 0, 6);

        GridPane.setConstraints(studentIdValue, 1, 3);
        GridPane.setConstraints(firstNameValue, 1, 4);
        GridPane.setConstraints(lastNameValue, 1, 5);
        GridPane.setConstraints(ageValue, 1, 6);

        GridPane.setConstraints(coursesTitleLabel, 0, 9);

        GridPane.setConstraints(javaLabel, 0, 10);
        GridPane.setConstraints(cSharpLabel, 0, 11);
        GridPane.setConstraints(pythonLabel, 0, 12);
        GridPane.setConstraints(phpLabel, 0, 13);

        GridPane.setConstraints(javaValue, 1, 10);
        GridPane.setConstraints(cSharpValue, 1, 11);
        GridPane.setConstraints(pythonValue, 1, 12);
        GridPane.setConstraints(phpValue, 1, 13);

        GridPane.setConstraints(resultsTitleLabel, 0, 16);

        GridPane.setConstraints(resultLabel, 0, 17);
        GridPane.setConstraints(retakesLabel, 0, 18);

        GridPane.setConstraints(resultValue, 1, 17);
        GridPane.setConstraints(retakesValue, 1, 18);

        GridPane.setConstraints(editReportButton, 0, 20);

        editReportButton.setOnAction(event -> {
            Scene scene = new Scene(new ReportUpdateView(window, student), 350, 600);
            window.setTitle("Update Report");
            window.setScene(scene);
        });

        this.getChildren().addAll(
                reportTitleLabel, studentIdLabel, firstNameLabel, lastNameLabel, ageLabel, studentIdValue, firstNameValue,
                lastNameValue, ageValue, coursesTitleLabel, javaLabel, cSharpLabel, pythonLabel, phpLabel, javaValue,
                cSharpValue, pythonValue, phpValue, resultsTitleLabel, resultLabel, retakesLabel, resultValue, retakesValue, editReportButton
        );
    }
}
