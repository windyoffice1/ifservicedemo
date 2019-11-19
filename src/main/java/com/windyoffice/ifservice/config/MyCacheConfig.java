package com.windyoffice.ifservice.config;

import com.windyoffice.ifservice.cache.MyCache;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.J2CacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableCaching(proxyTargetClass=true)
public class MyCacheConfig {

    @Autowired
    private MyCache myCache;

    @Bean
    public J2CacheConfig j2CacheConfig() throws Exception{
        return J2CacheConfig.initFromConfig(MyCacheConfig.class.getClassLoader().getResourceAsStream("j2cache.properties"));
    }

    @Bean
    public CacheChannel cacheChannel() throws Exception{
        return J2CacheBuilder.init(j2CacheConfig()).getChannel();
    }


    @Bean
    public SimpleCacheManager simpleCacheManager(){
        SimpleCacheManager simpleCacheManager=new SimpleCacheManager();
        Set<Cache> caches=new HashSet<>();
        caches.add(myCache);
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

}
