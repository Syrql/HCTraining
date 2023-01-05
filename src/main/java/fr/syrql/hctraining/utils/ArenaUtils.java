package fr.syrql.hctraining.utils;

import java.util.Collection;
import java.util.Optional;

public class ArenaUtils {

    public static <T> T getRandom(Collection<T> coll) {
        int num = (int) (Math.random() * coll.size());
        for(T t: coll) if (--num < 0) return t;
        throw new AssertionError();
    }
}
