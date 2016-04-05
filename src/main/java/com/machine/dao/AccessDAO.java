package com.machine.dao;

import java.util.Date;
import java.util.List;

import com.machine.model.Access;

public interface AccessDAO {
	void addAccessPage(Access access);
	List<Access> listAllAccess();
	List<Access> listAllAccessPerDate(Date date);
	List<Access> listAllAccessByPeriodTime(Date from,Date to);
}
