package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.OperationLogMapper;
import net.iretailer.rest.model.OperationLog;
import net.iretailer.rest.service.OperationLogService;

@Service
public class OperationLogServiceImpl implements OperationLogService{

	@Autowired 
	OperationLogMapper operationLogMapper;
	
	@Override
	public void insertOperationLog(OperationLog log) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertOperationLog(String ip, String function, String targetMapper, String result, String requestInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<OperationLog> getLog() {
		return operationLogMapper.getAllLog();

	}

}
