import java.util.*;

public class LruCache<K,V> {

    private final int MAX = 3;
    LinkedList<K> list = new LinkedList<>();
    Map<K, V> map = new HashMap<>();

    public void cache(K key, V value){
        if(map.containsKey(key)){
            list.remove(key);


        }else if(map.size() >= MAX){
            K last = list.getLast();
            list.removeLast();
            map.remove(last);
        }

        list.addFirst(key);
        map.put(key, value);
    }

    public V get(K key){
        if(!map.containsKey(key)){
            return null;
        }
        V value = map.get(key);
        list.remove(key);
        list.addFirst(key);
        return value;
    }

    public static void main(String[] argv){
        LruCache<String, Integer> lruCache = new LruCache<>();
        lruCache.cache("A",1);
        lruCache.cache("B",2);
        lruCache.cache("C",3);
        lruCache.cache("D",4);

        lruCache.cache("C",10);
        lruCache.cache("C",11);
        lruCache.cache("D",22);
        lruCache.get("B");

        Iterator iterator = lruCache.list.iterator();
        while(iterator.hasNext()){
            String key = (String) iterator.next();
            System.out.println(key + "->" + lruCache.map.get(key));
        }

    }
}
