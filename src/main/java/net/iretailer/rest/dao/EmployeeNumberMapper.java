package net.iretailer.rest.dao;

import java.util.List;
import java.util.Map;

public interface EmployeeNumberMapper {
	List<Map<String,Object>> selectAllEmployeeNum(Map<String,Object> map);
	void insertEmployeeNumber(Map<String,Object> map);
	void updateEmployeeNumber(Map<String,Object> map);
	void deleteEmployeeNumber(Map<String,Object> map);
}
