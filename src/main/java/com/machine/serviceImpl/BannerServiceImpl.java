package com.machine.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.machine.dao.BannerDAO;
import com.machine.model.Banner;
import com.machine.service.BannerService;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

	private BannerDAO bannerDAO;
	
	
	public void setBannerDAO(BannerDAO bannerDAO) {
		this.bannerDAO = bannerDAO;
	}

	@Override
	public List<Banner> listAllBanners() {
		return bannerDAO.listAllBanners();
	}

	@Override
	public void updateBanner(Banner banner) {
		bannerDAO.updateBanner(banner);
	}

	@Override
	public Banner getBannerById(int id) {
		return bannerDAO.getBannerById(id);
	}

	@Override
	public void deleteBanners(String[] bannersId) {
		bannerDAO.deleteBanners(bannersId);
	}

}
