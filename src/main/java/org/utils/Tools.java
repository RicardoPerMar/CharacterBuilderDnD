package org.utils;

public class Tools {

    public static boolean containsIgnoreCase(Object a, String b){
        if (a == null || b == null){
            return false;
        }
        return a.toString().toLowerCase().contains(b.toLowerCase());
    }

}
