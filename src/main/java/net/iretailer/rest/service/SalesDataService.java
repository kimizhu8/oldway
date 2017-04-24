package net.iretailer.rest.service;

import java.util.ArrayList;
import java.util.Date;

import net.iretailer.rest.dto.SalesDataDTO;

public interface SalesDataService {
	public ArrayList<SalesDataDTO> getAllSalesData(Integer page,Integer pageSize,String date,Integer siteId);
	public void insertSalesData(Short devicezoneId,Float sales,Integer trades,Integer goods,Date time);
	public void deleteSalesData(Date date,Short devicezoneId);
	public void updateSalesData(Integer id,Short devicezoneId,Float sales,Integer trades,Integer goods,Date time);
	public Integer getSalesDataCount(Integer siteId,String date);
}
