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

        Connection con = ConnectionProvider.getConnection();

        String createTableQuery = "create table if not exists student (" +
                "st_id int primary key auto_increment," +
                "st_img longblob" +
                ");";

        Statement st = con.createStatement();

        st.executeUpdate(createTableQuery);

        String insertImageQuery = "insert into student(st_img) values (?);";
        File file = new File("C:\\Users\\Pranshu Bansal\\Downloads\\img1.jpg");
        FileInputStream fis = new FileInputStream(file);

        PreparedStatement ps = con.prepareStatement(insertImageQuery);
        ps.setBinaryStream(1, fis, (int)file.length());
        ps.executeUpdate();

    }
}