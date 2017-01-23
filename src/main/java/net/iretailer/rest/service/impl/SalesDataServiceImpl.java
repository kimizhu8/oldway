package net.iretailer.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;

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
		MainRecords mainRecords = new MainRecords();
		mainRecords.setDevicezoneId(devicezoneId);
		mainRecords.setTime(time);
		recordsSalesMapper.insertMainRecords(mainRecords);
		Integer id = mainRecords.getId();
		RecordsSales recordsSales = new RecordsSales();
		recordsSales.setCountGoods(goods);
		recordsSales.setCountSales(sales);
		recordsSales.setCountTrades(trades);
		recordsSales.setFkRecordsId(id);
		recordsSalesMapper.insertSelective(recordsSales);
	}

	@Override
	public void deleteSalesData(Integer id) {
		recordsSalesMapper.deleteMainRecords(id);
	}

	@Override
	public void updateSalesData(Integer id, Short devicezoneId,Float sales, Integer trades, Integer goods, Date time) {
		MainRecords mainRecords = new MainRecords();
		mainRecords.setId(id);
		mainRecords.setDevicezoneId(devicezoneId);
		mainRecords.setTime(time);
		recordsSalesMapper.updateMainRecords(mainRecords);
		RecordsSales recordsSales = new RecordsSales();
		recordsSales.setCountGoods(goods);
		recordsSales.setCountSales(sales);
		recordsSales.setCountTrades(trades);
		recordsSales.setFkRecordsId(id);
		recordsSalesMapper.updateByPrimaryKey(recordsSales);
	}

	@Override
	public Integer getSalesDataCount(Integer siteId,String date) {
		return recordsSalesMapper.getSalesDataCount(siteId,date);
	}

}
