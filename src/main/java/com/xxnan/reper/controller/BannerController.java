package com.xxnan.reper.controller;


import com.xxnan.reper.common.result.R;
import com.xxnan.reper.pojo.entity.Banner;
import com.xxnan.reper.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/getAllBanner")
    public R getAllBanner(){
        List<Banner> banners=bannerService.getAllBanner();
        return R.success("成功获取轮播图与",banners);
    }
}
