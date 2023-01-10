package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    public TableColumn<Student, Integer> stIdCol;
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
    public TableColumn<Group, Integer> grIdCol;
    @FXML
    public TableColumn<Group, String> grNameCol;
    @FXML
    public TableColumn<Group, Integer> grNumberCol;
    @FXML
    public TableColumn<Group, String> grFacultyCol;

    ObservableList<Student> studentObservableList = FXCollections.observableArrayList();
    ObservableList<Group> groupObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resource) {

        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getDBConnection();

        String studentsViewQuery = "SELECT student_id, lastname, name, patronymic, birthdate, group_name FROM students.students";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(studentsViewQuery);

            while (queryOutput.next()) {

                Integer queryStudentId = queryOutput.getInt("student_id");
                String queryStudentLastname = queryOutput.getString("lastname");
                String queryStudentName = queryOutput.getString("name");
                String queryStudentPatronymic = queryOutput.getString("patronymic");
                String queryStudentBirthdate = queryOutput.getString("birthdate");
                String queryStudentGroupname = queryOutput.getString("group_name");

                studentObservableList.add(new Student(queryStudentId,
                        queryStudentName,
                        queryStudentLastname,
                        queryStudentPatronymic,
                        queryStudentBirthdate,
                        queryStudentGroupname));

            }

            stIdCol.setCellValueFactory(new PropertyValueFactory<>("student_id"));
            stLastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            stNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            stPatronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
            stDateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
            stGroupnameCol.setCellValueFactory(new PropertyValueFactory<>("group_name"));

            studentsTable.setItems(studentObservableList);

        } catch (SQLException e) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        String groupViewQuery = "SELECT group_id, group_name, group_number, faculty FROM students.groups";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(groupViewQuery);

            while (queryOutput.next()) {

                Integer queryGroupId = queryOutput.getInt("group_id");
                String queryGroupName = queryOutput.getString("group_name");
                Integer queryGroupNumber = queryOutput.getInt("group_number");
                String queryGroupFaculty = queryOutput.getString("faculty");

                groupObservableList.add(new Group(queryGroupId,
                        queryGroupName,
                        queryGroupNumber,
                        queryGroupFaculty));
            }

            grIdCol.setCellValueFactory(new PropertyValueFactory<>("group_id"));
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
