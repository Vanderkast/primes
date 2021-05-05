package com.vanderkast.primes.cache;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SimpleInMemoryPrimeCacheTest {

    @Test
    void ofPreloaded() {
        // given
        Map<Number, Boolean> preloaded = Map.of(
                100L, false,
                BigInteger.valueOf(3L), true,
                Integer.valueOf("19"), true);

        // when
        PrimeCache cache = new SimpleInMemoryPrimeCache(preloaded);

        // then
        preloaded.forEach(
                (number, prime) -> {
                    Optional<Boolean> value = cache.isPrime(number);
                    assertTrue(value.isPresent());
                    assertEquals(prime, value.get());
                }
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void putThenGet() {
        // given
        var number = 13L;
        var prime = true;
        var cache = new SimpleInMemoryPrimeCache();

        // when
        cache.put(number, prime);

        // then
        var actual = cache.isPrime(number);
        assertTrue(actual.isPresent());
        assertEquals(prime, actual.get());
    }

    @Test
    void putThenGetAll() {
        // given
        Map<Number, Boolean> numbers = Map.of(
                13L, true,
                BigInteger.valueOf(7), true,
                65027, false,
                Long.valueOf("99"), false
        );
        var cache = new SimpleInMemoryPrimeCache();

        // when
        numbers.forEach(cache::put);
        var stored = cache.getAll();

        // then
        assertEquals(stored, numbers);
    }
}
