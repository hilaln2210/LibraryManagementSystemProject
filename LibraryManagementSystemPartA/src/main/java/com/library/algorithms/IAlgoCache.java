package com.library.algorithms;

public interface IAlgoCache<K, V> {
    V get(K key);
    void put(K key, V value);
    void remove(K key);
    int size(); // הוספת המתודה size לממשק
}
