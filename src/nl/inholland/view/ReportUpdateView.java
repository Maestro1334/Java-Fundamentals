package nl.inholland.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.model.Student;

import javax.swing.*;

public class ReportUpdateView extends GridPane {

    Stage window;

    Label updateTitleLabel, currJavaLabel, currCSharpLabel, currPythonLabel, currPhpLabel,
            currJavaValueLabel, currCSharpValueLabel, currPythonValueLabel, currPhpValueLabel,
            newJavaLabel, newCSharpLabel, newPythonLabel, newPhpLabel;

    TextField newJavaTxt, newCSharpTxt, newPythonTxt, newPhpTxt;
    Button saveGradesButton, cancelButton;

    private final Student student;

    public ReportUpdateView(Stage window, Student student) {
        this.setPadding(new Insets(30, 30, 30, 30));
        this.setVgap(10);
        this.setHgap(8);

        this.window = window;
        this.student = student;

        updateTitleLabel = new Label("Update Report");

        currJavaLabel = new Label("Current Java grade is ");
        currCSharpLabel = new Label("Current CSharp grade is ");
        currPythonLabel = new Label("Current Python grade is ");
        currPhpLabel = new Label("Current PHP grade is ");

        currJavaValueLabel = new Label(Integer.toString(student.getJava()));
        currCSharpValueLabel = new Label(Integer.toString(student.getCSharp()));
        currPythonValueLabel = new Label(Integer.toString(student.getPython()));
        currPhpValueLabel = new Label(Integer.toString(student.getPHP()));

        newJavaLabel = new Label("New Java grade: ");
        newCSharpLabel = new Label("New CSharp grade: ");
        newPythonLabel = new Label("New Python grade: ");
        newPhpLabel = new Label("New PHP grade: ");

        newJavaTxt = new TextField();
        newCSharpTxt = new TextField();
        newPythonTxt = new TextField();
        newPhpTxt = new TextField();

        saveGradesButton = new Button("Save Grades");
        cancelButton = new Button("Cancel");

        GridPane.setConstraints(updateTitleLabel, 0, 0);

        GridPane.setConstraints(currJavaLabel, 0, 2);
        GridPane.setConstraints(currCSharpLabel, 0, 3);
        GridPane.setConstraints(currPythonLabel, 0, 4);
        GridPane.setConstraints(currPhpLabel, 0, 5);

        GridPane.setConstraints(currJavaValueLabel, 1, 2);
        GridPane.setConstraints(currCSharpValueLabel, 1, 3);
        GridPane.setConstraints(currPythonValueLabel, 1, 4);
        GridPane.setConstraints(currPhpValueLabel, 1, 5);

        GridPane.setConstraints(newJavaLabel, 0, 7);
        GridPane.setConstraints(newCSharpLabel, 0, 8);
        GridPane.setConstraints(newPythonLabel, 0, 9);
        GridPane.setConstraints(newPhpLabel, 0, 10);

        GridPane.setConstraints(newJavaTxt, 1, 7);
        GridPane.setConstraints(newCSharpTxt, 1, 8);
        GridPane.setConstraints(newPythonTxt, 1, 9);
        GridPane.setConstraints(newPhpTxt, 1, 10);

        GridPane.setConstraints(cancelButton, 0, 14);
        GridPane.setConstraints(saveGradesButton, 1, 14);

        cancelButton.setOnAction(event -> {
            returnToDetails();
        });

        saveGradesButton.setOnAction(event -> {
            try {
                save();
            } catch(Exception e){
                e.printStackTrace();
            }
        });

        this.getChildren().addAll(
                updateTitleLabel, currJavaLabel, currCSharpLabel, currPythonLabel, currPhpLabel,
                currJavaValueLabel, currCSharpValueLabel, currPythonValueLabel, currPhpValueLabel,
                newJavaLabel, newCSharpLabel, newPythonLabel, newPhpLabel, newJavaTxt, newCSharpTxt,
                newPythonTxt, newPhpTxt, saveGradesButton, cancelButton
        );
    }

    private void save() {
        try {
            if (fieldCheck()) {
                student.addGrade("Java", getIntegerFromTextField(newJavaTxt));
                student.addGrade("CSharp", getIntegerFromTextField(newCSharpTxt));
                student.addGrade("Python", getIntegerFromTextField(newPythonTxt));
                student.addGrade("PHP", getIntegerFromTextField(newPhpTxt));

                msgBox("Grades saved");
                returnToDetails();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Boolean fieldCheck() {
        try {
            getIntegerFromTextField(newJavaTxt);
            getIntegerFromTextField(newCSharpTxt);
            getIntegerFromTextField(newPythonTxt);
            getIntegerFromTextField(newPhpTxt);

            return true;
        } catch (NumberFormatException e) {
            msgBox("Please enter only numbers between 10 - 100");
            return false;
        }
    }

    Integer getIntegerFromTextField(TextField textBox) {
        int integer = 0;

        if (textBox.getText().trim().equals("")) {
            return integer;
        }
        else {
            integer = Integer.parseInt(textBox.getText());
        }

        if (integer < 10 || integer > 100){
            textBox.clear();
            throw new NumberFormatException();
        }

        return integer;
    }

    private void returnToDetails() {
        Scene scene = new Scene(new ReportView(window, student), 350, 600);
        window.setTitle("Report Details");
        window.setScene(scene);
        window.show();
    }

    private void msgBox(String s){
        JOptionPane.showMessageDialog(null, s);
    }
}

