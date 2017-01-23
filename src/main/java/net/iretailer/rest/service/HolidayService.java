package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.Holiday;
import net.iretailer.rest.model.Subject;


public interface HolidayService {
	public ArrayList<Holiday> getAllHoliday();
	public void deleteHoliday(Short id);
	public void insertHoliday(Holiday holiday);
	public void updateHoliday(Holiday holiday);
	
	public ArrayList<Subject> getAllSubject(Integer siteId);
	public void deleteSubject(Short id);
	public void insertSubject(Subject subject);
	public void updateSubject(Subject subject);
}
