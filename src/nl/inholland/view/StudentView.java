package nl.inholland.view;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.inholland.service.UserService;
import nl.inholland.model.Student;
import nl.inholland.model.User;
import nl.inholland.model.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentView extends VBox {
    UserService userService;

    Scene scene;

    ObservableList<Student> studentList = FXCollections.observableArrayList();
    TableView<Student> tableView = new TableView<>();

    TextField emailInput, passwordInput, firstNameInput, lastNameInput, dateOfBirthInput, groupInput;
    Button reportsButton, addButton, deleteButton;

    Boolean columnsAdded = false;

    public StudentView(User currentUser, UserService userService, Stage window) {

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
                    scene = new Scene(new StudentView(currentUser, userService, window), 1050, 400);
                    window.setTitle("Student Management");
                    window.setScene(scene);
                });

        teacherMenuItemList.setOnAction(
                arg0 -> {
                    scene = new Scene(new TeacherView(currentUser, userService, window), 1050, 400);
                    window.setTitle("Teacher Management");
                    window.setScene(scene);
                });


        Label title = new Label("Student List");
        fillTableView();

        if (currentUser.getUserType() == UserType.EDITOR || currentUser.getUserType() == UserType.ADMIN) {

            firstNameInput = new TextField();
            firstNameInput.setPromptText("First Name");

            lastNameInput = new TextField();
            lastNameInput.setPromptText("Last Name");

            emailInput = new TextField();
            emailInput.setPromptText("Email");

            passwordInput = new TextField();
            passwordInput.setPromptText("Password");

            dateOfBirthInput = new TextField();
            dateOfBirthInput.setPromptText("Date of birth");

            groupInput = new TextField();
            groupInput.setPromptText("Group");

            addButton = new Button("Add");
            addButton.setOnAction( event -> {
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                String email = emailInput.getText();
                String password = passwordInput.getText();
                LocalDate dob = parseStringToDate(dateOfBirthInput.getText());
                String group = groupInput.getText();

                Student student = new Student(userService.getAllUsers().size(), email, password, firstName, lastName, dob, group);
                userService.addStudent(student);
                tableView.getItems().add(student);

                clearInputs();
            });
            deleteButton = new Button("Delete");
            deleteButton.setOnAction( arg0 -> {
                ObservableList<Student> selectedUsers = tableView.getSelectionModel().getSelectedItems();
                studentList.removeAll(selectedUsers);
            });

            reportsButton = new Button("Show Reports");
            reportsButton.setWrapText(true);
            reportsButton.setOnAction(event -> showReports(tableView, getStudents()));


            HBox form = new HBox();
            form.setPadding(new Insets(10, 10, 10, 10));
            form.setSpacing(10);
            form
                    .getChildren()
                    .addAll(emailInput, passwordInput, firstNameInput, lastNameInput, dateOfBirthInput, addButton, deleteButton, reportsButton);

            this.getChildren().addAll(menuBar, title, tableView, form);
        }
        else {
            this.getChildren().addAll(menuBar, title, tableView);
        }
    }

    private void fillTableView(){

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Student, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.setMinWidth(100);

        TableColumn<Student, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setMinWidth(100);

        TableColumn<Student, String> bdayColumn = new TableColumn<>("Birth Date");
        bdayColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        bdayColumn.setMinWidth(100);

        TableColumn<Student, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setMinWidth(50);

        TableColumn<Student, String> groupColumn = new TableColumn<>("Group");
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        groupColumn.setMinWidth(100);


        tableView.setItems(getStudents());

        tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, bdayColumn, ageColumn, groupColumn);
    }

    private void showReports(TableView<Student> table, ObservableList<Student> students){
        if (!columnsAdded) {
            for (String key : students.get(0).getGrades().keySet()) {
                addColumn(table, key);
            }
            addReportDetailsButton(table);
        }
        columnsAdded = true;
    }

    private void addColumn(TableView<Student> table, String key) {
        TableColumn<Student, Number> column = new TableColumn<>(key);
        column.setCellValueFactory(data ->
                new ReadOnlyIntegerWrapper(data.getValue().getGrades().getOrDefault(key, 0)));
        column.setMinWidth(100);
        table.getColumns().add(column);
    }

    private void addReportDetailsButton(TableView<Student> table){
        TableColumn<Student, String> reportDetailsColumn = new TableColumn<>("Report Details");
        reportDetailsColumn.setCellValueFactory(new PropertyValueFactory<>(""));

        Callback<TableColumn<Student, String>, TableCell<Student, String>> cellFactory
                = //
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<Student, String> param) {
                        final TableCell<Student, String> cell = new TableCell<>() {

                            final Button reportDetailsButton = new Button("Report Details");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    reportDetailsButton.setOnAction(event -> {
                                        Student student = getTableView().getItems().get(getIndex());
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(new ReportView(stage, student), 350, 600);
                                        stage.setTitle("Report Details");
                                        stage.setScene(scene);
                                        stage.show();
                                    });
                                    setGraphic(reportDetailsButton);
                                }
                                setText(null);
                            }
                        };
                        return cell;
                    }
                };

        reportDetailsColumn.setCellFactory(cellFactory);
        table.getColumns().add(reportDetailsColumn);
    }

    private ObservableList<Student> getStudents() {
        return FXCollections.observableArrayList(userService.getAllStudents());
    }

    private void clearInputs() {
        emailInput.clear();
        passwordInput.clear();
        firstNameInput.clear();
        lastNameInput.clear();
        dateOfBirthInput.clear();
        groupInput.clear();
    }

    private LocalDate parseStringToDate(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(dateOfBirth, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LocalDate.parse(dateOfBirth, formatter);
    }
}
