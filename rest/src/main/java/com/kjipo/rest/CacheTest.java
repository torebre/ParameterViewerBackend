package com.kjipo.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class CacheTest implements ApplicationListener<ApplicationPreparedEvent> {

    @Autowired
    private CacheManager ehCacheCacheManager;



    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        System.out.println("Cache manager: " +ehCacheCacheManager);
    }


}
