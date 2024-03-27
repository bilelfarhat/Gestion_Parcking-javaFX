package com.example.newprojet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Button cancel;
    @FXML
    private Label pass;

    @FXML
    private Button login;

    @FXML
    private ImageView par;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane scenepane;

    @FXML
    private TextField username;

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    protected void login(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if (username.getText().toString().equals("admin")&& password.getText().toString().equals("admin")) {
            root = FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }



        else if (username.getText().isEmpty()&&password.getText().isEmpty()){
            pass.setText("please enter your data");



            }
        else {
            pass.setText("wrong username or password");
        }

    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }




}



