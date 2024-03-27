package com.example.newprojet;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class PlaceDAO {
    private ObservableList<Object> data;
    Statement st = null;

    public PlaceDAO() {
        Connection con = MaConnection.connecter();
        if (con != null) {
            try {
                st = con.createStatement();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    int ajouter(String place, String etat) {
        int a = 0;
        try {
            a = st.executeUpdate("insert into places values('" + place + "','" + etat + "')");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }


    int supprimer (String place ) {
        int a = 0;
        try {
            a= st.executeUpdate("delete from places where place ='" + place + "'");
        } catch (SQLException e) {
                // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }

    int Modifier( String place , String etat) {
        int a = 0;
        try {
            a = st.executeUpdate("update places set etat='" + etat + "' where place='" + place + "'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }
}


