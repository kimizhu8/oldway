package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.OpeningTime;

public interface OpeningTimeService {
	public void insertOpeningTime(OpeningTime openingTime);
	public void deleteOpeningTime(Short id);
	public ArrayList<OpeningTime> getOpeningTime(Integer siteId);
	public void updateOpeningTime(OpeningTime openingTime);
}
