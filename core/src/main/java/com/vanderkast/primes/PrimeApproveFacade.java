package com.vanderkast.primes;

import com.vanderkast.primes.cache.PrimeCache;
import com.vanderkast.primes.core.PrimeApprove;

import java.math.BigInteger;

public class PrimeApproveFacade {
    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

    private final PrimeApprove<Long> longPrimeApprove;
    private final PrimeApprove<BigInteger> bigIntegerPrimeApprove;
    private final PrimeCache cache;

    public PrimeApproveFacade(PrimeApprove<Long> longPrimeApprove,
                              PrimeApprove<BigInteger> bigIntegerPrimeApprove,
                              PrimeCache cache) {
        this.longPrimeApprove = longPrimeApprove;
        this.bigIntegerPrimeApprove = bigIntegerPrimeApprove;
        this.cache = cache;
    }

    public boolean isPrime(String number) {
        var value = new BigInteger(number);
        var cached = cache.isPrime(value);
        if(cached.isPresent())
            return cached.get();
        else  {
            var result = test(value);
            cache.put(value, result);
            return result;
        }
    }

    boolean test(BigInteger number) {
        if (number.compareTo(LONG_MAX) < 0)
            return longPrimeApprove.isPrime(number.longValue());
        return bigIntegerPrimeApprove.isPrime(number);
    }
}
