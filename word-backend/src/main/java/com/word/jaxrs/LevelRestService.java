package com.word.jaxrs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.word.dto.LevelDto;
import com.word.dto.ReturnDTO;
import com.word.dto.WordDto;
import com.word.service.ILevelSerice;

@CrossOrigin(origins = "*")
@Component
@Path("/api/word")
public class LevelRestService {

	private static final Logger logger = LogManager.getLogger(LevelRestService.class);

	@Autowired
	ILevelSerice levelSerice;

	@POST
	@Path("/level/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveLevel(@Context HttpServletRequest request, LevelDto levelDto) {
		logger.debug("saveLevel REST method begins.");
		ReturnDTO response = new ReturnDTO();
		try {
			if (levelDto == null) {
				response.setStatus(false);
				response.setMessage("Seviye objesi null");
				logger.info("Seviye objesi null");
			}
			levelDto.setId(null);
			
			LevelDto dto = levelSerice.getLevelByLevel(levelDto.getLevel());
			
			if (dto == null) {
				levelSerice.save(levelDto);
				response.setStatus(true);
				response.setMessage("Kayit basarili.");
				logger.info("Kayit basarili. Kaydedilen seviye:" + levelDto.getLevel());
			} else {
				response.setStatus(false);
				response.setMessage("Seviye kayitlı olduğu için işlem devam ettirilemiyor.");
				logger.debug("Kayitli seviye. Seviye:" +  levelDto.getLevel());
			}
			
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Seviye kaydinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("saveLevel REST method completed.");
		return Response.ok(response).build();
	}
	
	@POST
	@Path("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveWord(@Context HttpServletRequest request, WordDto wordDto) {
		logger.debug("saveWord REST method begins.");
		ReturnDTO response = new ReturnDTO();
		try {
			
			if (wordDto == null) {
				response.setStatus(false);
				response.setMessage("Kelime objesi null");
				logger.info("Kelime objesi null");
			}
			wordDto.setId(null);
			
			WordDto dto = levelSerice.getWordByName(wordDto.getName());
			
			if (dto == null) {
				levelSerice.save(wordDto);
				response.setStatus(true);
				response.setMessage("Kayit basarili.");
				logger.info("Kayit basarili. Kaydedilen kelime:" + wordDto.getName());
			} else {
				response.setStatus(false);
				response.setMessage("Kelime kayitlı olduğu için işlem devam ettirilemiyor.");
				logger.debug("Kayitli seviye. Kelime:" +  wordDto.getName());
			}
			
			levelSerice.save(wordDto);

			response.setStatus(true);
			response.setMessage("Kayit basarili.");
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Kelime kaydinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("saveWord REST method completed.");
		return Response.ok(response).build();
	}
	
	@GET
	@Path("/getLevels")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLevels() {
		logger.debug("getLevels REST method begins.");
		ReturnDTO response = new ReturnDTO();

		try {
			List<LevelDto> levelDtos = levelSerice.getLevels();

			response.setStatus(true);
			response.setResult(levelDtos);
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage(e.getMessage());
		}

		logger.debug("getLevels REST method completed.");
		return Response.ok(response).build();
	}

}
