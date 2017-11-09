package viagogo.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomMap<K, V> {

	// new Map with an ArrayList for the value 
    private Map<K, ArrayList<V>> m = new HashMap<>();

    /**
     * @param key
     * @param value
     * overridden value of put(k,v) that
     * will create an ArrayList in the case 
     * of duplicate value
     */
    public void put(K k, V v) {
        if (m.containsKey(k)) {
        	// add value to the arraylist
            m.get(k).add(v);
        } else {
        	// setup arraylist
            ArrayList<V> arr = new ArrayList<>();
            arr.add(v);
            m.put(k, arr);
        }
    }

     /**
     * @param key
     * @return value as ArrayList<V>
     */
    public ArrayList<V> get(K k) {
        return m.get(k);
    }

    /**
     * @param key
     * @param index
     * @return exact value inside ArrayList
     */
    public V get(K k, int index) {
        return m.get(k).size()-1 < index ? null : m.get(k).get(index);
    }
}
