package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{

    private OriginalRetriever originalRetriever;
    private LRUCache<Long, Object> lruCache;

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
        lruCache = new LRUCache<>(20);
    }


    @Override
    public Object retrieve(long id) {
        Object chach = lruCache.find(id);

        if(chach==null){
            chach = originalRetriever.retrieve(id);
            lruCache.set(id,chach);
        }
        return chach;
    }
}