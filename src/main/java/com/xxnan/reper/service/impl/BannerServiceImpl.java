package com.xxnan.reper.service.impl;

import com.xxnan.reper.mapper.BannerMapper;
import com.xxnan.reper.pojo.entity.Banner;
import com.xxnan.reper.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Cacheable(value="banner", key = "'list'")
    @Override
    public List<Banner> getAllBanner() {
        return bannerMapper.getAll();
    }
}
