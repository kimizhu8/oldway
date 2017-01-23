package net.iretailer.rest.filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager<T> {
	private Map<String,T> cache = new ConcurrentHashMap<String,T>();
	
	public T getValue (Object key) {
		if (key!=null){
			return cache.get(key);
		} else {
			return null;
		}
	}
	
	public void updateCache(String key,T value){
		cache.put(key, value);
	}
	
	public void removeCache(String key) {
		if (key==null) return;
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
	}
	
	public void removeCache() {
		cache.clear();
	}
}
