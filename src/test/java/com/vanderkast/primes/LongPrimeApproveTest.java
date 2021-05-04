package com.vanderkast.primes;

import com.vanderkast.primes.domain.LongPrimeApprove;
import com.vanderkast.primes.domain.PrimeApprove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LongPrimeApproveTest {
    private static final Long PRIME_61 = 61L;
    private static final Long PRIME_215_737 = 215_737L;
    private final PrimeApprove<Long> approve = new LongPrimeApprove();

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
        var fool = PRIME_61 * PRIME_215_737;

        // when
        var guess = approve.isPrime(fool);

        // then
        assertFalse(guess);
    }
}
