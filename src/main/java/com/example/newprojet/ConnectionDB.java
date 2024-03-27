package com.example.newprojet;

import java.sql.*;

class ConnectionDB{
    Statement stmt=null;
    public ConnectionDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test","root","");
        stmt=con.createStatement();
    }

}  