package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Group;
import model.Student;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainViewController implements Initializable {
    @FXML
    public TableView<Student> studentsTable;
    @FXML
    public TableColumn<Student, String> stLastnameCol;
    @FXML
    public TableColumn<Student, String> stNameCol;
    @FXML
    public TableColumn<Student, String> stPatronymicCol;
    @FXML
    public TableColumn<Student, String> stDateCol;
    @FXML
    public TableColumn<Student, String> stGroupnameCol;

    @FXML
    public TableView<Group> groupsTable;
    @FXML
    public TableColumn<Group, String> grNameCol;
    @FXML
    public TableColumn<Group, Integer> grNumberCol;
    @FXML
    public TableColumn<Group, String> grFacultyCol;

    @FXML
    public TextField searchTextField;

    ObservableList<Student> studentObservableList = FXCollections.observableArrayList();
    ObservableList<Group> groupObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resource) {

        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getDBConnection();

        String studentsViewQuery = "SELECT lastname, name, patronymic, birthdate, group_name FROM students.students";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(studentsViewQuery);

            while (queryOutput.next()) {

                String queryStudentLastname = queryOutput.getString("lastname");
                String queryStudentName = queryOutput.getString("name");
                String queryStudentPatronymic = queryOutput.getString("patronymic");
                String queryStudentBirthdate = queryOutput.getString("birthdate");
                String queryStudentGroupname = queryOutput.getString("group_name");

                studentObservableList.add(new Student(queryStudentName,
                        queryStudentLastname,
                        queryStudentPatronymic,
                        queryStudentBirthdate,
                        queryStudentGroupname));

            }

            stLastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            stNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            stPatronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
            stDateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
            stGroupnameCol.setCellValueFactory(new PropertyValueFactory<>("group_name"));

            studentsTable.setItems(studentObservableList);

            FilteredList<Student> filteredStudents = new FilteredList<>(studentObservableList, b -> true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredStudents.setPredicate(Student -> {

                    // If no search value then display all records or whatever records it current have. np changes
                    if (newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (Student.getLastname().toLowerCase().contains(searchKeyword)) {
                        return true; // Means we found a match in lastname
                    } else if (Student.getGroup_name().toLowerCase().contains(searchKeyword)) {
                        return true; // Means we found a match in groupname
                    } else
                        return false; // No match found
                });
            });

            SortedList<Student> sortedData = new SortedList<>(filteredStudents);

            // Bind sorted result with Table View
            sortedData.comparatorProperty().bind(studentsTable.comparatorProperty());

            // Apply filtered and sorted data to the Table View
            studentsTable.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        String groupViewQuery = "SELECT group_name, group_number, faculty FROM students.groups";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(groupViewQuery);

            while (queryOutput.next()) {

                String queryGroupName = queryOutput.getString("group_name");
                Integer queryGroupNumber = queryOutput.getInt("group_number");
                String queryGroupFaculty = queryOutput.getString("faculty");

                groupObservableList.add(new Group(queryGroupName,
                        queryGroupNumber,
                        queryGroupFaculty));
            }

            grNameCol.setCellValueFactory(new PropertyValueFactory<>("group_name"));
            grNumberCol.setCellValueFactory(new PropertyValueFactory<>("group_number"));
            grFacultyCol.setCellValueFactory(new PropertyValueFactory<>("faculty"));

            groupsTable.setItems(groupObservableList);

        } catch (SQLException e) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }


    }
}
