package com.machine.service;

import java.util.List;

import com.machine.model.Banner;

public interface BannerService {
	public List<Banner> listAllBanners();
	public void updateBanner(Banner banner);
	Banner getBannerById(int id);
	public void deleteBanners(String[] bannersId) ;
}
