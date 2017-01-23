package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.HolidayMapper;
import net.iretailer.rest.dao.SubjectMapper;
import net.iretailer.rest.model.Holiday;
import net.iretailer.rest.model.Subject;
import net.iretailer.rest.service.HolidayService;
@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayMapper holidayMapper;

	@Autowired
	private SubjectMapper subjectMapper;
	@Override
	public ArrayList<Holiday> getAllHoliday() {
		return holidayMapper.selectAll();
	}

	@Override
	public void deleteHoliday(Short id) {
		holidayMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insertHoliday(Holiday holiday) {
		holidayMapper.insertSelective(holiday);
	}

	@Override
	public void updateHoliday(Holiday holiday) {
		holidayMapper.updateByPrimaryKeySelective(holiday);
		
	}

	@Override
	public ArrayList<Subject> getAllSubject(Integer siteId) {
		return subjectMapper.selectAll(siteId);

	}

	@Override
	public void deleteSubject(Short id) {
		subjectMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void insertSubject(Subject subject) {
		subjectMapper.insertSelective(subject);
	}

	@Override
	public void updateSubject(Subject subject) {
		subjectMapper.updateByPrimaryKeySelective(subject);
		
	}

}
