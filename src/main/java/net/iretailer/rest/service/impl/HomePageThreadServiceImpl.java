package net.iretailer.rest.service.impl;

import java.util.ArrayList;
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

	public ArrayList<Object> getHomePage(Integer siteId,String todayStart,String todayEnd,String comparisonStart,String comparisonEnd) throws InterruptedException, ExecutionException{
		//创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(4);
		Byte siteType = siteService.testSelect(siteId.shortValue()).getType();
		//
		Callable<Object> c1 = new MyCallable(0,siteId,todayStart,todayEnd,siteType);
		Callable<Object> c2 = new MyCallable(0,siteId,comparisonStart,comparisonEnd,siteType);
		Callable<Object> c3 = new MyCallable(1,siteId,todayStart,todayEnd,siteType);
		Callable<Object> c4 = new MyCallable(1,siteId,comparisonStart,comparisonEnd,siteType);
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

		public MyCallable(Integer type,Integer siteId,String todayStart,String todayEnd) {
			this.siteId = siteId;
			this.todayStart = todayStart;
			this.todayEnd = todayEnd;
			this.type = type;
		}
		public MyCallable(Integer type,Integer siteId,String todayStart,String todayEnd,Byte siteType) {
			this.siteId = siteId;
			this.todayStart = todayStart;
			this.todayEnd = todayEnd;
			this.type = type;
			this.siteType = siteType;
		}
		@Override
		public Object call() throws Exception {
			
				if (type==0){
					HomePage homePage = null;
					if (siteType==0 || siteType==1 || siteType==3){
						homePage = homePageMapper.getHomePage(false, this.siteId, this.todayStart, this.todayEnd, "d","t");
					} else {
						homePage = homePageMapper.getHomePage(true, this.siteId, this.todayStart, this.todayEnd, "d","t");
					}

					return homePage;
				} else
				if (type==1){
					ArrayList<HomePageByLocation> homePage = null;
					if (siteType==0 || siteType==1 || siteType==3){
						homePage = homePageService.getHomePageByLocation(false, siteId, todayStart, todayEnd, "d",type);
					} else {
						homePage = homePageService.getHomePageByLocation(true, siteId, todayStart, todayEnd, "d",type);
					}
					return homePage;
				}
				return null;
			}
		}
}