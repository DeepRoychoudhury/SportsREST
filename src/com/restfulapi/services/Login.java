package com.restfulapi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("login")
public class Login {
@GET
@Produces("application/xml")
public String requestLogin() {	
	StringBuilder sb = new StringBuilder();
	sb.append("<note>");
	sb.append("<to>Guest</to>");
	sb.append("<from>Admin</from>");
	sb.append("<body>Please Login</body>");
	sb.append("</note>");
	return sb.toString();
}
}
