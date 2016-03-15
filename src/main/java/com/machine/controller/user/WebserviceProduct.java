package com.machine.controller.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/user")
public class WebserviceProduct {

	@GET
	@Produces("application/json")
	public Response convertCtoF() {
		JSONObject json = new JSONObject();
		json.put("id", "2");
		json.put("name", "hieu doan");
		System.out.println("Go here");
		return Response.status(200).entity(json.toString()).build();
	}
	
}
