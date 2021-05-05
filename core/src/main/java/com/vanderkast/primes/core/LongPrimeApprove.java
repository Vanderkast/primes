package com.vanderkast.primes.core;

import static java.lang.StrictMath.sqrt;

public class LongPrimeApprove implements PrimeApprove<Long> {
    @Override
    public boolean isPrime(Long number) {
        var root = (long) sqrt(number);
        for (int i = 2; i < root; i++) {
            if(number % i == 0)
                return false;
        }
        return true;
    }
}
