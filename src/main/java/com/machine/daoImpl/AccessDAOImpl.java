package com.machine.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.machine.dao.AccessDAO;
import com.machine.model.Access;
import com.machine.utils.DateUtils;

@Repository
public class AccessDAOImpl implements AccessDAO{
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addAccessPage(Access access) {
		getSession().persist(access);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Access> listAllAccess() {
		return getSession().getNamedQuery("Access.findAll").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Access> listAllAccessPerDate(Date date) {
		List<Date> startAndEndOfDate = DateUtils.getStartAndEndOfDate(date);
		return getSession().getNamedQuery("Access.findByPerioudTime")
				.setParameter("from", startAndEndOfDate.get(0).toString())
				.setParameter("end", startAndEndOfDate.get(1).toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Access> listAllAccessByPeriodTime(Date from, Date to) {
		List<Date> startAndEndOfDate = DateUtils.getStartAndEndOfPeriodTime(from,to);
		return getSession().getNamedQuery("Access.findByPerioudTime")
				.setParameter("from", startAndEndOfDate.get(0))
				.setParameter("end", startAndEndOfDate.get(1)).list();
	}

	@Override
	public int allAcccess() {
		return ((Long) getSession().getNamedQuery("Access.getAccessNumber").list().get(0)).intValue();
	}

	@Override
	public Access getAccessToday() {
		List<Access> accesses = getSession().getNamedQuery("Access.getAccessToday").setParameter("accessdate", new Date()).list();
		if(accesses == null){
			return null;
		}
		return accesses.get(0);
	}

	@Override
	public void updateAccess(Access access) {
		getSession().update(access);
	}

}
