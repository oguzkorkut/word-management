package com.word.jaxrs;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.word.dto.ReturnDTO;
import com.word.service.IUserService;

@Component
@Path("/rest-api/user")
public class UserRestService {
	private static final Logger logger = LogManager.getLogger(UserRestService.class);

	@Autowired
	IUserService userService;


	@GET
	@PreAuthorize("hasAuthority('ADMIN')")
	@Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnDTO checkAuth(@PathParam("token") String id) {
		ReturnDTO response = new ReturnDTO();
		response.setMessage("Token is valid.");
		response.setStatus(true);
		return response;
	}
	
}