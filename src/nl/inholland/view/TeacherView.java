package nl.inholland.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.service.UserService;
import nl.inholland.model.Teacher;
import nl.inholland.model.User;

public class TeacherView extends VBox {

    private final UserService userService;

    public TeacherView (User currentUser, UserService userService, Stage window){
        this.userService = userService;

        setPadding(new Insets(10, 10, 10, 10));

        MenuBar menuBar = new MenuBar();
        Menu studentMenu = new Menu("Students");
        Menu teacherMenu = new Menu("Teachers");
        MenuItem studentMenuItemList = new Menu("Display List");
        MenuItem teacherMenuItemList = new Menu("Display List");

        studentMenu.getItems().addAll(studentMenuItemList);
        teacherMenu.getItems().addAll(teacherMenuItemList);
        menuBar.getMenus().addAll(studentMenu, teacherMenu);

        studentMenuItemList.setOnAction(
                arg0 -> {
                    Scene scene = new Scene(new StudentView(currentUser, userService, window), 1050, 400);
                    window.setTitle("Student Management");
                    window.setScene(scene);
                });

        teacherMenuItemList.setOnAction(
                arg0 -> {
                    Scene scene = new Scene(new TeacherView(currentUser, userService, window), 1050, 400);
                    window.setTitle("Teacher Management");
                    window.setScene(scene);
                });



        Label title = new Label("Teacher List");

        TableView<Teacher> tableView = new TableView<>();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Teacher, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Teacher, String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.setMinWidth(100);

        TableColumn<Teacher, String> lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setMinWidth(100);

        TableColumn<Teacher, String> bdayColumn = new TableColumn<>("Birth date");
        bdayColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        bdayColumn.setMinWidth(100);

        TableColumn<Teacher, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setMinWidth(50);

        TableColumn<Teacher, String> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryColumn.setMinWidth(100);


        tableView.setItems(getTeachers());

        tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, bdayColumn, ageColumn, salaryColumn);

        this.getChildren().addAll(menuBar, title, tableView);

    }

    private ObservableList<Teacher> getTeachers() {
        return FXCollections.observableArrayList(userService.getAllTeachers());
    }
}
