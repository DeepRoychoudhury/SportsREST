package com.restfulapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.restfulapi.pojo.SportsObject;

public class SportsDao {
	
	public ArrayList<SportsObject> getcountrywisesports(Connection con){
	ArrayList<SportsObject> sportsdata = new ArrayList<SportsObject>();	
	String query = "SELECT * from sports.Countrywise_sports";
	try {
	PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery(); 

   while(rs.next()) {
    	SportsObject sportsobject = new SportsObject();
    	sportsobject.setCountry(rs.getString(2));
    	sportsobject.setSportsplayed(rs.getString(3));
    	sportsdata.add(sportsobject);
        }
	}
	catch(SQLException ex) {
		 Logger lgr = Logger.getLogger(SportsDao.class.getName());
         lgr.log(Level.SEVERE, ex.getMessage(), ex);
	}
	return sportsdata;
    }
}
