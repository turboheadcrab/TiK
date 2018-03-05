package ru.kpfu.itis;

import javafx.util.Pair;

/**
 * Created by Apraxin Vladimir on 5.3.18.
 */
public class MyPair<K, V> extends Pair implements Comparable<MyPair> {
    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public MyPair(Object key, Object value) {
        super(key, value);
    }


    @Override
    public int compareTo(MyPair anotherMyPair) {
        if ((int) this.getKey() > (int) anotherMyPair.getKey()) {
            return 1;
        } else if (this.getKey().equals(anotherMyPair.getKey())) {
            if ((int) this.getValue() > (int) anotherMyPair.getValue()) {
                return 1;
            } else if (this.getValue().equals(anotherMyPair.getValue())) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
