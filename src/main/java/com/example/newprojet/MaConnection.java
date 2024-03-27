package com.example.newprojet;

import java.sql.*;

class MaConnection {

        public static Connection connecter()
        {
            Connection con=null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/base1","root","");
                System.out.println("Connected...");

            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Error Connection!!");
            }

            return con;
        }

        public static void main(String[] args) {
            connecter();

        }

}  