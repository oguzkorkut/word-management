package com.word.jaxrs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.word.dto.LevelDto;
import com.word.dto.ReturnDTO;
import com.word.dto.WordDto;
import com.word.service.ILevelSerice;
import com.word.service.IWordSerice;

@CrossOrigin(origins = "*")
@Component
@Path("/api/word")
public class WordRestService {

	private static final Logger logger = LogManager.getLogger(WordRestService.class);

	@Autowired
	ILevelSerice levelSerice;

	@Autowired
	IWordSerice wordSerice;

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
				logger.debug("Kayitli seviye. Seviye:" + levelDto.getLevel());
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
	public Response saveWord(@Context HttpServletRequest request, @QueryParam("level") int level) {
		logger.debug("saveWord REST method begins.");
		ObjectMapper mapper = new ObjectMapper();
		ReturnDTO response = new ReturnDTO();
		try {
			String jsonData = IOUtils.toString(request.getInputStream());
			logger.debug("Request:\n" + jsonData);
			JSONObject jsonObject = new JSONObject(jsonData);
			WordDto wordDto = mapper.readValue(jsonObject.toString(), WordDto.class);

			if (wordDto == null) {
				response.setStatus(false);
				response.setMessage("Kelime objesi null");
				logger.info("Kelime objesi null");
			}

			WordDto dto = wordSerice.getWordByName(wordDto.getName());

			if (dto == null) {
				wordDto.setId(null);

				Integer id = wordSerice.save(level, wordDto);

				response.setStatus(true);
				response.setMessage("Kayit basarili.");
				logger.info("Kayit basarili. Kaydedilen kelime id:" + id + " kelime:" + wordDto.getName());
			} else {
				response.setStatus(false);
				response.setMessage("Kelime kayitlı olduğu için işlem devam ettirilemiyor.");
				logger.debug("Kayitli kelime. Kelime:" + wordDto.getName());
			}

		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Kelime kaydinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("saveWord REST method completed.");
		return Response.ok(response).build();
	}
	
	@PUT
	@Path("/update")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@Context HttpServletRequest request) {
		logger.debug("update word REST method begins.");
		ObjectMapper mapper = new ObjectMapper();
		ReturnDTO response = new ReturnDTO();
		try {
			String jsonData = IOUtils.toString(request.getInputStream());
			logger.debug("Request:\n" + jsonData);
			JSONObject jsonObject = new JSONObject(jsonData);
			WordDto wordDto = mapper.readValue(jsonObject.toString(), WordDto.class);

			if (wordDto == null) {
				response.setStatus(false);
				response.setMessage("Kelime objesi null");
				logger.info("Kelime objesi null");
			}

			WordDto dto = wordSerice.getWordByName(wordDto.getName());

			if (dto == null) {
				wordSerice.update(wordDto);

				response.setStatus(true);
				response.setMessage("Kelime güncellendi. Güncel kelime:" + wordDto.getName());
				logger.info("Kelime güncellendi. Güncel kelime id:" + wordDto.getId() + " kelime:" + wordDto.getName());
			} else {
				response.setStatus(false);
				response.setMessage("Kelime kayitlı olduğu için işlem devam ettirilemiyor.");
				logger.debug("Kayitli kelime. Kelime id:" + wordDto.getId() + " kelime:" + wordDto.getName());
			}

		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Kelime günellemesi sırasında bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("update word REST method completed.");
		return Response.ok(response).build();
	}

	@DELETE
	@Path("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@Context HttpServletRequest request, @PathParam("id") Integer id) {
		logger.debug("delete REST method begins. id:" + id);
		ReturnDTO response = new ReturnDTO();
		try {
			wordSerice.delete(id);

			response.setStatus(true);
			response.setMessage("Kayit basarili.");
			logger.info("Kelime basarili bir sekilde silindi. Silinen kelime id:" + id);
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Kelimenin silinmesi sirasinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("delete REST method completed.");
		return Response.ok(response).build();
	}

	@GET
	@Path("/getLevels")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLevels(@Context HttpServletRequest request) {
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

	@GET
	@Path("/getWordsByLevel")
	@Produces(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasAuthority('ADMIN')")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWordsByLevel(@Context HttpServletRequest request, @QueryParam("level") Integer levelId) {
		logger.debug("getWordsByLevel REST method begins. level:" + levelId);
		ReturnDTO response = new ReturnDTO();

		try {
			List<WordDto> wordDtos = wordSerice.getWordsByLevel(levelId);

			response.setStatus(true);
			response.setResult(wordDtos);
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage(e.getMessage());
		}

		logger.debug("getWordsByLevel REST method completed.");
		return Response.ok(response).build();
	}

}
