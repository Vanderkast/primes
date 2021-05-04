package com.vanderkast.primes.domain;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class PrimeApproveFacadeTest {
    private final PrimeApprove<Long> longs = mock(PrimeApprove.class);
    private final PrimeApprove<BigInteger> bigInts = mock(PrimeApprove.class);

    private final PrimeApproveFacade facade = new PrimeApproveFacade(
            longs,
            bigInts);

    @Test
    void incorrectNumber() {
        assertThrows(NumberFormatException.class,
                () -> facade.isPrime("lol man it's illegal"));
    }

    @Test
    void bigInt() {
        // given
        var number = BigInteger.valueOf(Long.MAX_VALUE);

        // when
        facade.isPrime(number.toString());

        // then
        verify(bigInts).isPrime(number);
        verifyZeroInteractions(longs);
    }

    @Test
    void longs() {
        // given
        long number = Long.MAX_VALUE - 10;

        // when
        facade.isPrime(String.valueOf(number));

        // then
        verify(longs).isPrime(number);
        verifyZeroInteractions(bigInts);
    }
}
