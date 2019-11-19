package com.windyoffice.ifservice.cache;

import com.windyoffice.ifservice.util.JsonUtil;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Component;

@Component
public class MyCache implements Cache {

    private String region="rx";

    @Autowired
    private CacheChannel cacheChannel;


    @Override
    public String getName() {
        return cacheChannel.getL1Provider().name()+cacheChannel.getL2Provider().name();
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public ValueWrapper get(Object key) {
        CacheObject cacheObject=cacheChannel.get(region,String.valueOf(key),false);
        return cacheObject ==null?null:new SimpleValueWrapper(cacheObject);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        CacheObject cacheObject=cacheChannel.get(region,String.valueOf(key),false);
        return JsonUtil.string2Obj(cacheObject.toString(),type);
    }

    @Override
    public void put(Object key, Object value) {
        cacheChannel.set(region,String.valueOf(key),value);
    }

    @Override
    public void evict(Object key) {
        cacheChannel.evict(region,String.valueOf(key));
    }

    @Override
    public void clear() {
        cacheChannel.clear(region);
    }
}
