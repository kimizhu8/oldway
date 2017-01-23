package net.iretailer.rest.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import net.iretailer.rest.util.ReturnMapUtil;

@WebFilter(filterName="commonFilter",urlPatterns="/*")
public class CommonFilter implements Filter{
	public static CacheManager<Long> cacheManager = new CacheManager<Long>();
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpServletRequest request = (HttpServletRequest) arg0;
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE"); 
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "*"); 
		response.setHeader("Access-Control-Allow-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, authorization");
		response.setContentType("application/json;charset=UTF-8");

		//登录逻辑
		//获取用户名
		String loginToken = request.getHeader("Authorization");
		
		String uri = request.getRequestURI();
		String apiName = uri.split("/")[2];
		
		if (apiName.equals("login")||apiName.equals("getRoleById")||apiName.equals("getSiteList")){
			arg2.doFilter(arg0, arg1);
			return;
		}

		Long lastOperationTime = cacheManager.getValue(loginToken);
	
		if (loginToken == null||loginToken==""||lastOperationTime==null){
			Map<String,Object> map = ReturnMapUtil.packError("1", "未登录");
			response.getWriter().write(JSONObject.toJSONString(map));
			return;
		} 
		if ((System.currentTimeMillis()-lastOperationTime)>(60 * 30 * 1000)){
			Map<String,Object> map = ReturnMapUtil.packError("1", "登录超时");
			response.getWriter().write(JSONObject.toJSONString(map));
			return;
		}
		cacheManager.updateCache(loginToken, System.currentTimeMillis());
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
