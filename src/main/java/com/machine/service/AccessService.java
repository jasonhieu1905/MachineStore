package com.machine.service;

import java.util.Date;
import java.util.List;

import com.machine.model.Access;

public interface AccessService {
	void addAccessPage(Access access);
	List<Access> listAllAccess();
	List<Access> listAllAccessPerDate(Date date);
	public List<Access> listAllAccessByPeriodTime(Date start,Date end);
	int allAcccess();
	public Access getAccessToday();
	void updateAccess(Access access);
}
