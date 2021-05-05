package com.vanderkast.primes.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple PrimeCache implementation that stores data in Map of number-boolean.
 * This is NOT thread safe class!
 */
public class SimpleInMemoryPrimeCache implements PrimeCache {
    protected final Map<Number, Boolean> cache;

    public SimpleInMemoryPrimeCache() {
        this(new HashMap<>());
    }

    SimpleInMemoryPrimeCache(Map<Number, Boolean> preloaded) {
        this.cache = new HashMap<>(preloaded);
    }

    /**
     *  Overwrites prime value if passed number stored already.
     */
    @Override
    public void put(Number number, Boolean prime) {
        cache.put(number, prime);
    }

    @Override
    public Optional<Boolean> isPrime(Number number) {
        return Optional.ofNullable(cache.get(number));
    }

    @Override
    public Map<Number, Boolean> getAll() {
        return Map.copyOf(cache);
    }
}
