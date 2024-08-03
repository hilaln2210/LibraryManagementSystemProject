package com.library.algorithms;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlgoCacheTest {
    @Test
    public void testLRUCache() {
        IAlgoCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "one");
        cache.put(2, "two");
        assertEquals("one", cache.get(1));
        cache.put(3, "three");
        assertNull(cache.get(2));
        assertEquals(2, cache.size()); // בדיקת גודל הקאש
    }

    @Test
    public void testRandomCache() {
        IAlgoCache<Integer, String> cache = new RandomCache<>(2);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        assertEquals(2, cache.size()); // בדיקת גודל הקאש
    }
}
