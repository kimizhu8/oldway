package net.iretailer.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.iretailer.rest.dto.SalesDataDTO;
import net.iretailer.rest.service.RoleService;
import net.iretailer.rest.service.SalesDataService;
import net.iretailer.rest.util.ReturnMapUtil;

@RestController
public class SalesDataController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SalesDataService salesDataService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/newSalesData", method = RequestMethod.GET)
	public Map<String,Object> newSalesData() throws ParseException{
		if (!roleService.blockRole(request,50)) return ReturnMapUtil.packData("恶意登录");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(request.getParameter("time"));
		Float countSales = Float.parseFloat(request.getParameter("countSales"));
		Integer countTrades = Integer.parseInt(request.getParameter("countTrades"));
		Integer countGoods = Integer.parseInt(request.getParameter("countGoods"));
		Short devicezoneId = Short.parseShort(request.getParameter("devicezoneId"));
		salesDataService.insertSalesData(devicezoneId,countSales, countTrades, countGoods,time);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/deleteSalesData", method = RequestMethod.GET)
	public Map<String,Object> deleteSalesData() throws ParseException{
		if (!roleService.blockRole(request,50)) return ReturnMapUtil.packData("恶意登录");
		Integer id = Integer.parseInt(request.getParameter("id"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(request.getParameter("time"));
		Short devicezoneId = Short.parseShort(request.getParameter("devicezoneId"));
		salesDataService.deleteSalesData(time,devicezoneId);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateSalesData", method = RequestMethod.GET)
	public Map<String,Object> updateSalesData() throws ParseException{
		if (!roleService.blockRole(request,50)) return ReturnMapUtil.packData("恶意登录");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer id = Integer.parseInt(request.getParameter("id"));
		Date time = sdf.parse(request.getParameter("time"));
		Short devicezoneId = Short.parseShort(request.getParameter("devicezoneId"));
		Float countSales = Float.parseFloat(request.getParameter("countSales"));
		Integer countTrades = Integer.parseInt(request.getParameter("countTrades"));
		Integer countGoods = Integer.parseInt(request.getParameter("countGoods"));
		salesDataService.updateSalesData(id,devicezoneId,countSales,countTrades,countGoods,time);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/getAllSalesData", method = RequestMethod.GET)
	public Map<String,Object> getAllSalesData(){
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		Integer siteId = Integer.parseInt(request.getParameter("siteId"));
		String date = request.getParameter("date");
		ArrayList<SalesDataDTO> salesData = salesDataService.getAllSalesData(page-1,pageSize,date,siteId);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", salesData);
		resultMap.put("status", "0");
		resultMap.put("message", "success");
		resultMap.put("count", salesDataService.getSalesDataCount(siteId,date)/pageSize);
		return ReturnMapUtil.packData(resultMap);
	}

	
}
