package com.remitly;

import org.junit.Assert;
import org.junit.Test;

public class LRUTest {

    @Test
    public void testAlwaysPass() {
        // nothing
    }

    @Test
    public void testBasicEviction() {
        final LRUCache cache = new LRUCache(2);
        cache.add(1, "a");
        cache.add(2, "b");
        cache.add(3, "c");

        String one = cache.get(1);
        String two = cache.get(2);
        String three = cache.get(3);

        Assert.assertEquals("b", two);
        Assert.assertEquals("c", three);

        // should be null - evicted when we added 3
        Assert.assertNull(one);
    }

    @Test
    public void testBasicOrdering() {
        final LRUCache cache = new LRUCache(2);
        cache.add(1, "a");
        cache.add(2, "b");

        // should now make `a:1` the most recently used
        String one = cache.get(1);
        Assert.assertEquals(one, "a");

        cache.add(3, "c");

        one = cache.get(1);
        String two = cache.get(2);
        String three = cache.get(3);

        Assert.assertEquals("a", one);
        Assert.assertEquals("c", three);

        // should be null - evicted when we added 3
        Assert.assertNull(two);
    }
}