package com.word.jaxrs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.word.dto.ReturnDTO;

@Controller
@RequestMapping("/admin-api")
public class AdminController {

	private static final Logger logger = LogManager.getLogger(AdminController.class);


	// @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CAMPAIGNUSER')")
	@RequestMapping(method = RequestMethod.GET, value = "/status")
	public @ResponseBody ReturnDTO checkStatus() {
		logger.trace("in MainController checkStatus method started.");
		ReturnDTO returnDTO = null;
		try {
			returnDTO = new ReturnDTO();
			returnDTO.setStatus(true);
			returnDTO.setMessage("");
		} catch (Exception e) {
			logger.error(e, e);
		}
		logger.trace("checkStatus method completed.");
		return returnDTO;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/checkAuth")
	public @ResponseBody boolean checkAuthentication() {
		logger.trace("in AdminController checkAuthentication method started.");
		return true;
	}

}
