package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
            return values().size();
    }

    @Override
    public V put(K key, V value) {

        List<V> vArrayList =  map.get(key);
        V lastVal=null;

        if(vArrayList==null){
            vArrayList = new ArrayList<>();
        }else {
            lastVal=vArrayList.get(vArrayList.size()-1);

             if(vArrayList.size()==repeatCount){
                vArrayList.remove(0);
            }
        }

        if(vArrayList.size()<repeatCount) vArrayList.add(value);
        map.put(key,vArrayList);
        return lastVal;
    }

    @Override
    public V remove(Object key) {

        List<V> vArrayList = map.get(key);
        if (vArrayList == null)    return null;

        V val=null;
        if(vArrayList.size()>0){
            val = vArrayList.get(0);
            vArrayList.remove(0);
        }

        if (vArrayList.size() == 0) map.remove(key);
        return val;
    }

    @Override
    public Set<K> keySet() {

        return map.keySet();
        //напишите тут ваш код
    }

    @Override
    public Collection<V> values() {

        ArrayList<V> arrayList = new ArrayList<>();

        for (K in: map.keySet()){
            for (V into: map.get(in)){
                arrayList.add(into);
            }
        }

        return arrayList;
        //напишите тут ваш код
    }

    @Override
    public boolean containsKey(Object key) {

        return map.keySet().contains(key);
        //напишите тут ваш код
    }

    @Override
    public boolean containsValue(Object value) {
        boolean cont=false;
        //напишите тут ваш код
        for (K in: map.keySet()){
           cont= map.get(in).contains(value);
           if(cont) return true;
        }
        return cont;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}