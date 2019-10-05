package com.waqar.reservation.web.config.listener;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import java.util.logging.Logger;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

    private static final Logger log = Logger.getLogger(CacheEventLogger.class.getSimpleName());

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        String message = "Cache event "+cacheEvent.getType()+" for item with key "+cacheEvent.getKey()+". Old value = "+cacheEvent.getOldValue()+", New value = "+cacheEvent.getNewValue();
        log.info(message);
    }
}