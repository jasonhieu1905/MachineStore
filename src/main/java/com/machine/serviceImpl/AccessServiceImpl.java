package com.machine.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.machine.dao.AccessDAO;
import com.machine.model.Access;
import com.machine.service.AccessService;


@Service
@Transactional
public class AccessServiceImpl implements AccessService{

	private AccessDAO accessDAO;
	
	public void setAccessDAO(AccessDAO accessDAO) {
		this.accessDAO = accessDAO;
	}


	@Override
	public void addAccessPage(Access access) {
		accessDAO.addAccessPage(access);
	}


	@Override
	public List<Access> listAllAccess() {
		return accessDAO.listAllAccess();
	}


	@Override
	public List<Access> listAllAccessPerDate(Date date) {
		return accessDAO.listAllAccessPerDate(date);
	}


	@Override
	public List<Access> listAllAccessByPeriodTime(Date start, Date end) {
		return accessDAO.listAllAccessByPeriodTime(start, end);
	}


	@Override
	public int allAcccess() {
		return accessDAO.allAcccess();
	}


	@Override
	public Access getAccessToday() {
		return accessDAO.getAccessToday();
	}


	@Override
	public void updateAccess(Access access) {
		accessDAO.updateAccess(access);
	}
	
	

}
