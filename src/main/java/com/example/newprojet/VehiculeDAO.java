package com.example.newprojet;


import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class VehiculeDAO {
    Statement st=null;
    private ComboBox<Object> places;
    private ObservableList<Object> data;

    public VehiculeDAO() {
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
    int Ajouter( String Marque , String Modele , String Matricule,String Type,String place)
    {
        int a =0;
        try {
            a=st.executeUpdate("insert into vehicule values(NULL,'"+Marque+"','"+Modele+"','"+Matricule+"','"+Type+"','"+place+"')");
            a= st.executeUpdate("update places set etat='occuper' where place='"+place+"'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }

    int Liberer(String Matricule ,String place) {
        int a = 0;
        try {
            a= st.executeUpdate("delete from vehicule where Matricule ='" + Matricule + "'");
            a= st.executeUpdate("update places set etat='libre' where place='"+place+"'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }

    int Modifier( String Marque , String Modele , String Matricule,String Type,String place) {
        int a = 0;
        try {
            a = st.executeUpdate("update vehicule set marque='" + Marque + "', modèle='" + Modele + "',type='"+ Type +"',place='"+place+"' where  matricule='" + Matricule + "'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }


}
