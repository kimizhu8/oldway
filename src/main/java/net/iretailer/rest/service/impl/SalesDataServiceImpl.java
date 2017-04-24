package net.iretailer.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.RecordsSalesMapper;
import net.iretailer.rest.dto.SalesDataDTO;
import net.iretailer.rest.model.MainRecords;
import net.iretailer.rest.model.RecordsSales;
import net.iretailer.rest.service.SalesDataService;

@Service
public class SalesDataServiceImpl implements SalesDataService{

	@Autowired
	private RecordsSalesMapper recordsSalesMapper;
	
	@Override
	public ArrayList<SalesDataDTO> getAllSalesData(Integer page,Integer pageSize,String date,Integer siteId) {
		return recordsSalesMapper.selectAll(page*pageSize,pageSize,date,siteId);
	}

	@Override
	public void insertSalesData(Short devicezoneId,Float sales, Integer trades, Integer goods, Date time) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devicezoneId", devicezoneId);
		map.put("time", time);
		map.put("countGoods", goods);
		map.put("countSales", sales);
		map.put("countTrades", trades);
		recordsSalesMapper.insertByMap(map);
	}

	@Override
	public void deleteSalesData(Date time,Short devicezoneId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devicezoneId", devicezoneId);
		map.put("time", time);
		recordsSalesMapper.deleteByMap(map);
	}

	@Override
	public void updateSalesData(Integer id, Short devicezoneId,Float sales, Integer trades, Integer goods, Date time) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devicezoneId", devicezoneId);
		map.put("time", time);
		map.put("countGoods", goods);
		map.put("countSales", sales);
		map.put("countTrades", trades);
		recordsSalesMapper.updateByMap(map);
	}

	@Override
	public Integer getSalesDataCount(Integer siteId,String date) {
		return recordsSalesMapper.getSalesDataCount(siteId,date);
	}

}
