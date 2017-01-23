package net.iretailer.rest.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataParserController {
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/downloadExcel")
	public Object downloadExcel(){
		ArrayList<String> map = (ArrayList<String>) request.getParameterNames();
		return null;
	}
}
