package nl.inholland;

import javafx.scene.control.*;
import nl.inholland.service.AuthenticationService;
import nl.inholland.service.UserService;
import nl.inholland.view.StudentView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;


public class Main extends Application {

    Scene scene;

    AuthenticationService authService = new AuthenticationService();
    UserService userService = authService.getUserService();

    @Override
    public void start(Stage window) {

        window.setTitle("Login screen");


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50, 10, 10, 30));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        Label userLabel = new Label("Email");
        GridPane.setConstraints(userLabel, 0, 0);

        TextField usernameField = new TextField();
        usernameField.setPromptText("email");
        GridPane.setConstraints(usernameField, 1, 0);

        Label passwordLabel = new Label("Password");
        GridPane.setConstraints(passwordLabel,  0,  1);

        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 1);

        Button loginButton = new Button();
        loginButton.setText("Log in");
        GridPane.setConstraints(loginButton, 1, 2);

        scene = new Scene(gridPane, 300, 200);

        loginButton.setOnAction( arg -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                if (!username.equals("") && !password.equals("")) {
                    if (authService.login(username, password)){

                        scene = new Scene(new StudentView(authService.getCurrentUser(), this.userService, window), 1050, 400);
                        window.setTitle("Student Management");
                        window.setScene(scene);
                    }
                    else {
                        msgBox("Username or password incorrect. Please try again.");
                    }
                }
                else {
                    msgBox("Please fill in both fields");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }


        });

        gridPane.getChildren().addAll(userLabel, usernameField, passwordLabel, passwordField, loginButton);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void msgBox(String s){
        JOptionPane.showMessageDialog(null, s);
    }
}

