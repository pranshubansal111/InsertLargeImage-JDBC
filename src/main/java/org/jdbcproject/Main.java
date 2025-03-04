package org.jdbcproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    
    public static void main(String[] args) throws SQLException, FileNotFoundException {

        //establishing connection
        Connection con = ConnectionProvider.getConnection();

        //query to create table
        String createTableQuery = "create table if not exists student (" +
                "st_id int primary key auto_increment," +
                "st_img longblob" +
                ");";

        //creating statement
        Statement st = con.createStatement();

        //executing statement
        st.executeUpdate(createTableQuery);

        //query to insert image to db
        String insertImageQuery = "insert into student(st_img) values (?);";

        File file = new File("C:\\Users\\Pranshu Bansal\\Downloads\\img1.jpg");
        FileInputStream fis = new FileInputStream(file);

        //preparing statement
        PreparedStatement ps = con.prepareStatement(insertImageQuery);
        ps.setBinaryStream(1, fis, (int)file.length());

        //executing statement
        ps.executeUpdate();
    }
}