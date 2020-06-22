package nl.inholland;

import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
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

    private final Config config = new Config();

    private Scene scene;

    private final AuthenticationService authService = new AuthenticationService();
    private final UserService userService = authService.getUserService();

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

        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#ff0000"));
        GridPane.setConstraints(errorLabel, 0, 3);

        scene = new Scene(gridPane, 400, 200);

        loginButton.setOnAction( arg -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                if (!username.equals("") && !password.equals("")) {
                    if (authService.login(username, password)){

                        scene = new Scene(new StudentView(authService.getCurrentUser(), this.userService, window), config.getWindowWidth(), config.getWindowHeight());
                        window.setTitle("Student Management");
                        window.setScene(scene);
                    }
                    else {
                        passwordField.clear();
                        usernameField.requestFocus();
                        throw new UnauthorizedException("Bad credentials");

                    }
                }
                else {
                    usernameField.requestFocus();
                    throw new UnauthorizedException("Empty username or password");
                }
            } catch (IllegalArgumentException | UnauthorizedException e) {
                errorLabel.setText(String.valueOf(e.getMessage()));
            }


        });

        gridPane.getChildren().addAll(userLabel, usernameField, passwordLabel, passwordField, loginButton, errorLabel);

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

class UnauthorizedException extends Exception
{
    public UnauthorizedException(String message) {
        super(message);
    }
}

