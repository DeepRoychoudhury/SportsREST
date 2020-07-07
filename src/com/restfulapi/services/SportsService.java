package com.restfulapi.services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.restfulapi.model.MysqlJdbcConnection;
import com.restfulapi.pojo.SportsObject;

@Path("/sports")
public class SportsService {
	
@GET
@Path("/listall")
@Produces("application/json")
public String requestAllList() {	
	String data = null;
	ArrayList<SportsObject> sportsdata = null;
	MysqlJdbcConnection mydata = new MysqlJdbcConnection();
	sportsdata=mydata.getcountrywisesports();
	Gson gson = new Gson();
	System.out.println(gson.toJson(sportsdata));
	data = gson.toJson(sportsdata);

	/*
	 * StringBuilder sb = new StringBuilder(); for(int i=0; i<sportsdata.size();i++)
	 * { sb.append("<sportsdetails>");
	 * sb.append("<country>"+sportsdata.get(i).getCountry().toString()+"</country>")
	 * ; sb.append("<sports>"+sportsdata.get(i).getSportsplayed().toString()+
	 * "</sports>"); sb.append("</sportsdetails>"); } return sb.toString();
	 */
	return data;
}
}
