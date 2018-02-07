package com.onionch.webapp.website.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by onionch on 1/12/17.
 */
@Controller
@RequestMapping("/*")
public class IndexConotroller {

    private static final Logger logger = Logger.getLogger(IndexConotroller.class);

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private ModelAndView index(){
        Map map= new HashMap();
        return new ModelAndView("index",map);
    }
}
