package com.w.demo;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @copyright SHENZHEN RONG WANG HUI ZHI TECHNOLOGY CORP
 * @author Lyon.liao
 * 创建时间：2015年3月2日
 * 类说明：
 * 
 * 最后修改时间：2015年3月2日
 * 修改内容： 新建此类
 */

public class LRU<K, V> {

    private final int MAX_CACHE_SIZE;
    private Entry<K, V> first;
    private Entry<K, V> last;

    private ConcurrentHashMap<K, Entry<K, V>> hashMap;

    public LRU(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        hashMap = new ConcurrentHashMap<K, Entry<K, V>>(cacheSize);
    }

    public void put(K key, V value) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            if (hashMap.size() >= MAX_CACHE_SIZE) {
                hashMap.remove(last.key);
                removeLast();
            }
            entry = new Entry<K, V>();
            entry.key = key;
        }
        entry.value = value;
        moveToFirst(entry);
        hashMap.put(key, entry);
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) return null;
        moveToFirst(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            if (entry.pre != null) entry.pre.next = entry.next;
            if (entry.next != null) entry.next.pre = entry.pre;
            if (entry == first) first = entry.next;
            if (entry == last) last = entry.pre;
        }
        hashMap.remove(key);
    }

    public int size() {
    	return hashMap.size();
    }
    private void moveToFirst(Entry<K, V> entry) {
        if (entry == first) return;
        if (entry.pre != null) entry.pre.next = entry.next;
        if (entry.next != null) entry.next.pre = entry.pre;
        if (entry == last) last = last.pre;

        if (first == null || last == null) {
            first = last = entry;
            return;
        }

        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) first = null;
            else last.next = null;
        }
    }


    private Entry<K, V> getEntry(K key) {
        return hashMap.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Entry<K, V> entry = first;
        while (entry != null) {
            sb.append(String.format("%s:%s ", entry.key, entry.value));
            entry = entry.next;
        }
        return sb.toString();
    }

    @SuppressWarnings("hiding")
	class Entry<K, V> {
        public Entry<K, V> pre;
        public Entry<K, V> next;
        public K key;
        public V value;
    }
}