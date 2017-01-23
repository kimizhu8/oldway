package net.iretailer.rest.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.iretailer.rest.dao.HomePageMapper;
import net.iretailer.rest.model.HomePage;
import net.iretailer.rest.model.HomePageByLocation;
import net.iretailer.rest.model.HomePageByTag;
import net.iretailer.rest.model.HomePageByZonetype;
import net.iretailer.rest.service.HomePageService;
import net.iretailer.rest.service.RoleService;
import net.iretailer.rest.service.SiteService;
import net.iretailer.rest.util.ReturnMapUtil;

@RestController
public class ReportDataController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HomePageService homePageService;
	
	@Autowired
	private HomePageMapper homePageMapper;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private RoleService roleService;
	
	Map<Integer,String> scaleMap = new HashMap<Integer,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(1,"m");
			put(2,"w");
			put(3,"d");
			put(4,"dh");
			put(5,"30");
			put(6,"15");
			put(7,"10");
			put(8,"5");
		}
	};
	Map<Integer,Object> scaleTimeLine = new HashMap<Integer,Object>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(1,new SimpleDateFormat("yyyy-MM"));
			put(2,new SimpleDateFormat("yyyy-MM-dd"));
			put(3,new SimpleDateFormat("MM-dd"));
			put(4,new SimpleDateFormat("HH:mm"));
			put(5,new SimpleDateFormat("HH:mm"));
			put(6,new SimpleDateFormat("HH:mm"));
			put(7,new SimpleDateFormat("HH:mm"));
			put(8,new SimpleDateFormat("HH:mm"));
		}
	};
    @SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping("/chartData")
    public Map<String,Object> chartData(@RequestParam int cateId,@RequestParam int filterId,@RequestParam String site_name,
    		@RequestParam long st,@RequestParam long et,@RequestParam int scale) throws ParseException{
    	


    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	SimpleDateFormat sdf1 = (SimpleDateFormat) scaleTimeLine.get(scale);
    	if (cateId == 101 || cateId == 100){
    		switch (filterId) {
    		case 0:
    			Map<String,Object> result = new HashMap<String,Object>();
    			Long startTime = 0L;
    			Long endTime = 0L;
    			ArrayList<String> dataType = new ArrayList<String>();
    			dataType.add("进入流");
    			dataType.add("出入流");
    			ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
    			data.add(new ArrayList<Integer>());
    			data.add(new ArrayList<Integer>());
    			result.put("startTime", startTime);
    			result.put("endTime", endTime);
    			result.put("data", data);
    			return ReturnMapUtil.packData(result);
    		}
    	}
    	
//    	if (cateId ==102) {
//			ArrayList<HomePage> list = homePageService.getTotalCompareData(false, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)));
//			Map<String,Object> result = new HashMap<String,Object>();
//    		ArrayList<String> categories = new ArrayList<String>();
//    		categories.add("进入流");
//    		categories.add("出入流");
//			result.put("categories", categories);
//			ArrayList<ArrayList<Map<String,Object>>> dataList = new ArrayList<ArrayList<Map<String,Object>>>();
//			Map<String,Object> dataIn = new HashMap<String,Object>();
//			
//			dataIn.put("name","汇总");
//			ArrayList<Integer> tempList = new ArrayList<Integer>();
//			tempList.add(list.get(0).getEnter());
//			tempList.add(list.get(0).getExit());
//			
//			dataIn.put("data", tempList.clone());
//			ArrayList<Map<String,Object>> dataInList = new ArrayList<Map<String,Object>>();
//			dataInList.add(dataIn);
//			dataList.add(dataInList);
//			result.put("data", dataList);
//			return ReturnMapUtil.packData(result);
//    	}
//    	
//    	if (cateId ==103) {
//    		ArrayList<HomePageByZonetype> list = homePageService.getTotalSitezoneData(false, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), filterId-2);
//		//	ArrayList<HomePage> list = homePageService.getTotalCompareData(false, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)));
//			Map<String,Object> result = new HashMap<String,Object>();
//    		ArrayList<String> categories = new ArrayList<String>();
//    		for (int i=0;i<list.size();i++){
//    			categories.add(list.get(i).getZoneName());
//    		}
//			result.put("categories", categories);
//
//			ArrayList<ArrayList<Map<String,Object>>> dataList = new ArrayList<ArrayList<Map<String,Object>>>();
//
//			//单个数据
//			ArrayList<Map<String,Object>> dataInList = new ArrayList<Map<String,Object>>();
//			for (int i=0;i<list.size();i++){
//				Map<String,Object> dataIn = new HashMap<String,Object>();
//				dataIn.put("name",list.get(i).getZoneName());
//				ArrayList<Integer> tempDataList = new ArrayList<Integer>();
//				tempDataList.add(list.get(i).getCountIn());
//				dataIn.put("data", tempDataList.clone());
//				dataInList.add(dataIn);
//			}
//			//单个数据
//			dataList.add(dataInList);
//			result.put("data", dataList);
//			return ReturnMapUtil.packData(result);
//    	}
    	if (cateId ==11 || cateId ==12 || cateId == 14 || cateId == 22){
    		if (cateId==11||cateId==12||cateId==14){
        		if (!roleService.blockRole(request,Integer.parseInt(site_name),cateId)) return ReturnMapUtil.packData("恶意登录");
    		}
    		if (cateId==22){
        		if (!roleService.blockRole(request,Integer.parseInt(site_name),22)) return ReturnMapUtil.packData("恶意登录");
    		}
    		ArrayList<String> categories = new ArrayList<String>(); 
    		Map<String,String> categoryMap = new HashMap<String,String>();
    		if (filterId==0 || filterId == 1) {
    			Byte siteType = siteService.testSelect(Short.parseShort(site_name)).getType();
    			ArrayList<HomePage> list = new ArrayList<HomePage>();
    			if (filterId ==1){
        			list = homePageService.getTotalData(false, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), scaleMap.get(scale),"f");
    			} else {
        			list = homePageService.getTotalData(true, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), scaleMap.get(scale),"f");
    			}
    			int j=-1;
    			//添加category
    			categoryMap.clear();
    			for (int i=0;i<list.size();i++){
    				categoryMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), "1");
    			}
    			categories = sort(categoryMap);
    			Map<String,Object> resultMap = new HashMap<String,Object>();
    			resultMap.put("categories", categories);
    			//添加数据
    			Map<String,Map<String,Object>> dataMap = new HashMap<String,Map<String,Object>>();
    			for (int i=0;i<list.size();i++){
    				if (dataMap.containsKey(list.get(i).getDisplayName())){
    					Map<String,Object> sonDataMap = dataMap.get(list.get(i).getDisplayName());
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				} else {
    					Map<String,Object> sonDataMap = new HashMap<String,Object>();
    					dataMap.put(list.get(i).getDisplayName(), sonDataMap);
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				}
    			}
    			ArrayList<ArrayList<Map<String,Object>>> dataList = new ArrayList<ArrayList<Map<String,Object>>>();
    			
    			ArrayList<Map<String,Object>> dataInList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataOutList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataPassbyList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataStayTimeList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataRateList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerManList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsPerTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsPerManList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerGoodsList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataJikeliList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataPingxiaoList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataYuangongbiList = new ArrayList<Map<String,Object>>();
    			
    			
    	    	for (Map.Entry<String, Map<String,Object>> entry:dataMap.entrySet()){
    	    		if (entry.getKey()==null) continue;
        			Map<String,Object> dataIn = new HashMap<String,Object>();
        			Map<String,Object> dataOut = new HashMap<String,Object>();
        			Map<String,Object> dataPassby = new HashMap<String,Object>();
        			Map<String,Object> dataStayTime = new HashMap<String,Object>();
        			Map<String,Object> dataSales = new HashMap<String,Object>();
        			Map<String,Object> dataTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoods = new HashMap<String,Object>();
        			Map<String,Object> dataRate = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerMan = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoodsPerTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoodsPerMan = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerGoods = new HashMap<String,Object>();
        			Map<String,Object> dataJikeli = new HashMap<String,Object>();
        			Map<String,Object> dataPingxiao = new HashMap<String,Object>();
        			Map<String,Object> dataYuangongbi = new HashMap<String,Object>();
        			
    	    		ArrayList<Integer> enterList = new ArrayList<Integer>();
    	    		ArrayList<Integer> exitList = new ArrayList<Integer>();
    	    		ArrayList<Double> passbyList = new ArrayList<Double>();
    	    		ArrayList<Double> stayTimeList = new ArrayList<Double>();
    	    		ArrayList<Double> salesList = new ArrayList<Double>();
    	    		ArrayList<Integer> tradesList = new ArrayList<Integer>();
    	    		ArrayList<Integer> goodsList = new ArrayList<Integer>();
    	    		ArrayList<Double> rateList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerManList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerTradesList = new ArrayList<Double>();
    	    		ArrayList<Double> goodsPerTradesList = new ArrayList<Double>();
    	    		ArrayList<Double> goodsPerManList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerGoodsList = new ArrayList<Double>();
    	    		ArrayList<Double> jikeliList = new ArrayList<Double>();
    	    		ArrayList<Double> pingxiaoList = new ArrayList<Double>();
    	    		ArrayList<Double> yuangongbiList = new ArrayList<Double>();
    	    		
    	    		Map<String,Object> sonDataMap = entry.getValue();
    	    		for (int i=0;i<categories.size();i++){
    	    			if (sonDataMap.containsKey(categories.get(i))){
        	    			HomePage value = (HomePage)sonDataMap.get(categories.get(i));
    	    				//进入流
        	    			if (value.getEnter()==null){
        	    				enterList.add(0);
        	    			} else {
        	    				enterList.add(value.getEnter());
        	    			}
    	    				//出入流
        	    			if (value.getExit()==null){
        	    				exitList.add(0);
        	    			} else {
        	    				exitList.add(value.getExit());
        	    			}
    	    				//入店率
            				if (value.getEnter()==null || value.getPassby()==null || value.getPassby()==0){
            					passbyList.add(0.00);
            				} else {
            					BigDecimal bg = new BigDecimal(((double)value.getEnter()*100)/value.getPassby()).setScale(2,RoundingMode.UP);
            					passbyList.add(bg.doubleValue());
            				}
            				//停留时间
        	    			if (value.getStayTime()==null){
        	    				stayTimeList.add(0.00);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getStayTime()/60)).setScale(2,RoundingMode.UP);
            					stayTimeList.add(Math.abs(bg.doubleValue()));
        	    			}
        	    			//销售额
        	    			if (value.getSales()==null){
        	    				salesList.add(0.00);
        	    			} else {
            					BigDecimal bg = new BigDecimal(value.getSales()).setScale(2,RoundingMode.UP);
        	    				salesList.add(bg.doubleValue());
        	    			}
        	    			//交易数
        	    			if (value.getTrades()==null){
        	    				tradesList.add(0);
        	    			} else {
        	    				tradesList.add(value.getTrades());
        	    			}
        	    			//交易件数
        	    			if (value.getGoods()==null){
        	    				goodsList.add(0);
        	    			} else {
        	    				goodsList.add(value.getGoods());
        	    			}
        	    			//转化率
        	    			if (value.getTrades()==null || value.getEnter()==null || value.getEnter()==0){
        	    				rateList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getTrades()*100)/value.getEnter()).setScale(2,RoundingMode.UP);
        	    				rateList.add(bg.doubleValue());
        	    			}
        	    			//客单价
        	    			if (value.getSales()==null || value.getEnter()==null || value.getEnter()==0){
        	    				salesPerManList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getEnter()).setScale(2,RoundingMode.UP);
        	    				salesPerManList.add(bg.doubleValue());
        	    			}
        	    			//票单价
        	    			if (value.getSales()==null || value.getTrades()==null || value.getTrades()==0){
        	    				salesPerTradesList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getTrades()).setScale(2,RoundingMode.UP);
        	    				salesPerTradesList.add(bg.doubleValue());
        	    			}
        	    			//票单量
        	    			if (value.getGoods()==null || value.getTrades()==null || value.getTrades()==0){
        	    				goodsPerTradesList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getGoods())/value.getTrades()).setScale(2,RoundingMode.UP);
        	    				goodsPerTradesList.add(bg.doubleValue());
        	    			}
        	    			//客单量
        	    			if (value.getGoods()==null || value.getEnter()==null || value.getEnter()==0){
        	    				goodsPerManList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getGoods())/value.getEnter()).setScale(2,RoundingMode.UP);
        	    				goodsPerManList.add(bg.doubleValue());
        	    			}
        	    			//件单价
        	    			if (value.getSales()==null || value.getGoods()==null || value.getGoods()==0){
        	    				salesPerGoodsList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getGoods()).setScale(2,RoundingMode.UP);
        	    				salesPerGoodsList.add(bg.doubleValue());
        	    			}
        	    			//集客力
        	    			if (value.getEnter()==null || value.getAcreage()==null || value.getAcreage()==0){
        	    				jikeliList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getEnter())/value.getAcreage()).setScale(2,RoundingMode.UP);
        	    				jikeliList.add(bg.doubleValue());
        	    			}
        	    			//平效
        	    			if (value.getSales()==null || value.getAcreage()==null || value.getAcreage()==0){
        	    				pingxiaoList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getAcreage()).setScale(2,RoundingMode.UP);
        	    				pingxiaoList.add(bg.doubleValue());
        	    			}
        	    			//顾客员工比
        	    			if (value.getEnter()==null || value.getEmployeeNumber()==null || value.getEmployeeNumber()==0){
        	    				yuangongbiList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getEnter())/value.getEmployeeNumber()).setScale(2,RoundingMode.UP);
        	    				yuangongbiList.add(bg.doubleValue());
        	    			}
    	    			} else {
    	    				enterList.add(0);
    	    				exitList.add(0);
    	    				passbyList.add(0.00);
    	    				stayTimeList.add(0.00);
    	    				salesList.add(0.00);
    	    				tradesList.add(0);
    	    				goodsList.add(0);
    	    				rateList.add(0.00);
    	    				salesPerManList.add(0.00);
    	    				salesPerTradesList.add(0.00);
    	    				goodsPerTradesList.add(0.00);
    	    				goodsPerManList.add(0.00);
    	    				salesPerGoodsList.add(0.00);
    	    				jikeliList.add(0.00);
    	    				pingxiaoList.add(0.00);
    	    				yuangongbiList.add(0.00);
    	    			}
    	    		}
    	    		for (Map.Entry<String, Object> entry1:sonDataMap.entrySet()){
    	    			HomePage value = (HomePage)entry1.getValue();
    	    			dataIn.put("name", value.getDisplayName());
    	    			dataOut.put("name", value.getDisplayName());
    	    			dataPassby.put("name", value.getDisplayName());
    	    			dataStayTime.put("name",value.getDisplayName());
    	    			dataSales.put("name", value.getDisplayName());
    	    			dataTrades.put("name", value.getDisplayName());
    	    			dataGoods.put("name", value.getDisplayName());
    	    			dataRate.put("name", value.getDisplayName());
    	    			dataSalesPerMan.put("name", value.getDisplayName());
    	    			dataSalesPerTrades.put("name", value.getDisplayName());
    	    			dataGoodsPerTrades.put("name", value.getDisplayName());
    	    			dataGoodsPerMan.put("name", value.getDisplayName());
    	    			dataSalesPerGoods.put("name", value.getDisplayName());
    	    			dataJikeli.put("name", value.getDisplayName());
    	    			dataPingxiao.put("name", value.getDisplayName());
    	    			dataYuangongbi.put("name", value.getDisplayName());
    	    		}
    	    		dataIn.put("data", enterList);
    	    		dataOut.put("data", exitList);
    	    		dataPassby.put("data", passbyList);
    	    		dataStayTime.put("data", stayTimeList);
    	    		dataSales.put("data", salesList);
    	    		dataTrades.put("data", tradesList);
    	    		dataGoods.put("data", goodsList);
    	    		dataRate.put("data", rateList);
    	    		dataSalesPerMan.put("data", salesPerManList);
    	    		dataSalesPerTrades.put("data", salesPerTradesList);
    	    		dataGoodsPerTrades.put("data", goodsPerTradesList);
    	    		dataGoodsPerMan.put("data", goodsPerManList);
    	    		dataSalesPerGoods.put("data", salesPerGoodsList);
    	    		dataJikeli.put("data", jikeliList);
    	    		dataPingxiao.put("data", pingxiaoList);
    	    		dataYuangongbi.put("data", yuangongbiList);
    	    		
    	    		dataInList.add(dataIn);
    	    		dataOutList.add(dataOut);
    	    		dataPassbyList.add(dataPassby);
    	    		dataStayTimeList.add(dataStayTime);
    	    		dataSalesList.add(dataSales);
    	    		dataTradesList.add(dataTrades);
    	    		dataGoodsList.add(dataGoods);
    	    		dataRateList.add(dataRate);
    	    		dataSalesPerManList.add(dataSalesPerMan);
    	    		dataSalesPerTradesList.add(dataSalesPerTrades);
    	    		dataGoodsPerTradesList.add(dataGoodsPerTrades);
    	    		dataGoodsPerManList.add(dataGoodsPerMan);
    	    		dataSalesPerGoodsList.add(dataSalesPerGoods);
    	    		dataJikeliList.add(dataJikeli);
    	    		dataPingxiaoList.add(dataPingxiao);
    	    		dataYuangongbiList.add(dataYuangongbi); 	
    	    	}

    			if (cateId==11){
        			dataList.add(dataInList);
        			dataList.add(dataOutList);
    			} else if (cateId==12) {
    				dataList.add(dataPassbyList);
    			} else if (cateId==14) {
    				dataList.add(dataStayTimeList);
    			} else if (cateId==22) {
    				dataList.add(dataSalesList);
    				dataList.add(dataTradesList);
    				dataList.add(dataGoodsList);
    				dataList.add(dataRateList);
    				dataList.add(dataSalesPerManList);
    				dataList.add(dataSalesPerTradesList);
    				dataList.add(dataGoodsPerTradesList);
    				dataList.add(dataGoodsPerManList);
    				dataList.add(dataSalesPerGoodsList);
    				dataList.add(dataJikeliList);
    				dataList.add(dataPingxiaoList);
    				dataList.add(dataYuangongbiList);
    			}
    			resultMap.put("data", dataList);

    			//汇总数据
    			
        		String names = request.getParameter("total");
        		String[] name = names.split(",");

        		Map<String,Object> result = new HashMap<String,Object>();
        		HashMap<String,Object> map = new HashMap<String,Object>();
        		map.put("type",scaleMap.get(scale));
        		map.put("starttime", sdf.format(new Date(st)));
        		map.put("endtime", sdf.format(new Date(et)));
        		map.put("siteid", Integer.parseInt(site_name));
        		map.put("names", name);
    			if (filterId == 1){
    				map.put("findSite", 1);
    				name = new String[1];
    				name[0] = site_name;
            		map.put("names", name);
    			}        		
        		ArrayList<Map<String,Object>> data1sourcelist = homePageMapper.getCollectData(map);
        		HashMap<String,Object> noDataMap = new HashMap<String,Object>();
        		noDataMap.put("startTime", sdf.format(new Date(st)));
        		noDataMap.put("endTime",sdf.format(new Date(et)));
        		noDataMap.put("siteId",Integer.parseInt(site_name));
        		noDataMap.put("total", "t");
    			noDataMap.put("findSite", 1);
        		if (filterId == 1){
        			noDataMap.put("findSite", 0);
        		}
        		noDataMap.put("names", name);
        		ArrayList<Map<String,Object>> data2sourceList = homePageMapper.getCollectDataNoTime(noDataMap);
        		noDataMap.put("total", "f");
        		ArrayList<Map<String,Object>> data3sourceList = homePageMapper.getCollectDataNoTime(noDataMap);
        		Map<String,Map<String,Object>> data1sourceMap = new HashMap<String,Map<String,Object>>();
        		for (int i=0;i<data1sourcelist.size();i++){
        			Map<String, Object> value =data1sourcelist.get(i);
        			java.sql.Timestamp time = (java.sql.Timestamp)value.get("f_time");
        			Date date = new Date(time.getTime());
        			data1sourceMap.put(sdf1.format(date),value);
        		}
        		
        		ArrayList<Map<String,Object>> data1List = new ArrayList<Map<String,Object>>();
        		ArrayList<Map<String,Object>> data2List = new ArrayList<Map<String,Object>>();
        		ArrayList<Map<String,Object>> data3List = new ArrayList<Map<String,Object>>();
    			Map<String,Object> map1 = new HashMap<String,Object>();
    			Map<String,Object> map2 = new HashMap<String,Object>();
    			Map<String,Object> map3 = new HashMap<String,Object>();
    			Map<String,Object> map4 = new HashMap<String,Object>();
    			Map<String,Object> map5 = new HashMap<String,Object>();
    			Map<String,Object> map6 = new HashMap<String,Object>();
    			Map<String,Object> map7 = new HashMap<String,Object>();
    			Map<String,Object> map8 = new HashMap<String,Object>();
    			Map<String,Object> map9 = new HashMap<String,Object>();
    			Map<String,Object> map10 = new HashMap<String,Object>();
    			Map<String,Object> map11 = new HashMap<String,Object>();
    			Map<String,Object> map12 = new HashMap<String,Object>();
    			Map<String,Object> map13 = new HashMap<String,Object>();
    			Map<String,Object> map14 = new HashMap<String,Object>();
    			Map<String,Object> map15 = new HashMap<String,Object>();
    			Map<String,Object> map16 = new HashMap<String,Object>();
    			
    			map1.put("name", "进入流");
    			map2.put("name", "出入流");
    			map3.put("name", "入店率");
    			map4.put("name", "停留时间");
    			map5.put("name", "销售额");
    			map6.put("name", "交易数");
    			map7.put("name", "交易件数");
    			map8.put("name", "转化率");
    			map9.put("name", "客单价");
    			map10.put("name", "票单价");
    			map11.put("name", "票单量");
    			map12.put("name", "客单量");
    			map13.put("name", "件单价");
    			map14.put("name", "集客力");
    			map15.put("name", "平效");
    			map16.put("name","顾客员工比");
    			
    			ArrayList<Object> list1 = new ArrayList<Object>();
    			ArrayList<Object> list2 = new ArrayList<Object>();
    			ArrayList<Object> list3 = new ArrayList<Object>();
    			ArrayList<Object> list4 = new ArrayList<Object>();
    			ArrayList<Object> list5 = new ArrayList<Object>();
    			ArrayList<Object> list6 = new ArrayList<Object>();
    			ArrayList<Object> list7 = new ArrayList<Object>();
    			ArrayList<Object> list8 = new ArrayList<Object>();
    			ArrayList<Object> list9 = new ArrayList<Object>();
    			ArrayList<Object> list10 = new ArrayList<Object>();
    			ArrayList<Object> list11 = new ArrayList<Object>();
    			ArrayList<Object> list12 = new ArrayList<Object>();
    			ArrayList<Object> list13 = new ArrayList<Object>();
    			ArrayList<Object> list14 = new ArrayList<Object>();
    			ArrayList<Object> list15= new ArrayList<Object>();
    			ArrayList<Object> list16 = new ArrayList<Object>();

    			map1.put("data", list1);
    			map2.put("data", list2);
    			map3.put("data", list3);
    			map4.put("data", list4);
    			map5.put("data", list5);
    			map6.put("data", list6);
    			map7.put("data", list7);
    			map8.put("data", list8);
    			map9.put("data", list9);
    			map10.put("data", list10);
    			map11.put("data", list11);
    			map12.put("data", list12);
    			map13.put("data", list13);
    			map14.put("data", list14);
    			map15.put("data", list15);
    			map16.put("data", list16);
    			
    			Integer totalEnter = 0;
    			Integer totalExit = 0;
    			Integer totalPassby = 0;
    			Double totalStayTime = 0.00;
    			Double totalSales = 0.00;
    			Integer totalTrades = 0;
    			Integer totalGoods = 0;
    			Integer totalAcreage = 0;
    			Integer totalNumber = 0;
    			if (data2sourceList.get(0).get("enter")!=null){
        			totalEnter = Integer.parseInt(data2sourceList.get(0).get("enter").toString());
    			}
    			if (data2sourceList.get(0).get("exit")!=null){
    				totalExit = Integer.parseInt(data2sourceList.get(0).get("exit").toString());
    			}
    			if (data2sourceList.get(0).get("passby")!=null){
    				totalPassby = Integer.parseInt(data2sourceList.get(0).get("passby").toString());
    			}
    			if (data2sourceList.get(0).get("stay_time")!=null){
    				totalStayTime = Double.parseDouble(data2sourceList.get(0).get("stay_time").toString());
    			}
    			if (data2sourceList.get(0).get("sales")!=null){
    			totalSales = Double.parseDouble(data2sourceList.get(0).get("sales").toString());
    			}
    			if (data2sourceList.get(0).get("trades")!=null){
    				totalTrades = Integer.parseInt(data2sourceList.get(0).get("trades").toString());
    			}
    			if (data2sourceList.get(0).get("goods")!=null){
    				totalGoods = Integer.parseInt(data2sourceList.get(0).get("goods").toString());
    			}
    			if (data2sourceList.get(0).get("acreage")!=null){
    				totalAcreage = Integer.parseInt(data2sourceList.get(0).get("acreage").toString());
    			}
    			if (data2sourceList.get(0).get("employee_number")!=null){
    				totalNumber = Integer.parseInt(data2sourceList.get(0).get("employee_number").toString());
    			}
    			ArrayList<String> nameList = new ArrayList<String>();
    			if (cateId == 11){
        			data1List.add(map1);
        			data1List.add(map2);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData1.put("name", "进入流");
        			mapNoData1.put("data", new Integer[]{totalEnter});
        			mapNoData2.put("name", "出入流");
        			mapNoData2.put("data", new Integer[]{totalExit});
        			
        			Map<String,Object> mapNoData3 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData4 = new HashMap<String,Object>();
        			mapNoData3.put("name", "进入流");
        			mapNoData4.put("name", "出入流");
        			ArrayList<Integer> listNoData1 = new ArrayList<Integer>();
        			ArrayList<Integer> listNoData2 = new ArrayList<Integer>();
        			mapNoData3.put("data", listNoData1);
        			mapNoData4.put("data", listNoData2);
        			mapNoData3.put("categories", nameList);
        			mapNoData4.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				listNoData1.add(Integer.parseInt(data3sourceList.get(i).get("enter").toString()));
        				listNoData2.add(Integer.parseInt(data3sourceList.get(i).get("exit").toString()));
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
        			data2List.add(mapNoData1);
        			data2List.add(mapNoData2);
        			data3List.add(mapNoData3);
        			data3List.add(mapNoData4);
    			} else if (cateId == 12){
    				data1List.add(map3);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			mapNoData1.put("name", "入店率");
        			if (totalPassby!=0){
        				mapNoData1.put("data",new Double[]{new BigDecimal((double) totalEnter*100/totalPassby).setScale(2,RoundingMode.UP).doubleValue()});
        			}
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData2.put("name", "入店率");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			mapNoData2.put("data", listNoData1);
        			mapNoData2.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				if (Integer.parseInt(data3sourceList.get(i).get("passby").toString())!=0){
        					listNoData1.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())*100/Integer.parseInt(data3sourceList.get(i).get("passby").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
    				data2List.add(mapNoData1);
    				data3List.add(mapNoData2);
    			} else if (cateId == 14){
    				data1List.add(map4);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			mapNoData1.put("name", "停留时间");
        			mapNoData1.put("data", new Double[]{Math.abs(new BigDecimal(totalStayTime/60).setScale(2,RoundingMode.UP).doubleValue())});
        			
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData2.put("name", "停留时间");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			mapNoData2.put("data", listNoData1);
        			mapNoData2.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				if (Double.parseDouble(data3sourceList.get(i).get("stay_time").toString())!=0){
        					listNoData1.add(Math.abs(new BigDecimal(Double.parseDouble(data3sourceList.get(i).get("stay_time").toString())/60).setScale(2,RoundingMode.UP).doubleValue()));
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
    				data2List.add(mapNoData1);
    				data3List.add(mapNoData2);
    			} else if (cateId == 22){
    				data1List.add(map5);
    				data1List.add(map6);
    				data1List.add(map7);
    				data1List.add(map8);
    				data1List.add(map9);
    				data1List.add(map10);
    				data1List.add(map11);
    				data1List.add(map12);
    				data1List.add(map13);
    				data1List.add(map14);
    				data1List.add(map15);
    				data1List.add(map16);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData3 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData4 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData5 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData6 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData7 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData8 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData9 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData10 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData11 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData12 = new HashMap<String,Object>();
        			mapNoData1.put("name", "销售额");
        			mapNoData2.put("name", "交易数");
        			mapNoData3.put("name", "交易件数");
        			mapNoData4.put("name", "转化率");
        			mapNoData5.put("name", "客单价");
        			mapNoData6.put("name", "票单价");
        			mapNoData7.put("name", "票单量");
        			mapNoData8.put("name", "客单量");
        			mapNoData9.put("name", "件单价");
        			mapNoData10.put("name", "集客力");
        			mapNoData11.put("name", "平效");
        			mapNoData12.put("name", "顾客员工比");
        			mapNoData1.put("data", new Double[]{new BigDecimal(totalSales).setScale(2,RoundingMode.UP).doubleValue()});
        			mapNoData2.put("data", new Integer[]{totalTrades});
        			mapNoData3.put("data", new Integer[]{totalGoods});
        			if (totalEnter!=0){
        				mapNoData4.put("data", new Double[]{new BigDecimal((double) totalTrades*100/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData4.put("data",0.00);
        			}
        			if (totalEnter!=0){
        				mapNoData5.put("data", new Double[]{new BigDecimal((double) totalSales/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData5.put("data",0.00);
        			}
        			if (totalTrades!=0){
        				mapNoData6.put("data", new Double[]{new BigDecimal((double) totalSales/totalTrades).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData6.put("data",0.00);
        			}
        			if (totalTrades!=0){
        				mapNoData7.put("data", new Double[]{new BigDecimal((double) totalGoods/totalTrades).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData7.put("data",0.00);
        			}
        			if (totalEnter!=0){
        				mapNoData8.put("data", new Double[]{new BigDecimal((double) totalGoods/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData8.put("data",0.00);
        			}
        			if (totalGoods!=0){
        				mapNoData9.put("data", new Double[]{new BigDecimal((double) totalSales/totalGoods).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData9.put("data",0.00);
        			}
        			if (totalAcreage!=0){
        				mapNoData10.put("data", new Double[]{new BigDecimal((double) totalEnter/totalAcreage).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData10.put("data",0.00);
        			}
        			if (totalAcreage!=0){
        				mapNoData11.put("data", new Double[]{new BigDecimal((double) totalSales/totalAcreage).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData11.put("data",0.00);
        			}
        			if (totalNumber!=0){
        				mapNoData12.put("data", new Double[]{new BigDecimal((double) totalEnter/totalNumber).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData12.put("data",0.00);
        			}
        			
        			Map<String,Object> mapNoData13 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData14 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData15 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData16 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData17 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData18 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData19 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData20 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData21 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData22 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData23 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData24 = new HashMap<String,Object>();
        			
        			mapNoData13.put("name", "销售额");
        			mapNoData14.put("name", "交易数");
        			mapNoData15.put("name", "交易件数");
        			mapNoData16.put("name", "转化率");
        			mapNoData17.put("name", "客单价");
        			mapNoData18.put("name", "票单价");
        			mapNoData19.put("name", "票单量");
        			mapNoData20.put("name", "客单量");
        			mapNoData21.put("name", "件单价");
        			mapNoData22.put("name", "集客力");
        			mapNoData23.put("name", "平效");
        			mapNoData24.put("name", "顾客员工比");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			ArrayList<Integer> listNoData2 = new ArrayList<Integer>();
        			ArrayList<Integer> listNoData3 = new ArrayList<Integer>();
        			ArrayList<Double> listNoData4 = new ArrayList<Double>();
        			ArrayList<Double> listNoData5 = new ArrayList<Double>();
        			ArrayList<Double> listNoData6 = new ArrayList<Double>();
        			ArrayList<Double> listNoData7 = new ArrayList<Double>();
        			ArrayList<Double> listNoData8 = new ArrayList<Double>();
        			ArrayList<Double> listNoData9 = new ArrayList<Double>();
        			ArrayList<Double> listNoData10 = new ArrayList<Double>();
        			ArrayList<Double> listNoData11 = new ArrayList<Double>();
        			ArrayList<Double> listNoData12 = new ArrayList<Double>();
        			       			
        			mapNoData13.put("data", listNoData1);
        			mapNoData14.put("data", listNoData2);
        			mapNoData15.put("data", listNoData3);
        			mapNoData16.put("data", listNoData4);
        			mapNoData17.put("data", listNoData5);
        			mapNoData18.put("data", listNoData6);
        			mapNoData19.put("data", listNoData7);
        			mapNoData20.put("data", listNoData8);
        			mapNoData21.put("data", listNoData9);
        			mapNoData22.put("data", listNoData10);
        			mapNoData23.put("data", listNoData11);
        			mapNoData24.put("data", listNoData12);
        			
        			mapNoData13.put("categories", nameList);
        			mapNoData14.put("categories", nameList);
        			mapNoData15.put("categories", nameList);
        			mapNoData16.put("categories", nameList);
        			mapNoData17.put("categories", nameList);
        			mapNoData18.put("categories", nameList);
        			mapNoData19.put("categories", nameList);
        			mapNoData20.put("categories", nameList);
        			mapNoData21.put("categories", nameList);
        			mapNoData22.put("categories", nameList);
        			mapNoData23.put("categories", nameList);
        			mapNoData24.put("categories", nameList);
    
        			for (int i=0;i<data3sourceList.size();i++){
        				listNoData1.add(new BigDecimal(Double.parseDouble(data3sourceList.get(i).get("sales").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				listNoData2.add(Integer.parseInt(data3sourceList.get(i).get("trades").toString()));
        				listNoData3.add(Integer.parseInt(data3sourceList.get(i).get("goods").toString()));
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
        					listNoData4.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("trades").toString())*100/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
            				listNoData5.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("trades").toString())!=0){
        					listNoData6.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("trades").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("trades").toString())!=0){
        					listNoData7.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("goods").toString())/Integer.parseInt(data3sourceList.get(i).get("trades").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
        					listNoData8.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("goods").toString())/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("goods").toString())!=0){
        					listNoData9.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("goods").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("acreage").toString())!=0){
            				listNoData10.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())/Integer.parseInt(data3sourceList.get(i).get("acreage").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("acreage").toString())!=0){
            				listNoData11.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("acreage").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("employee_number").toString())!=0){
            				listNoData12.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())/Integer.parseInt(data3sourceList.get(i).get("employee_number").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
        			
        			
    				data2List.add(mapNoData1);
    				data2List.add(mapNoData2);
    				data2List.add(mapNoData3);
    				data2List.add(mapNoData4);
    				data2List.add(mapNoData5);
    				data2List.add(mapNoData6);
    				data2List.add(mapNoData7);
    				data2List.add(mapNoData8);
    				data2List.add(mapNoData9);
    				data2List.add(mapNoData10);
    				data2List.add(mapNoData11);
    				data2List.add(mapNoData12);
    				
    				data3List.add(mapNoData13);
    				data3List.add(mapNoData14);
    				data3List.add(mapNoData15);
    				data3List.add(mapNoData16);
    				data3List.add(mapNoData17);
    				data3List.add(mapNoData18);
    				data3List.add(mapNoData19);
    				data3List.add(mapNoData20);
    				data3List.add(mapNoData21);
    				data3List.add(mapNoData22);
    				data3List.add(mapNoData23);
    				data3List.add(mapNoData24);
    			}


    			for (int i=0;i<categories.size();i++){
    				if (data1sourceMap.containsKey(categories.get(i))){
    					Map<String,Object> value = data1sourceMap.get(categories.get(i));
            			list1.add(value.get("enter"));
            			list2.add(value.get("exit"));
            			if (value.get("enter")==null || value.get("passby")==null || value.get("passby").equals(0)){
            				list3.add(0.00);
            			} else {
                			Double enter = Double.parseDouble(value.get("enter").toString());
                			Double passby = Double.parseDouble(value.get("passby").toString());
        					BigDecimal bg = new BigDecimal(enter*100/passby).setScale(2,RoundingMode.UP);
        					list3.add(bg.doubleValue());
            			}

    					if (value.get("stay_time")==null){
    						list4.add(0.00);
    					} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("stay_time").toString())/60).setScale(2,RoundingMode.UP);
    	    				list4.add(Math.abs(bg.doubleValue()));
    					}
    					
    					if (value.get("sales")==null){
    						list5.add(0.00);
    					} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())).setScale(2,RoundingMode.UP);
    	    				list5.add(bg.doubleValue());
    					}

    	    			if (value.get("trades")==null){
    	    				list6.add(0);
    	    			} else {
    	    				list6.add(value.get("trades"));
    	    			}
    	    			if (value.get("goods")==null){
    	    				list7.add(0);
    	    			} else {
    	    				list7.add(value.get("goods"));
    	    			}
    	    			//转化率
    	    			if (value.get("trades")==null || value.get("enter")==null || ((BigDecimal)value.get("enter")).equals(0)){
    	    				list8.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("trades").toString())*100)/Double.parseDouble(value.get("enter").toString())).setScale(2,RoundingMode.UP);
    	    				list8.add(bg.doubleValue());
    	    			}
    	    			//客单价
    	    			if (value.get("sales")==null || value.get("enter")==null ||  ((BigDecimal)value.get("enter")).equals(0)){
    	    				list9.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("sales").toString()))/Double.parseDouble(value.get("enter").toString())).setScale(2,RoundingMode.UP);
    	    				list9.add(bg.doubleValue());
    	    			}
    	    			//票单价
    	    			if (value.get("sales")==null || value.get("trades")==null || ((BigDecimal)value.get("trades")).equals(0)){
    	    				list10.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("sales").toString()))/Double.parseDouble(value.get("trades").toString())).setScale(2,RoundingMode.UP);
    	    				list10.add(bg.doubleValue());
    	    			}
    	    			//票单量
    	    			if (value.get("goods")==null || value.get("trades")==null || ((BigDecimal)value.get("trades")).equals(0)){
    	    				list11.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("goods").toString()))/Double.parseDouble(value.get("trades").toString())).setScale(2,RoundingMode.UP);
    	    				list11.add(bg.doubleValue());
    	    			}
    	    			//客单量
    	    			if (value.get("goods")==null || value.get("enter")==null || ((BigDecimal)value.get("enter")).equals(0)){
    	    				list12.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("goods").toString()))/Double.parseDouble(value.get("enter").toString())).setScale(2,RoundingMode.UP);
    	    				list12.add(bg.doubleValue());
    	    			}
    	    			//件单价
    	    			if (value.get("sales")==null || value.get("goods")==null || ((BigDecimal)value.get("goods")).equals(0)){
    	    				list13.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("sales").toString()))/Double.parseDouble(value.get("goods").toString())).setScale(2,RoundingMode.UP);
    	    				list13.add(bg.doubleValue());
    	    			}
    	    			//集客力
    	    			if (value.get("enter")==null || value.get("acreage")==null || value.get("acreage").equals(0)){
    	    				list14.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("enter").toString()))/Double.parseDouble(value.get("acreage").toString())).setScale(2,RoundingMode.UP);
    	    				list14.add(bg.doubleValue());
    	    			}
    	    			//平效
    	    			if (value.get("sales")==null || value.get("acreage")==null || value.get("acreage").equals(0)){
    	    				list15.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("sales").toString()))/Double.parseDouble(value.get("acreage").toString())).setScale(2,RoundingMode.UP);
    	    				list15.add(bg.doubleValue());
    	    			}
    	    			//顾客员工比
    	    			if (value.get("enter")==null || value.get("employee_number")==null || value.get("employee_number").equals(0)){
    	    				list16.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal((Double.parseDouble(value.get("enter").toString()))/Double.parseDouble(value.get("employee_number").toString())).setScale(2,RoundingMode.UP);
    	    				list16.add(bg.doubleValue());
    	    			}
    				} else {
    					list1.add(0);
    					list2.add(0);
    					list3.add(0.00);
    					list4.add(0.00);
    					list5.add(0.00);
    				}
    			}
        		resultMap.put("data1", data1List);
        		resultMap.put("data2", data2List);
        		resultMap.put("data3", data3List);
    			ArrayList<String> dataType = new ArrayList<String>();
    			//添加dataType
    			if (cateId==11){
        			dataType.add("进入流");
        			dataType.add("出入流");
    			} else if (cateId == 12){
    				dataType.add("入店率");
    			} else if (cateId == 14){
    				dataType.add("停留时间");
    			} else if (cateId == 22){
    				dataType.add("销售额");
    				dataType.add("交易数");
    				dataType.add("交易件数");
    				dataType.add("转化率");
    				dataType.add("客单价");
    				dataType.add("票单价");
    				dataType.add("票单量");
    				dataType.add("客单量");
    				dataType.add("件单价");
    				dataType.add("集客力");
    				dataType.add("平效");
    				dataType.add("顾客员工比");
    			}
    			resultMap.put("dataType", dataType);
    			if (siteType == 0 || siteType == 1 || siteType == 3){
    				Map<String,Object> parameterMap = new HashMap<String,Object>();
    				parameterMap.put("findSite", 0);
    				parameterMap.put("siteId", Integer.parseInt(site_name));
    				parameterMap.put("startTime", sdf.format(new Date(st)));
    				parameterMap.put("endTime", sdf.format(new Date(et)));
    				resultMap.put("periodSubjectAndHoliday", homePageMapper.getPeriodSubjectAndHoliday(parameterMap));
    			} else {
    				Map<String,Object> parameterMap = new HashMap<String,Object>();
    				parameterMap.put("findSite", 1);
    				parameterMap.put("siteId", Integer.parseInt(site_name));
    				parameterMap.put("startTime", sdf.format(new Date(st)));
    				parameterMap.put("endTime", sdf.format(new Date(et)));
    				resultMap.put("periodSubjectAndHoliday", homePageMapper.getPeriodSubjectAndHoliday(parameterMap));
    			}
    			return ReturnMapUtil.packData(resultMap);
    		}
    		if (filterId==1){
    			Map<String,Object> resultMap = new HashMap<String,Object>();
    			ArrayList<HomePage> list = homePageService.getTotalData(false, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), scaleMap.get(scale),"f");
    			//添加category
    			categoryMap.clear();
    			for (int i=0;i<list.size();i++){
    				categoryMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), "1");
    			}
    			categories = sort(categoryMap);
    			resultMap.put("categories", categories);
    			//添加数据
    			Map<String,Map<String,Object>> dataMap = new HashMap<String,Map<String,Object>>();
    			for (int i=0;i<list.size();i++){
    				if (dataMap.containsKey(list.get(i).getDisplayName())){
    					Map<String,Object> sonDataMap = dataMap.get(list.get(i).getDisplayName());
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				} else {
    					Map<String,Object> sonDataMap = new HashMap<String,Object>();
    					dataMap.put(list.get(i).getDisplayName(), sonDataMap);
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				}
    			}
    			ArrayList<ArrayList<Map<String,Object>>> dataList = new ArrayList<ArrayList<Map<String,Object>>>();
    			ArrayList<Map<String,Object>> dataInList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataOutList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataPassbyList = new ArrayList<Map<String,Object>>();

    			Map<String,Object> dataIn = new HashMap<String,Object>();
    			Map<String,Object> dataOut = new HashMap<String,Object>();
    			Map<String,Object> dataPassby = new HashMap<String,Object>();

    	    	for (Map.Entry<String, Map<String,Object>> entry:dataMap.entrySet()){
    	    		if (entry.getKey()==null) continue;
    	    		ArrayList<Integer> enterList = new ArrayList<Integer>();
    	    		ArrayList<Integer> exitList = new ArrayList<Integer>();
    	    		ArrayList<Double> passbyList = new ArrayList<Double>();
    	    		Map<String,Object> sonDataMap = entry.getValue();
    	    		for (int i=0;i<categories.size();i++){
    	    			if (sonDataMap.containsKey(categories.get(i))){
        	    			HomePage value = (HomePage)sonDataMap.get(categories.get(i));
    	    				//进入流
        	    			enterList.add(value.getEnter());
    	    				//出入流
    	    				exitList.add(value.getExit());
    	    				//入店率
            				if (list.get(i).getPassby()==null){
            					passbyList.add(0.00);
            				} else {
            					BigDecimal bg = new BigDecimal(((double)value.getEnter()*100)/value.getPassby()).setScale(2,RoundingMode.UP);
            					passbyList.add(bg.doubleValue());
            				}
    	    			} else {
    	    				enterList.add(0);
    	    				exitList.add(0);
    	    				passbyList.add(0.00);
    	    			}
    	    		}
    	    		for (Map.Entry<String, Object> entry1:sonDataMap.entrySet()){
    	    			HomePage value = (HomePage)entry1.getValue();
    	    			dataIn.put("name", value.getDisplayName());
    	    			dataOut.put("name", value.getDisplayName());
    	    			dataPassby.put("name", value.getDisplayName());
    	    		}
    	    		dataIn.put("data", enterList);
    	    		dataOut.put("data", exitList);
    	    		dataPassby.put("data", passbyList);
    	    		dataInList.add(dataIn);
    	    		dataOutList.add(dataOut);
    	    		dataPassbyList.add(dataPassby);
    	    	}

    			if (cateId==11){
        			dataList.add(dataInList);
        			dataList.add(dataOutList);
    			} else if (cateId==12) {
    				dataList.add(dataPassbyList);
    			}
    			resultMap.put("data", dataList);

    			//汇总数据
        		String names = request.getParameter("total");
        		String[] name = names.split(",");

        		Map<String,Object> result = new HashMap<String,Object>();
        		HashMap<String,Object> map = new HashMap<String,Object>();
        		map.put("type",scaleMap.get(scale));
        		map.put("starttime", sdf.format(new Date(st)));
        		map.put("endtime", sdf.format(new Date(et)));
        		map.put("siteid", Integer.parseInt(site_name));
        		map.put("names", name);
        		ArrayList<Map<String,Object>> data1sourcelist = homePageMapper.getCollectData(map);
        		Map<String,Map<String,Object>> data1sourceMap = new HashMap<String,Map<String,Object>>();
        		for (int i=0;i<data1sourcelist.size();i++){
        			Map<String, Object> value =data1sourcelist.get(i);
        			java.sql.Timestamp time = (java.sql.Timestamp)value.get("f_time");
        			Date date = new Date(time.getTime());
        			data1sourceMap.put(sdf1.format(date),value);
        		}
        		
        		ArrayList<Map<String,Object>> data1List = new ArrayList<Map<String,Object>>();
    			Map<String,Object> map1 = new HashMap<String,Object>();
    			Map<String,Object> map2 = new HashMap<String,Object>();
    			Map<String,Object> map3 = new HashMap<String,Object>();
    			map1.put("name", "进入流");
    			map2.put("name", "出入流");
    			map3.put("name", "入店率");
    			ArrayList<Object> list1 = new ArrayList<Object>();
    			ArrayList<Object> list2 = new ArrayList<Object>();
    			ArrayList<Object> list3 = new ArrayList<Object>();
    			map1.put("data", list1);
    			map2.put("data", list2);
    			map3.put("data", list3);
    			if (cateId == 11){
        			data1List.add(map1);
        			data1List.add(map2);
    			} else if (cateId == 12){
    				data1List.add(map3);
    			}

    			for (int i=0;i<categories.size();i++){
    				if (data1sourceMap.containsKey(categories.get(i))){
    					Map<String,Object> value = data1sourceMap.get(categories.get(i));
            			list1.add(value.get("enter"));
            			list2.add(value.get("exit"));
            			Double enter = Double.parseDouble(value.get("enter").toString());
            			Double passby = Double.parseDouble(value.get("passby").toString());
    					BigDecimal bg = new BigDecimal(enter*100/passby).setScale(2,RoundingMode.UP);
    					list3.add(bg.doubleValue());
    				} else {
    					list1.add(0);
    					list2.add(0);
    					list3.add(0.00);
    				}
    			}
        		resultMap.put("data1", data1List);
//////////////////////////////////////////////////////////////////////////////////////////    
    			ArrayList<String> dataType = new ArrayList<String>();
    			dataType = new ArrayList<String>();
    			if (cateId==11){
    				dataType.add("进入流");
    				dataType.add("出入流");
    			} else {
    				dataType.add("入店率");
    			}
    			resultMap.put("dataType", dataType);
    			return ReturnMapUtil.packData(resultMap);
    		}
    		if (filterId==2 || filterId==3 || filterId==4 || filterId==5 || filterId==6){
    			Map<String,Object> resultMap = new HashMap<String,Object>();
    			ArrayList<HomePageByZonetype> list = homePageService.getHomePagebyZonetype(false, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), scaleMap.get(scale),filterId-2);
    			//添加category
    			categoryMap.clear();
    			for (int i=0;i<list.size();i++){
    				categoryMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), "1");
    			}
    			categories = sort(categoryMap);
    			resultMap.put("categories", categories);
    			//添加数据
    			Map<String,Map<String,Object>> dataMap = new HashMap<String,Map<String,Object>>();
    			for (int i=0;i<list.size();i++){
    				if (dataMap.containsKey(list.get(i).getZoneName())){
    					Map<String,Object> sonDataMap = dataMap.get(list.get(i).getZoneName());
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				} else {
    					Map<String,Object> sonDataMap = new HashMap<String,Object>();
    					dataMap.put(list.get(i).getZoneName(), sonDataMap);
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				}
    			}
    			ArrayList<ArrayList<Map<String,Object>>> dataList = new ArrayList<ArrayList<Map<String,Object>>>();
    			ArrayList<Map<String,Object>> dataInList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataOutList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataPassbyList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataStayTimeList = new ArrayList<Map<String,Object>>();

    	    	for (Map.Entry<String, Map<String,Object>> entry:dataMap.entrySet()){
    	    		if (entry.getKey()==null) continue;
    	    		
        			Map<String,Object> dataIn = new HashMap<String,Object>();
        			Map<String,Object> dataOut = new HashMap<String,Object>();
        			Map<String,Object> dataPassby = new HashMap<String,Object>();
        			Map<String,Object> dataStayTime = new HashMap<String,Object>();
        			
    	    		ArrayList<Integer> enterList = new ArrayList<Integer>();
    	    		ArrayList<Integer> exitList = new ArrayList<Integer>();
    	    		ArrayList<Double> passbyList = new ArrayList<Double>();
    	    		ArrayList<Double> stayTimeList=  new ArrayList<Double>();
    	    		Map<String,Object> sonDataMap = entry.getValue();
    	    		for (int i=0;i<categories.size();i++){
    	    			if (sonDataMap.containsKey(categories.get(i))){
    	    				HomePageByZonetype value = (HomePageByZonetype)sonDataMap.get(categories.get(i));
    	    				//进入流
    	    				if (value.getCountIn()==null){
    	    					enterList.add(0);
    	    				} else {
            	    			enterList.add(value.getCountIn());
    	    				}
    	    				//出入流
    	    				if (value.getCountOut()==null){
    	    					exitList.add(0);
    	    				} else {
        	    				exitList.add(value.getCountOut());
    	    				}

    	    				//入店率
            				if (value.getPassby()==null){
            					passbyList.add(0.00);
            				} else {
            					BigDecimal bg = new BigDecimal(((double)value.getCountIn()*100)/value.getPassby()).setScale(2,RoundingMode.UP);
            					passbyList.add(bg.doubleValue());
            				}
            				
            				if (value.getStayTime()==null){
            					stayTimeList.add(0.00);
            				} else {
            					BigDecimal bg = new BigDecimal(Math.abs(((double)value.getStayTime())/60)).setScale(2,RoundingMode.UP);
            					stayTimeList.add(bg.doubleValue());
            				}
    	    			} else {
    	    				enterList.add(0);
    	    				exitList.add(0);
    	    				passbyList.add(0.00);
    	    				stayTimeList.add(0.00);
    	    			}
    	    		}
    	    		for (Map.Entry<String, Object> entry1:sonDataMap.entrySet()){
    	    			HomePageByZonetype value = (HomePageByZonetype)entry1.getValue();
    	    			dataIn.put("name", value.getZoneName());
    	    			dataOut.put("name", value.getZoneName());
    	    			dataPassby.put("name", value.getZoneName());
    	    			dataStayTime.put("name",value.getZoneName());
    	    		}
    	    		dataIn.put("data", enterList);
    	    		dataOut.put("data", exitList);
    	    		dataPassby.put("data", passbyList);
    	    		dataStayTime.put("data", stayTimeList);
    	    		
    	    		dataInList.add(dataIn);
    	    		dataOutList.add(dataOut);
    	    		dataPassbyList.add(dataPassby);
    	    		dataStayTimeList.add(dataStayTime);
    	    	}

    			if (cateId==11){
        			dataList.add(dataInList);
        			dataList.add(dataOutList);
    			} else if (cateId==12) {
    				dataList.add(dataPassbyList);
    			} else if (cateId==14) {
    				dataList.add(dataStayTimeList);
    			}
    			resultMap.put("data", dataList);
    			//汇总数据
    			
        		String names = request.getParameter("total");
        		String[] name = names.split(",");
        		Map<String,Object> result = new HashMap<String,Object>();
        		HashMap<String,Object> map = new HashMap<String,Object>();
        		map.put("type",scaleMap.get(scale));
        		map.put("zoneType", filterId-2);
        		map.put("starttime", sdf.format(new Date(st)));
        		map.put("endtime", sdf.format(new Date(et)));
        		map.put("siteid", Integer.parseInt(site_name));
        		map.put("names", name);
        		ArrayList<Map<String,Object>> data1sourcelist = homePageMapper.getCollectSitezoneData(map);
        		HashMap<String,Object> noDataMap = new HashMap<String,Object>();
        		noDataMap.put("startTime", sdf.format(new Date(st)));
        		noDataMap.put("endTime",sdf.format(new Date(et)));
        		noDataMap.put("siteId",Integer.parseInt(site_name));
        		noDataMap.put("zoneType", filterId-2);
        		noDataMap.put("total", "t");
        		noDataMap.put("findSite", 0);
        		noDataMap.put("names", name);
        		ArrayList<Map<String,Object>> data2sourceList = homePageMapper.getSitezoneDataNoTime(noDataMap);
        		noDataMap.put("total", "f");
        		ArrayList<Map<String,Object>> data3sourceList = homePageMapper.getSitezoneDataNoTime(noDataMap);
        		Map<String,Map<String,Object>> data1sourceMap = new HashMap<String,Map<String,Object>>();
        		for (int i=0;i<data1sourcelist.size();i++){
        			Map<String, Object> value =data1sourcelist.get(i);
        			java.sql.Timestamp time = (java.sql.Timestamp)value.get("f_time");
        			Date date = new Date(time.getTime());
        			data1sourceMap.put(sdf1.format(date),value);
        		}
        		
        		ArrayList<Map<String,Object>> data1List = new ArrayList<Map<String,Object>>();
        		ArrayList<Map<String,Object>> data2List = new ArrayList<Map<String,Object>>();
        		ArrayList<Map<String,Object>> data3List = new ArrayList<Map<String,Object>>();
    			Map<String,Object> map1 = new HashMap<String,Object>();
    			Map<String,Object> map2 = new HashMap<String,Object>();
    			Map<String,Object> map3 = new HashMap<String,Object>();
    			Map<String,Object> map4 = new HashMap<String,Object>();
    			map1.put("name", "进入流");
    			map2.put("name", "出入流");
    			map3.put("name", "入店率");
    			map4.put("name", "停留时间");
    			ArrayList<Object> list1 = new ArrayList<Object>();
    			ArrayList<Object> list2 = new ArrayList<Object>();
    			ArrayList<Object> list3 = new ArrayList<Object>();
    			ArrayList<Object> list4 = new ArrayList<Object>();
    			map1.put("data", list1);
    			map2.put("data", list2);
    			map3.put("data", list3);
    			map4.put("data", list4);
    			
    			
    			Integer totalEnter = 0;
    			Integer totalExit = 0;
    			Integer totalPassby = 0;
    			Double totalStayTime = 0.00;
    			if (data2sourceList.get(0).get("enter")!=null){
        			totalEnter = Integer.parseInt(data2sourceList.get(0).get("enter").toString());
    			}
    			if (data2sourceList.get(0).get("exit")!=null){
    				totalExit = Integer.parseInt(data2sourceList.get(0).get("exit").toString());
    			}
    			if (data2sourceList.get(0).get("passby")!=null){
    				totalPassby = Integer.parseInt(data2sourceList.get(0).get("passby").toString());
    			}
    			if (data2sourceList.get(0).get("stay_time")!=null){
    				totalStayTime = Double.parseDouble(data2sourceList.get(0).get("stay_time").toString());
    			}
    			ArrayList<String> nameList = new ArrayList<String>();
    			if (cateId == 11){
        			data1List.add(map1);
        			data1List.add(map2);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData1.put("name", "进入流");
        			mapNoData1.put("data", new Integer[]{totalEnter});
        			mapNoData2.put("name", "出入流");
        			mapNoData2.put("data", new Integer[]{totalExit});
        			
        			Map<String,Object> mapNoData3 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData4 = new HashMap<String,Object>();
        			mapNoData3.put("name", "进入流");
        			mapNoData4.put("name", "出入流");
        			ArrayList<Integer> listNoData1 = new ArrayList<Integer>();
        			ArrayList<Integer> listNoData2 = new ArrayList<Integer>();
        			mapNoData3.put("data", listNoData1);
        			mapNoData4.put("data", listNoData2);
        			
        			mapNoData3.put("categories", nameList);
        			mapNoData4.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				listNoData1.add(Integer.parseInt(data3sourceList.get(i).get("enter").toString()));
        				listNoData2.add(Integer.parseInt(data3sourceList.get(i).get("exit").toString()));
        				nameList.add(data3sourceList.get(i).get("zone_name").toString());
        			}
        			
        			data2List.add(mapNoData1);
        			data2List.add(mapNoData2);
        			data3List.add(mapNoData3);
        			data3List.add(mapNoData4);
    			} else if (cateId == 12){
    				data1List.add(map3);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			mapNoData1.put("name", "入店率");
        			if (totalPassby!=0){
        				mapNoData1.put("data", new Double[]{new BigDecimal((double) totalEnter*100/totalPassby).setScale(2,RoundingMode.UP).doubleValue()});
        			}
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData2.put("name", "入店率");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			mapNoData2.put("data", listNoData1);
        			mapNoData2.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				if (Integer.parseInt(data3sourceList.get(i).get("passby").toString())!=0){
        					listNoData1.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())*100/Integer.parseInt(data3sourceList.get(i).get("passby").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				nameList.add(data3sourceList.get(i).get("zone_name").toString());
        			}
    				data2List.add(mapNoData1);
    				data3List.add(mapNoData2);
    			} else if (cateId == 14){
    				data1List.add(map4);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			mapNoData1.put("name", "停留时间");
        			mapNoData1.put("data", new Double[]{Math.abs(new BigDecimal(totalStayTime/60).setScale(2,RoundingMode.UP).doubleValue())});
        			
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData2.put("name", "停留时间");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			mapNoData2.put("data", listNoData1);
        			mapNoData2.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				if (Double.parseDouble(data3sourceList.get(i).get("stay_time").toString())!=0){
        					listNoData1.add(Math.abs(new BigDecimal(Double.parseDouble(data3sourceList.get(i).get("stay_time").toString())/60).setScale(2,RoundingMode.UP).doubleValue()));
        				}
        				nameList.add(data3sourceList.get(i).get("zone_name").toString());
        			}
    				data2List.add(mapNoData1);
    				data3List.add(mapNoData2);
    			}

    			for (int i=0;i<categories.size();i++){
    				if (data1sourceMap.containsKey(categories.get(i))){
    					Map<String,Object> value = data1sourceMap.get(categories.get(i));
    					Integer enter = 0;
    					Integer exit = 0;
    					Integer passby = 0;
    					
            			if(null == value.get("enter")) {
    						enter = 0;
            			} else {
    						enter = Integer.parseInt(value.get("enter").toString());
            			}
            			if (null == value.get("exit")) {
            				exit = 0;
            			} else {
    						exit = Integer.parseInt(value.get("exit").toString());
            			}
            			if (null == value.get("passby")) {
            				passby = 0;
            			} else {
            				passby = Integer.parseInt(value.get("passby").toString());
            			}
            			list1.add(enter);
            			list2.add(exit);
            			Double rate = 0.00;
            			if (passby !=0){
        					BigDecimal bg = new BigDecimal(enter*100/passby).setScale(2,RoundingMode.UP);
        					list3.add(bg.doubleValue());
            			}
            			
    					if (value.get("stay_time")==null){
    						list4.add(0.00);
    					} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("stay_time").toString())/60).setScale(2,RoundingMode.UP);
    	    				list4.add(Math.abs(bg.doubleValue()));
    					}
    				} else {
    					list1.add(0);
    					list2.add(0);
    					list3.add(0.00);
    					list4.add(0.00);
    				}
    			}
        		resultMap.put("data1", data1List);
        		resultMap.put("data2", data2List);
        		resultMap.put("data3", data3List);
//////////////////////////////////////////////////////////////////////////////////////////   
    			ArrayList<String> dataType = new ArrayList<String>();
    			dataType = new ArrayList<String>();
    			if (cateId == 11){
        			dataType.add("进入流");
        			dataType.add("出入流");
    			} else if (cateId == 12) {
    				dataType.add("入店率");
    			} else if (cateId ==14) {
    				dataType.add("停留时间");
    			}

    			resultMap.put("dataType", dataType);

    				Map<String,Object> parameterMap = new HashMap<String,Object>();
    				parameterMap.put("findSite", 0);
    				parameterMap.put("siteId", Integer.parseInt(site_name));
    				parameterMap.put("startTime", sdf.format(new Date(st)));
    				parameterMap.put("endTime", sdf.format(new Date(et)));
    				resultMap.put("periodSubjectAndHoliday", homePageMapper.getPeriodSubjectAndHoliday(parameterMap));
    			return ReturnMapUtil.packData(resultMap);
    		}
    		if (filterId==7){
    			Map<String,Object> resultMap = new HashMap<String,Object>();
    			String type = request.getParameter("tagType");
    			ArrayList<HomePageByTag> list = homePageService.getHomePagebyTag(true, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), scaleMap.get(scale),type);
    			//添加category
    			categoryMap.clear();
    			for (int i=0;i<list.size();i++){
    				categoryMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), "1");
    			}
    			categories = sort(categoryMap);
    			resultMap.put("categories", categories);
    			//添加数据
    			Map<String,Map<String,Object>> dataMap = new HashMap<String,Map<String,Object>>();
    			for (int i=0;i<list.size();i++){
    				if (dataMap.containsKey(list.get(i).getTagValue())){
    					Map<String,Object> sonDataMap = dataMap.get(list.get(i).getTagValue());
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				} else {
    					Map<String,Object> sonDataMap = new HashMap<String,Object>();
    					dataMap.put(list.get(i).getTagValue(), sonDataMap);
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				}
    			}
    			ArrayList<ArrayList<Map<String,Object>>> dataList = new ArrayList<ArrayList<Map<String,Object>>>();
    			ArrayList<Map<String,Object>> dataInList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataOutList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataPassbyList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataStayTimeList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataRateList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerManList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsPerTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsPerManList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerGoodsList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataJikeliList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataPingxiaoList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataYuangongbiList = new ArrayList<Map<String,Object>>();

    			for (Map.Entry<String, Map<String,Object>> entry:dataMap.entrySet()){
    	    		if (entry.getKey()==null) continue;
        			Map<String,Object> dataIn = new HashMap<String,Object>();
        			Map<String,Object> dataOut = new HashMap<String,Object>();
        			Map<String,Object> dataPassby = new HashMap<String,Object>();
        			Map<String,Object> dataStayTime = new HashMap<String,Object>();
        			Map<String,Object> dataSales = new HashMap<String,Object>();
        			Map<String,Object> dataTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoods = new HashMap<String,Object>();
        			Map<String,Object> dataRate = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerMan = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoodsPerTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoodsPerMan = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerGoods = new HashMap<String,Object>();
        			Map<String,Object> dataJikeli = new HashMap<String,Object>();
        			Map<String,Object> dataPingxiao = new HashMap<String,Object>();
        			Map<String,Object> dataYuangongbi = new HashMap<String,Object>();
        			
    	    		ArrayList<Integer> enterList = new ArrayList<Integer>();
    	    		ArrayList<Integer> exitList = new ArrayList<Integer>();
    	    		ArrayList<Double> passbyList = new ArrayList<Double>();
    	    		ArrayList<Double> stayTimeList = new ArrayList<Double>();
    	    		ArrayList<Double> salesList = new ArrayList<Double>();
    	    		ArrayList<Integer> tradesList = new ArrayList<Integer>();
    	    		ArrayList<Integer> goodsList = new ArrayList<Integer>();
    	    		ArrayList<Double> rateList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerManList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerTradesList = new ArrayList<Double>();
    	    		ArrayList<Double> goodsPerTradesList = new ArrayList<Double>();
    	    		ArrayList<Double> goodsPerManList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerGoodsList = new ArrayList<Double>();
    	    		ArrayList<Double> jikeliList = new ArrayList<Double>();
    	    		ArrayList<Double> pingxiaoList = new ArrayList<Double>();
    	    		ArrayList<Double> yuangongbiList = new ArrayList<Double>();
    	    		
    	    		Map<String,Object> sonDataMap = entry.getValue();
    	    		for (int i=0;i<categories.size();i++){
    	    			if (sonDataMap.containsKey(categories.get(i))){
        	    			HomePageByTag value = (HomePageByTag)sonDataMap.get(categories.get(i));
    	    				//进入流
        	    			if (value.getCountIn()==null){
        	    				enterList.add(0);
        	    			} else {
        	    				enterList.add(value.getCountIn());
        	    			}
    	    				//出入流
        	    			if (value.getCountOut()==null){
        	    				exitList.add(0);
        	    			} else {
        	    				exitList.add(value.getCountOut());
        	    			}
    	    				//入店率
            				if (value.getCountIn()==null || value.getPassby()==null || value.getPassby()==0){
            					passbyList.add(0.00);
            				} else {
            					BigDecimal bg = new BigDecimal(((double)value.getCountIn()*100)/value.getPassby()).setScale(2,RoundingMode.UP);
            					passbyList.add(bg.doubleValue());
            				}
            				//停留时间
        	    			if (value.getStayTime()==null){
        	    				stayTimeList.add(0.00);
        	    			} else {
            					BigDecimal bg = new BigDecimal(Math.abs(((double)value.getStayTime()/60))).setScale(2,RoundingMode.UP);
            					stayTimeList.add(bg.doubleValue());
        	    			}
        	    			//销售额
        	    			if (value.getSales()==null){
        	    				salesList.add(0.00);
        	    			} else {
            					BigDecimal bg = new BigDecimal(value.getSales()).setScale(2,RoundingMode.UP);
        	    				salesList.add(value.getSales());
        	    			}
        	    			//交易数
        	    			if (value.getTrades()==null){
        	    				tradesList.add(0);
        	    			} else {
        	    				tradesList.add(value.getTrades());
        	    			}
        	    			//交易件数
        	    			if (value.getGoods()==null){
        	    				goodsList.add(0);
        	    			} else {
        	    				tradesList.add(value.getGoods());
        	    			}
        	    			//转化率
        	    			if (value.getTrades()==null || value.getCountIn()==null || value.getCountIn()==0){
        	    				rateList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getTrades())/value.getCountIn()).setScale(2,RoundingMode.UP);
        	    				rateList.add(bg.doubleValue());
        	    			}
        	    			//客单价
        	    			if (value.getSales()==null || value.getCountIn()==null || value.getCountIn()==0){
        	    				salesPerManList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getCountIn()).setScale(2,RoundingMode.UP);
        	    				salesPerManList.add(bg.doubleValue());
        	    			}
        	    			//票单价
        	    			if (value.getSales()==null || value.getTrades()==null || value.getTrades()==0){
        	    				salesPerTradesList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getCountIn())/value.getPassby()).setScale(2,RoundingMode.UP);
        	    				salesPerTradesList.add(bg.doubleValue());
        	    			}
        	    			//票单量
        	    			if (value.getGoods()==null || value.getTrades()==null || value.getTrades()==0){
        	    				goodsPerTradesList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getGoods())/value.getTrades()).setScale(2,RoundingMode.UP);
        	    				goodsPerTradesList.add(bg.doubleValue());
        	    			}
        	    			//客单量
        	    			if (value.getGoods()==null || value.getCountIn()==null || value.getCountIn()==0){
        	    				goodsPerManList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getGoods())/value.getCountIn()).setScale(2,RoundingMode.UP);
        	    				goodsPerManList.add(bg.doubleValue());
        	    			}
        	    			//件单价
        	    			if (value.getSales()==null || value.getGoods()==null || value.getGoods()==0){
        	    				salesPerGoodsList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getGoods()).setScale(2,RoundingMode.UP);
        	    				salesPerGoodsList.add(bg.doubleValue());
        	    			}
        	    			//集客力
        	    			if (value.getCountIn()==null || value.getAcreage()==null || value.getAcreage()==0){
        	    				jikeliList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getCountIn())/value.getAcreage()).setScale(2,RoundingMode.UP);
        	    				jikeliList.add(bg.doubleValue());
        	    			}
        	    			//平效
        	    			if (value.getSales()==null || value.getCountIn()==null || value.getCountIn()==0){
        	    				pingxiaoList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getAcreage()).setScale(2,RoundingMode.UP);
        	    				pingxiaoList.add(bg.doubleValue());
        	    			}
        	    			//顾客员工比
        	    			if (value.getCountIn()==null || value.getEmployeeNumber()==null || value.getEmployeeNumber()==0){
        	    				yuangongbiList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getCountIn())/value.getEmployeeNumber()).setScale(2,RoundingMode.UP);
        	    				yuangongbiList.add(bg.doubleValue());
        	    			}
    	    			} else {
    	    				enterList.add(0);
    	    				exitList.add(0);
    	    				passbyList.add(0.00);
    	    				stayTimeList.add(0.00);
    	    				salesList.add(0.00);
    	    				tradesList.add(0);
    	    				goodsList.add(0);
    	    				rateList.add(0.00);
    	    				salesPerManList.add(0.00);
    	    				salesPerTradesList.add(0.00);
    	    				goodsPerTradesList.add(0.00);
    	    				goodsPerManList.add(0.00);
    	    				salesPerGoodsList.add(0.00);
    	    				jikeliList.add(0.00);
    	    				pingxiaoList.add(0.00);
    	    				yuangongbiList.add(0.00);
    	    			}
    	    		}
    	    		for (Map.Entry<String, Object> entry1:sonDataMap.entrySet()){
    	    			HomePageByTag value = (HomePageByTag)entry1.getValue();
    	    			dataIn.put("name", value.getTagValue());
    	    			dataOut.put("name", value.getTagValue());
    	    			dataPassby.put("name", value.getTagValue());
    	    			dataStayTime.put("name",value.getTagValue());
    	    			dataSales.put("name", value.getTagValue());
    	    			dataTrades.put("name", value.getTagValue());
    	    			dataGoods.put("name", value.getTagValue());
    	    			dataRate.put("name", value.getTagValue());
    	    			dataSalesPerMan.put("name", value.getTagValue());
    	    			dataSalesPerTrades.put("name", value.getTagValue());
    	    			dataGoodsPerTrades.put("name", value.getTagValue());
    	    			dataGoodsPerMan.put("name", value.getTagValue());
    	    			dataSalesPerGoods.put("name", value.getTagValue());
    	    			dataJikeli.put("name", value.getTagValue());
    	    			dataPingxiao.put("name", value.getTagValue());
    	    			dataYuangongbi.put("name", value.getTagValue());
    	    		}
    	    		dataIn.put("data", enterList);
    	    		dataOut.put("data", exitList);
    	    		dataPassby.put("data", passbyList);
    	    		dataStayTime.put("data", stayTimeList);
    	    		dataSales.put("data", salesList);
    	    		dataTrades.put("data", tradesList);
    	    		dataGoods.put("data", goodsList);
    	    		dataRate.put("data", rateList);
    	    		dataSalesPerMan.put("data", salesPerManList);
    	    		dataSalesPerTrades.put("data", salesPerTradesList);
    	    		dataGoodsPerTrades.put("data", goodsPerTradesList);
    	    		dataGoodsPerMan.put("data", goodsPerManList);
    	    		dataSalesPerGoods.put("data", salesPerGoodsList);
    	    		dataJikeli.put("data", jikeliList);
    	    		dataPingxiao.put("data", pingxiaoList);
    	    		dataYuangongbi.put("data", yuangongbiList);
    	    		
    	    		dataInList.add(dataIn);
    	    		dataOutList.add(dataOut);
    	    		dataPassbyList.add(dataPassby);
    	    		dataStayTimeList.add(dataStayTime);
    	    		dataSalesList.add(dataSales);
    	    		dataTradesList.add(dataTrades);
    	    		dataGoodsList.add(dataGoods);
    	    		dataRateList.add(dataRate);
    	    		dataSalesPerManList.add(dataSalesPerMan);
    	    		dataSalesPerTradesList.add(dataSalesPerTrades);
    	    		dataGoodsPerTradesList.add(dataGoodsPerTrades);
    	    		dataGoodsPerManList.add(dataGoodsPerMan);
    	    		dataSalesPerGoodsList.add(dataSalesPerGoods);
    	    		dataJikeliList.add(dataJikeli);
    	    		dataPingxiaoList.add(dataPingxiao);
    	    		dataYuangongbiList.add(dataYuangongbi); 	
    	    	}

    			if (cateId==11){
        			dataList.add(dataInList);
        			dataList.add(dataOutList);
    			} else if (cateId==12) {
    				dataList.add(dataPassbyList);
    			} else if (cateId==14) {
    				dataList.add(dataStayTimeList);
    			} else if (cateId==22) {
    				dataList.add(dataSalesList);
    				dataList.add(dataTradesList);
    				dataList.add(dataGoodsList);
    				dataList.add(dataRateList);
    				dataList.add(dataSalesPerManList);
    				dataList.add(dataSalesPerTradesList);
    				dataList.add(dataGoodsPerTradesList);
    				dataList.add(dataGoodsPerManList);
    				dataList.add(dataSalesPerGoodsList);
    				dataList.add(dataJikeliList);
    				dataList.add(dataPingxiaoList);
    				dataList.add(dataYuangongbiList);
    			}
    			resultMap.put("data", dataList);
    			//汇总数据
        		String names = request.getParameter("total");
        		String[] name = names.split(",");

        		Map<String,Object> result = new HashMap<String,Object>();
        		HashMap<String,Object> map = new HashMap<String,Object>();
        		map.put("type",scaleMap.get(scale));
        		map.put("starttime", sdf.format(new Date(st)));
        		map.put("endtime", sdf.format(new Date(et)));
        		map.put("siteid", Integer.parseInt(site_name));
        		map.put("tagType", type);
        		map.put("names", name);
        		ArrayList<Map<String,Object>> data1sourcelist = homePageMapper.getCollectTagData(map);
        		HashMap<String,Object> noDataMap = new HashMap<String,Object>();
        		noDataMap.put("startTime", sdf.format(new Date(st)));
        		noDataMap.put("endTime",sdf.format(new Date(et)));
        		noDataMap.put("siteId",Integer.parseInt(site_name));
        		noDataMap.put("tagType", type);
        		noDataMap.put("total", "t");
        		noDataMap.put("findSite", 1);
        		noDataMap.put("names", name);
        		ArrayList<Map<String,Object>> data2sourceList = homePageMapper.getTagDataNoTime(noDataMap);
        		noDataMap.put("total", "f");
        		ArrayList<Map<String,Object>> data3sourceList = homePageMapper.getTagDataNoTime(noDataMap);
        		Map<String,Map<String,Object>> data1sourceMap = new HashMap<String,Map<String,Object>>();
        		for (int i=0;i<data1sourcelist.size();i++){
        			Map<String, Object> value =data1sourcelist.get(i);
        			java.sql.Timestamp time = (java.sql.Timestamp)value.get("f_time");
        			Date date = new Date(time.getTime());
        			data1sourceMap.put(sdf1.format(date),value);
        		}
        		
        		ArrayList<Map<String,Object>> data1List = new ArrayList<Map<String,Object>>();
        		ArrayList<Map<String,Object>> data2List = new ArrayList<Map<String,Object>>();
        		ArrayList<Map<String,Object>> data3List = new ArrayList<Map<String,Object>>();
    			Map<String,Object> map1 = new HashMap<String,Object>();
    			Map<String,Object> map2 = new HashMap<String,Object>();
    			Map<String,Object> map3 = new HashMap<String,Object>();
    			Map<String,Object> map4 = new HashMap<String,Object>();
    			Map<String,Object> map5 = new HashMap<String,Object>();
    			Map<String,Object> map6 = new HashMap<String,Object>();
    			Map<String,Object> map7 = new HashMap<String,Object>();
    			Map<String,Object> map8 = new HashMap<String,Object>();
    			Map<String,Object> map9 = new HashMap<String,Object>();
    			Map<String,Object> map10 = new HashMap<String,Object>();
    			Map<String,Object> map11 = new HashMap<String,Object>();
    			Map<String,Object> map12 = new HashMap<String,Object>();
    			Map<String,Object> map13 = new HashMap<String,Object>();
    			Map<String,Object> map14 = new HashMap<String,Object>();
    			Map<String,Object> map15 = new HashMap<String,Object>();
    			Map<String,Object> map16 = new HashMap<String,Object>();
    			
    			map1.put("name", "进入流");
    			map2.put("name", "出入流");
    			map3.put("name", "入店率");
    			map4.put("name", "停留时间");
    			map5.put("name", "销售额");
    			map6.put("name", "交易数");
    			map7.put("name", "交易件数");
    			map8.put("name", "转化率");
    			map9.put("name", "客单价");
    			map10.put("name", "票单价");
    			map11.put("name", "票单量");
    			map12.put("name", "客单量");
    			map13.put("name", "件单价");
    			map14.put("name", "集客力");
    			map15.put("name", "平效");
    			map16.put("name","顾客员工比");
    			
    			
    			ArrayList<Object> list1 = new ArrayList<Object>();
    			ArrayList<Object> list2 = new ArrayList<Object>();
    			ArrayList<Object> list3 = new ArrayList<Object>();
    			ArrayList<Object> list4 = new ArrayList<Object>();
    			ArrayList<Object> list5 = new ArrayList<Object>();
    			ArrayList<Object> list6 = new ArrayList<Object>();
    			ArrayList<Object> list7 = new ArrayList<Object>();
    			ArrayList<Object> list8 = new ArrayList<Object>();
    			ArrayList<Object> list9 = new ArrayList<Object>();
    			ArrayList<Object> list10 = new ArrayList<Object>();
    			ArrayList<Object> list11 = new ArrayList<Object>();
    			ArrayList<Object> list12 = new ArrayList<Object>();
    			ArrayList<Object> list13 = new ArrayList<Object>();
    			ArrayList<Object> list14 = new ArrayList<Object>();
    			ArrayList<Object> list15= new ArrayList<Object>();
    			ArrayList<Object> list16 = new ArrayList<Object>();

    			map1.put("data", list1);
    			map2.put("data", list2);
    			map3.put("data", list3);
    			map4.put("data", list4);
    			map5.put("data", list5);
    			map6.put("data", list6);
    			map7.put("data", list7);
    			map8.put("data", list8);
    			map9.put("data", list9);
    			map10.put("data", list10);
    			map11.put("data", list11);
    			map12.put("data", list12);
    			map13.put("data", list13);
    			map14.put("data", list14);
    			map15.put("data", list15);
    			map16.put("data", list16);
    						
    			Integer totalEnter = 0;
    			Integer totalExit = 0;
    			Integer totalPassby = 0;
    			Double totalStayTime = 0.00;
    			Double totalSales = 0.00;
    			Integer totalTrades = 0;
    			Integer totalGoods = 0;
    			Integer totalAcreage = 0;
    			Integer totalNumber = 0;
    			if (data2sourceList.get(0).get("enter")!=null){
        			totalEnter = Integer.parseInt(data2sourceList.get(0).get("enter").toString());
    			}
    			if (data2sourceList.get(0).get("exit")!=null){
    				totalExit = Integer.parseInt(data2sourceList.get(0).get("exit").toString());
    			}
    			if (data2sourceList.get(0).get("passby")!=null){
    				totalPassby = Integer.parseInt(data2sourceList.get(0).get("passby").toString());
    			}
    			if (data2sourceList.get(0).get("stay_time")!=null){
    				totalStayTime = Double.parseDouble(data2sourceList.get(0).get("stay_time").toString());
    			}
    			if (data2sourceList.get(0).get("sales")!=null){
    			totalSales = Double.parseDouble(data2sourceList.get(0).get("sales").toString());
    			}
    			if (data2sourceList.get(0).get("trades")!=null){
    				totalTrades = Integer.parseInt(data2sourceList.get(0).get("trades").toString());
    			}
    			if (data2sourceList.get(0).get("goods")!=null){
    				totalGoods = Integer.parseInt(data2sourceList.get(0).get("goods").toString());
    			}
    			if (data2sourceList.get(0).get("acreage")!=null){
    				totalAcreage = Integer.parseInt(data2sourceList.get(0).get("acreage").toString());
    			}
    			if (data2sourceList.get(0).get("employee_number")!=null){
    				totalNumber = Integer.parseInt(data2sourceList.get(0).get("employee_number").toString());
    			}
    			ArrayList<String> nameList = new ArrayList<String>();
    			if (cateId == 11){
        			data1List.add(map1);
        			data1List.add(map2);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData1.put("name", "进入流");
        			mapNoData1.put("data", new Integer[]{totalEnter});
        			mapNoData2.put("name", "出入流");
        			mapNoData2.put("data", new Integer[]{totalExit});
        			
        			Map<String,Object> mapNoData3 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData4 = new HashMap<String,Object>();
        			mapNoData3.put("name", "进入流");
        			mapNoData4.put("name", "出入流");
        			ArrayList<Integer> listNoData1 = new ArrayList<Integer>();
        			ArrayList<Integer> listNoData2 = new ArrayList<Integer>();
        			mapNoData3.put("data", listNoData1);
        			mapNoData4.put("data", listNoData2);
        			
        			mapNoData3.put("categories", nameList);
        			mapNoData4.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				listNoData1.add(Integer.parseInt(data3sourceList.get(i).get("enter").toString()));
        				listNoData2.add(Integer.parseInt(data3sourceList.get(i).get("exit").toString()));
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
        			data2List.add(mapNoData1);
        			data2List.add(mapNoData2);
        			data3List.add(mapNoData3);
        			data3List.add(mapNoData4);
    			} else if (cateId == 12){
    				data1List.add(map3);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			mapNoData1.put("name", "入店率");
        			if (totalPassby!=0){
        				mapNoData1.put("data",new Double[]{new BigDecimal((double) totalEnter*100/totalPassby).setScale(2,RoundingMode.UP).doubleValue()});
        			}
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData2.put("name", "入店率");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			mapNoData2.put("data", listNoData1);
        			mapNoData2.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				if (Integer.parseInt(data3sourceList.get(i).get("passby").toString())!=0){
        					listNoData1.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())*100/Integer.parseInt(data3sourceList.get(i).get("passby").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
    				data2List.add(mapNoData1);
    				data3List.add(mapNoData2);
    			} else if (cateId == 14){
    				data1List.add(map4);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			mapNoData1.put("name", "停留时间");
        			mapNoData1.put("data", new Double[]{Math.abs(new BigDecimal(totalStayTime/60).setScale(2,RoundingMode.UP).doubleValue())});
        			
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData2.put("name", "入店率");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			mapNoData2.put("data", listNoData1);
        			mapNoData2.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				if (Double.parseDouble(data3sourceList.get(i).get("stay_time").toString())!=0){
        					listNoData1.add(Math.abs(new BigDecimal(Double.parseDouble(data3sourceList.get(i).get("stay_time").toString())/60).setScale(2,RoundingMode.UP).doubleValue()));
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
    				data2List.add(mapNoData1);
    				data3List.add(mapNoData2);
    			} else if (cateId == 22){
    				data1List.add(map5);
    				data1List.add(map6);
    				data1List.add(map7);
    				data1List.add(map8);
    				data1List.add(map9);
    				data1List.add(map10);
    				data1List.add(map11);
    				data1List.add(map12);
    				data1List.add(map13);
    				data1List.add(map14);
    				data1List.add(map15);
    				data1List.add(map16);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData3 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData4 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData5 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData6 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData7 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData8 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData9 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData10 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData11 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData12 = new HashMap<String,Object>();
        			mapNoData1.put("name", "销售额");
        			mapNoData2.put("name", "交易数");
        			mapNoData3.put("name", "交易件数");
        			mapNoData4.put("name", "转化率");
        			mapNoData5.put("name", "客单价");
        			mapNoData6.put("name", "票单价");
        			mapNoData7.put("name", "票单量");
        			mapNoData8.put("name", "客单量");
        			mapNoData9.put("name", "件单价");
        			mapNoData10.put("name", "集客力");
        			mapNoData11.put("name", "平效");
        			mapNoData12.put("name", "顾客员工比");
        			mapNoData1.put("data", new Double[]{new BigDecimal(totalSales).setScale(2,RoundingMode.UP).doubleValue()});
        			mapNoData2.put("data", new Integer[]{totalTrades});
        			mapNoData3.put("data", new Integer[]{totalGoods});
        			if (totalEnter!=0){
        				mapNoData4.put("data", new Double[]{new BigDecimal((double) totalTrades*100/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData4.put("data",0.00);
        			}
        			if (totalEnter!=0){
        				mapNoData5.put("data", new Double[]{new BigDecimal((double) totalSales/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData5.put("data",0.00);
        			}
        			if (totalTrades!=0){
        				mapNoData6.put("data", new Double[]{new BigDecimal((double) totalSales/totalTrades).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData6.put("data",0.00);
        			}
        			if (totalTrades!=0){
        				mapNoData7.put("data", new Double[]{new BigDecimal((double) totalGoods/totalTrades).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData7.put("data",0.00);
        			}
        			if (totalEnter!=0){
        				mapNoData8.put("data", new Double[]{new BigDecimal((double) totalGoods/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData8.put("data",0.00);
        			}
        			if (totalGoods!=0){
        				mapNoData9.put("data", new Double[]{new BigDecimal((double) totalSales/totalGoods).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData9.put("data",0.00);
        			}
        			if (totalAcreage!=0){
        				mapNoData10.put("data", new Double[]{new BigDecimal((double) totalEnter/totalAcreage).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData10.put("data",0.00);
        			}
        			if (totalAcreage!=0){
        				mapNoData11.put("data", new Double[]{new BigDecimal((double) totalSales/totalAcreage).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData11.put("data",0.00);
        			}
        			if (totalNumber!=0){
        				mapNoData12.put("data", new Double[]{new BigDecimal((double) totalEnter/totalNumber).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData12.put("data",0.00);
        			}
        			
        			Map<String,Object> mapNoData13 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData14 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData15 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData16 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData17 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData18 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData19 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData20 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData21 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData22 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData23 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData24 = new HashMap<String,Object>();
        			
        			mapNoData13.put("name", "销售额");
        			mapNoData14.put("name", "交易数");
        			mapNoData15.put("name", "交易件数");
        			mapNoData16.put("name", "转化率");
        			mapNoData17.put("name", "客单价");
        			mapNoData18.put("name", "票单价");
        			mapNoData19.put("name", "票单量");
        			mapNoData20.put("name", "客单量");
        			mapNoData21.put("name", "件单价");
        			mapNoData22.put("name", "集客力");
        			mapNoData23.put("name", "平效");
        			mapNoData24.put("name", "顾客员工比");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			ArrayList<Integer> listNoData2 = new ArrayList<Integer>();
        			ArrayList<Integer> listNoData3 = new ArrayList<Integer>();
        			ArrayList<Double> listNoData4 = new ArrayList<Double>();
        			ArrayList<Double> listNoData5 = new ArrayList<Double>();
        			ArrayList<Double> listNoData6 = new ArrayList<Double>();
        			ArrayList<Double> listNoData7 = new ArrayList<Double>();
        			ArrayList<Double> listNoData8 = new ArrayList<Double>();
        			ArrayList<Double> listNoData9 = new ArrayList<Double>();
        			ArrayList<Double> listNoData10 = new ArrayList<Double>();
        			ArrayList<Double> listNoData11 = new ArrayList<Double>();
        			ArrayList<Double> listNoData12 = new ArrayList<Double>();
        			       			
        			mapNoData13.put("data", listNoData1);
        			mapNoData14.put("data", listNoData2);
        			mapNoData15.put("data", listNoData3);
        			mapNoData16.put("data", listNoData4);
        			mapNoData17.put("data", listNoData5);
        			mapNoData18.put("data", listNoData6);
        			mapNoData19.put("data", listNoData7);
        			mapNoData20.put("data", listNoData8);
        			mapNoData21.put("data", listNoData9);
        			mapNoData22.put("data", listNoData10);
        			mapNoData23.put("data", listNoData11);
        			mapNoData24.put("data", listNoData12);
        			
        			mapNoData13.put("categories", nameList);
        			mapNoData14.put("categories", nameList);
        			mapNoData15.put("categories", nameList);
        			mapNoData16.put("categories", nameList);
        			mapNoData17.put("categories", nameList);
        			mapNoData18.put("categories", nameList);
        			mapNoData19.put("categories", nameList);
        			mapNoData20.put("categories", nameList);
        			mapNoData21.put("categories", nameList);
        			mapNoData22.put("categories", nameList);
        			mapNoData23.put("categories", nameList);
        			mapNoData24.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				listNoData1.add(new BigDecimal(Double.parseDouble(data3sourceList.get(i).get("sales").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				listNoData2.add(Integer.parseInt(data3sourceList.get(i).get("trades").toString()));
        				listNoData3.add(Integer.parseInt(data3sourceList.get(i).get("goods").toString()));
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
        					listNoData4.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("trades").toString())*100/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
            				listNoData5.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("trades").toString())!=0){
        					listNoData6.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("trades").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("trades").toString())!=0){
        					listNoData7.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("goods").toString())/Integer.parseInt(data3sourceList.get(i).get("trades").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
        					listNoData8.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("goods").toString())/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("goods").toString())!=0){
        					listNoData9.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("goods").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("acreage").toString())!=0){
            				listNoData10.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())/Integer.parseInt(data3sourceList.get(i).get("acreage").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("acreage").toString())!=0){
            				listNoData11.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("acreage").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("employee_number").toString())!=0){
            				listNoData12.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())/Integer.parseInt(data3sourceList.get(i).get("employee_number").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}

    				data2List.add(mapNoData1);
    				data2List.add(mapNoData2);
    				data2List.add(mapNoData3);
    				data2List.add(mapNoData4);
    				data2List.add(mapNoData5);
    				data2List.add(mapNoData6);
    				data2List.add(mapNoData7);
    				data2List.add(mapNoData8);
    				data2List.add(mapNoData9);
    				data2List.add(mapNoData10);
    				data2List.add(mapNoData11);
    				data2List.add(mapNoData12);
    				
    				data3List.add(mapNoData13);
    				data3List.add(mapNoData14);
    				data3List.add(mapNoData15);
    				data3List.add(mapNoData16);
    				data3List.add(mapNoData17);
    				data3List.add(mapNoData18);
    				data3List.add(mapNoData19);
    				data3List.add(mapNoData20);
    				data3List.add(mapNoData21);
    				data3List.add(mapNoData22);
    				data3List.add(mapNoData23);
    				data3List.add(mapNoData24);
    			}

    			for (int i=0;i<categories.size();i++){
    				if (data1sourceMap.containsKey(categories.get(i))){
    					Map<String,Object> value = data1sourceMap.get(categories.get(i));
            			list1.add(value.get("enter"));
            			list2.add(value.get("exit"));
            			if (value.get("enter")==null || value.get("passby")==null || value.get("passby").equals(0)){
            				list3.add(0.00);
            			} else {
                			Double enter = Double.parseDouble(value.get("enter").toString());
                			Double passby = Double.parseDouble(value.get("passby").toString());
        					BigDecimal bg = new BigDecimal(enter*100/passby).setScale(2,RoundingMode.UP);
        					list3.add(bg.doubleValue());
            			}

    					if (value.get("stay_time")==null){
    						list4.add(0.00);
    					} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("stay_time").toString())/60).setScale(2,RoundingMode.UP);
    	    				list4.add(Math.abs(bg.doubleValue()));
    					}
    					
    					if (value.get("sales")==null){
    						list5.add(0.00);
    					} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())).setScale(2,RoundingMode.UP);
    	    				list5.add(bg.doubleValue());
    					}

    	    			if (value.get("trades")==null){
    	    				list6.add(0);
    	    			} else {
    	    				list6.add(value.get("trades"));
    	    			}
    	    			if (value.get("goods")==null){
    	    				list7.add(0);
    	    			} else {
    	    				list7.add(value.get("goods"));
    	    			}
    	    			//转化率
    	    			if (value.get("trades")==null || value.get("enter")==null || ((BigDecimal)value.get("enter")).equals(0)){
    	    				list8.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("trades").toString())/Double.parseDouble(value.get("enter").toString())*100).setScale(2,RoundingMode.UP);
    	    				list8.add(bg.doubleValue());
    	    			}
    	    			//客单价
    	    			if (value.get("sales")==null || value.get("enter")==null ||  ((BigDecimal)value.get("enter")).equals(0)){
    	    				list9.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())/Double.parseDouble(value.get("enter").toString())).setScale(2,RoundingMode.UP);
    	    				list9.add(bg.doubleValue());
    	    			}
    	    			//票单价
    	    			if (value.get("sales")==null || value.get("trades")==null || ((BigDecimal)value.get("trades")).equals(0)){
    	    				list10.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())/Double.parseDouble(value.get("trades").toString())).setScale(2,RoundingMode.UP);
    	    				list10.add(bg.doubleValue());
    	    			}
    	    			//票单量
    	    			if (value.get("goods")==null || value.get("trades")==null || ((BigDecimal)value.get("trades")).equals(0)){
    	    				list11.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("goods").toString())/Double.parseDouble(value.get("trades").toString())).setScale(2,RoundingMode.UP);
    	    				list11.add(bg.doubleValue());
    	    			}
    	    			//客单量
    	    			if (value.get("goods")==null || value.get("enter")==null || ((BigDecimal)value.get("enter")).equals(0)){
    	    				list12.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("goods").toString())/Double.parseDouble(value.get("enter").toString())).setScale(2,RoundingMode.UP);
    	    				list12.add(bg.doubleValue());
    	    			}
    	    			//件单价
    	    			if (value.get("sales")==null || value.get("goods")==null || ((BigDecimal)value.get("goods")).equals(0)){
    	    				list13.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())/Double.parseDouble(value.get("goods").toString())).setScale(2,RoundingMode.UP);
    	    				list13.add(bg.doubleValue());
    	    			}
    	    			//集客力
    	    			if (value.get("enter")==null || value.get("acreage")==null || value.get("acreage").equals(0)){
    	    				list14.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("enter").toString())/Double.parseDouble(value.get("acreage").toString())).setScale(2,RoundingMode.UP);
    	    				list14.add(bg.doubleValue());
    	    			}
    	    			//平效
    	    			if (value.get("sales")==null || value.get("acreage")==null || value.get("acreage").equals(0)){
    	    				list15.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())/Double.parseDouble(value.get("acreage").toString())).setScale(2,RoundingMode.UP);
    	    				list15.add(bg.doubleValue());
    	    			}
    	    			//顾客员工比
    	    			if (value.get("enter")==null || value.get("employee_number")==null || value.get("employee_number").equals(0)){
    	    				list16.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("enter").toString())/Double.parseDouble(value.get("employee_number").toString())).setScale(2,RoundingMode.UP);
    	    				list16.add(bg.doubleValue());
    	    			}
    				} else {
    					list1.add(0);
    					list2.add(0);
    					list3.add(0.00);
    					list4.add(0.00);
    					list5.add(0.00);
    				}
    			}
        		resultMap.put("data1", data1List);
        		resultMap.put("data2", data2List);
        		resultMap.put("data3", data3List);
    			ArrayList<String> dataType = new ArrayList<String>();
    			//添加dataType
    			if (cateId==11){
        			dataType.add("进入流");
        			dataType.add("出入流");
    			} else if (cateId == 12){
    				dataType.add("入店率");
    			} else if (cateId == 14){
    				dataType.add("停留时间");
    			} else if (cateId == 22){
    				dataType.add("销售额");
    				dataType.add("交易数");
    				dataType.add("交易件数");
    				dataType.add("转化率");
    				dataType.add("客单价");
    				dataType.add("票单价");
    				dataType.add("票单量");
    				dataType.add("客单量");
    				dataType.add("件单价");
    				dataType.add("集客力");
    				dataType.add("平效");
    				dataType.add("顾客员工比");
    			}
    			resultMap.put("dataType", dataType);
    			Map<String,Object> parameterMap = new HashMap<String,Object>();
    			parameterMap.put("findSite", 1);
    			parameterMap.put("siteId", Integer.parseInt(site_name));
    			parameterMap.put("startTime", sdf.format(new Date(st)));
    			parameterMap.put("endTime", sdf.format(new Date(et)));
    			resultMap.put("periodSubjectAndHoliday", homePageMapper.getPeriodSubjectAndHoliday(parameterMap));
    			return ReturnMapUtil.packData(resultMap);
    		}
    		if (filterId==8||filterId==9||filterId==10||filterId==11){
    			Map<String,Object> resultMap = new HashMap<String,Object>();
    			ArrayList<HomePageByLocation> list = homePageService.getHomePageByLocation(true, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), scaleMap.get(scale),filterId-8);
    			//添加category
    			categoryMap.clear();
    			for (int i=0;i<list.size();i++){
    				categoryMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), "1");
    			}
    			categories = sort(categoryMap);
    			resultMap.put("categories", categories);
    			//添加数据
    			Map<String,Map<String,Object>> dataMap = new HashMap<String,Map<String,Object>>();
    			for (int i=0;i<list.size();i++){
    				if (dataMap.containsKey(list.get(i).getLocationName())){
    					Map<String,Object> sonDataMap = dataMap.get(list.get(i).getLocationName());
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				} else {
    					Map<String,Object> sonDataMap = new HashMap<String,Object>();
    					dataMap.put(list.get(i).getLocationName(), sonDataMap);
    					sonDataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
    				}
    			}
    			ArrayList<ArrayList<Map<String,Object>>> dataList = new ArrayList<ArrayList<Map<String,Object>>>();
    			ArrayList<Map<String,Object>> dataInList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataOutList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataPassbyList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataStayTimeList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataRateList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerManList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsPerTradesList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataGoodsPerManList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataSalesPerGoodsList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataJikeliList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataPingxiaoList = new ArrayList<Map<String,Object>>();
    			ArrayList<Map<String,Object>> dataYuangongbiList = new ArrayList<Map<String,Object>>();

    			for (Map.Entry<String, Map<String,Object>> entry:dataMap.entrySet()){
    	    		if (entry.getKey()==null) continue;
    	    		
        			Map<String,Object> dataIn = new HashMap<String,Object>();
        			Map<String,Object> dataOut = new HashMap<String,Object>();
        			Map<String,Object> dataPassby = new HashMap<String,Object>();
        			Map<String,Object> dataStayTime = new HashMap<String,Object>();
        			Map<String,Object> dataSales = new HashMap<String,Object>();
        			Map<String,Object> dataTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoods = new HashMap<String,Object>();
        			Map<String,Object> dataRate = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerMan = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoodsPerTrades = new HashMap<String,Object>();
        			Map<String,Object> dataGoodsPerMan = new HashMap<String,Object>();
        			Map<String,Object> dataSalesPerGoods = new HashMap<String,Object>();
        			Map<String,Object> dataJikeli = new HashMap<String,Object>();
        			Map<String,Object> dataPingxiao = new HashMap<String,Object>();
        			Map<String,Object> dataYuangongbi = new HashMap<String,Object>();
        			
    	    		ArrayList<Integer> enterList = new ArrayList<Integer>();
    	    		ArrayList<Integer> exitList = new ArrayList<Integer>();
    	    		ArrayList<Double> passbyList = new ArrayList<Double>();
    	    		ArrayList<Double> stayTimeList = new ArrayList<Double>();
    	    		ArrayList<Double> salesList = new ArrayList<Double>();
    	    		ArrayList<Integer> tradesList = new ArrayList<Integer>();
    	    		ArrayList<Integer> goodsList = new ArrayList<Integer>();
    	    		ArrayList<Double> rateList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerManList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerTradesList = new ArrayList<Double>();
    	    		ArrayList<Double> goodsPerTradesList = new ArrayList<Double>();
    	    		ArrayList<Double> goodsPerManList = new ArrayList<Double>();
    	    		ArrayList<Double> salesPerGoodsList = new ArrayList<Double>();
    	    		ArrayList<Double> jikeliList = new ArrayList<Double>();
    	    		ArrayList<Double> pingxiaoList = new ArrayList<Double>();
    	    		ArrayList<Double> yuangongbiList = new ArrayList<Double>();
    	    		
    	    		Map<String,Object> sonDataMap = entry.getValue();
    	    		for (int i=0;i<categories.size();i++){
    	    			if (sonDataMap.containsKey(categories.get(i))){
        	    			HomePageByLocation value = (HomePageByLocation)sonDataMap.get(categories.get(i));
    	    				//进入流
        	    			if (value.getCountIn()==null){
        	    				enterList.add(0);
        	    			} else {
        	    				enterList.add(value.getCountIn());
        	    			}
    	    				//出入流
        	    			if (value.getCountOut()==null){
        	    				exitList.add(0);
        	    			} else {
        	    				exitList.add(value.getCountOut());
        	    			}
    	    				//入店率
            				if (value.getCountIn()==null || value.getPassby()==null || value.getPassby()==0){
            					passbyList.add(0.00);
            				} else {
            					BigDecimal bg = new BigDecimal(((double)value.getCountIn()*100)/value.getPassby()).setScale(2,RoundingMode.UP);
            					passbyList.add(bg.doubleValue());
            				}
            				//停留时间
        	    			if (value.getStayTime()==null){
        	    				stayTimeList.add(0.00);
        	    			} else {
            					BigDecimal bg = new BigDecimal(Math.abs(((double)value.getStayTime()/60))).setScale(2,RoundingMode.UP);
            					stayTimeList.add(bg.doubleValue());
        	    			}
        	    			//销售额
        	    			if (value.getSales()==null){
        	    				salesList.add(0.00);
        	    			} else {
            					BigDecimal bg = new BigDecimal(value.getSales()).setScale(2,RoundingMode.UP);
        	    				salesList.add(value.getSales());
        	    			}
        	    			//交易数
        	    			if (value.getTrades()==null){
        	    				tradesList.add(0);
        	    			} else {
        	    				tradesList.add(value.getTrades());
        	    			}
        	    			//交易件数
        	    			if (value.getGoods()==null){
        	    				goodsList.add(0);
        	    			} else {
        	    				tradesList.add(value.getGoods());
        	    			}
        	    			//转化率
        	    			if (value.getTrades()==null || value.getCountIn()==null || value.getCountIn()==0){
        	    				rateList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getTrades())/value.getCountIn()*100).setScale(2,RoundingMode.UP);
        	    				rateList.add(bg.doubleValue());
        	    			}
        	    			//客单价
        	    			if (value.getSales()==null || value.getCountIn()==null || value.getCountIn()==0){
        	    				salesPerManList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getCountIn()).setScale(2,RoundingMode.UP);
        	    				salesPerManList.add(bg.doubleValue());
        	    			}
        	    			//票单价
        	    			if (value.getSales()==null || value.getTrades()==null || value.getTrades()==0){
        	    				salesPerTradesList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getTrades()).setScale(2,RoundingMode.UP);

        	    				salesPerTradesList.add(bg.doubleValue());
        	    			}
        	    			//票单量
        	    			if (value.getGoods()==null || value.getTrades()==null || value.getTrades()==0){
        	    				goodsPerTradesList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getGoods())/value.getTrades()).setScale(2,RoundingMode.UP);
        	    				goodsPerTradesList.add(bg.doubleValue());
        	    			}
        	    			//客单量
        	    			if (value.getGoods()==null || value.getCountIn()==null || value.getCountIn()==0){
        	    				goodsPerManList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getGoods())/value.getCountIn()).setScale(2,RoundingMode.UP);
        	    				goodsPerManList.add(bg.doubleValue());
        	    			}
        	    			//件单价
        	    			if (value.getSales()==null || value.getGoods()==null || value.getGoods()==0){
        	    				salesPerGoodsList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getGoods()).setScale(2,RoundingMode.UP);
        	    				salesPerGoodsList.add(bg.doubleValue());
        	    			}
        	    			//集客力
        	    			if (value.getCountIn()==null || value.getAcreage()==null || value.getAcreage()==0){
        	    				jikeliList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getCountIn())/value.getAcreage()).setScale(2,RoundingMode.UP);
        	    				jikeliList.add(bg.doubleValue());
        	    			}
        	    			//平效
        	    			if (value.getSales()==null || value.getAcreage()==null || value.getAcreage()==0){
        	    				pingxiaoList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getSales())/value.getAcreage()).setScale(2,RoundingMode.UP);
        	    				pingxiaoList.add(bg.doubleValue());
        	    			}
        	    			//顾客员工比
        	    			if (value.getCountIn()==null || value.getEmployeeNumber()==null || value.getEmployeeNumber()==0){
        	    				yuangongbiList.add(0.0);
        	    			} else {
            					BigDecimal bg = new BigDecimal(((double)value.getCountIn())/value.getEmployeeNumber()).setScale(2,RoundingMode.UP);
        	    				yuangongbiList.add(bg.doubleValue());
        	    			}
    	    			} else {
    	    				enterList.add(0);
    	    				exitList.add(0);
    	    				passbyList.add(0.00);
    	    				stayTimeList.add(0.00);
    	    				salesList.add(0.00);
    	    				tradesList.add(0);
    	    				goodsList.add(0);
    	    				rateList.add(0.00);
    	    				salesPerManList.add(0.00);
    	    				salesPerTradesList.add(0.00);
    	    				goodsPerTradesList.add(0.00);
    	    				goodsPerManList.add(0.00);
    	    				salesPerGoodsList.add(0.00);
    	    				jikeliList.add(0.00);
    	    				pingxiaoList.add(0.00);
    	    				yuangongbiList.add(0.00);
    	    			}
    	    		}
    	    		for (Map.Entry<String, Object> entry1:sonDataMap.entrySet()){
    	    			HomePageByLocation value = (HomePageByLocation)entry1.getValue();
    	    			dataIn.put("name", value.getLocationName());
    	    			dataOut.put("name", value.getLocationName());
    	    			dataPassby.put("name", value.getLocationName());
    	    			dataStayTime.put("name",value.getLocationName());
    	    			dataSales.put("name", value.getLocationName());
    	    			dataTrades.put("name", value.getLocationName());
    	    			dataGoods.put("name", value.getLocationName());
    	    			dataRate.put("name", value.getLocationName());
    	    			dataSalesPerMan.put("name", value.getLocationName());
    	    			dataSalesPerTrades.put("name", value.getLocationName());
    	    			dataGoodsPerTrades.put("name", value.getLocationName());
    	    			dataGoodsPerMan.put("name", value.getLocationName());
    	    			dataSalesPerGoods.put("name", value.getLocationName());
    	    			dataJikeli.put("name", value.getLocationName());
    	    			dataPingxiao.put("name", value.getLocationName());
    	    			dataYuangongbi.put("name", value.getLocationName());
    	    		}
    	    		dataIn.put("data", enterList);
    	    		dataOut.put("data", exitList);
    	    		dataPassby.put("data", passbyList);
    	    		dataStayTime.put("data", stayTimeList);
    	    		dataSales.put("data", salesList);
    	    		dataTrades.put("data", tradesList);
    	    		dataGoods.put("data", goodsList);
    	    		dataRate.put("data", rateList);
    	    		dataSalesPerMan.put("data", salesPerManList);
    	    		dataSalesPerTrades.put("data", salesPerTradesList);
    	    		dataGoodsPerTrades.put("data", goodsPerTradesList);
    	    		dataGoodsPerMan.put("data", goodsPerManList);
    	    		dataSalesPerGoods.put("data", salesPerGoodsList);
    	    		dataJikeli.put("data", jikeliList);
    	    		dataPingxiao.put("data", pingxiaoList);
    	    		dataYuangongbi.put("data", yuangongbiList);
    	    		
    	    		dataInList.add(dataIn);
    	    		dataOutList.add(dataOut);
    	    		dataPassbyList.add(dataPassby);
    	    		dataStayTimeList.add(dataStayTime);
    	    		dataSalesList.add(dataSales);
    	    		dataTradesList.add(dataTrades);
    	    		dataGoodsList.add(dataGoods);
    	    		dataRateList.add(dataRate);
    	    		dataSalesPerManList.add(dataSalesPerMan);
    	    		dataSalesPerTradesList.add(dataSalesPerTrades);
    	    		dataGoodsPerTradesList.add(dataGoodsPerTrades);
    	    		dataGoodsPerManList.add(dataGoodsPerMan);
    	    		dataSalesPerGoodsList.add(dataSalesPerGoods);
    	    		dataJikeliList.add(dataJikeli);
    	    		dataPingxiaoList.add(dataPingxiao);
    	    		dataYuangongbiList.add(dataYuangongbi); 	
    	    	}

    			if (cateId==11){
        			dataList.add(dataInList);
        			dataList.add(dataOutList);
    			} else if (cateId==12) {
    				dataList.add(dataPassbyList);
    			} else if (cateId==14) {
    				dataList.add(dataStayTimeList);
    			} else if (cateId==22) {
    				dataList.add(dataSalesList);
    				dataList.add(dataTradesList);
    				dataList.add(dataGoodsList);
    				dataList.add(dataRateList);
    				dataList.add(dataSalesPerManList);
    				dataList.add(dataSalesPerTradesList);
    				dataList.add(dataGoodsPerTradesList);
    				dataList.add(dataGoodsPerManList);
    				dataList.add(dataSalesPerGoodsList);
    				dataList.add(dataJikeliList);
    				dataList.add(dataPingxiaoList);
    				dataList.add(dataYuangongbiList);
    			}
    			resultMap.put("data", dataList);
    			//汇总数据
        		String names = request.getParameter("total");
        		String[] name = names.split(",");

        		Map<String,Object> result = new HashMap<String,Object>();
        		HashMap<String,Object> map = new HashMap<String,Object>();
        		map.put("locationType",filterId-8);
        		map.put("type",scaleMap.get(scale));
        		map.put("starttime", sdf.format(new Date(st)));
        		map.put("endtime", sdf.format(new Date(et)));
        		map.put("siteid", Integer.parseInt(site_name));
        		map.put("names", name);
        		ArrayList<Map<String,Object>> data1sourcelist = homePageMapper.getCollectLocationData(map);
        		HashMap<String,Object> noDataMap = new HashMap<String,Object>();
        		noDataMap.put("startTime", sdf.format(new Date(st)));
        		noDataMap.put("endTime",sdf.format(new Date(et)));
        		noDataMap.put("siteId",Integer.parseInt(site_name));
        		noDataMap.put("locationType", filterId-8);
        		noDataMap.put("total", "t");
        		noDataMap.put("findSite", 1);
        		noDataMap.put("names", name);
        		ArrayList<Map<String,Object>> data2sourceList = homePageMapper.getLocationNoTime(noDataMap);
        		noDataMap.put("total", "f");
        		ArrayList<Map<String,Object>> data3sourceList = homePageMapper.getLocationNoTime(noDataMap);
        		
        		Map<String,Map<String,Object>> data1sourceMap = new HashMap<String,Map<String,Object>>();
        		for (int i=0;i<data1sourcelist.size();i++){
        			Map<String, Object> value =data1sourcelist.get(i);
        			java.sql.Timestamp time = (java.sql.Timestamp)value.get("f_time");
        			Date date = new Date(time.getTime());
        			data1sourceMap.put(sdf1.format(date),value);
        		}
        		
        		ArrayList<Map<String,Object>> data1List = new ArrayList<Map<String,Object>>();
        		ArrayList<Map<String,Object>> data2List = new ArrayList<Map<String,Object>>();
        		ArrayList<Map<String,Object>> data3List = new ArrayList<Map<String,Object>>();
    			Map<String,Object> map1 = new HashMap<String,Object>();
    			Map<String,Object> map2 = new HashMap<String,Object>();
    			Map<String,Object> map3 = new HashMap<String,Object>();
    			Map<String,Object> map4 = new HashMap<String,Object>();
    			Map<String,Object> map5 = new HashMap<String,Object>();
    			Map<String,Object> map6 = new HashMap<String,Object>();
    			Map<String,Object> map7 = new HashMap<String,Object>();
    			Map<String,Object> map8 = new HashMap<String,Object>();
    			Map<String,Object> map9 = new HashMap<String,Object>();
    			Map<String,Object> map10 = new HashMap<String,Object>();
    			Map<String,Object> map11 = new HashMap<String,Object>();
    			Map<String,Object> map12 = new HashMap<String,Object>();
    			Map<String,Object> map13 = new HashMap<String,Object>();
    			Map<String,Object> map14 = new HashMap<String,Object>();
    			Map<String,Object> map15 = new HashMap<String,Object>();
    			Map<String,Object> map16 = new HashMap<String,Object>();
    			
    			map1.put("name", "进入流");
    			map2.put("name", "出入流");
    			map3.put("name", "入店率");
    			map4.put("name", "停留时间");
    			map5.put("name", "销售额");
    			map6.put("name", "交易数");
    			map7.put("name", "交易件数");
    			map8.put("name", "转化率");
    			map9.put("name", "客单价");
    			map10.put("name", "票单价");
    			map11.put("name", "票单量");
    			map12.put("name", "客单量");
    			map13.put("name", "件单价");
    			map14.put("name", "集客力");
    			map15.put("name", "平效");
    			map16.put("name","顾客员工比");
    			
    			ArrayList<Object> list1 = new ArrayList<Object>();
    			ArrayList<Object> list2 = new ArrayList<Object>();
    			ArrayList<Object> list3 = new ArrayList<Object>();
    			ArrayList<Object> list4 = new ArrayList<Object>();
    			ArrayList<Object> list5 = new ArrayList<Object>();
    			ArrayList<Object> list6 = new ArrayList<Object>();
    			ArrayList<Object> list7 = new ArrayList<Object>();
    			ArrayList<Object> list8 = new ArrayList<Object>();
    			ArrayList<Object> list9 = new ArrayList<Object>();
    			ArrayList<Object> list10 = new ArrayList<Object>();
    			ArrayList<Object> list11 = new ArrayList<Object>();
    			ArrayList<Object> list12 = new ArrayList<Object>();
    			ArrayList<Object> list13 = new ArrayList<Object>();
    			ArrayList<Object> list14 = new ArrayList<Object>();
    			ArrayList<Object> list15= new ArrayList<Object>();
    			ArrayList<Object> list16 = new ArrayList<Object>();

    			map1.put("data", list1);
    			map2.put("data", list2);
    			map3.put("data", list3);
    			map4.put("data", list4);
    			map5.put("data", list5);
    			map6.put("data", list6);
    			map7.put("data", list7);
    			map8.put("data", list8);
    			map9.put("data", list9);
    			map10.put("data", list10);
    			map11.put("data", list11);
    			map12.put("data", list12);
    			map13.put("data", list13);
    			map14.put("data", list14);
    			map15.put("data", list15);
    			map16.put("data", list16);
    			
    			Integer totalEnter = 0;
    			Integer totalExit = 0;
    			Integer totalPassby = 0;
    			Double totalStayTime = 0.00;
    			Double totalSales = 0.00;
    			Integer totalTrades = 0;
    			Integer totalGoods = 0;
    			Integer totalAcreage = 0;
    			Integer totalNumber = 0;
    			if (data2sourceList.get(0).get("enter")!=null){
        			totalEnter = Integer.parseInt(data2sourceList.get(0).get("enter").toString());
    			}
    			if (data2sourceList.get(0).get("exit")!=null){
    				totalExit = Integer.parseInt(data2sourceList.get(0).get("exit").toString());
    			}
    			if (data2sourceList.get(0).get("passby")!=null){
    				totalPassby = Integer.parseInt(data2sourceList.get(0).get("passby").toString());
    			}
    			if (data2sourceList.get(0).get("stay_time")!=null){
    				totalStayTime = Double.parseDouble(data2sourceList.get(0).get("stay_time").toString());
    			}
    			if (data2sourceList.get(0).get("sales")!=null){
    			totalSales = Double.parseDouble(data2sourceList.get(0).get("sales").toString());
    			}
    			if (data2sourceList.get(0).get("trades")!=null){
    				totalTrades = Integer.parseInt(data2sourceList.get(0).get("trades").toString());
    			}
    			if (data2sourceList.get(0).get("goods")!=null){
    				totalGoods = Integer.parseInt(data2sourceList.get(0).get("goods").toString());
    			}
    			if (data2sourceList.get(0).get("acreage")!=null){
    				totalAcreage = Integer.parseInt(data2sourceList.get(0).get("acreage").toString());
    			}
    			if (data2sourceList.get(0).get("employee_number")!=null){
    				totalNumber = Integer.parseInt(data2sourceList.get(0).get("employee_number").toString());
    			}
    			ArrayList<String> nameList = new ArrayList<String>();
    			if (cateId == 11){
        			data1List.add(map1);
        			data1List.add(map2);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData1.put("name", "进入流");
        			mapNoData1.put("data", new Integer[]{totalEnter});
        			mapNoData2.put("name", "出入流");
        			mapNoData2.put("data", new Integer[]{totalExit});
        			
        			Map<String,Object> mapNoData3 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData4 = new HashMap<String,Object>();
        			mapNoData3.put("name", "进入流");
        			mapNoData4.put("name", "出入流");
        			ArrayList<Integer> listNoData1 = new ArrayList<Integer>();
        			ArrayList<Integer> listNoData2 = new ArrayList<Integer>();
        			mapNoData3.put("data", listNoData1);
        			mapNoData4.put("data", listNoData2);
        			mapNoData3.put("categories", nameList);
        			mapNoData4.put("categories", nameList);
        			
        			for (int i=0;i<data3sourceList.size();i++){
        				listNoData1.add(Integer.parseInt(data3sourceList.get(i).get("enter").toString()));
        				listNoData2.add(Integer.parseInt(data3sourceList.get(i).get("exit").toString()));
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
        			
        			data2List.add(mapNoData1);
        			data2List.add(mapNoData2);
        			data3List.add(mapNoData3);
        			data3List.add(mapNoData4);
    			} else if (cateId == 12){
    				data1List.add(map3);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			mapNoData1.put("name", "入店率");
        			if (totalPassby!=0){
        				mapNoData1.put("data",new Double[]{new BigDecimal((double) totalEnter*100/totalPassby).setScale(2,RoundingMode.UP).doubleValue()});
        			}
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData2.put("name", "入店率");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			mapNoData2.put("data", listNoData1);
        			mapNoData2.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				if (Integer.parseInt(data3sourceList.get(i).get("passby").toString())!=0){
        					listNoData1.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())*100/Integer.parseInt(data3sourceList.get(i).get("passby").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
    				data2List.add(mapNoData1);
    				data3List.add(mapNoData2);
    			} else if (cateId == 14){
    				data1List.add(map4);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			mapNoData1.put("name", "停留时间");
        			mapNoData1.put("data", new Double[]{new BigDecimal(Math.abs(totalStayTime/60)).setScale(2,RoundingMode.UP).doubleValue()});
        			
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			mapNoData2.put("name", "停留时间");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			mapNoData2.put("data", listNoData1);
        			mapNoData2.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				if (Double.parseDouble(data3sourceList.get(i).get("stay_time").toString())!=0){
        					listNoData1.add(Math.abs(new BigDecimal(Double.parseDouble(data3sourceList.get(i).get("stay_time").toString())/60).setScale(2,RoundingMode.UP).doubleValue()));
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}
    				data2List.add(mapNoData1);
    				data3List.add(mapNoData2);
    			} else if (cateId == 22){
    				data1List.add(map5);
    				data1List.add(map6);
    				data1List.add(map7);
    				data1List.add(map8);
    				data1List.add(map9);
    				data1List.add(map10);
    				data1List.add(map11);
    				data1List.add(map12);
    				data1List.add(map13);
    				data1List.add(map14);
    				data1List.add(map15);
    				data1List.add(map16);
        			Map<String,Object> mapNoData1 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData2 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData3 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData4 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData5 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData6 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData7 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData8 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData9 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData10 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData11 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData12 = new HashMap<String,Object>();
        			mapNoData1.put("name", "销售额");
        			mapNoData2.put("name", "交易数");
        			mapNoData3.put("name", "交易件数");
        			mapNoData4.put("name", "转化率");
        			mapNoData5.put("name", "客单价");
        			mapNoData6.put("name", "票单价");
        			mapNoData7.put("name", "票单量");
        			mapNoData8.put("name", "客单量");
        			mapNoData9.put("name", "件单价");
        			mapNoData10.put("name", "集客力");
        			mapNoData11.put("name", "平效");
        			mapNoData12.put("name", "顾客员工比");
        			mapNoData1.put("data", new Double[]{new BigDecimal(totalSales).setScale(2,RoundingMode.UP).doubleValue()});
        			mapNoData2.put("data", new Integer[]{totalTrades});
        			mapNoData3.put("data", new Integer[]{totalGoods});
        			if (totalEnter!=0){
        				mapNoData4.put("data", new Double[]{new BigDecimal((double) totalTrades*100/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData4.put("data",0.00);
        			}
        			if (totalEnter!=0){
        				mapNoData5.put("data", new Double[]{new BigDecimal((double) totalSales/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData5.put("data",0.00);
        			}
        			if (totalTrades!=0){
        				mapNoData6.put("data", new Double[]{new BigDecimal((double) totalSales/totalTrades).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData6.put("data",0.00);
        			}
        			if (totalTrades!=0){
        				mapNoData7.put("data", new Double[]{new BigDecimal((double) totalGoods/totalTrades).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData7.put("data",0.00);
        			}
        			if (totalEnter!=0){
        				mapNoData8.put("data", new Double[]{new BigDecimal((double) totalGoods/totalEnter).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData8.put("data",0.00);
        			}
        			if (totalGoods!=0){
        				mapNoData9.put("data", new Double[]{new BigDecimal((double) totalSales/totalGoods).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData9.put("data",0.00);
        			}
        			if (totalAcreage!=0){
        				mapNoData10.put("data", new Double[]{new BigDecimal((double) totalEnter/totalAcreage).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData10.put("data",0.00);
        			}
        			if (totalAcreage!=0){
        				mapNoData11.put("data", new Double[]{new BigDecimal((double) totalSales/totalAcreage).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData11.put("data",0.00);
        			}
        			if (totalNumber!=0){
        				mapNoData12.put("data", new Double[]{new BigDecimal((double) totalEnter/totalNumber).setScale(2,RoundingMode.UP).doubleValue()});
        			} else {
        				mapNoData12.put("data",0.00);
        			}
        			
        			Map<String,Object> mapNoData13 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData14 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData15 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData16 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData17 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData18 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData19 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData20 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData21 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData22 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData23 = new HashMap<String,Object>();
        			Map<String,Object> mapNoData24 = new HashMap<String,Object>();
        			
        			mapNoData13.put("name", "销售额");
        			mapNoData14.put("name", "交易数");
        			mapNoData15.put("name", "交易件数");
        			mapNoData16.put("name", "转化率");
        			mapNoData17.put("name", "客单价");
        			mapNoData18.put("name", "票单价");
        			mapNoData19.put("name", "票单量");
        			mapNoData20.put("name", "客单量");
        			mapNoData21.put("name", "件单价");
        			mapNoData22.put("name", "集客力");
        			mapNoData23.put("name", "平效");
        			mapNoData24.put("name", "顾客员工比");
        			ArrayList<Double> listNoData1 = new ArrayList<Double>();
        			ArrayList<Integer> listNoData2 = new ArrayList<Integer>();
        			ArrayList<Integer> listNoData3 = new ArrayList<Integer>();
        			ArrayList<Double> listNoData4 = new ArrayList<Double>();
        			ArrayList<Double> listNoData5 = new ArrayList<Double>();
        			ArrayList<Double> listNoData6 = new ArrayList<Double>();
        			ArrayList<Double> listNoData7 = new ArrayList<Double>();
        			ArrayList<Double> listNoData8 = new ArrayList<Double>();
        			ArrayList<Double> listNoData9 = new ArrayList<Double>();
        			ArrayList<Double> listNoData10 = new ArrayList<Double>();
        			ArrayList<Double> listNoData11 = new ArrayList<Double>();
        			ArrayList<Double> listNoData12 = new ArrayList<Double>();
        			       			
        			mapNoData13.put("data", listNoData1);
        			mapNoData14.put("data", listNoData2);
        			mapNoData15.put("data", listNoData3);
        			mapNoData16.put("data", listNoData4);
        			mapNoData17.put("data", listNoData5);
        			mapNoData18.put("data", listNoData6);
        			mapNoData19.put("data", listNoData7);
        			mapNoData20.put("data", listNoData8);
        			mapNoData21.put("data", listNoData9);
        			mapNoData22.put("data", listNoData10);
        			mapNoData23.put("data", listNoData11);
        			mapNoData24.put("data", listNoData12);
        			
        			mapNoData13.put("categories", nameList);
        			mapNoData14.put("categories", nameList);
        			mapNoData15.put("categories", nameList);
        			mapNoData16.put("categories", nameList);
        			mapNoData17.put("categories", nameList);
        			mapNoData18.put("categories", nameList);
        			mapNoData19.put("categories", nameList);
        			mapNoData20.put("categories", nameList);
        			mapNoData21.put("categories", nameList);
        			mapNoData22.put("categories", nameList);
        			mapNoData23.put("categories", nameList);
        			mapNoData24.put("categories", nameList);
        			for (int i=0;i<data3sourceList.size();i++){
        				listNoData1.add(new BigDecimal(Double.parseDouble(data3sourceList.get(i).get("sales").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				listNoData2.add(Integer.parseInt(data3sourceList.get(i).get("trades").toString()));
        				listNoData3.add(Integer.parseInt(data3sourceList.get(i).get("goods").toString()));
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
        					listNoData4.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("trades").toString())*100/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
            				listNoData5.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("trades").toString())!=0){
        					listNoData6.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("trades").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("trades").toString())!=0){
        					listNoData7.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("goods").toString())/Integer.parseInt(data3sourceList.get(i).get("trades").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("enter").toString())!=0){
        					listNoData8.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("goods").toString())/Integer.parseInt(data3sourceList.get(i).get("enter").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("goods").toString())!=0){
        					listNoData9.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("goods").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("acreage").toString())!=0){
            				listNoData10.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())/Integer.parseInt(data3sourceList.get(i).get("acreage").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("acreage").toString())!=0){
            				listNoData11.add(new BigDecimal((double) Double.parseDouble(data3sourceList.get(i).get("sales").toString())/Integer.parseInt(data3sourceList.get(i).get("acreage").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				if (Integer.parseInt(data3sourceList.get(i).get("employee_number").toString())!=0){
            				listNoData12.add(new BigDecimal((double) Integer.parseInt(data3sourceList.get(i).get("enter").toString())/Integer.parseInt(data3sourceList.get(i).get("employee_number").toString())).setScale(2,RoundingMode.UP).doubleValue());
        				}
        				nameList.add(data3sourceList.get(i).get("display_name").toString());
        			}

    				data2List.add(mapNoData1);
    				data2List.add(mapNoData2);
    				data2List.add(mapNoData3);
    				data2List.add(mapNoData4);
    				data2List.add(mapNoData5);
    				data2List.add(mapNoData6);
    				data2List.add(mapNoData7);
    				data2List.add(mapNoData8);
    				data2List.add(mapNoData9);
    				data2List.add(mapNoData10);
    				data2List.add(mapNoData11);
    				data2List.add(mapNoData12);
    				
    				data3List.add(mapNoData13);
    				data3List.add(mapNoData14);
    				data3List.add(mapNoData15);
    				data3List.add(mapNoData16);
    				data3List.add(mapNoData17);
    				data3List.add(mapNoData18);
    				data3List.add(mapNoData19);
    				data3List.add(mapNoData20);
    				data3List.add(mapNoData21);
    				data3List.add(mapNoData22);
    				data3List.add(mapNoData23);
    				data3List.add(mapNoData24);
    			}

    			for (int i=0;i<categories.size();i++){
    				if (data1sourceMap.containsKey(categories.get(i))){
    					Map<String,Object> value = data1sourceMap.get(categories.get(i));
            			list1.add(value.get("enter"));
            			list2.add(value.get("exit"));
            			if (value.get("enter")==null || value.get("passby")==null || value.get("passby").equals(0)){
            				list3.add(0.00);
            			} else {
                			Double enter = Double.parseDouble(value.get("enter").toString());
                			Double passby = Double.parseDouble(value.get("passby").toString());
        					BigDecimal bg = new BigDecimal(enter*100/passby).setScale(2,RoundingMode.UP);
        					list3.add(bg.doubleValue());
            			}

    					if (value.get("stay_time")==null){
    						list4.add(0.00);
    					} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("stay_time").toString())/60).setScale(2,RoundingMode.UP);
    	    				list4.add(Math.abs(bg.doubleValue()));
    					}
    					
    					if (value.get("sales")==null){
    						list5.add(0.00);
    					} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())).setScale(2,RoundingMode.UP);
    	    				list5.add(bg.doubleValue());
    					}

    	    			if (value.get("trades")==null){
    	    				list6.add(0);
    	    			} else {
    	    				list6.add(value.get("trades"));
    	    			}
    	    			if (value.get("goods")==null){
    	    				list7.add(0);
    	    			} else {
    	    				list7.add(value.get("goods"));
    	    			}
    	    			//转化率
    	    			if (value.get("trades")==null || value.get("enter")==null || ((BigDecimal)value.get("enter")).equals(0)){
    	    				list8.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("trades").toString())/Double.parseDouble(value.get("enter").toString())*100).setScale(2,RoundingMode.UP);
    	    				list8.add(bg.doubleValue());
    	    			}
    	    			//客单价
    	    			if (value.get("sales")==null || value.get("enter")==null ||  ((BigDecimal)value.get("enter")).equals(0)){
    	    				list9.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())/Double.parseDouble(value.get("enter").toString())).setScale(2,RoundingMode.UP);
    	    				list9.add(bg.doubleValue());
    	    			}
    	    			//票单价
    	    			if (value.get("sales")==null || value.get("trades")==null || ((BigDecimal)value.get("trades")).equals(0)){
    	    				list10.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())/Double.parseDouble(value.get("trades").toString())).setScale(2,RoundingMode.UP);
    	    				list10.add(bg.doubleValue());
    	    			}
    	    			//票单量
    	    			if (value.get("goods")==null || value.get("trades")==null || ((BigDecimal)value.get("trades")).equals(0)){
    	    				list11.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("goods").toString())/Double.parseDouble(value.get("trades").toString())).setScale(2,RoundingMode.UP);
    	    				list11.add(bg.doubleValue());
    	    			}
    	    			//客单量
    	    			if (value.get("goods")==null || value.get("enter")==null || ((BigDecimal)value.get("enter")).equals(0)){
    	    				list12.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("goods").toString())/Double.parseDouble(value.get("enter").toString())).setScale(2,RoundingMode.UP);
    	    				list12.add(bg.doubleValue());
    	    			}
    	    			//件单价
    	    			if (value.get("sales")==null || value.get("goods")==null || ((BigDecimal)value.get("goods")).equals(0)){
    	    				list13.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())/Double.parseDouble(value.get("goods").toString())).setScale(2,RoundingMode.UP);
    	    				list13.add(bg.doubleValue());
    	    			}
    	    			//集客力
    	    			if (value.get("enter")==null || value.get("acreage")==null || value.get("acreage").equals(0)){
    	    				list14.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("enter").toString())/Double.parseDouble(value.get("acreage").toString())).setScale(2,RoundingMode.UP);
    	    				list14.add(bg.doubleValue());
    	    			}
    	    			//平效
    	    			if (value.get("sales")==null || value.get("acreage")==null || value.get("acreage").equals(0)){
    	    				list15.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("sales").toString())/Double.parseDouble(value.get("acreage").toString())).setScale(2,RoundingMode.UP);
    	    				list15.add(bg.doubleValue());
    	    			}
    	    			//顾客员工比
    	    			if (value.get("enter")==null || value.get("employee_number")==null || value.get("employee_number").equals(0)){
    	    				list16.add(0.0);
    	    			} else {
        					BigDecimal bg = new BigDecimal(Double.parseDouble(value.get("enter").toString())/Double.parseDouble(value.get("employee_number").toString())).setScale(2,RoundingMode.UP);
    	    				list16.add(bg.doubleValue());
    	    			}
    				} else {
    					list1.add(0);
    					list2.add(0);
    					list3.add(0.00);
    					list4.add(0.00);
    					list5.add(0.00);
    				}
    			}
        		resultMap.put("data1", data1List);
        		resultMap.put("data2", data2List);
        		resultMap.put("data3", data3List);
    			ArrayList<String> dataType = new ArrayList<String>();
    			//添加dataType
    			if (cateId==11){
        			dataType.add("进入流");
        			dataType.add("出入流");
    			} else if (cateId == 12){
    				dataType.add("入店率");
    			} else if (cateId == 14){
    				dataType.add("停留时间");
    			} else if (cateId == 22){
    				dataType.add("销售额");
    				dataType.add("交易数");
    				dataType.add("交易件数");
    				dataType.add("转化率");
    				dataType.add("客单价");
    				dataType.add("票单价");
    				dataType.add("票单量");
    				dataType.add("客单量");
    				dataType.add("件单价");
    				dataType.add("集客力");
    				dataType.add("平效");
    				dataType.add("顾客员工比");
    			}
    			resultMap.put("dataType", dataType);
    			Map<String,Object> parameterMap = new HashMap<String,Object>();
    			parameterMap.put("findSite", 1);
    			parameterMap.put("siteId", Integer.parseInt(site_name));
    			parameterMap.put("startTime", sdf.format(new Date(st)));
    			parameterMap.put("endTime", sdf.format(new Date(et)));
    			resultMap.put("periodSubjectAndHoliday", homePageMapper.getPeriodSubjectAndHoliday(parameterMap));
    			return ReturnMapUtil.packData(resultMap);
    		}
    	}
    	if (cateId == 13){
			Map<String,Object> resultMap = new HashMap<String,Object>();
			ArrayList<ArrayList<Integer>> dataList = new ArrayList<ArrayList<Integer>>();
			if (filterId == 0){
				ArrayList<HomePage> list = homePageService.getTotalData(false, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), "dh","f");
				for (int i=0;i<list.size();i++){
					ArrayList<Integer> data = new ArrayList<Integer>();
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdf.parse(list.get(i).getTime()));

					Integer weekDay = 0;
					if(cal.get(Calendar.DAY_OF_WEEK) == 0){
						weekDay = 5;
					} else if (cal.get(Calendar.DAY_OF_WEEK) == 1){
						weekDay = 6;
					} else {
						weekDay = cal.get(Calendar.DAY_OF_WEEK) - 2;
					}
					data.add(weekDay);
					data.add(cal.get(Calendar.HOUR_OF_DAY));
					data.add(list.get(i).getEnter());
					dataList.add(data);
				}
				resultMap.put("name","汇总");
				resultMap.put("data", dataList);
    			return ReturnMapUtil.packData(resultMap);
			} else 
			if (filterId == 1){
    			ArrayList<HomePageByZonetype> list = homePageService.getHomePagebyZonetype(false, Integer.parseInt(site_name), sdf.format(new Date(st)), sdf.format(new Date(et)), "dh",1);
				String sitezoneName= request.getParameter("sitezoneName");
    			for (int i=0;i<list.size();i++){
    				if (!sitezoneName.equals(list.get(i).getZoneName())){
    					continue;
    				}
					ArrayList<Integer> data = new ArrayList<Integer>();
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdf.parse(list.get(i).getTime()));
					Integer weekDay = 0;
					if(cal.get(Calendar.DAY_OF_WEEK) == 0){
						weekDay = 5;
					} else if (cal.get(Calendar.DAY_OF_WEEK) == 1){
						weekDay = 6;
					} else {
						weekDay = cal.get(Calendar.DAY_OF_WEEK) - 2;
					}
					data.add(weekDay);
					data.add(cal.get(Calendar.HOUR_OF_DAY));
					data.add(list.get(i).getCountIn());
					dataList.add(data);
				}
				resultMap.put("name","出入口");
				resultMap.put("data", dataList);
    			return ReturnMapUtil.packData(resultMap);
			}
			
    	}
		if (cateId == 21){
			switch (filterId) {
			case 0:
        		HashMap<String,Object> map = new HashMap<String,Object>();
        		//当前时间段内数据总和
        		map.put("type","d");
        		map.put("starttime", sdf.format(new Date(st)));
        		map.put("endtime", sdf.format(new Date(et)));
        		map.put("siteid", Integer.parseInt(site_name));
        		map.put("names", Integer.parseInt(site_name));
        		map.put("findSite",1);
        		ArrayList<Map<String,Object>> data1sourcelist = homePageMapper.getCollectData(map);
        		//过去时间
        		Date startTime = new Date(st);
        		Date endTime = new Date(et);
        		Calendar calendar = Calendar.getInstance();
        		calendar.setTime(startTime);
        		calendar.add(Calendar.DATE, -7);
        		Date lStartTime = calendar.getTime();
        		calendar.setTime(endTime);
        		calendar.add(Calendar.DATE, -7);
        		Date lEndTime = calendar.getTime();
        		//过去时间段内数据总和
        		map.put("type","d");
        		map.put("starttime", sdf.format(lStartTime));
        		map.put("endtime", sdf.format(lEndTime));
        		map.put("siteid", Integer.parseInt(site_name));
        		map.put("names", Integer.parseInt(site_name));
        		map.put("findSite",1);
        		ArrayList<Map<String,Object>> data2sourcelist = homePageMapper.getCollectData(map);
        		//数据定义
        		Integer nowPassby = 0;
        		Integer lastPassby = 0;
        		Integer nowEnter = 0;
        		Integer lastEnter = 0;
        		Integer nowTrades = 0;
        		Integer lastTrades = 0;
        		for (int i=0;i<data1sourcelist.size();i++){
        			if (data1sourcelist.get(i).get("passby") !=null){
            			nowPassby +=Integer.parseInt(data1sourcelist.get(i).get("passby").toString());
        			}
        			if (data1sourcelist.get(i).get("enter") !=null){
            			nowEnter +=Integer.parseInt(data1sourcelist.get(i).get("enter").toString());
        			}
        			if (data1sourcelist.get(i).get("trades") !=null){
            			nowTrades +=Integer.parseInt(data1sourcelist.get(i).get("trades").toString());
        			}
        		}
        		for (int i=0;i<data2sourcelist.size();i++){
        			if (data2sourcelist.get(i).get("passby") !=null){
            			lastPassby +=Integer.parseInt(data2sourcelist.get(i).get("passby").toString());
        			}
        			if (data2sourcelist.get(i).get("enter") !=null){
            			lastEnter +=Integer.parseInt(data2sourcelist.get(i).get("enter").toString());
        			}
        			if (data2sourcelist.get(i).get("trades") !=null){
            			lastTrades +=Integer.parseInt(data2sourcelist.get(i).get("trades").toString());
        			}
        		}
        		ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        		Map<String,Object> map1 = new HashMap<String,Object>();
        		map1.put("name", "passby");
        		map1.put("value", nowPassby);
        		if (lastPassby != 0){
            		map1.put("change", (double)(nowPassby-lastPassby)*100/lastPassby);
        		} else {
        			map1.put("change", 0);
        		}
        		Map<String,Object> map2 = new HashMap<String,Object>();
        		map2.put("name", "enter");
        		map2.put("value", nowEnter);
        		if (lastEnter != 0){
        			map2.put("change", (double)(nowEnter-lastEnter)*100/lastEnter);
        		} else {
        			map2.put("change", 0);
        		}
        		if (nowPassby !=0){
            		map1.put("conversion", (double)nowEnter*100/nowPassby);
        		} else {
        			map1.put("conversion", 0);
        		}
        		Map<String,Object> map3 = new HashMap<String,Object>();
        		map3.put("name", "trades");
        		map3.put("value", nowTrades);
        		if (lastTrades != 0){
        			map3.put("change", (double)(nowTrades-lastTrades)*100/lastTrades);
        		} else {
        			map3.put("change", 0);
        		}
           		if (nowEnter !=0){
            		map2.put("conversion", (double)nowTrades*100/nowEnter);
        		} else {
            		map2.put("conversion", 0);
        		}
           		map3.put("conversion",0);
           		resultList.add(map1);
           		resultList.add(map2);
           		resultList.add(map3);
            	return ReturnMapUtil.packData(resultList);
			}
		}
    	return ReturnMapUtil.packData(null);
    }
    
    
    
    public ArrayList<String> sort(Map<String,String> map){
    	ArrayList<String> list = new ArrayList<String>();
    	for (Map.Entry<String, String> entry:map.entrySet()){
    		list.add(entry.getKey());
    	}
    	String temp;
    	for (int i=0;i<list.size();i++){
    		for (int j=i;j<list.size();j++){
    			if (list.get(i).compareTo(list.get(j))>0){
    				temp = list.get(i);
    				list.set(i, list.get(j));
    				list.set(j, temp);
    			}
    		}
    	}
    	return list;
    }
}
