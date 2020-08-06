package com.restfulapi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.restfulapi.model.MysqlJdbcConnection;
import com.restfulapi.model.PostgresqlJdbcConnection;
import com.restfulapi.pojo.SportsObject;

@Path("/sports")
public class SportsService {
	
	private static Map<Integer,SportsObject> sportsobject = new HashMap<Integer,SportsObject>();
	
@GET
@Path("/listall")
@Produces("application/json")
public String requestAllList() {	
	String data = null;
	ArrayList<SportsObject> sportsdata = null;
	PostgresqlJdbcConnection mydata = new PostgresqlJdbcConnection();
	sportsdata=mydata.getcountrywisesports();
	Gson gson = new Gson();
	System.out.println(gson.toJson(sportsdata));
	data = gson.toJson(sportsdata);
	System.out.println();
	/*
	 * StringBuilder sb = new StringBuilder(); for(int i=0; i<sportsdata.size();i++)
	 * { sb.append("<sportsdetails>");
	 * sb.append("<country>"+sportsdata.get(i).getCountry().toString()+"</country>")
	 * ; sb.append("<sports>"+sportsdata.get(i).getSportsplayed().toString()+
	 * "</sports>"); sb.append("</sportsdetails>"); } return sb.toString();
	 */
	return data;
}

@GET
@Path("{id}")
@Produces("application/json")
public String requestbasedid(@PathParam("id") int id) {
	String data = null;
	ArrayList<SportsObject> sportsdata = null;
	PostgresqlJdbcConnection mydata = new PostgresqlJdbcConnection();
	sportsdata=mydata.getcountrywithid(id);
	Gson gson = new Gson();
	System.out.println(gson.toJson(sportsdata));
	data = gson.toJson(sportsdata);
	return data;
	
}
}
