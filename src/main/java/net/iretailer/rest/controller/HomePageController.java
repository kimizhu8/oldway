package net.iretailer.rest.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import net.iretailer.rest.model.OpeningTimeTmp;
import net.iretailer.rest.model.OpeningTimeTmpKey;
import net.iretailer.rest.service.HomePageService;
import net.iretailer.rest.service.OpeningTimeTmpService;
import net.iretailer.rest.service.RoleService;
import net.iretailer.rest.service.SiteService;
import net.iretailer.rest.service.impl.HomePageThreadServiceImpl;
import net.iretailer.rest.util.ReturnMapUtil;

@RestController
public class HomePageController {
		
	@Autowired
	private HomePageService homePageService;
	
	@Autowired
	private HomePageMapper homePageMapper;
	
	@Autowired
	private OpeningTimeTmpService openingTimeTmpService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HomePageThreadServiceImpl homepageThreadService;
	
	@Autowired
	private RoleService roleService;
	
	Map<Integer,Object> scaleTimeLine = new HashMap<Integer,Object>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0,new SimpleDateFormat("HH:mm"));
			put(1,new SimpleDateFormat("MM-dd"));
			put(2,new SimpleDateFormat("yyyy-MM-dd"));
			put(3,new SimpleDateFormat("MM-dd"));
			put(4,new SimpleDateFormat("HH:mm:ss"));
			put(5,new SimpleDateFormat("HH:mm:ss"));
			put(6,new SimpleDateFormat("HH:mm"));
			put(7,new SimpleDateFormat("HH:mm"));
			put(8,new SimpleDateFormat("HH:mm"));
		}
	};
	
	@RequestMapping("/getCommonHome")
	public Map<String,Object> getCommonHome(@RequestParam long st,@RequestParam long et,@RequestParam String siteName ) throws ParseException, InterruptedException, ExecutionException{
		if (!roleService.blockRole(request, Integer.parseInt(siteName),10)) return ReturnMapUtil.packData("恶意登录");
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfDateWOHMS = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("0.00");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		Integer siteId = Integer.parseInt(siteName);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		String todayStart = sdfDateWOHMS.format(date);
		String todayEnd = sdfDateWOHMS.format(cal.getTime());
		
		String comparisonStart = "";
		String comparisonEnd = "";
		if (st == 0) {
			cal.setTime(cal.getTime());
			cal.add(Calendar.DATE, -7);
			comparisonStart = sdfDate.format(cal.getTime());
			cal.add(Calendar.DATE, 1);
			comparisonEnd = sdfDate.format(cal.getTime());
		} else {
			cal.setTimeInMillis(st);
			comparisonStart = sdfDate.format(cal.getTime());
			cal.add(Calendar.DATE, 1);
			comparisonEnd = sdfDate.format(cal.getTime());
		}
		ArrayList<Object> list = homepageThreadService.getHomePage(siteId, todayStart, todayEnd, comparisonStart, comparisonEnd);
		//今天的数据
		HomePage todayHomePage = (HomePage)list.get(0);
		//昨天的数据
		HomePage comparisonHomePage = (HomePage)list.get(1);
		
		ArrayList<HomePageByLocation> todayHomePageByLocation = (ArrayList<HomePageByLocation>) list.get(2);
		
		ArrayList<HomePageByLocation> comparisonHomePageByLocation = (ArrayList<HomePageByLocation>) list.get(3);
		Map<String,Object> selfCallback =getLocationSalesForThread(todayHomePageByLocation,comparisonHomePageByLocation);
//				getLocationSales(st,et,siteName,0);
//		//今天的数据
//		HomePage todayHomePage = homePageService.getHomePage(false, siteId, todayStart, todayEnd, "d");
//		//比较天数的数据
//		HomePage comparisonHomePage = homePageService.getHomePage(false, siteId, comparisonStart, comparisonEnd, "d");
		Map<String,Object> custom = new HashMap<String,Object>();
		Map<String,Object> sales = new HashMap<String,Object>();
		Map<String,Object> stayMap = new HashMap<String,Object>();
		Map<String,Object> areaMap = new HashMap<String,Object>();
		Map<String,Object> salesPerManMap = new HashMap<String,Object>();
		ArrayList<Map<String,Object>> actions = new ArrayList<Map<String,Object>>();

		//整理数据
		Integer nowEnter = 0;
		Integer compareEnter = 0;
		Integer nowPassby = 0;
		Double nowSales = 0.00;
		Double compareSales = 0.00;
		Integer nowTrades = 0;
		Integer nowExit = 0;
		Integer compareExit = 0;
		Integer nowAccreage = 0;
		Integer compareAccreage = 0;

		if (todayHomePage.getEnter()!=null){
			nowEnter = todayHomePage.getEnter();
		}
		if (todayHomePage.getPassby()!=null){
			nowPassby = todayHomePage.getPassby();
		}
		if (todayHomePage.getSales()!=null){
			nowSales = todayHomePage.getSales();
		}
		if (todayHomePage.getTrades()!=null){
			nowTrades = todayHomePage.getTrades();
		}
		if (todayHomePage.getExit()!=null){
			nowExit = todayHomePage.getExit();
		}
		if (todayHomePage.getAcreage()!=null){
			nowAccreage = todayHomePage.getAcreage();
		}
		if (comparisonHomePage.getEnter()!=null){
			compareEnter = comparisonHomePage.getEnter();
		}
		if (comparisonHomePage.getSales()!=null){
			compareSales = comparisonHomePage.getSales();
		}
		if (comparisonHomePage.getExit()!=null){
			compareExit = comparisonHomePage.getExit();
		}
		if (comparisonHomePage.getAcreage()!=null){
			compareAccreage = comparisonHomePage.getAcreage();
		}


		//插入数据
		//客流数据
		custom.put("countIn", (todayHomePage.getEnter()==null)?0:todayHomePage.getEnter());
		String compareRatio = "0.00";
		if (compareEnter!=0){
			compareRatio = df.format((double)(nowEnter-compareEnter)*100/compareEnter); 
		}
		custom.put("compareRatio", compareRatio);
		String entryRatio = "0.00";
		if (nowPassby!=0){
			entryRatio = df.format((double)nowEnter*100/nowPassby);
		}
		custom.put("entryRatio", entryRatio);
		custom.put("passby", (todayHomePage.getPassby()==null)?0:todayHomePage.getPassby());
		resultMap.put("custom", custom);
		//销售数据
		sales.put("num", df.format(nowSales));
		String change = "0.00";
		if (compareSales!=0){
			change = df.format((double)(nowSales-compareSales)*100/compareSales);
		}
		sales.put("change", change);
		String sign = "0.00";
		if (nowEnter!=0){
			sign = df.format((double)nowSales/nowEnter);
		}
		sales.put("sign", sign);
		String transform = "0.00";
		if (nowEnter!=0){
			transform = df.format((double)nowTrades*100/nowEnter);
		}
		sales.put("transform", transform);
		resultMap.put("sales", sales);
		//停留人数数据
		stayMap.put("name", "停留人数");
		stayMap.put("summary", "人");
		stayMap.put("num",Math.abs(nowEnter-nowExit));
		stayMap.put("id", 4);
		Integer todayStay = Math.abs(nowEnter-nowExit);
		Integer comparisonStay = Math.abs(compareEnter-compareExit);
		if (comparisonStay!=0){
			change = df.format((todayStay-comparisonStay)*100/comparisonStay);
		}
		stayMap.put("change",change);
		//集客力
		areaMap.put("name", "集客力");
		areaMap.put("summary", "/㎡");
		areaMap.put("id", 5);
		String todayAcreage = "0.00";
		if (nowAccreage!=0){
			todayAcreage = df.format((double)nowEnter/nowAccreage);
		}

		if (compareEnter !=0 && compareAccreage!=0 && nowAccreage !=0){
			change = df.format(((double)nowEnter/nowAccreage-(double)compareEnter/compareAccreage)*100/((double)compareEnter/compareAccreage));
		}
		areaMap.put("num",todayAcreage);
		areaMap.put("change", change);
		//客单价
		salesPerManMap.put("name", "客单价");
		salesPerManMap.put("summary", "￥");
		salesPerManMap.put("id", 6);
		String salesPerMan = "0.00";
		change = "0.00";
		if (nowEnter!=0 && compareEnter!=0){
			salesPerMan = df.format(nowSales/nowEnter);
			change = df.format(((double)nowSales/nowEnter-(double)compareSales/compareEnter)*100/((double)compareSales/compareEnter));
		}
		salesPerManMap.put("num", salesPerMan);
		salesPerManMap.put("change", change);
		//添加action
		actions.add(stayMap);
		actions.add(areaMap);
		actions.add(salesPerManMap);
		resultMap.put("actions",actions);
		
		//存疑 等会儿看

		@SuppressWarnings("unchecked")
		ArrayList<Map<String,Object>> rate = (ArrayList<Map<String,Object>>)selfCallback.get("data");
		resultMap.put("rate", rate);
		return ReturnMapUtil.packData(resultMap);
	}
	
    @RequestMapping("/getLocationSales")
    public Map<String,Object> getLocationSales(@RequestParam long st,@RequestParam long et,@RequestParam String siteName,@RequestParam int type) throws ParseException{
		if (!roleService.blockRole(request, Integer.parseInt(siteName),10)) return ReturnMapUtil.packData("恶意登录");
    	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfDateWOHMS = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("0.00");
		
		Integer siteId = Integer.parseInt(siteName);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer hour = cal.get(Calendar.HOUR_OF_DAY);
		Integer minute = cal.get(Calendar.MINUTE);
		Integer second = cal.get(Calendar.SECOND);
		cal.add(Calendar.DATE, 1);
		String todayStart = sdfDateWOHMS.format(date);
		String todayEnd = sdfDateWOHMS.format(cal.getTime());
		
		String comparisonStart = "";
		String comparisonEnd = "";
		if (st == 0) {
			cal.setTime(cal.getTime());
			cal.add(Calendar.DATE, -7);
			comparisonStart = sdfDate.format(cal.getTime());
			cal.add(Calendar.DATE, 1);
			cal.add(Calendar.HOUR_OF_DAY, hour);
			cal.add(Calendar.MINUTE, minute);
			cal.add(Calendar.SECOND, second);
			comparisonEnd = sdfDate.format(cal.getTime());
		} else {
			cal.setTimeInMillis(st);
			comparisonStart = sdfDate.format(cal.getTime());
			cal.add(Calendar.DATE, 1);
			cal.add(Calendar.HOUR_OF_DAY, hour);
			cal.add(Calendar.MINUTE, minute);
			cal.add(Calendar.SECOND, second);
			comparisonEnd = sdfDate.format(cal.getTime());
		}
		Byte siteType = siteService.testSelect(Short.parseShort(siteName)).getType();
		ArrayList<HomePageByLocation> todayHomePage = null;
		ArrayList<HomePageByLocation> comparisonHomePage = null;
		if (siteType==0 || siteType==1 || siteType==3){
			//今天的数据
			todayHomePage = homePageService.getHomePageByLocation(false, siteId, todayStart, todayEnd, "d",type);
			//比较天数的数据
			comparisonHomePage = homePageService.getHomePageByLocation(false, siteId, comparisonStart, comparisonEnd, "d",type);
		} else {
			//今天的数据
			todayHomePage = homePageService.getHomePageByLocation(true, siteId, todayStart, todayEnd, "d",type);
			//比较天数的数据
			comparisonHomePage = homePageService.getHomePageByLocation(true, siteId, comparisonStart, comparisonEnd, "d",type);
	
		}

		ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		Map<String,Double> nowMap = new HashMap<String,Double>();
		Map<String,Double> lastMap = new HashMap<String,Double>();
		
		for (int i=0;i<todayHomePage.size();i++){
			if (todayHomePage.get(i).getSales()!=null&&todayHomePage.get(i).getLocationName()!=null){
				nowMap.put(todayHomePage.get(i).getLocationName(), todayHomePage.get(i).getSales());
			}
		}
		for (int i=0;i<comparisonHomePage.size();i++){
			lastMap.put(comparisonHomePage.get(i).getLocationName(), comparisonHomePage.get(i).getSales());
		}
		String maxKey = "";
		ArrayList<Map.Entry<String,Double>> l = new ArrayList<Map.Entry<String,Double>>(nowMap.entrySet());    
        Collections.sort(l, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
                double delta = (o2.getValue() - o1.getValue());
                if(delta > 0) return 1;
                else if(delta == 0) return 0;
                else return -1;
            }      
        });
        
        int len = 5> l.size()? l.size():5;
		for (int i = 0; i < len; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", l.get(i).getKey());
			map.put("num", df.format(l.get(i).getValue()));
			String change = "0.00";
			if (lastMap.get(maxKey) != null && lastMap.get(maxKey) != 0) {
				change = df.format((nowMap.get(maxKey) - lastMap.get(maxKey)) / lastMap.get(maxKey));
			}
			map.put("change", change);
			resultList.add(map);
		}
		return ReturnMapUtil.packData(resultList);
    }

    @RequestMapping("/getMainPageTagTypeSales")
    public Map<String,Object> getMainPageTagTypeSales(@RequestParam long st,@RequestParam long et,@RequestParam String siteName) throws ParseException{
    	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfDateWOHMS = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("0.00");
		
		Integer siteId = Integer.parseInt(siteName);
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer hour = cal.get(Calendar.HOUR_OF_DAY);
		Integer minute = cal.get(Calendar.MINUTE);
		Integer second = cal.get(Calendar.SECOND);
		cal.add(Calendar.DATE, 1);
		String todayStart = sdfDateWOHMS.format(date);
		String todayEnd = sdfDateWOHMS.format(cal.getTime());
		
		String comparisonStart = "";
		String comparisonEnd = "";
		if (st == 0) {
			cal.setTime(cal.getTime());
			cal.add(Calendar.DATE, -7);
			comparisonStart = sdfDate.format(cal.getTime());
			cal.add(Calendar.DATE, 1);
			cal.add(Calendar.HOUR_OF_DAY, hour);
			cal.add(Calendar.MINUTE, minute);
			cal.add(Calendar.SECOND, second);
			comparisonEnd = sdfDate.format(cal.getTime());
		} else {
			cal.setTimeInMillis(st);
			comparisonStart = sdfDate.format(cal.getTime());
			cal.add(Calendar.DATE, 1);
			cal.add(Calendar.HOUR_OF_DAY, hour);
			cal.add(Calendar.MINUTE, minute);
			cal.add(Calendar.SECOND, second);
			comparisonEnd = sdfDate.format(cal.getTime());
		}
		Byte siteType = siteService.testSelect(Short.parseShort(siteName)).getType();
		ArrayList<HomePageByTag> todayHomePage = null;
		ArrayList<HomePageByTag> comparisonHomePage = null;
		if (siteType==0 || siteType==1 || siteType==3){
			//今天的数据
			todayHomePage = homePageService.getHomePagebyTag(false, siteId, todayStart, todayEnd, "d","首页");
			//比较天数的数据
			comparisonHomePage = homePageService.getHomePagebyTag(false, siteId, comparisonStart, comparisonEnd, "d","首页");
		} else {
			//今天的数据
			todayHomePage = homePageService.getHomePagebyTag(true, siteId, todayStart, todayEnd, "d","首页");
			//比较天数的数据
			comparisonHomePage = homePageService.getHomePagebyTag(true, siteId, comparisonStart, comparisonEnd, "d","首页");
	
		}

		ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		Map<String,Double> nowMap = new HashMap<String,Double>();
		Map<String,Double> lastMap = new HashMap<String,Double>();
		
		for (int i=0;i<todayHomePage.size();i++){
			if (todayHomePage.get(i).getSales()!=null&&todayHomePage.get(i).getTagValue()!=null){
				nowMap.put(todayHomePage.get(i).getTagValue(), todayHomePage.get(i).getSales());
			}
		}
		for (int i=0;i<comparisonHomePage.size();i++){
			lastMap.put(comparisonHomePage.get(i).getTagValue(), comparisonHomePage.get(i).getSales());
		}
		String maxKey = "";
		ArrayList<Map.Entry<String,Double>> l = new ArrayList<Map.Entry<String,Double>>(nowMap.entrySet());    
        Collections.sort(l, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
                double delta = (o2.getValue() - o1.getValue());
                if(delta > 0) return 1;
                else if(delta == 0) return 0;
                else return -1;
            }      
        });
        
		for (int i = 0; i < l.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", l.get(i).getKey());
			map.put("num", df.format(l.get(i).getValue()));
			String change = "0.00";
			if (lastMap.get(maxKey) != null && lastMap.get(maxKey) != 0) {
				change = df.format((nowMap.get(maxKey) - lastMap.get(maxKey)) / lastMap.get(maxKey));
			}
			map.put("change", change);
			resultList.add(map);
		}
		return ReturnMapUtil.packData(resultList);
    }
    
    public Map<String,Object> getLocationSalesForThread(ArrayList<HomePageByLocation> todayHomePage,ArrayList<HomePageByLocation> comparisonHomePage) throws ParseException{
		DecimalFormat df = new DecimalFormat("0.00");
		ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		Map<String,Double> nowMap = new HashMap<String,Double>();
		Map<String,Double> lastMap = new HashMap<String,Double>();
		
		for (int i=0;i<todayHomePage.size();i++){
			if (todayHomePage.get(i).getSales()!=null&&todayHomePage.get(i).getLocationName()!=null){
				nowMap.put(todayHomePage.get(i).getLocationName(), todayHomePage.get(i).getSales());
			}
		}
		for (int i=0;i<comparisonHomePage.size();i++){
			lastMap.put(comparisonHomePage.get(i).getLocationName(), comparisonHomePage.get(i).getSales());
		}
		String maxKey = "";
		ArrayList<Map.Entry<String,Double>> l = new ArrayList<Map.Entry<String,Double>>(nowMap.entrySet());    
        Collections.sort(l, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
                double delta = (o2.getValue() - o1.getValue());
                if(delta > 0) return 1;
                else if(delta == 0) return 0;
                else return -1;
            }      
        });
        
        int len = 5> l.size()? l.size():5;
		for (int i = 0; i < len; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", l.get(i).getKey());
			map.put("num", df.format(l.get(i).getValue()));
			String change = "0.00";
			if (lastMap.get(maxKey) != null && lastMap.get(maxKey) != 0) {
				change = df.format((nowMap.get(maxKey) - lastMap.get(maxKey)) / lastMap.get(maxKey));
			}
			map.put("change", change);
			resultList.add(map);
		}
		return ReturnMapUtil.packData(resultList);
    }
	
    @RequestMapping("/getSiteZoneColumn")
    public Map<String,Object> getSiteZoneColumn(@RequestParam String siteName,@RequestParam long st,@RequestParam long et,@RequestParam int scale,@RequestParam int sitezoneType){
    	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    	String sitezoneTypeName = null;
		switch (sitezoneType){
		case 0:
			sitezoneTypeName="主出入口";
		case 1:
			sitezoneTypeName="出入口";
		case 2:
			sitezoneTypeName="通道";
		case 3:
			sitezoneTypeName="楼层";
		case 4:
			sitezoneTypeName="区域";
		}
		st = st/1000 + 28800;
		et = et/1000 + 28800;
		ArrayList<HomePageByZonetype> list = homePageService.getHomePagebyZonetype(false, Integer.parseInt(siteName), sdfDate.format(new Date(st)), sdfDate.format(new Date(et)), "d",0);
		
		ArrayList<String> categories = new ArrayList<String>();
		ArrayList<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			HomePageByZonetype model = list.get(i);
			if (model.getZoneName().equals(sitezoneType + "")) {
				categories.add(model.getZoneType());
				data.add(model.getCountIn());
			}
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("category", categories);
		Map<String,Object> resultDataModel = new HashMap<String,Object>();
		resultDataModel.put("name", sitezoneTypeName);
		resultDataModel.put("data", data);
		result.put("data", resultDataModel);
		return ReturnMapUtil.packData(result);
    }
    
    @RequestMapping("/getBaseInformation")
    public Map<String,Object> getBaseInformation(@RequestParam String siteName) throws IOException, ParseException {
		if (!roleService.blockRole(request, Integer.parseInt(siteName),10)) return ReturnMapUtil.packData("恶意登录");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
    	String date = sdf.format(new Date());
    	String line = "";
    	String weather = "";
    	String holiday = "";
//    	String urlString = "http://api.map.baidu.com/telematics/v3/weather?location=%E4%B8%8A%E6%B5%B7&output=json&ak=1cU6UvzVVGCfYbeDMua8XEzP";
//    	URL url = new URL(urlString);
//    	URLConnection connection = url.openConnection();
//    	connection.connect();
//    	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
//    	String line = "";
//    	String weather = "";
//    	String holiday = "";
//    	while ((line = in.readLine())!=null){
//    		weather +=line;
//    	}
//    	in.close();
    	HashMap<String,Object> map = new HashMap<String,Object>();
    	map.put("siteId", Integer.parseInt(siteName));
    	map.put("date", sdfDate.format(new Date()));
    	ArrayList<Map<String,Object>> weatherList = homePageMapper.getWeather(map);
    	if (weatherList.size()==0){
    		weather = "";
    	} else {
        	String nowTemp = weatherList.get(0).get("now_temp").toString();
        	String type = weatherList.get(0).get("type").toString();
        	String windLevel =weatherList.get(0).get("wind_level").toString();
        	weather = type + "/" + nowTemp + "℃/" + windLevel;
    	}

    	ArrayList<Map<String,Object>> subjectList = homePageMapper.getSubject(map);
    	String subject = "";
    	if (subjectList.size()==0){
    		subject = "无";
    	} else {
        	for (int i=0;i<subjectList.size()-1;i++){
        		subject += subjectList.get(i).get("name").toString() + "/";
        	}
    		subject += subjectList.get(subjectList.size()-1).get("name").toString();
    	}
	
    	ArrayList<Map<String,Object>> holidayList = homePageMapper.getHoliday(map);
    	if (holidayList.size()==0){
    		holiday = "无";
    	} else {
    		holiday = "";
        	for (int i=0;i<holidayList.size()-1;i++){
        		holiday += holidayList.get(i).get("name").toString() + "/";
        	}
        	holiday += holidayList.get(holidayList.size()-1).get("name").toString();
    	}

    	Map<String,Object> baseInformation = new HashMap<String,Object>();
    	baseInformation.put("date", date);
    	baseInformation.put("weather",weather);
    	baseInformation.put("holiday", holiday);
    	baseInformation.put("subject", subject);
    	OpeningTimeTmpKey key = new OpeningTimeTmpKey();
    	key.setFkSiteId(Short.parseShort(siteName));
    	key.setDate(sdfDate.parse(sdfDate.format(new Date())));
    	OpeningTimeTmp openingTime = openingTimeTmpService.getOpeningTimeByDate(key);
    	if (openingTime==null){
        	baseInformation.put("startTime", "startTime");
        	baseInformation.put("endTime", "endTime");
    	} else {
        	baseInformation.put("startTime", sdfTime.format(openingTime.getStartTime()));
        	baseInformation.put("endTime", sdfTime.format(openingTime.getEndTime()));
    	}
    	return ReturnMapUtil.packData(baseInformation);
    }
    
    @RequestMapping("/HomePageChart")
    public Map<String,Object> HomePageChart() throws ParseException{
    	
    	Integer scale = Integer.parseInt(request.getParameter("scale"));
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Integer siteId = Integer.parseInt(request.getParameter("siteId"));
		if (!roleService.blockRole(request,siteId,10)) return ReturnMapUtil.packData("恶意登录");
    	Long st = Long.parseLong(request.getParameter("st"));
    	Long et = Long.parseLong(request.getParameter("et"));
		String pType = request.getParameter("type");
		if (scale == 0 || scale == 1) {
			if(null == pType) {
				pType = "0";
			}
			Integer type = Integer.parseInt(pType);
			String scaleType = "";
			if (type == 0) {
				scaleType = "dh";
			} else {
				scaleType = "d";
			}
			ArrayList<HomePage> list;
    		Byte siteType = siteService.testSelect(Short.parseShort(request.getParameter("siteId"))).getType();
    		if (siteType==0 || siteType==1 || siteType==3){
    			list = homePageService.getTotalData(false, siteId, sdf.format(new Date(st)), sdf.format(new Date(et)),
    					scaleType,"t");
    		} else {
    			list = homePageService.getTotalData(true, siteId, sdf.format(new Date(st)), sdf.format(new Date(et)),
    					scaleType,"t");
    		}


			ArrayList<String> categories = new ArrayList<String>();
			Map<String, String> categoryMap = new HashMap<String, String>();
			SimpleDateFormat sdf1 = (SimpleDateFormat) scaleTimeLine.get(type);
			// 添加category
			categoryMap.clear();
			for (int i = 0; i < list.size(); i++) {
				categoryMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), "1");
			}
			categories = sort(categoryMap);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("categories", categories);
			ArrayList<Object> dataList1 = new ArrayList<Object>();
			ArrayList<Object> dataList2 = new ArrayList<Object>();
			// 添加数据
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (int i = 0; i < list.size(); i++) {
				dataMap.put(sdf1.format(sdf.parse(list.get(i).getTime())), list.get(i));
			}
			for (int i = 0; i < categories.size(); i++) {
				if (dataMap.containsKey(categories.get(i))) {
					HomePage value = (HomePage) dataMap.get(categories.get(i));
					dataList1.add(value.getEnter());
					dataList2.add(value.getSales());
				}
			}
			ArrayList<ArrayList<Object>> interList = new ArrayList<ArrayList<Object>>();
			interList.add(dataList1);
			if (scale == 0) {
				interList.add(dataList2);
			}
			resultMap.put("data", interList);
			return ReturnMapUtil.packData(resultMap);
		}
    	if (scale==2){
        	Integer type = Integer.parseInt(pType);
        	HashMap<String,Object> map = new HashMap<String,Object>();

        	map.put("startTime", sdf.format(new Date(st)));
        	map.put("endTime",sdf.format(new Date(et)));
        	map.put("siteId",siteId);
        	map.put("zoneType",type);
        	map.put("total", "f");
    		Byte siteType = siteService.testSelect(Short.parseShort(request.getParameter("siteId"))).getType();
    		if (siteType==0 || siteType==1 || siteType==3){
            	map.put("findSite", 0);
    		} else {
            	map.put("findSite", 1);
    		}
        	ArrayList<Map<String,Object>> list = homePageMapper.getSitezoneDataNoTime(map);
        	ArrayList<Object> categories = new ArrayList<Object>();
        	ArrayList<Object> dataList1 = new ArrayList<Object>();
        	ArrayList<Object> dataList2 = new ArrayList<Object>();
        	for (int i=0;i<list.size();i++){
        		categories.add(list.get(i).get("zone_name"));
        		dataList1.add(list.get(i).get("enter"));
        		dataList2.add(list.get(i).get("exit"));
        	}
    		ArrayList<ArrayList<Object>> interList = new ArrayList<ArrayList<Object>>();
    		interList.add(dataList1);
    		interList.add(dataList2);
    		Map<String,Object> resultMap = new HashMap<String,Object>();
    		resultMap.put("categories", categories);
    		resultMap.put("data", interList);
        	return ReturnMapUtil.packData(resultMap);
    	}
    	if (scale==3){
        	Integer type = Integer.parseInt(pType);
        	HashMap<String,Object> map = new HashMap<String,Object>();
        	map.put("findSite", 1);
        	map.put("startTime", sdf.format(new Date(st)));
        	map.put("endTime",sdf.format(new Date(et)));
        	map.put("siteId",siteId);
        	map.put("locationType",type);
        	map.put("total", "f");
        	ArrayList<Map<String,Object>> list = homePageMapper.getLocationNoTime(map);
        	ArrayList<Object> categories = new ArrayList<Object>();
        	ArrayList<Object> dataList1 = new ArrayList<Object>();
        	ArrayList<Object> dataList2 = new ArrayList<Object>();
        	for (int i=0;i<list.size();i++){
        		categories.add(list.get(i).get("display_name"));
        		dataList1.add(list.get(i).get("enter"));
        		dataList2.add(list.get(i).get("sales"));
        	}
    		ArrayList<ArrayList<Object>> interList = new ArrayList<ArrayList<Object>>();
    		interList.add(dataList1);
    		interList.add(dataList2);
    		Map<String,Object> resultMap = new HashMap<String,Object>();
    		resultMap.put("categories", categories);
    		resultMap.put("data", interList);
        	return ReturnMapUtil.packData(resultMap);
    	}
    	if (scale==4){
        	String type = pType;
        	HashMap<String,Object> map = new HashMap<String,Object>();
        	map.put("findSite", 1);
        	map.put("startTime", sdf.format(new Date(st)));
        	map.put("endTime",sdf.format(new Date(et)));
        	map.put("siteId",siteId);
        	map.put("tagType",type);
        	map.put("total", "f");
        	ArrayList<Map<String,Object>> list = homePageMapper.getTagDataNoTime(map);
        	ArrayList<Object> categories = new ArrayList<Object>();
        	ArrayList<Object> dataList1 = new ArrayList<Object>();
        	ArrayList<Object> dataList2 = new ArrayList<Object>();
        	for (int i=0;i<list.size();i++){
        		categories.add(list.get(i).get("display_name"));
        		dataList1.add(list.get(i).get("enter"));
        		dataList2.add(list.get(i).get("sales"));
        	}
    		ArrayList<ArrayList<Object>> interList = new ArrayList<ArrayList<Object>>();
    		interList.add(dataList1);
    		interList.add(dataList2);
    		Map<String,Object> resultMap = new HashMap<String,Object>();
    		resultMap.put("categories", categories);
    		resultMap.put("data", interList);
        	return ReturnMapUtil.packData(resultMap);
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
