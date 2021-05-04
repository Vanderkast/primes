package com.vanderkast.primes.domain;

import java.math.BigInteger;

public class PrimeApproveFacade {
    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

    private final PrimeApprove<Long> longPrimeApprove;
    private final PrimeApprove<BigInteger> bigIntegerPrimeApprove;

    public PrimeApproveFacade(PrimeApprove<Long> longPrimeApprove,
                              PrimeApprove<BigInteger> bigIntegerPrimeApprove) {
        this.longPrimeApprove = longPrimeApprove;
        this.bigIntegerPrimeApprove = bigIntegerPrimeApprove;
    }

    public boolean isPrime(String number) {
        var value = new BigInteger(number);
        if (value.compareTo(LONG_MAX) < 0)
            return longPrimeApprove.isPrime(value.longValue());
        return bigIntegerPrimeApprove.isPrime(value);
    }
}
