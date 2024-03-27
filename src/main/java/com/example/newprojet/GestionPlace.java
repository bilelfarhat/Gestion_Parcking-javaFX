package com.example.newprojet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Callback;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionPlace {

    @FXML
    private Button ajouter;

    @FXML
    private Button exit;

    @FXML
    private Button modifier;

    @FXML
    private Button retour;

    @FXML
    private Button supprimer;

    @FXML
    private TextField textetat;
    @FXML
    private Label label;
    @FXML
    private TableView<ObservableList> places;

    @FXML
    private TextField textplace;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void initialize() {
        buildData("select * from places");

    }

    void buildData(String sql) {
        ObservableList<ObservableList> data;
        data = FXCollections.observableArrayList();

        try {
            Connection con = MaConnection.connecter();
            ResultSet rs = con.createStatement().executeQuery(sql);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                places.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            places.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    void ajouter(ActionEvent event) {
        if(! isEmpty()){
            PlaceDAO pdao= new PlaceDAO();
            pdao.ajouter(textplace.getText(),textetat.getText());
            places.getItems().clear();
            places.getColumns().clear();
            buildData("SELECT * FROM places");
            textplace.setText(null);
            textetat.setText(null);
        }
        else {
            label.setText("Il faut remplir les champs");
            label.setVisible(true);
        }

    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();

    }


    @FXML
    void modifier(ActionEvent event) {
        if(! isEmpty()){
            PlaceDAO pdao= new PlaceDAO();
            pdao.Modifier(textplace.getText(),textetat.getText());
            places.getItems().clear();
            places.getColumns().clear();
            buildData("SELECT * FROM places");
            textplace.setText(null);
            textetat.setText(null);
        }
        else {
            label.setText("Il faut remplir les champs");
            label.setVisible(true);
        }


    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void supprimer(ActionEvent event) {
        if(! isEmpty()){
            PlaceDAO pdao = new PlaceDAO();
            pdao.supprimer( textplace.getText());
            places.getItems().clear();
            places.getColumns().clear();
            buildData("SELECT * FROM palaces");
            textplace.setText(null);
            textetat.setText(null);
        }
        else {
            label.setText("Il faut remplir les champs");
            label.setVisible(true);
        }


    }
    private boolean isEmpty() {
        return textplace.getText().trim().isEmpty() && textetat.getText().trim().isEmpty();
    }
}


