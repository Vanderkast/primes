package com.vanderkast.primes;

import com.vanderkast.primes.domain.BigIntegerPrimeApprove;
import com.vanderkast.primes.domain.PrimeApprove;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigIntegerPrimeApproveTest {
    private static final BigInteger PRIME_61 = BigInteger.valueOf(61);
    private static final BigInteger PRIME_215_737 = BigInteger.valueOf(215_737);
    private final PrimeApprove<BigInteger> approve = new BigIntegerPrimeApprove();

    @Test
    void _61() {
        // when
        var actual = approve.isPrime(PRIME_61);

        // then
        assertTrue(actual);
    }

    @Test
    void _215_737() {
        // when
        var actual = approve.isPrime(PRIME_215_737);

        // then
        assertTrue(actual);
    }

    @Test
    void multiplied() {
        // given
        var fool = PRIME_215_737.multiply(PRIME_61);

        // when
        var guess = approve.isPrime(fool);

        // then
        assertFalse(guess);
    }
}
