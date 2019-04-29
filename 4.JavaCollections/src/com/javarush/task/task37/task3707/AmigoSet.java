package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final Object PRESENT  = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E > collection) {

        int cap = Math.max(16,(int)Math.ceil(collection.size()/.75f));
        map = new HashMap<>(cap);
        for (E in: collection){
            map.put(in,PRESENT);
        }
    }


    public  Iterator<E> iterator(){
        Iterator<E> iterator = (Iterator<E>) map.keySet().iterator();

        return iterator;
    }

    @Override
    public boolean add(E e) {
        return map.put(e,PRESENT)==null;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        if(map.containsKey(o)){
            map.remove(o);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        AmigoSet<E> amigoSet = null;
        try {

            amigoSet = new AmigoSet<>();
            amigoSet.map = (HashMap<E, Object>) this.map.clone();


        }catch (Exception e){
            throw  new InternalError();
        }

        return amigoSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AmigoSet<?> amigoSet = (AmigoSet<?>) o;

        return map != null ? map.equals(amigoSet.map) : amigoSet.map == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
    }

    private void writeObject(ObjectOutputStream oss) throws IOException {
        oss.defaultWriteObject();
        oss.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        oss.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        oss.writeInt(map.keySet().size());
        for(E e : map.keySet()) {
            oss.writeObject(e);
        }
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        map = new HashMap(capacity, loadFactor);
        int size = ois.readInt();
        for(int i = 0; i<size; i++) {
            map.put((E)ois.readObject(), PRESENT);
        }
    }
}
