package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;

    public CustomTree(){

        root = new Entry<>("treeRoot");
    }



    @Override
    public boolean addAll(int index, Collection<? extends String> c) throws UnsupportedOperationException {

        throw new UnsupportedOperationException();
      //  return super.addAll(index, c);
    }

    @Override
    public String set(int index, String element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
      //  return super.set(index, element);
    }

    @Override
    public void add(int index, String element)  throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
      //  super.add(index, element);
    }

    @Override
    public String remove(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
      //  return super.remove(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex)  throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
     //   return super.subList(fromIndex, toIndex);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
      //  super.removeRange(fromIndex, toIndex);
    }

    @Override
    public String get(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
      //  return null;
    }

    @Override
    public int size() {
      //  int lSize = root.leftChild.
       // return root.root.rightChild;
        return 0;
    }

    @Override
    public boolean add(String s) {
        return super.add(s);
    }

    static class  Entry<T> implements Serializable{

        Entry<String> root;
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName){
            this.elementName = elementName;
            availableToAddLeftChildren=true;
            availableToAddRightChildren=true;
        }

        void checkChildren(){
            if(leftChild!=null) availableToAddLeftChildren=false;
            if(rightChild!=null) availableToAddRightChildren=false;
        }

       public boolean isAvailableToAddChildren(){

            return availableToAddLeftChildren||availableToAddRightChildren;
        }
    }
}
