package com.restfulapi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.restfulapi.dao.SportsDao;
import com.restfulapi.pojo.SportsObject;

public class MysqlJdbcConnection {
	public ArrayList<SportsObject> getcountrywisesports(){

        String url = "jdbc:mysql://localhost:3306/sports?useSSL=false";
        String user = "root";
        String password = "root"; 
        ArrayList<SportsObject> sportsdata = null;

        try {
        	Class.forName("com.mysql.jdbc.Driver");  
        	Connection con = DriverManager.getConnection(url, user, password);        
            SportsDao sportsdao = new SportsDao();
            sportsdata=sportsdao.getcountrywisesports(con);

        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(MysqlJdbcConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sportsdata; 
    }
}
