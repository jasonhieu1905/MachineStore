package com.machine.service;

import java.util.List;

import com.machine.model.Banner;

public interface BannerService {
	public List<Banner> listAllBanners();
	public void updateBanner(Banner banner);
	Banner getBannerById(int id);
	Banner deleteBanner(int bannersId);
	public void addNewBanner(Banner banner);
}
