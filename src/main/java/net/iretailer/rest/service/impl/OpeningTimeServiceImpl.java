package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.OpeningTimeMapper;
import net.iretailer.rest.model.OpeningTime;
import net.iretailer.rest.service.OpeningTimeService;
@Service
public class OpeningTimeServiceImpl implements OpeningTimeService{

	@Autowired
	OpeningTimeMapper openingTimeMapper;
	@Override
	public void insertOpeningTime(OpeningTime openingTime) {
		openingTimeMapper.insert(openingTime);
	}

	@Override
	public void deleteOpeningTime(Short id) {
		openingTimeMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public ArrayList<OpeningTime> getOpeningTime(Integer siteId) {
		return openingTimeMapper.selectAll(siteId);	
	}

	@Override
	public void updateOpeningTime(OpeningTime openingTime) {
		openingTimeMapper.updateByPrimaryKeySelective(openingTime);
	}

}
