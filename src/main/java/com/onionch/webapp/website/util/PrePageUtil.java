package com.onionch.webapp.website.util;

/**
 * Created by yangjunfeng on 17-10-23.
 */
public class PrePageUtil {

    public static Integer pageCount(Integer viewCount,Integer total) {
        Integer pageCount = 0;
        pageCount = pageCount + total/viewCount;
        pageCount = pageCount + (total%viewCount > 0 ? 1 : 0);
        return pageCount;
    }
}
