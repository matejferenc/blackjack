package com.mf.utils;

import java.util.Random;
import java.util.Set;

public class Utils {

    private static Random random = new Random();

    public static <E> E random(Set<E> set) {
        int item = random.nextInt(set.size());
        int i = 0;
        for (E element : set) {
            if (i == item) {
                return element;
            }
            i++;
        }
        throw new IllegalStateException();
    }
    
    public static <E> E random(E[] array) {
        int index = random.nextInt(array.length);
        return array[index];
    }
}
