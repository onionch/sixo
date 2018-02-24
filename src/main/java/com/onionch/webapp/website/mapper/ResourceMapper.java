package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {

    List<Resource> all(@Param("resName") String resName, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    Integer allCount(@Param("resName") String resName);

    List<Resource> listAll();

    void create(Resource resource);

    void update(Resource resource);

    void delete(String uId);

    Resource findByName(@Param("resName") String resName);

    Resource findBySerialNum(@Param("serialNum") String serialNum);
}

