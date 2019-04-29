package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){

        Date start = new Date();

        for (String in: strings){
            ids.add(shortener.getId(in));
        }
        Date end = new Date();
        return end.getTime()-start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){

        Date start = new Date();

        for (Long in: ids){
            strings.add(shortener.getString(in));
        }
        Date end = new Date();
        return end.getTime()-start.getTime();
    }

    @Test
    public void testHashMapStorage(){

        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int x=0; x<10000; x++){
            origStrings.add( Helper.generateRandomString());
        }

       Set<Long> ids1 = new HashSet<>();
       long time = getTimeForGettingIds(shortener1,origStrings,ids1);

        Set<Long> ids2 = new HashSet<>();
        long time2 = getTimeForGettingIds(shortener2,origStrings,ids2);

        Assert.assertTrue(time>time2);


        Set<String> strings1 = new HashSet<>();
        long time3 = getTimeForGettingStrings(shortener1,ids1,strings1);

        Set<String> strings2 = new HashSet<>();
        long time4 = getTimeForGettingStrings(shortener2,ids2,strings2);

        Assert.assertEquals(time4,time3,30);

    }
}
