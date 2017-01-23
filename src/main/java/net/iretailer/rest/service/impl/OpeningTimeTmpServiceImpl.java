package net.iretailer.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.OpeningTimeTmpMapper;
import net.iretailer.rest.model.OpeningTimeTmp;
import net.iretailer.rest.model.OpeningTimeTmpKey;
import net.iretailer.rest.service.OpeningTimeTmpService;

@Service
public class OpeningTimeTmpServiceImpl implements OpeningTimeTmpService {

	@Autowired
	private OpeningTimeTmpMapper openingTimeTmpMapper;
	
	@Override
	public OpeningTimeTmp getOpeningTimeByDate(OpeningTimeTmpKey key) {
		return openingTimeTmpMapper.selectByPrimaryKey(key);
	}

}
