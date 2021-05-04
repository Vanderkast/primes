package com.vanderkast.primes.domain;

public interface PrimeApprove<T extends Number> {
    boolean isPrime(T t);
}
