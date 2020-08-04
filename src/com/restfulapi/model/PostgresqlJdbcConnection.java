package com.restfulapi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.restfulapi.dao.SportsDao;
import com.restfulapi.pojo.SportsObject;

public class PostgresqlJdbcConnection {
	public ArrayList<SportsObject> getcountrywisesports(){

        String url = "jdbc:postgresql://sportsdatabase.cpwytlyekgvv.us-east-1.rds.amazonaws.com:5432/sports";
        String user = "postgres";
        String password = "postgres"; 
        ArrayList<SportsObject> sportsdata = null;

        try {
        	Class.forName("org.postgresql.Driver");  
        	Connection con = DriverManager.getConnection(url, user, password);        
            SportsDao sportsdao = new SportsDao();
            sportsdata=sportsdao.getcountrywisesports(con);

        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(PostgresqlJdbcConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sportsdata; 
    }
}
