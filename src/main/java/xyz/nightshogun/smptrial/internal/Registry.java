package xyz.nightshogun.smptrial.internal;

/**
 * Simple interface that represents a registry for holding
 * data mapped to the respective key
 *
 * @param <K> The key the value will be mapped to
 * @param <V> The actual value that the key will store
 *
 * @author NightShogun
 * */
public interface Registry<K, V> {

    /**
    * Caches the given value into the registry with its key
    *
    * @param key the key to map the value to
    * @param value the value to cache
    *
    * */
    void inject(K key, V value);

    /**
     * Removes the given key from the registry
     *
     * @param key the key to remove
     *
     * */
    void eject(K key);

    /**
     * Retrieves the value mapped to the given key
     *
     * @param key the key to get the value from
     *
     * @return the value mapped to the given key
     *
     * */
    V get(K key);

}
