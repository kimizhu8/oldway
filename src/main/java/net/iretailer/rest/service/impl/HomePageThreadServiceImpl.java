package net.iretailer.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.HomePageMapper;
import net.iretailer.rest.model.HomePage;
import net.iretailer.rest.model.HomePageByLocation;
import net.iretailer.rest.service.HomePageService;
import net.iretailer.rest.service.SiteService;

@Service
public class HomePageThreadServiceImpl{
	@Autowired
	private HomePageMapper homePageMapper;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private HomePageService homePageService;

	public ArrayList<Object> getHomePage(Integer siteId,String todayStart,String todayEnd,String comparisonStart,String comparisonEnd,Integer[] blockSite) throws InterruptedException, ExecutionException{
		//创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(4);
		Byte siteType = siteService.testSelect(siteId.shortValue()).getType();
		//
		Callable<Object> c1 = new MyCallable(0,siteId,todayStart,todayEnd,siteType,blockSite);
		Callable<Object> c2 = new MyCallable(0,siteId,comparisonStart,comparisonEnd,siteType,blockSite);
		Callable<Object> c3 = new MyCallable(1,siteId,todayStart,todayEnd,siteType,blockSite);
		Callable<Object> c4 = new MyCallable(1,siteId,comparisonStart,comparisonEnd,siteType,blockSite);
		Future<Object> todayFuture = pool.submit(c1);
		Future<Object> comparisonFuture = pool.submit(c2);
		Future<Object> locationScalesToday = pool.submit(c3);
		Future<Object> locationScalesComparison = pool.submit(c4);
		ArrayList<Object> resultList = new ArrayList<Object>();
		resultList.add(todayFuture.get());
		resultList.add(comparisonFuture.get());
		resultList.add(locationScalesToday.get());
		resultList.add(locationScalesComparison.get());
		pool.shutdown();
		return resultList;
		
	}
	class MyCallable implements Callable<Object> {
		private Integer siteId;
		private String todayStart;
		private String todayEnd;
		private Integer type;
		private Byte siteType;
		private Integer[] blockSite;

		public MyCallable(Integer type,Integer siteId,String todayStart,String todayEnd,Integer[] blockSite) {
			this.siteId = siteId;
			this.todayStart = todayStart;
			this.todayEnd = todayEnd;
			this.type = type;
			this.blockSite = blockSite;
		}
		public MyCallable(Integer type,Integer siteId,String todayStart,String todayEnd,Byte siteType,Integer[] blockSite) {
			this.siteId = siteId;
			this.todayStart = todayStart;
			this.todayEnd = todayEnd;
			this.type = type;
			this.siteType = siteType;
			this.blockSite = blockSite;
		}
		@Override
		public Object call() throws Exception {
			
				if (type==0){
					List<HomePage> homePageList = null;
					HomePage homePage = new HomePage();
					if (siteType==0 || siteType==1 || siteType==3){
						HashMap<String,Object> map = new HashMap<String,Object>();
						map.put("siteId",siteId);
						map.put("startTime", this.todayStart);
						map.put("endTime", this.todayEnd);
						map.put("findchild", 0);
						this.todayStart +=" 00:00:00";
						this.todayEnd +=" 00:00:00";
						String acreage = homePageMapper.getAcreage(map);
						String trueStartTime = homePageMapper.getTrueStartTime(map);
						String trueEndTime = homePageMapper.getTrueEndTime(map);
						homePage = homePageMapper.getHomePage(false, this.siteId, this.todayStart, this.todayEnd, "d2","t",acreage,trueStartTime,trueEndTime,blockSite);
//						homePageList = homePageMapper.getTotalData(false, this.siteId, this.todayStart, this.todayEnd, "dh","t",acreage,trueStartTime,trueEndTime,blockSite,null);
//						if (homePageList.size()!=0){
//							homePage.setAcreage(Integer.parseInt(acreage));
//							for (int i=0;i<homePageList.size();i++){
//								homePage.setEnter(homePage.getEnter()+homePageList.get(i).getEnter());
//								homePage.setExit(homePage.getExit()+homePageList.get(i).getExit());
//								homePage.setPassby(homePage.getPassby()+homePageList.get(i).getPassby());
//								homePage.setSales(homePage.getSales()+homePageList.get(i).getSales());
//								homePage.setGoods(homePage.getGoods()+homePageList.get(i).getGoods());
//								homePage.setTrades(homePage.getTrades()+homePageList.get(i).getTrades());
//								homePage.setEmployeeNumber(homePageList.get(i).getEmployeeNumber());
//								homePage.setDisplayName(homePageList.get(i).getDisplayName());
//							}
//						} else {
//							homePage = null;
//						}

						
					} else {
						HashMap<String,Object> map = new HashMap<String,Object>();
						map.put("siteId",siteId);
						map.put("startTime", this.todayStart);
						map.put("endTime", this.todayEnd);
						map.put("findchild", 1);
						this.todayStart +=" 00:00:00";
						this.todayEnd +=" 00:00:00";
						String acreage = homePageMapper.getAcreage(map);
						String trueStartTime = homePageMapper.getTrueStartTime(map);
						String trueEndTime = homePageMapper.getTrueEndTime(map);
						homePage = homePageMapper.getHomePage(true, this.siteId, this.todayStart, this.todayEnd, "d2","t",acreage,trueStartTime,trueEndTime,blockSite);
//						homePageList = homePageMapper.getTotalData(true, this.siteId, this.todayStart, this.todayEnd, "dh","t",acreage,trueStartTime,trueEndTime,blockSite,null);
//						if (homePageList.size()!=0){
//							homePage.setAcreage(Integer.parseInt(acreage));
//							for (int i=0;i<homePageList.size();i++){
//								homePage.setEnter(homePage.getEnter()+homePageList.get(i).getEnter());
//								homePage.setExit(homePage.getExit()+homePageList.get(i).getExit());
//								homePage.setPassby(homePage.getPassby()+homePageList.get(i).getPassby());
//								homePage.setSales(homePage.getSales()+homePageList.get(i).getSales());
//								homePage.setGoods(homePage.getGoods()+homePageList.get(i).getGoods());
//								homePage.setTrades(homePage.getTrades()+homePageList.get(i).getTrades());
//								homePage.setEmployeeNumber(homePageList.get(i).getEmployeeNumber());
//								homePage.setDisplayName(homePageList.get(i).getDisplayName());
//							}
//						} else {
//							homePage = null;
//						}

					}

					return homePage;
				} else
				if (type==1){
					ArrayList<HomePageByLocation> homePage = null;
					if (siteType==0 || siteType==1 || siteType==3){
						this.todayStart +=" 00:00:00";
						this.todayEnd +=" 00:00:00";
						homePage = homePageService.getHomePageByLocation(false, siteId, todayStart, todayEnd, "d2",type,blockSite,null);
					} else {
						this.todayStart +=" 00:00:00";
						this.todayEnd +=" 00:00:00";
						homePage = homePageService.getHomePageByLocation(true, siteId, todayStart, todayEnd, "d2",type,blockSite,null);
					}
					return homePage;
				}
				return null;
			}
		}
}