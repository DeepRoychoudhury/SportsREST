package com.restfulapi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlJdbcConnection {
	public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/sports?useSSL=false";
        String user = "root";
        String password = "root";
        
        String query = "SELECT * from sports.Countrywise_sports";

        try (Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                System.out.println(rs.getString(2)+" has " +rs.getString(3));
                }
            

        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(MysqlJdbcConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } 
    }
}
