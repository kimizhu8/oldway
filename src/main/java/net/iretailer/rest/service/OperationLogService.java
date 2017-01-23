package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.OperationLog;

public interface OperationLogService {
	public void insertOperationLog(OperationLog log);
	public void insertOperationLog(String ip,String function,String targetMapper,String result,String requestInfo);
	public ArrayList<OperationLog> getLog();
}
