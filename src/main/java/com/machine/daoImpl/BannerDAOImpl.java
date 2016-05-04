package com.machine.daoImpl;

import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.machine.dao.BannerDAO;
import com.machine.model.Banner;

@Repository
public class BannerDAOImpl implements BannerDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Banner> listAllBanners() {
		List<Banner> banners = getSession().getNamedQuery("Banner.findAll").list();
	    banners.sort(new Comparator<Banner>() {
			@Override
			public int compare(Banner o1, Banner o2) {
				return o1.getPriority() - o2.getPriority();
			}
		});
		return banners;
	}

	@Override
	public void updateBanner(Banner banner) {
		getSession().merge(banner);
	}

	@Override
	public Banner getBannerById(int id) {
		return (Banner) getSession().getNamedQuery("Banner.findById").setParameter("id", id).list().get(0);
	}

	@Override
	public Banner deleteBanner(int bannersId) {
			Banner banner = getBannerById(bannersId);
			getSession().delete(banner);
			return banner;
	}

	@Override
	public void addNewBanner(Banner banner) {
		getSession().persist(banner);
	}
	
}
