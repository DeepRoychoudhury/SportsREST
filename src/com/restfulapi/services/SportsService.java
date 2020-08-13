package com.restfulapi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.restfulapi.model.MysqlJdbcConnection;
import com.restfulapi.model.PostgresqlJdbcConnection;
import com.restfulapi.pojo.SportsObject;
import com.sun.jersey.core.header.reader.HttpHeaderReader.Event;

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
	System.out.println(data);
	return data;
}

@GET
@Path("{id}")
@Produces("application/json")
public String requestbasedid(@PathParam("id") int id, @HeaderParam("authorization") String authString) {
	if(!isUserAuthenticated(authString)){
        return "{\"error\":\"User not authenticated\"}";
    }
	String data = null;
	ArrayList<SportsObject> sportsdata = null;
	PostgresqlJdbcConnection mydata = new PostgresqlJdbcConnection();
	sportsdata=mydata.getcountrywithid(id);
	Gson gson = new Gson();
	System.out.println(gson.toJson(sportsdata));
	data = gson.toJson(sportsdata);
	return data;
	
}

@GET
@Path("/country/{country}")
@Produces("application/json")
public String requestbasedcountry(@PathParam("country") String country) {
	/*
	 * if(!isUserAuthenticated(authString)){ return
	 * "{\"error\":\"User not authenticated\"}"; }
	 */
	String data = null;
	ArrayList<SportsObject> sportsdata = null;
	PostgresqlJdbcConnection mydata = new PostgresqlJdbcConnection();
	sportsdata=mydata.getcountrywithname(country);
	Gson gson = new Gson();
	System.out.println(gson.toJson(sportsdata));
	data = gson.toJson(sportsdata);
	return data;
	
}

@POST
@Path("/add")
@Consumes("text/plain,text/html,application/octet-stream")
@Produces("application/json")
public Response addData(InputStream inputdata, @HeaderParam("authorization") String authString) {	
	if(!isUserAuthenticated(authString)){
        return Response.status(401).entity("Invalid Credentials").build();
    }
	StringBuilder builder = new StringBuilder();
	SportsObject sportsobject = new SportsObject();
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputdata));
		String line = null;
		while((line=in.readLine())!=null) {
			builder.append(line);
		}
	}catch(Exception e) {
		System.out.println("Error");
	}
	System.out.println("Country :"+builder.toString());
	JsonObject jsonobj = new JsonParser().parse(builder.toString()).getAsJsonObject();
	String country = jsonobj.get("country").getAsString();
	String sportsplayed = jsonobj.get("sportsplayed").getAsString();
	System.out.println("Country : "+country+" and sports played are : "+sportsplayed);
	sportsobject.setCountry(country);
	sportsobject.setSportsplayed(sportsplayed);
	PostgresqlJdbcConnection mydata = new PostgresqlJdbcConnection();
	mydata.addData(country,sportsplayed);
	return Response.status(201).entity(inputdata).build();
}

private boolean isUserAuthenticated(String authString){
    
    String decodedAuth = "";
    // Header is in the format "Basic 5tyc0uiDat4"
    // We need to extract data before decoding it back to original string
    String[] authParts = authString.split("\\s+");
    String authInfo = authParts[1];
    // Decode the data back to original string
    byte[] bytes = null;
	
    bytes = Base64.getDecoder().decode(authInfo);	
	 
    decodedAuth = new String(bytes);
    System.out.println(decodedAuth);
    String[] decodedAuthsplit=decodedAuth.split(":"); 
    String username = decodedAuthsplit[0];
    String password = decodedAuthsplit[1];
    if(username.equals("admin")&&password.equals("secret")) {
    return true;
    }
    return false;
}

}
