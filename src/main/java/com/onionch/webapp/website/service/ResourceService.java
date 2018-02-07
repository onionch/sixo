package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.Resource;

import java.util.List;

public interface ResourceService {
    List<Resource> listAll();
    void create(Resource resource);
    void update(Resource resource);
    void delete(String uId);
    Resource findByName(String resName);
}
