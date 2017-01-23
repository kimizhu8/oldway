package net.iretailer.rest.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnMapUtil {
	public static Map<String,Object> packData(Object data){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", data);
		resultMap.put("status", "0");
		resultMap.put("message", "success");
		return resultMap;
	}
	
	public static Map<String,Object> packError(String code,String message){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", "");
		resultMap.put("status", code);
		resultMap.put("message", message);
		return resultMap;
	}
}
