package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/*
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object obj) {


        Solution n = (Solution) obj;

        if(obj==this) return true;
        if(!(obj instanceof Solution)) return false;

        if (first != null ? !first.equals(n.first) : n.first != null) return false;
        return last != null ? last.equals(n.last) : n.last == null;
    }

    public int hashCode() {

        int hashCode = 1;
        hashCode = 31 * hashCode + (( this.first == null) ? 0 :  this.first.hashCode());
        hashCode = 31 * hashCode + ((this.last == null) ? 0 : this.last.hashCode());
        return hashCode;

    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();

        Solution sol1 = new Solution("Donald", "Duck");
        Solution sol2 = new Solution("Donald", "Duck");

        s.add(sol1);
        s.add(sol2);

        System.out.println(sol1.equals(sol2));
        System.out.println(sol1.hashCode());
        System.out.println(sol2.hashCode());

        System.out.println(s.contains(sol1));
        System.out.println(s.contains(sol2));
        System.out.println(s.contains( new Solution("Donald", "Duck")));

    }
}
