package com.word.jaxrs;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.word.dto.ReturnDTO;
import com.word.dto.RoleDto;
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
	
	@GET
	@Path("/role/getRoles")
	@Produces(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasAuthority('ADMIN')")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getRoles(@Context HttpServletRequest request) {
		logger.debug("getRoles REST method begins.");
		ReturnDTO response = new ReturnDTO();

		try {
			List<RoleDto> roleDtos = userService.getRoles();

			response.setStatus(true);
			response.setResult(roleDtos);
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage(e.getMessage());
		}

		logger.debug("getRoles REST method completed.");
		return Response.ok(response).build();
	}
	
	@DELETE
	@Path("/role/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRole(@Context HttpServletRequest request, @PathParam("id") Integer id) {
		logger.debug("deleteRole REST method begins. id:" + id);
		ReturnDTO response = new ReturnDTO();
		try {
			userService.deleteRoleById(id);

			response.setStatus(true);
			response.setMessage("Silme basarili.");
			logger.info("Rol basarili bir sekilde silindi. Silinen rol id:" + id);
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Rolün silinmesi sirasinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("deleteRole REST method completed.");
		return Response.ok(response).build();
	}
	
	@POST
	@Path("/role/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveRole(@Context HttpServletRequest request, RoleDto roleDto) {
		logger.debug("saveRole REST method begins.");
		ReturnDTO response = new ReturnDTO();
		try {
			if (roleDto == null) {
				response.setStatus(false);
				response.setMessage("Rol objesi null");
				logger.info("Rol objesi null");
			}
			roleDto.setId(null);

			RoleDto dto = userService.getRoleByRoleName(roleDto.getName());

			if (dto == null) {
				RoleDto tempRole = userService.saveRole(roleDto);
				response.setStatus(true);
				response.setMessage("Kayit basarili.");
				
				logger.info("Kayit basarili. Kaydedilen rol id:" + tempRole.getId() + " rol:" + tempRole.getName());
			} else {
				response.setStatus(false);
				response.setMessage("Rol kayitlı olduğu için işlem devam ettirilemiyor. Rol:" + roleDto.getName());
				logger.debug("Kayitli rol. Role id:" + roleDto.getId());
			}

		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Rol kaydinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("saveLevel REST method completed.");
		return Response.ok(response).build();
	}
}