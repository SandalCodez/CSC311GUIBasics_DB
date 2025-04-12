package com.example.module03_basicgui_db_interface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.List;
import java.util.ResourceBundle;


public class DB_GUI_Controller implements Initializable {

    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Jacob", "Smith", "CPIS", "CS"),
                    new Person("Jacob2", "Smith1", "CPIS1", "CS")

            );


    @FXML
    TextField first_name, last_name, department, major;
    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, Integer> tv_id;
    @FXML
    private TableColumn<Person, String> tv_fn, tv_ln, tv_dept, tv_major;

    @FXML
    private Button showAllBtn;

    @FXML
    ImageView img_view;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tv_dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        tv_major.setCellValueFactory(new PropertyValueFactory<>("major"));


        tv.setItems(data);
    }


    @FXML
    protected void addNewRecord() {

//        data.add(new Person(
//                data.size()+1,
//                first_name.getText(),
//                last_name.getText(),
//                department.getText(),
//                major.getText()
//        ));
        ConnDBOpsCopy connection = new ConnDBOpsCopy();
        connection.connectToDatabase();

        //Database INSERT INTO query action
        connection.insertUser(first_name.getText(), last_name.getText(), department.getText(), major.getText());
    }

    @FXML
    protected void clearForm() {
        first_name.clear();
        last_name.setText("");
        department.setText("");
        major.setText("");
    }

    @FXML
    protected void closeApplication() {
        System.exit(0);
    }


    @FXML
    protected void editRecord() {
        Person p= tv.getSelectionModel().getSelectedItem();
        int c=data.indexOf(p);
        Person p2= new Person();
        p2.setId(c+1);
        p2.setFirstName(first_name.getText());
        p2.setLastName(last_name.getText());
        p2.setDept(department.getText());
        p2.setMajor(major.getText());
        data.remove(c);
        data.add(c,p2);
        tv.getSelectionModel().select(c);

        //Database UPDATE query action
        ConnDBOpsCopy connection = new ConnDBOpsCopy();
        connection.editUser(first_name.getText(), last_name.getText(), department.getText(), major.getText());
    }

    @FXML
    protected void deleteRecord() {
        Person p= tv.getSelectionModel().getSelectedItem();
        data.remove(p);

        //Database DELETE query call
        ConnDBOpsCopy connection = new ConnDBOpsCopy();
        connection.deleteUser(first_name.getText(), last_name.getText());
    }



    @FXML
    protected void showImage() {
        File file= (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
        if(file!=null){
            img_view.setImage(new Image(file.toURI().toString()));

        }
    }
    //method to populate the list on the UI with data from the dataBase
    @FXML
    protected void handleShowAll() {
        ConnDBOpsCopy connection = new ConnDBOpsCopy();
        List<Person> users = connection.listAllUsers();
        if(users!=null){
            ObservableList<Person> data = FXCollections.observableArrayList(users);
            tv.setItems(data);
        }else{
            System.out.println("No users found");
        }

    }





    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        Person p= tv.getSelectionModel().getSelectedItem();
        first_name.setText(p.getFirstName());
        last_name.setText(p.getLastName());
        department.setText(p.getDept());
        major.setText(p.getMajor());


    }
}