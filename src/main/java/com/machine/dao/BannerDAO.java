package com.machine.dao;

import java.util.List;

import com.machine.model.Banner;

public interface BannerDAO {

	List<Banner> listAllBanners();
	void updateBanner(Banner banner);
	Banner getBannerById(int id);
	Banner deleteBanner(int bannersId);
	public void addNewBanner(Banner banner);
}
