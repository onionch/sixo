package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.Resource;
import com.onionch.webapp.website.mapper.ResourceMapper;
import com.onionch.webapp.website.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("resourceServiceImpl")
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> listAll() {
        return resourceMapper.listAll();
    }

    @Override
    public void create(Resource resource) {
        resourceMapper.create(resource);
    }

    @Override
    public void update(Resource resource) {
        resourceMapper.update(resource);
    }

    @Override
    public void delete(String uId) {
        resourceMapper.delete(uId);
    }

    @Override
    public Resource findByName(String resName) {
        return resourceMapper.findByName(resName);
    }
}
