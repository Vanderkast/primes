package com.vanderkast.primes.domain;

import java.math.BigInteger;

public class BigIntegerPrimeApprove implements PrimeApprove<BigInteger> {
    @Override
    public boolean isPrime(BigInteger number) {
        var root = number.sqrt();
        var i = BigInteger.TWO;
        while (i.compareTo(root) <= 0) {
            if(number.mod(i).equals(BigInteger.ZERO))
                return false;
            i = i.add(BigInteger.ONE);
        }
        return true;
    }
}
