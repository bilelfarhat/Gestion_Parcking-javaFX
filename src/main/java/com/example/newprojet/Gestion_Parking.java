package com.example.newprojet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Gestion_Parking implements Initializable {

    @FXML
    private Button Ajouter;

    @FXML
    private Button Exit;

    @FXML
    private Button Liberer;

    @FXML
    private Button Modifier;

    @FXML
    private Label alert;
    @FXML
    private Label laert2;
    @FXML
    private Button retour;

    @FXML
    private ComboBox<String> tf_Marque;

    @FXML
    private TextField tf_Matricule;

    @FXML
    private TextField tf_Modele;
    @FXML
    private TextField tf_p;

    @FXML
    private ComboBox<?> tf_place;

    @FXML
    private ComboBox<String> tf_type;

    @FXML
    private TableView<ObservableList> vehicule;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void initialize() {
        buildData("select * from vehicule");

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
                vehicule.getColumns().addAll(col);
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
            vehicule.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    void Ajouter(ActionEvent event) throws SQLException {
        if (!isEmpty()) {
            VehiculeDAO vdao = new VehiculeDAO();
            vdao.Ajouter(tf_Marque.getValue().toString(), tf_Modele.getText(), tf_Matricule.getText(), tf_type.getValue().toString(), tf_place.getValue().toString());
            cbox();

            vehicule.getItems().clear();
            vehicule.getColumns().clear();
            buildData("select * from vehicule");
            tf_Modele.setText(null);
            tf_Matricule.setText(null);
        } else {
            alert.setText("Il faut remplir les champs");
            alert.setVisible(true);
        }



    }

    @FXML
    void Exit(ActionEvent event) {
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Liberer(ActionEvent event) throws SQLException {
        if (!lib()) {
            VehiculeDAO vdao = new VehiculeDAO();
            vdao.Liberer(tf_Matricule.getText(), tf_p.getText());
            cbox();
            vehicule.getItems().clear();
            vehicule.getColumns().clear();
            buildData("SELECT * FROM vehicule");
            tf_Modele.setText(null);
            tf_Matricule.setText(null);
            tf_p.setText(null);


        } else {
            laert2.setText("Il faut remplir les champs");
            laert2.setVisible(true);
        }

    }

    @FXML
    void Modifier(ActionEvent event) throws SQLException {
        if(!isEmpty()){
            VehiculeDAO vdao = new VehiculeDAO();
            vdao.Modifier(tf_Marque.getValue().toString(), tf_Modele.getText(), tf_Matricule.getText(), tf_type.getValue().toString(), tf_place.getValue().toString());
            cbox();
            vehicule.getItems().clear();
            vehicule.getColumns().clear();
            buildData("SELECT * FROM vehicule");
            tf_Modele.setText(null);
            tf_Matricule.setText(null);

        }
        else {
              alert.setText("Il faut remplir les champs");
              alert.setVisible(true);
        }



    }

    @FXML
    void reset(ActionEvent event) {
        tf_Modele.setText(null);
        tf_Matricule.setText(null);
        tf_p.setText(null);


    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tf_type.getItems().add("Voiture");
        tf_type.getItems().add("Camion");
        tf_type.getItems().add("Camion à remorque");
        tf_Marque.getItems().add("ALFA ROMEO");
        tf_Marque.getItems().add("AUDI");
        tf_Marque.getItems().add("BMW");
        tf_Marque.getItems().add("CHERY");
        tf_Marque.getItems().add("CHEVROLET");
        tf_Marque.getItems().add("FIAT");
        tf_Marque.getItems().add("FORD");
        tf_Marque.getItems().add("HAVAL");
        tf_Marque.getItems().add("HONDA");
        tf_Marque.getItems().add("HYUNDAI");
        tf_Marque.getItems().add("JAGUAR");
        tf_Marque.getItems().add("JEEP");
        tf_Marque.getItems().add("KIA");
        tf_Marque.getItems().add("LAND-ROVER");
        tf_Marque.getItems().add("MAHINDRA");
        tf_Marque.getItems().add("MARCEDES-BENZ");
        tf_Marque.getItems().add("MG");
        tf_Marque.getItems().add("MINI");
        tf_Marque.getItems().add("MITSUBISHI");
        tf_Marque.getItems().add("NISSAN");
        tf_Marque.getItems().add("OPEL");
        tf_Marque.getItems().add("PEUGEOT");
        tf_Marque.getItems().add("PORSCHE");
        tf_Marque.getItems().add("renault");
        tf_Marque.getItems().add("SEAT");
        tf_Marque.getItems().add("SKODA");
        tf_Marque.getItems().add("SUZUKI");
        tf_Marque.getItems().add("TOYOTA");
        tf_Marque.getItems().add("VOLKSWAGEN");
        tf_Marque.getItems().add("MAN");
        tf_Marque.getItems().add("VOLVO");
        tf_Marque.getItems().add("ISUZU");
        try {
            cbox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        public void cbox () throws SQLException {
            try {
                Connection con = MaConnection.connecter();
                ResultSet rs = con.createStatement().executeQuery("select place from places where etat='libre'");
                ObservableList data = FXCollections.observableArrayList();
                while (rs.next()) {
                    data.add(new String(rs.getString(1)));
                }
                tf_place.setItems(data);
                vehicule.getItems().clear();
                vehicule.getColumns().clear();
                buildData("SELECT * FROM vehicule");
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        private boolean isEmpty () {
            return tf_Modele.getText().trim().isEmpty() && tf_Matricule.getText().trim().isEmpty();
        }
        private boolean lib() {
            return tf_Matricule.getText().trim().isEmpty() && tf_p.getText().trim().isEmpty();
        }
    }



