package com.vanderkast.primes.cache;

import java.util.Map;
import java.util.Optional;

public interface PrimeCache {
    /**
     * <p>Stores passed number and it's primality test result in cache.</p>
     * <p>Behavior on putting duplicates with different primality value is undefined!
     * Checkout implementation for details</p>
     */
    void put(Number number, Boolean prime);

    default void putPrime(Number number) {
        put(number, true);
    }

    default void putComposite(Number number) {
        put(number, false);
    }

    /**
     * @param number that primality test result of is cached
     * @return cached passed number primality test result if it's cached, empty otherwise
     */
    Optional<Boolean> isPrime(Number number);

    /**
     * @return unmodifiable map of cached primality test results
     */
    Map<Number, Boolean> getAll();
}
