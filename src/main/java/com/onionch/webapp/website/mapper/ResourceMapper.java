package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Resource;
import com.onionch.webapp.website.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    List<Resource> listAll();
    void create(Resource resource);
    void update(Resource resource);
    void delete(String uId);
    Resource findByName(@Param("resName") String resName);
}

