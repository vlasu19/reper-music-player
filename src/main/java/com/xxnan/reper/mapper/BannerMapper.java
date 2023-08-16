package com.xxnan.reper.mapper;

import com.xxnan.reper.pojo.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {
    @Select("select * from banner")
    List<Banner> getAll();
}
