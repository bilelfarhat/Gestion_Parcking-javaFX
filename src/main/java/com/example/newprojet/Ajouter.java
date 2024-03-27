package com.example.newprojet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Ajouter {

    @FXML
    private Button Gestion_parking;

    @FXML
    private Button Gestion_places;
    @FXML
    private Button retour;

    @FXML
    private ImageView ImageView;
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void Gestion_parking(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Gestion_Parking.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Gestion_places(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestionPlace.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void retour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();



    }

}


